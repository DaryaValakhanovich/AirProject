package dao;

import entities.Plane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneDao extends BaseDao<Plane> {


    private PlaneDao() {
    }

    private static final class PlaneDaoHolder {
        private static final PlaneDao INSTANCE = new PlaneDao();
    }

    public static PlaneDao getInstance() {
        return PlaneDaoHolder.INSTANCE;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Plane plane) throws SQLException {
        statement.setInt(1, plane.getNumberOfSeats());
        statement.setDouble(2, plane.getWeight());
        statement.setDouble(3, plane.getCruisingSpeed());
        statement.setString(4, plane.getModel());
        statement.setString(5, plane.getCompany());
        statement.setDouble(6, plane.getMaxFlightAltitude());
        statement.setDouble(7, plane.getMaxRangeOfFlight());
    }

    @Override
    public Plane createFromResultSet(ResultSet resultSet) throws SQLException {
        Plane plane = new Plane();
        plane.setId(resultSet.getLong("id"));
        plane.setNumberOfSeats(resultSet.getInt( "numberOfSeats"));
        plane.setWeight(resultSet.getLong( "weight"));
        plane.setCruisingSpeed(resultSet.getLong("cruisingSpeed"));
        plane.setModel(resultSet.getString( "model"));
        plane.setCompany(resultSet.getString("company"));
        plane.setMaxFlightAltitude(resultSet.getLong( "maxFlightAltitude"));
        plane.setMaxRangeOfFlight(resultSet.getLong( "maxRangeOfFlight"));
        return plane;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM planes " +
                "WHERE id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM planes ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO planes (numberOfSeats, weight, cruisingSpeed, model, company, maxFlightAltitude, maxRangeOfFlight) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }
}
