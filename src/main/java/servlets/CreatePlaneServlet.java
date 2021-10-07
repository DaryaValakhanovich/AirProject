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
        StringBuilder errorString = new StringBuilder();
        int numberOfSeats = 0;
        if(request.getParameter("numberOfSeats").isEmpty()  || Integer.parseInt(request.getParameter("numberOfSeats"))<=0){
            errorString.append("Enter correct number of seats. ");
        } else {
            numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        }
        double weight = 0;
        if(request.getParameter("weight").isEmpty()  || Double.parseDouble(request.getParameter("weight"))<=0){
            errorString.append("Enter correct weight. ");
        } else {
            weight = Double.parseDouble(request.getParameter("weight"));
        }
        double cruisingSpeed = 0;
        if(request.getParameter("cruisingSpeed").isEmpty()  || Double.parseDouble(request.getParameter("cruisingSpeed"))<=0){
            errorString.append("Enter correct cruising speed. ");
        } else {
            cruisingSpeed = Double.parseDouble(request.getParameter("cruisingSpeed"));
        }
        String model = null;
        if(request.getParameter("model").isEmpty()){
            errorString.append("Enter model. ");
        } else {
            model = request.getParameter("model");
        }
        String company = null;
        if(request.getParameter("company").isEmpty()){
            errorString.append("Enter company. ");
        } else {
            company = request.getParameter("company");
        }
        double maxFlightAltitude = 0;
        if(request.getParameter("maxFlightAltitude").isEmpty()  || Double.parseDouble(request.getParameter("maxFlightAltitude"))<=0){
            errorString.append("Enter correct max flight altitude. ");
        } else {
            maxFlightAltitude = Double.parseDouble(request.getParameter("maxFlightAltitude"));
        }
        double maxRangeOfFlight = 0;
        if(request.getParameter("maxRangeOfFlight").isEmpty()  || Double.parseDouble(request.getParameter("maxRangeOfFlight"))<=0){
            errorString.append("Enter correct max range of flight. ");
        } else {
            maxRangeOfFlight = Double.parseDouble(request.getParameter("maxRangeOfFlight"));
        }



        request.setAttribute("errorString", errorString);

        if (!errorString.isEmpty()) {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/views/createPlaneView.jsp");
            dispatcher.forward(request, response);
        } else {
            Plane plane = new Plane();
            plane.setNumberOfSeats(numberOfSeats);
            plane.setWeight(weight);
            plane.setCruisingSpeed(cruisingSpeed);
            plane.setModel(model);
            plane.setCompany(company);
            plane.setMaxFlightAltitude(maxFlightAltitude);
            plane.setMaxRangeOfFlight(maxRangeOfFlight);
            plane = PlaneService.getInstance().create(plane);
            if(plane.getId()==0L){
                errorString.append("Can't add plane. ");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/views/createPlaneView.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }
}
