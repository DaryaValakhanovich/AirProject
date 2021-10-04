package servlets;

import dao.AccountDao;
import entities.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;


@WebServlet(urlPatterns = { "/makeAdmin" })
public class MakeAdminServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public MakeAdminServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/makeAdmin.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean hasError = false;
        String errorString = null;

        if (email == null) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Account account = AccountDao.getInstance().findByEmail(email);
            if (account == null) {
                hasError = true;
                errorString = "User Name or password invalid";
            } else {
                AccountDao.getInstance().makeAdmin(account.getId());
            }
        }
        if (hasError) {

            //  user = new UserAccount();
            //   user.setUserName(userName);
            //    user.setPassword(password);

            // Сохранить информацию в request attribute перед forward.
            //    request.setAttribute("errorString", errorString);
            //    request.setAttribute("user", user);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/makeAdmin.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}
