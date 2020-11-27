package tech.itpark5.jdbc5.model;

import lombok.Value;

@Value
public class Car {
    long id;
    long firmId;
    int pricePerDay;
    int yearOfManufacture;
    String model;
    String color;
}
