package org.example.carsharing_server.Booking;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Car.Car;
import org.example.carsharing_server.User.User;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecification {
    static Specification<Booking> pastBookings() {
        return (root, query, builder) ->
                builder.isNotNull(root.get("endTime"));
    }

    static Specification<Booking> presentBookings() {
        return (root, query, builder) ->
                builder.isNull(root.get("endTime"));
    }

    static Specification<Booking> usersBookings(int userID) {
        return (root, query, builder) -> {
            Join<User, Booking> usersBooking =root.join("user");
            return builder.equal(usersBooking.get("userID"), userID);
        };
    }

    static Specification<Booking> carsBookings(String licensePlate) {
        return (root, query, builder) -> {
            Join<Car, Booking> carsBooking =root.join("car");
            return builder.equal(carsBooking.get("licensePlate"), licensePlate);
        };
    }
}
