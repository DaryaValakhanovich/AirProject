package servlets;

import services.TicketService;

import javax.servlet.RequestDispatcher;
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
        request.setAttribute("ticketId", Integer.parseInt(request.getParameter("ticketId")));
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/views/confirmDeactivate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TicketService.getInstance().deactivate(Integer.parseInt(req.getParameter("ticketId")));
        resp.sendRedirect(req.getContextPath() + "/showMyTickets");
    }
}
