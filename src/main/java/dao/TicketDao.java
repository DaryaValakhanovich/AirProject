package dao;

import entities.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TicketDao {

    public Ticket findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ticket.class, id);
    }

    public void save(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(ticket);
        tx1.commit();
        session.close();
    }

    public void update(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    public void delete(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ticket);
        tx1.commit();
        session.close();
    }

    public List<Ticket> findAll() {
        List<Ticket> tickets =
                (List<Ticket>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Ticket ").list();
        return tickets;
    }

    public void deactivate(Ticket ticket) {
        ticket.setActive(false);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    public List<Ticket> findByAccountId(Integer id) {
        AccountDao accountDao = new AccountDao();
        List<Ticket> list =
                (List<Ticket>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Ticket where account = :account")
                        .setParameter("account", accountDao.findById(id)).list();
        return list;
    }
    /*
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

    public List<Ticket> findByAccountId(long id){
        List<Ticket> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tickets WHERE accountId = ?")) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(createFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    */

}
