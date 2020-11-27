package tech.itpark5.jdbc5.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark5.jdbc5.manager.CarManager;
import tech.itpark5.jdbc5.model.Car;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarManager manager;


    @GetMapping
    public List<Car> getAll() {
        return manager.getAll();
    }


    @GetMapping("/{id}")
    public Car getById(@PathVariable long id) {
        return manager.getById(id);

    }
    @GetMapping("/carRentalService/{firmId}")
    public List<Car> getByFirmId(@PathVariable long firmId) {
        return manager.getByFirmId(firmId);
    }


    @PostMapping
    public Car save(@RequestBody Car item) {
        return manager.save(item);
    }

    @DeleteMapping("/{id}/")
    public Car removeById(@PathVariable long id) {
        return manager.removeById(id);


    }
}

