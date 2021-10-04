package dao;

import utils.ConnectionManager;
import entities.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends BaseDao<Flight>{

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

    public List<Flight> findRightFlights(LocalDate departure, int numberOfSeats,
                                                String startAirport, String finalAirport)  {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String preparesStatement1 = "SELECT * FROM flights " +
                    "WHERE departure = ? AND numberOfFreeSeats >= ? AND startAirport = ? AND finalAirport = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(preparesStatement1)) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(departure.atStartOfDay()));
                preparedStatement.setInt(2, numberOfSeats);
                preparedStatement.setString(3, startAirport);
                preparedStatement.setString(4, finalAirport);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        flights.add(getInstance().createFromResultSet(resultSet));
                    }
                }
            }
            if(flights.isEmpty()){
                String preparesStatement2 = "SELECT * FROM flights WHERE (departure::date = ? OR departure::date = ?) AND numberOfFreeSeats >= ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(preparesStatement2)) {
                    preparedStatement.setDate(1, Date.valueOf(departure));
                    preparedStatement.setDate(2, Date.valueOf(departure.plusDays(1)));
                    preparedStatement.setInt(3, numberOfSeats);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            flights.add(getInstance().createFromResultSet(resultSet));
                        }
                        flights = findDifficultWay2(startAirport, finalAirport, flights, new ArrayList<>());

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }


    private List<Flight> findDifficultWay2(String startAirport, String finalAirport,
                                                 List<Flight> allFlights, List<Flight> previousFlights) {
        List<Flight> resultFlights = new ArrayList<>();
        List<Flight> probablyFlights = new ArrayList<>();
        for (Flight currentFlight : allFlights) {
            if (startAirport.equals(currentFlight.getStartAirport())){
                if (previousFlights.isEmpty() ||
                        previousFlights.get(previousFlights.size() - 1).getArrival()
                                .isBefore(currentFlight.getDeparture())) {
                    if (currentFlight.getFinalAirport().equals(finalAirport)) {
                        resultFlights.addAll(previousFlights);
                        resultFlights.add(currentFlight);

                    } else {
                        probablyFlights.add(currentFlight);
                    }
                }
            }
        }
        for (Flight probablyFlight : probablyFlights) {
            List<Flight> newPreviousFlights = new ArrayList<>(previousFlights);
            newPreviousFlights.add(probablyFlight);
            List<Flight> newFlights =
                    findDifficultWay2(probablyFlight.getFinalAirport(), finalAirport, allFlights, newPreviousFlights);
            if (!newFlights.isEmpty()) {
                resultFlights.addAll(newFlights);
                resultFlights.add(null);
            }
        }
        return resultFlights;
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
    }
}
