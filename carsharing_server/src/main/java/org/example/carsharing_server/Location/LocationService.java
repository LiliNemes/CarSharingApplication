package org.example.carsharing_server.Location;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<Location> getLocations();

    void addNewLocation(Location location);

    @Transactional
    void updateLocation(Location location);

    void deleteLocation(int locationId);

    List<Location> getCarsLocation(String licensPlate);

    List<Location> getBookingStartingLocation(int bookingId);

    List<Location> getBookingEndingLocation(int bookingId);

    Location getLocation(int locationID);
}
