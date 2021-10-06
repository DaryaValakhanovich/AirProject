package servlets;

import entities.Flight;
import entities.Ticket;
import services.AccountService;
import services.FlightService;
import services.TicketService;
import utils.AppUtils;
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
        StringBuilder errorString = new StringBuilder();
        String date = request.getParameter("departure");
        LocalDate departure = null;
        if(date.isEmpty()){
           errorString.append("Enter date. ");
        }else {
            departure = TimeUtils
                    .convertToLocalDateViaMilisecond(Date.valueOf(request.getParameter("departure")));
        }
        int numberOfSeats = 0;
        if(request.getParameter("numberOfSeats").isEmpty()  || Integer.parseInt(request.getParameter("numberOfSeats"))<=0){
            errorString.append("Enter correct number of seats. ");
        } else {
            numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        }

        String startAirport = request.getParameter("startAirport");
        String finalAirport = request.getParameter("finalAirport");

        if(startAirport.isEmpty() || finalAirport.isEmpty()){
            errorString.append("Enter airport. ");
        }

        if (errorString.isEmpty()) {
            List<Flight> flights = FlightService.getInstance()
                    .findRightFlights(departure, numberOfSeats, startAirport, finalAirport);

            if (flights.isEmpty()) {
                flights = FlightService.getInstance().findRightDifficultWay(departure, numberOfSeats, startAirport, finalAirport);
                List<List<Flight>> newFlights = new ArrayList<>();
                List<Flight> partOfNewFlights = new ArrayList<>();
                for (Flight flight : flights) {
                    if (flight == null) {
                        newFlights.add(partOfNewFlights);
                        partOfNewFlights = new ArrayList<>();
                    } else {
                        partOfNewFlights.add(flight);
                    }
                }
                if (newFlights.isEmpty()) {
                    request.setAttribute("errorString", "There are no flights for you.");
                    RequestDispatcher dispatcher = this.getServletContext()
                            .getRequestDispatcher("/views/findFlightView.jsp");
                    dispatcher.forward(request, response);
                }
                request.setAttribute("listsOfFlights", newFlights);
                for (List<Flight> listFlight : newFlights) {
                    for (Flight flight : listFlight) {
                        flight.setPrice(FlightService.getInstance().getPrice(flight, numberOfSeats));
                    }
                }
                request.setAttribute("numberOfSeats", numberOfSeats);
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/views/chooseDifficultWayView.jsp");
                dispatcher.forward(request, response);
            } else {
                for (Flight flight : flights) {
                    flight.setPrice(FlightService.getInstance().getPrice(flight, numberOfSeats));
                }
                request.setAttribute("flights", flights);
                request.setAttribute("numberOfSeats", numberOfSeats);

                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/views/chooseFlightsView.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/findFlightView.jsp");
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
                (AppUtils.getLoginedUser(request.getSession()).getEmail()).getId());
        ticket.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
        Ticket ticket1 = TicketService.getInstance().create(ticket);

        if(ticket1.getId()==0L){
            request.setAttribute("errorString", "Something go wrong");
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/chooseFlightsView.jsp");
            dispatcher.forward(request, response);
        }

        request.setAttribute("ticketId", ticket.getId());
        response.sendRedirect(request.getContextPath() + "/showMyTickets");
    }
}
