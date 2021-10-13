package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Flight;
import utils.HibernateSessionFactoryUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class FlightDao {
    public Flight findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Flight.class, id);
    }

    public void save(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(flight);
        tx1.commit();
        session.close();
    }

    public void update(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

    public void delete(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(flight);
        tx1.commit();
        session.close();
    }

    public List<Flight> findAll() {
        List<Flight> flights =
                (List<Flight>)  HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Flight ").list();
        return flights;
    }


    public List<Flight> findFlightsForDifficultWay(LocalDate departure, int numberOfSeats)  {
        List<Flight> flights =
                (List<Flight>)  HibernateSessionFactoryUtil
                        .getSessionFactory().openSession()
                        .createQuery("FROM Flight WHERE numberOfFreeSeats >= ?1 and (date(departure) = ?2 OR date(departure) = ?3)")
                        .setParameter(1, numberOfSeats)
                        .setParameter(2,  Date.valueOf(departure))
                        .setParameter(3, Date.valueOf(departure.plusDays(1)))
                                .list();
        return flights;
    }

    public List<Flight> findRightFlights(LocalDate departure, int numberOfSeats, String startAirport, String finalAirport)  {
        List<Flight> flights =
                (List<Flight>)  HibernateSessionFactoryUtil
                        .getSessionFactory().openSession()
                        .createQuery("FROM Flight WHERE date(departure) = ?1 AND numberOfFreeSeats >= ?2 AND startAirport = ?3 AND finalAirport = ?4")
                        .setParameter(1,  Date.valueOf(departure))
                        .setParameter(2, numberOfSeats)
                        .setParameter(3, startAirport)
                        .setParameter(4, finalAirport)
                        .list();
        return flights;
    }

    public void buyTicket(Integer id, int numberOfSeats) {
        Flight flight = findById(id);
        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats()-numberOfSeats);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

    public void returnTicket(Integer id, int numberOfSeats) {
        Flight flight = findById(id);
        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats()+numberOfSeats);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

/*
    private FlightDao() {
    }

    private static final class FlightDaoHolder {
        private static final FlightDao INSTANCE = new FlightDao();
    }

    public static FlightDao getInstance() {
        return FlightDaoHolder.INSTANCE;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Flight flight) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(flight.getDeparture()));
        statement.setTimestamp(2, Timestamp.valueOf(flight.getArrival()));
        statement.setInt(3, flight.getPlane().getNumberOfSeats());
        statement.setString(4, flight.getStartAirport());
        statement.setString(5, flight.getFinalAirport());
        statement.setLong(6, flight.getPlane().getId());
    }

    @Override
    public Flight createFromResultSet(ResultSet resultSet) throws SQLException {
        Flight flight =  new Flight();
        flight.setId(resultSet.getLong("id"));
        flight.setDeparture((resultSet.getTimestamp( "departure")).toLocalDateTime());
        flight.setArrival((resultSet.getTimestamp( "arrival").toLocalDateTime()));
        flight.setNumberOfFreeSeats(resultSet.getInt( "numberOfFreeSeats"));
        flight.setStartAirport(resultSet.getString("startAirport"));
        flight.setFinalAirport(resultSet.getString("finalAirport"));
        PlaneDao.getInstance().findById(resultSet.getLong("planeId")).ifPresent(flight::setPlane);
        return flight;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM flights " +
                "WHERE id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM flights ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO flights (departure, arrival, numberOfFreeSeats, startAirport, finalAirport, planeId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    public List<Flight> findFlightsForDifficultWay(LocalDate departure, int numberOfSeats)  {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String preparesStatement2 = "SELECT * FROM flights WHERE (departure::date = ? OR departure::date = ?) AND numberOfFreeSeats >= ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(preparesStatement2)) {
                preparedStatement.setDate(1, Date.valueOf(departure));
                preparedStatement.setDate(2, Date.valueOf(departure.plusDays(1)));
                preparedStatement.setInt(3, numberOfSeats);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        flights.add(getInstance().createFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flight> findRightFlights(LocalDate departure, int numberOfSeats, String startAirport, String finalAirport)  {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String preparesStatement1 = "SELECT * FROM flights " +
                    "WHERE (departure::date = ?) AND numberOfFreeSeats >= ? AND startAirport = ? AND finalAirport = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(preparesStatement1)) {
                preparedStatement.setDate(1, Date.valueOf(departure));
                preparedStatement.setInt(2, numberOfSeats);
                preparedStatement.setString(3, startAirport);
                preparedStatement.setString(4, finalAirport);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        flights.add(getInstance().createFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void buyTicket(long id, int numberOfSeats) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String preparesStatement3 = "UPDATE flights SET numberOfFreeSeats = (numberOfFreeSeats - ?) WHERE id = ?;";
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(preparesStatement3)) {
                preparedStatement.setInt(1, numberOfSeats);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnTicket(long id, int numberOfSeats) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String preparesStatement3 = "UPDATE flights SET numberOfFreeSeats = (numberOfFreeSeats + ?) WHERE id = ?;";
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(preparesStatement3)) {
                preparedStatement.setInt(1, numberOfSeats);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
