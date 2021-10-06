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
        LocalDateTime departure = LocalDateTime.parse(request.getParameter("departure"));
        LocalDateTime arrival = LocalDateTime.parse(request.getParameter("arrival"));
        String startAirport = request.getParameter("startAirport");
        String finalAirport = request.getParameter("finalAirport");
        Plane plane = PlaneService.getInstance().findById(Integer.parseInt(request.getParameter("planeId")));

        Flight flight = new Flight();
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setStartAirport(startAirport);
        flight.setFinalAirport(finalAirport);
        flight.setPlane(plane);
        FlightService.getInstance().create(flight);

        String errorString = null;
        request.setAttribute("errorString", errorString);

        if (errorString != null) {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createFlightView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
