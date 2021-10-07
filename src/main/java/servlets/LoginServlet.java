package servlets;

import entities.Account;
import services.AccountService;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/loginView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        Account user = null;
        boolean hasError = false;
        String errorString = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required email and password!";
        } else {
            user = AccountService.getInstance().findByEmail(email);
            if (user == null
                    || !(user.getPassword().equals(password))) {
                hasError = true;
                errorString = "Email or password invalid";
            }
        }

        if (hasError) {
            user = new Account();
            user.setEmail(email);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/views/loginView.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            AppUtils.storeLoginedUser(session, user);

            if (remember) {
                AppUtils.storeUserCookie(response, user);
            } else {
                AppUtils.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}
