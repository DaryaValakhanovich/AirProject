package servlets;

import entities.Plane;
import services.PlaneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = { "/createPlane" })
public class CreatePlaneServlet extends HttpServlet{
    @Serial
    private static final long serialVersionUID = 1L;

    public CreatePlaneServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/createPlaneView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        double cruisingSpeed = Double.parseDouble(request.getParameter("cruisingSpeed"));
        String model = request.getParameter("model");
        String company = request.getParameter("company");
        double maxFlightAltitude = Double.parseDouble(request.getParameter("maxFlightAltitude"));
        double maxRangeOfFlight = Double.parseDouble(request.getParameter("maxRangeOfFlight"));

        Plane plane = new Plane();
        plane.setNumberOfSeats(numberOfSeats);
        plane.setWeight(weight);
        plane.setCruisingSpeed(cruisingSpeed);
        plane.setModel(model);
        plane.setCompany(company);
        plane.setMaxFlightAltitude(maxFlightAltitude);
        plane.setMaxRangeOfFlight(maxRangeOfFlight);
        PlaneService.getInstance().create(plane);

        String errorString = null;
        request.setAttribute("errorString", errorString);

        if (errorString != null) {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createPlaneView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
