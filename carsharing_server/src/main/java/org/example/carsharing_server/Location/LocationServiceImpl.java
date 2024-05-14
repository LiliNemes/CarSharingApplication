package org.example.carsharing_server.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Location.LocationSpecification.*;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {this.locationRepository = locationRepository;}

    @Override
    public List<Location> getLocations() {return locationRepository.findAll();}

    @Override
    public void addNewLocation(Location location) {locationRepository.save(location);}

    @Override
    public void updateLocation(Location location) {
        Location currentLocation = locationRepository.findById(location.getLocationID()).orElseThrow(() ->
                new IllegalArgumentException("Location Id " + location.getLocationID() + "does not exist"));
        currentLocation.setLocationID(location.getLocationID());
        currentLocation.setAddress(location.getAddress());
        currentLocation.setGPS_height(location.getGPS_height());
        currentLocation.setGPS_width(location.getGPS_width());
        currentLocation.setCars_here(location.getCars_here());
        currentLocation.setEnded_here(location.getEnded_here());
        currentLocation.setStarted_here(location.getStarted_here());
        locationRepository.save(currentLocation);
    }

    @Override
    public void deleteLocation(int locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() ->
                new IllegalArgumentException("Location Id " + locationId + "does not exist"));
        locationRepository.delete(location);
    }

    @Override
    public List<Location> getCarsLocation(String licensePlate) {
        return locationRepository.findAll(carsLocation(licensePlate));
    }

    @Override
    public List<Location> getBookingStartingLocation(String bookingId) {
        int bookingIdNum = Integer.parseInt(bookingId);
        return locationRepository.findAll(bookingStartingLocation(bookingIdNum));
    }

    public List<Location> getBookingEndingLocation(String bookingId) {
        int bookingIdNum = Integer.parseInt(bookingId);
        return locationRepository.findAll(bookingEndingLocation(bookingIdNum));
    }
}
