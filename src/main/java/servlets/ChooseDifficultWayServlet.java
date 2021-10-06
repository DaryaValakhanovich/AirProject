package servlets;

import entities.Flight;
import entities.Ticket;
import services.AccountService;
import services.FlightService;
import services.TicketService;
import utils.AppUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/chooseDifficultWay")
public class ChooseDifficultWayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String[] listOfFlights = request.getParameterValues("listOfFlightsIds");
        Flight flight;
        Ticket ticket = new Ticket();
        ticket.setAccountId(AccountService.getInstance().findByEmail
                (AppUtils.getLoginedUser(request.getSession()).getEmail()).getId());
        ticket.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));

        for (String flightString:listOfFlights) {
            flight = FlightService.getInstance().findById(Integer.parseInt(flightString));
            ticket.setFlight(flight);
            TicketService.getInstance().create(ticket);
        }
        String errorString = null;
        request.setAttribute("errorString", errorString);
        if (errorString != null) {
            response.sendRedirect(request.getContextPath() + "/chooseDifficultWay");
        } else {
            response.sendRedirect(request.getContextPath() + "/showMyTickets");
        }
    }
}
