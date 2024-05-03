package org.example.carsharing_server.CarMaintenance;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Car.Car;
import org.springframework.data.jpa.domain.Specification;

public class CarMaintenanceSpecification {

    static Specification<CarMaintenance> carsCarMaintenance(String licensePlate) {
        return (root, query, builder) -> {
            Join<Car, CarMaintenance> carsCarMaintenance = root.join("car");
            return builder.equal(carsCarMaintenance.get("licensePlate"), licensePlate);
        };
    }
}
