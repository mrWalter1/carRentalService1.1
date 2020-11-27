CREATE TABLE carRentalService
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    name    TEXT NOT NULL,
    address TEXT NOT NULL


);


CREATE TABLE cars
(
    id                INTEGER PRIMARY KEY AUTO_INCREMENT,
    firm_id           INTEGER NOT NULL REFERENCES carRentalService,
    pricePerDay       INTEGER NOT NULL CHECK (pricePerDay > 0),
    yearOfManufacture INTEGER NOT NULL CHECK (yearOfManufacture > 0),
    model             TEXT    NOT NULL,
    color             TEXT    NOT NULL


);