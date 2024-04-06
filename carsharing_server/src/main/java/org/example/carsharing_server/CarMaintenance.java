import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

class CarMaintenanceDAO {
    private Connection connection;

    public CarMaintenanceDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
public class CarMaintenance {
    int maintenanceID;
    Car carID;
    Date maintenanceDate;
    String typeOfMaintenance;
    String details;
    int cost;

    public CarMaintenance(int maintenanceID, Date maintenanceDate, String typeOfMaintenance, String details, int cost) {
        this.maintenanceID = maintenanceID;
        this.maintenanceDate = maintenanceDate;
        this.typeOfMaintenance = typeOfMaintenance;
        this.details = details;
        this.cost = cost;
    }

    public int getMaintenanceID() {
        return maintenanceID;
    }

    public Car getCarID() {
        return carID;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public String getTypeOfMaintenance() {
        return  typeOfMaintenance;
    }

    public String getDetails(){
        return details;
    }

    public int getCost(){
        return cost;
    }

}

