import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

class LocationDAO {
    private Connection connection;

    public LocationDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

public class Location {
    int locationID;
    String address;
    ArrayList<Car> currentCarsAvailable;

    public Location(String address)
    {
        this.address = address;
    }

    public int getLocationID() {
        return locationID;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Car> getCurrentCarsAvailable() {
        return currentCarsAvailable;
    }
}
