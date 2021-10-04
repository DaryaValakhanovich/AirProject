package dao;

import entities.Ticket;
import utils.ConnectionManager;

import java.sql.*;

public class TicketDao extends BaseDao<Ticket> {

    private TicketDao() {
    }

    private static final class TicketDaoHolder {
        private static final TicketDao INSTANCE = new TicketDao();
    }

    public static TicketDao getInstance() {
        return TicketDaoHolder.INSTANCE;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Ticket ticket) throws SQLException {
        statement.setLong(1, ticket.getAccountId());
        statement.setLong(2, ticket.getFlight().getId());
        statement.setInt(3, ticket.getNumberOfSeats());
        statement.setBoolean(4, ticket.isActive());
    }

    @Override
    public Ticket createFromResultSet(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        FlightDao.getInstance().findById(resultSet.getLong( "flightId")).ifPresent(ticket::setFlight);
        ticket.setNumberOfSeats(resultSet.getInt( "numberOfSeats"));
        ticket.setActive(resultSet.getBoolean( "active"));
        return ticket;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM tickets " +
                "WHERE tickets.id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM tickets ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO tickets (accountId, flightId, numberOfSeats, active) " +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public Ticket create(Ticket object) {
        Ticket ticket = super.create(object);
        SeatDao.getInstance().create(ticket.getId(), ticket.getFlight().getNumberOfFreeSeats() ,ticket.getNumberOfSeats());
        FlightDao.getInstance().buyTicket(ticket.getId(), ticket.getNumberOfSeats());
        return super.create(object);
    }

    public void deactivate(Ticket ticket){
        try (Connection connection = ConnectionManager.getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE tickets SET active = false WHERE id = ?;")) {
                    preparedStatement.setLong(1, ticket.getId());
                    preparedStatement.executeUpdate();
                }
            System.out.println();
                FlightDao.getInstance().returnTicket(ticket.getId(), ticket.getNumberOfSeats());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
