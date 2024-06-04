package org.example.carsharing_server.Car;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Location.Location;
import org.example.carsharing_server.User.User;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    static Specification<Car> ownedCars(int userID) {
        return (root, query, builder) -> {
            Join<User, Car> ownedCars = root.join("owner");
            return builder.equal(ownedCars.get("userID"), userID);
        };
    }
}
