package org.example.carsharing_server.Location;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Car.Car;
import org.example.carsharing_server.Booking.Booking;
import org.springframework.data.jpa.domain.Specification;

public class LocationSpecification {

    static Specification<Location> carsLocation(String licensePlate) {
        return (root, query, builder) -> {
            Join<Car, Location> carsLocation = root.join("car");
            return builder.equal(carsLocation.get("licensePlate"), licensePlate);
        };
    }

    static Specification<Location> bookingStartingLocation(int bookingId) {
        return (root, query, builder) -> {
            Join<Booking, Location> bookingStartingLocation = root.join("booking");
            return builder.equal(bookingStartingLocation.get("bookingId"), bookingId);
        };
    }

    static Specification<Location> bookingEndingLocation(int bookingId) {
        return (root, query, builder) -> {
            Join<Booking, Location> bookingEndingLocation = root.join("booking");
            return builder.equal(bookingEndingLocation.get("bookingId"), bookingId);
        };
    }
}
