package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Seat;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SeatDao {
    public Seat findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Seat.class, id);
    }

    public void save(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(seat);
        tx1.commit();
        session.close();
    }

    public void update(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(seat);
        tx1.commit();
        session.close();
    }

    public void delete(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(seat);
        tx1.commit();
        session.close();
    }

    public List<Seat> findAll() {
        List<Seat> seats =
                (List<Seat>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Seat ").list();
        return seats;
    }

    public List<Seat> findByTicketId(Integer ticketId) {
        List<Seat> seats =
                (List<Seat>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Seat where ticket = :ticket")
                        .setParameter("ticket", new TicketDao().findById(ticketId)).list();
        return seats;
    }
    /*

    private static final class SeatDaoHolder {
        private static final SeatDao INSTANCE = new SeatDao();
    }

    public static SeatDao getInstance() {
        return SeatDaoHolder.INSTANCE;
    }

    public void create(long ticketId, int numberOfFirstSeat, int amountOfSeats) {
        try (Connection connection = ConnectionManager.getConnection()) {
            for (int i = 0; i < amountOfSeats; i++) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO seats (ticketId, seat) VALUES (?, ?)")) {
                    preparedStatement.setLong(1, ticketId);
                    preparedStatement.setInt(2, numberOfFirstSeat--);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Seat> findByTicketId(long id){
        List<Seat> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM seats WHERE ticketId = ?")) {
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

    public Seat createFromResultSet(ResultSet resultSet) throws SQLException {
        Seat seat = new Seat();
        seat.setTicketId(resultSet.getLong("ticketId"));
        seat.setSeat(resultSet.getInt("seat"));
        return seat;
    }
    */

}
