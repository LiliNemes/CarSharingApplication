package org.example.carsharing_server.User;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Car.Car;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    static Specification<User> carsOwner(String licensePlate) {
        return (root, query, builder) -> {
          Join<Car, User> carsOwner = root.join("cars");
          return builder.equal(carsOwner.get("licensePlate"), licensePlate);
        };
    }
}
