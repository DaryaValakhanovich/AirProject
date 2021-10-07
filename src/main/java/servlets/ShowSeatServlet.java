package servlets;

import entities.Seat;
import services.SeatService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(urlPatterns = { "/showSeats" })
public class ShowSeatServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ShowSeatServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Seat> seats = SeatService.getInstance().findByTicketId(Integer.parseInt(request.getParameter("ticketId")));
        request.setAttribute("seats", seats);
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/showSeatsView.jsp");
        dispatcher.forward(request, response);
    }
}