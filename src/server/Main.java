package server;

import java.sql.Connection;
import server.controller.Connect;
import server.controller.DBController;
import server.controller.network.Server;

public class Main {
    public static void main(String[] args) {
        Connection conn = Connect.connect();
        Connect.create();
        DBController dbcontroller = new DBController(conn);
        Server server = new Server(8088);
        server.listener(dbcontroller);
    }
}
