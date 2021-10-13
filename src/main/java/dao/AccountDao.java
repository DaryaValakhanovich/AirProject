package dao;

import entities.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class AccountDao{

    public Account findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Account.class, id);
    }

    public Account findByEmail(String email) {
        List<Account> accounts =
                (List<Account>)  HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Account where email = :email")
                        .setParameter("email", email).list();
        return accounts.get(0);
    }

    public void save(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(account);
        tx1.commit();
        session.close();
    }

    public void update(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(account);
        tx1.commit();
        session.close();
    }

    public void delete(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(account);
        tx1.commit();
        session.close();
    }

    public List<Account> findAll() {
        List<Account> accounts =
                (List<Account>)  HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Account ").list();
        return accounts;
    }
    /*

    private AccountDao() {
    }

    private static final class AccountDaoHolder {
        private static final AccountDao INSTANCE = new AccountDao();
    }

    public static AccountDao getInstance() {
        return AccountDaoHolder.INSTANCE;
    }


    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Account account) throws SQLException {
        statement.setString(1, account.getPassword());
        statement.setString(2, Role.USER.toString());
        statement.setString(3, account.getEmail());
        statement.setString(4, account.getNumber());
    }

    @Override
    public Account createFromResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setRole(Role.valueOf(resultSet.getString("role")));
        account.setEmail(resultSet.getString("email"));
        account.setNumber(resultSet.getString("number"));
        account.setPassword(resultSet.getString("password"));
        return account;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM accounts " +
                "WHERE accounts.id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM accounts ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO accounts (password, role, email, number) " +
                "VALUES (?, ?, ?, ?)";
    }

    public void makeAdmin(long id){
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE accounts SET role = 'ADMIN' WHERE id = ?;")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findByEmail(String email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE email = ?")) {
                preparedStatement.setString(1, email);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return createFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
