package servlets;

import entities.Account;
import services.AccountService;

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
        RequestDispatcher dispatcher
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
            errorString = "Required email!";
        } else {
            Account account = AccountService.getInstance().findByEmail(email);
            if (account.getId() == 0L) {
                hasError = true;
                errorString = "There is no such user!";
            } else {
                AccountService.getInstance().makeAdmin(account.getId());
            }
        }
        if (hasError) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/makeAdmin.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}
