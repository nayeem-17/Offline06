package server.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.model.Car;
import server.model.User;

public class DBController {
    Connection connection = null;

    public DBController(Connection connection) {
        this.connection = connection;
    }

    public boolean updateCar(Car car) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Cars " + "SET " + " yearMade= " + car.getYearMade() + ", carMake= '"
                    + car.getCarMake() + "', carModel= '" + car.getCarModel() + "', priceOfCar='" + car.getPriceOfCar()
                    + "', colorOfCar='" + car.getColorOfCar() + "' WHERE " + "registrationNumber= '"
                    + car.getRegistrationNumber() + "';";

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Updated successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCar(String registrationNumber) {
        String query = "DELETE FROM Cars WHERE " + "registrationNumber = " + "'" + registrationNumber + "'";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Deleted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
            return false;
        }

    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Car> getAlllCar() {
        List<Car> cars = new ArrayList<Car>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Cars";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Car car = new Car();
                car.setCarMake(rs.getString("carmake"));
                car.setRegistrationNumber(rs.getString("registrationNumber"));
                car.setCarModel(rs.getString("carModel"));
                car.setColorOfCar(rs.getString("colorOfCar"));
                car.setPriceOfCar(rs.getInt("priceOfCar"));
                car.setYearMade(rs.getInt("yearMade"));
                System.out.println(car);
                cars.add(car);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
        }
        return cars;
    }

    public boolean addCar(Car car) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO cars " + "VALUES " + "( '" + car.getRegistrationNumber() + "' , "
                    + car.getYearMade() + ",'" + car.getCarMake() + "','" + car.getCarModel() + "','"
                    + car.getPriceOfCar() + "','" + car.getColorOfCar() + "');";
            statement.executeUpdate(query);
            System.out.println("Inserted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
            return false;
        }
    }
}
