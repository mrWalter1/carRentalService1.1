package tech.itpark5.jdbc5.manager;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark5.jdbc5.mapper.CarRowMapper;
import tech.itpark5.jdbc5.model.Car;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CarManager {
    private final NamedParameterJdbcTemplate template;
    private final CarRowMapper rowMapper = new CarRowMapper();


    public List<Car> getAll() {
        return template.query(
                "SELECT id,firm_id,pricePerDay,yearOfManufacture,model,color FROM cars ORDER BY id LIMIT 50",
                rowMapper
        );

    }


    public Car getById(long id) {
        return template.queryForObject(
                "SELECT id,firm_id,pricePerDay,yearOfManufacture,model,color FROM cars WHERE id= :id",
                Map.of("id", id),
                rowMapper
        );
    }


    public List<Car> getByFirmId(long firmId) {
        return template.query(
                "SELECT id,firm_id,pricePerDay,yearOfManufacture,model,color FROM cars WHERE firm_id= :firm_id",
                Map.of("firm_id", firmId),
                rowMapper
        );

    }


    public Car save(Car item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(
                    "INSERT INTO cars(firm_id,pricePerDay, yearOfManufacture, model, color) VALUES (:firm_id,:pricePerDay,:yearOfManufacture,:model,:color)",
                    new MapSqlParameterSource(Map.of(
                            "firm_id", item.getFirmId(),
                            "pricePerDay", item.getPricePerDay(),
                            "yearOfManufacture", item.getYearOfManufacture(),
                            "model", item.getModel(),
                            "color", item.getColor()

                    )),
                    keyHolder


            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }

        template.update(
                "UPDATE cars SET firm_id=:firm_id, pricePerDay=:pricePerDay, yearOfManufacture=:yearOfManufacture, model=:model, color=:color WHERE id=:id",
                Map.of(
                        "id", item.getId(),
                        "firm_id", item.getFirmId(),
                        "pricePerDay", item.getPricePerDay(),
                        "yearOfManufacture", item.getYearOfManufacture(),
                        "model", item.getModel(),
                        "color", item.getColor()
                )
        );
        return getById(item.getId());

    }

    public Car removeById(long id) {
        Car item = getById(id);
        template.update(
                "DELETE FROM cars WHERE id=:id",
                Map.of(
                        "id", item.getId())

        );

        return item;
    }

}

