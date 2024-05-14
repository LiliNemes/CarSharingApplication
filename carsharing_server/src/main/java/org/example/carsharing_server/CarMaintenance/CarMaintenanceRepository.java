package org.example.carsharing_server.CarMaintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Integer>, JpaSpecificationExecutor<CarMaintenance> {

}
