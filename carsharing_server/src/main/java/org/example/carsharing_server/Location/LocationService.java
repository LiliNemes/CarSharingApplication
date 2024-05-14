package org.example.carsharing_server.Location;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface LocationService {

    List<Location> getLocations();

    void addNewLocation(Location location);

    void updateLocation(Location location);

    void deleteLocation(int locationId);

    List<Location> getCarsLocation(String licensPlate);
    List<Location> getBookingStartingLocation(String bookingId);
    List<Location> getBookingEndingLocation(String bookingId);
}
