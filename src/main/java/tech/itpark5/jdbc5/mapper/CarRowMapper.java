package tech.itpark5.jdbc5.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark5.jdbc5.model.Car;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet rs, int rowNub) throws SQLException {
        return new Car(
                rs.getLong("id"),
                rs.getLong("firm_id"),
                rs.getInt("pricePerDay"),
                rs.getInt("yearOfManufacture"),
                rs.getString("model"),
                rs.getString("color")


        );
    }
}
