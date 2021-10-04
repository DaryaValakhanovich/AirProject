package dao;

import entities.Account;
import entities.Role;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends BaseDao<Account> {

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

}
