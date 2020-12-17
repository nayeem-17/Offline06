package client.controller.network;

import client.controller.Controller;
import client.model.Car;
import server.util.NetworkUtil;

public class Client {

    NetworkUtil networkUtil;

    public Client(String url, int port) {
        networkUtil = new NetworkUtil(url, port);
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public static void main(String[] args) {
        Client client = new Client("0.0.0.0", 8088);
        Controller con = new Controller(client);
        Car car = new Car();
        car.setCarMake("carMake1213");
        car.setCarModel("cococola");
        car.setColorOfCar("black");
        car.setRegistrationNumber("Yobruh");
        car.setPriceOfCar(2457898);
        car.setYearMade(1998);
        System.out.println((con.updateCar(car)));
        con.readAllCars();
    }
}
