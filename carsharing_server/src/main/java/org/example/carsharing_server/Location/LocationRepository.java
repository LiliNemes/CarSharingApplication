package org.example.carsharing_server.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {

}
