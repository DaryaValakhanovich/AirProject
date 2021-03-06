package servlets;

import entities.Account;
import services.AccountService;
import utils.StringUtils;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreateUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/createUserView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String number = request.getParameter("number");

        StringBuilder errorString = new StringBuilder();

        if(!StringUtils.checkPassword(password)) errorString.append("Wrong input of password. ");
        if(!StringUtils.checkEmail(email)) errorString.append("Wrong input of email. ");
        if(!StringUtils.checkNumber(number)) errorString.append("Wrong input of number. ");

        if (errorString.isEmpty()){
            Account account = AccountService.getInstance().create(new Account(email, password, number));
            if (account.getId() == 0L){
                errorString.append("Can't add user. ");
            }
        }

        request.setAttribute("errorString", errorString);
        if (errorString.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/createUserView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
