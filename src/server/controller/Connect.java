package server.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;

public class Connect {
    private static String dbmainUrl = "jdbc:sqlite:./src/server/main.db";
    private static String dbtestUrl = "jdbc:sqlite:./src/server/test.db";
    private static Connection connection;

    public static Connection connect() {
        try {
            Driver driver = new JDBC();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            System.out.println("Unable to adding driver! " + e.getMessage());

        }
        try {
            connection = DriverManager.getConnection(dbmainUrl);
            System.out.println("Connection Successful!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void create() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String usertableQuery = "CREATE TABLE  users (" + "username	TEXT," + "password	TEXT NOT NULL,"
                    + "PRIMARY KEY(username));";
            statement.executeUpdate(usertableQuery);
            String carTableQuery = "CREATE TABLE Cars (" + "registrationNumber	TEXT NOT NULL UNIQUE,"
                    + "  yearMade	INTEGER," + "  carMake	TEXT," + "  carModel	TEXT," + "  priceOfCar	INTEGER,"
                    + "  colorOfCar	TEXT," + "  PRIMARY KEY(registrationNumber)" + ")";
            statement.executeUpdate(carTableQuery);

            System.out.println("DOne Bruh!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error occured " + e.getMessage());
            }
        }
    }
}
