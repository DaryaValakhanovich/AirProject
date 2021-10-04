package servlets;

import dao.AccountDao;
import dao.FlightDao;
import dao.SeatDao;
import dao.TicketDao;
import entities.Account;
import entities.Flight;
import entities.Seat;
import entities.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/chooseFlight")
public class ChooseFlight extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ChooseFlight() {
        super();
    }

    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LocalDate departure = convertToLocalDateViaMilisecond(Date.valueOf(request.getParameter("departure")));
        int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        String startAirport = request.getParameter("startAirport");
        String finalAirport = request.getParameter("finalAirport");

        List<Flight> flights = FlightDao.getInstance().findRightFlights(departure, numberOfSeats, startAirport, finalAirport);

        flights.forEach(System.out::println);

        request.setAttribute("flights", flights);
        /*


        Product product = null;

        String errorString = null;

        try {
            product = DBUtils.findProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).
        // Redirect sang trang danh sách sản phẩm.
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
*/
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/views/chooseFlightsView.jsp");
        dispatcher.forward(request, response);
    }

    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Connection conn = MyUtils.getStoredConnection(request);


        Ticket ticket = new Ticket();

        int flightId = Integer.parseInt(request.getParameter("flightId"));

        FlightDao.getInstance().findById(flightId).ifPresent(ticket :: setFlight);
        ticket.setAccountId(AccountDao.getInstance().findByEmail(request.getParameter("loginUser.email")).getId());
        ticket.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
        ticket = TicketDao.getInstance().create(ticket);
        List<Seat> seats = SeatDao.getInstance().findByTicketId(ticket.getId());
        request.setAttribute("seats", seats);


        String errorString = null;

        // Кодом продукта является строка [a-zA-Z_0-9]
        // Имеет минимум 1 символ.

        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        // request.setAttribute("product", product);

        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/showSeatsView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
