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

}
