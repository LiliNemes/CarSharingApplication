package org.example.carsharing_server;
import java.sql.*;;
class UserDAO {
    private Connection connection;
    public UserDAO() {
        try {
            connection = DriverManager.getConnection("","username","password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



public class User {
    private int userId;
    private String name;
    private String email;
    private int phone_number;
    private String password;
    private Payment payment;

    public User(String name, String email, int phone_number, String password) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
