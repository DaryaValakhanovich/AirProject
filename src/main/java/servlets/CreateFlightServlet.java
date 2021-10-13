package servlets;

import entities.Flight;
import entities.Plane;
import services.FlightService;
import services.PlaneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = { "/createFlight" })
public class CreateFlightServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreateFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("planes", PlaneService.getInstance().findAll());
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/createFlightView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder errorString = new StringBuilder();

        String date = request.getParameter("departure");
        LocalDateTime departure = null;
        if(date.isEmpty()){
            errorString.append("Enter departure date. ");
        }else {
            departure = LocalDateTime.parse(request.getParameter("departure"));
        }

        date = request.getParameter("arrival");
        LocalDateTime arrival = null;
        if(date.isEmpty()){
            errorString.append("Enter arrival date. ");
        }else {
            arrival = LocalDateTime.parse(request.getParameter("arrival"));
        }

        if (arrival!=null && departure != null && (arrival.isBefore(departure) || arrival.isEqual(departure))){
            errorString.append("Enter correct dates. ");
        }

        String startAirport = request.getParameter("startAirport");
        String finalAirport = request.getParameter("finalAirport");
        if(startAirport.isEmpty() || finalAirport.isEmpty()){
            errorString.append("Enter airports. ");
        }

        Plane plane = null;
        if(request.getParameter("planeId")==null){
            errorString.append("Choose plane. ");
        } else {
            plane = PlaneService.getInstance().findById(Integer.parseInt(request.getParameter("planeId")));
            if(plane.getId()==0L){
                errorString.append("Wrong plane. ");
            }
        }

        if (errorString.isEmpty()) {
            Flight flight = new Flight();
            flight.setDeparture(departure);
            flight.setArrival(arrival);
            flight.setStartAirport(startAirport);
            flight.setFinalAirport(finalAirport);
            flight.setPlane(plane);
            FlightService.getInstance().create(flight);

                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/views/homeView.jsp");
                dispatcher.forward(request, response);

        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("planes", PlaneService.getInstance().findAll());
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/createFlightView.jsp");
        dispatcher.forward(request, response);

    }
}
