package servlets;

import entities.Flight;
import entities.Ticket;
import services.AccountService;
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

@WebServlet(urlPatterns = "/chooseDifficultWay")
public class ChooseDifficultWayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String[] listOfFlights = request.getParameterValues("listOfFlightsIds");
        Flight flight;
        Ticket ticket = new Ticket();
        ticket.setAccountId(AccountService.getInstance().findByEmail
                (AppUtils.getLoginedUser(request.getSession()).getEmail()).getId());
        ticket.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));

        for (String flightString : listOfFlights) {
            flight = FlightService.getInstance().findById(Integer.parseInt(flightString));
            ticket.setFlight(flight);
            TicketService.getInstance().create(ticket);
        }
        if (ticket.getId() != 0L) {
            response.sendRedirect(request.getContextPath() + "/showMyTickets");
        } else {
            request.setAttribute("errorString", "Can't add ticket. ");
            request.setAttribute("listsOfFlights", request.getParameter("listsOfFlights"));
            request.setAttribute("numberOfSeats", request.getParameter("numberOfSeats"));

            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/chooseDifficultWayView.jsp");
            dispatcher.forward(request, response);
        }

    }
}
