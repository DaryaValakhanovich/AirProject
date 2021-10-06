package servlets;

import entities.Flight;
import entities.Ticket;
import services.AccountService;
import services.FlightService;
import services.TicketService;
import utils.MyUtils;
import utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/chooseFlight")
public class ChooseFlightServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ChooseFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate departure = TimeUtils
                .convertToLocalDateViaMilisecond(Date.valueOf(request.getParameter("departure")));
        int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        String startAirport = request.getParameter("startAirport");
        String finalAirport = request.getParameter("finalAirport");

        List<Flight> flights = FlightService.getInstance()
                .findRightFlights(departure, numberOfSeats, startAirport, finalAirport);

        if(flights.isEmpty()) {
            flights = FlightService.getInstance().findRightDifficultWay(departure, numberOfSeats,startAirport,finalAirport);
            List<List<Flight>> newFlights = new ArrayList<>();
            List<Flight> partOfNewFlights = new ArrayList<>();
            for (Flight flight:flights) {
                if (flight == null) {
                    newFlights.add(partOfNewFlights);
                    partOfNewFlights= new ArrayList<>();
                } else {
                    partOfNewFlights.add(flight);
                }
            }
            request.setAttribute("listsOfFlights", newFlights);
            for (List<Flight> listFlight:newFlights) {
                for (Flight flight : listFlight) {
                    flight.setPrice(FlightService.getInstance().getPrice(flight, numberOfSeats));
                }
            }
            request.setAttribute("numberOfSeats", numberOfSeats);
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/chooseDifficultWayView.jsp");
            dispatcher.forward(request, response);
        } else {
            for (Flight flight:flights) {
                flight.setPrice(FlightService.getInstance().getPrice(flight,numberOfSeats));
            }
            request.setAttribute("flights", flights);
            request.setAttribute("numberOfSeats", numberOfSeats);

            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/chooseFlightsView.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Ticket ticket = new Ticket();
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        ticket.setFlight(FlightService.getInstance().findById(flightId));
        ticket.setAccountId(AccountService.getInstance().findByEmail
                (MyUtils.getLoginedUser(request.getSession()).getEmail()).getId());
        ticket.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
        TicketService.getInstance().create(ticket);

        request.setAttribute("ticketId", ticket.getId());
        String errorString = null;

        request.setAttribute("errorString", errorString);
        if (errorString != null) {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/chooseFlightsView.jsp.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/showMyTickets");
        }
    }
}
