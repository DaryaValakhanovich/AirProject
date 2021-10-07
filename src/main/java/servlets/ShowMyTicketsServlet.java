package servlets;

import entities.Ticket;
import services.FlightService;
import services.TicketService;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;


@WebServlet(urlPatterns = { "/showMyTickets" })
public class ShowMyTicketsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ShowMyTicketsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ticket> tickets = TicketService.getInstance().findByAccountEmail(AppUtils.getLoginedUser(request.getSession()).getEmail());
        request.setAttribute("tickets", tickets);

        for (Ticket ticket: tickets) {
            ticket.getFlight().setPrice(FlightService.getInstance().getPrice(ticket.getFlight(),ticket.getNumberOfSeats()));
        }

        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/showMyTickets.jsp");
        dispatcher.forward(request, response);
    }
}