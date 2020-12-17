package server.controller.network;

import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import server.controller.Controller;
import server.controller.DBController;
import server.util.NetworkUtil;

public class AsyncReadData {
    private NetworkUtil networkUtil;
    Controller controller;

    public AsyncReadData(NetworkUtil networkUtil, DBController dbcontroller) {
        this.networkUtil = networkUtil;
        controller = new Controller(dbcontroller);
    }

    public void ReadData() {
        while (true) {
            Object o = networkUtil.read();
            if (o != null) {
                String data = (String) o;
                JSONParser parser = new JSONParser();
                try {
                    JSONObject jsonoObject = (JSONObject) parser.parse(data);
                    String message = controller.crud(jsonoObject);
                    new AsyncWriteData(this.networkUtil).writeData(message);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

            }
        }
    }

}
