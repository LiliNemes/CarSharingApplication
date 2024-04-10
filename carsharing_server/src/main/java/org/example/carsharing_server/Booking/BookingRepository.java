package org.example.carsharing_server.Booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {


}
