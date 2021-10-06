package servlets;

import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = { "/deactivate" })
public class DeactivateTicketServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public DeactivateTicketServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TicketService.getInstance().deactivate(Integer.parseInt(request.getParameter("ticketId")));
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
