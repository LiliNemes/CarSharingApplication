package org.example.carsharing_server.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface CarRepository extends JpaRepository<Car, String>, JpaSpecificationExecutor<Car>{

}
