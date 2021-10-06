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

@WebServlet(urlPatterns = { "/showPlane" })
public class ShowPlaneServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ShowPlaneServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Plane plane = PlaneService.getInstance().findById(Integer.parseInt(request.getParameter("planeId")));
        request.setAttribute("plane", plane);

        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/showPlaneView.jsp");
        dispatcher.forward(request, response);
    }
}
