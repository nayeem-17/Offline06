package client.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.controller.network.Client;
import client.model.Car;

import server.util.NetworkUtil;

public class Controller {
    private NetworkUtil networkUtil;
    private JsonHandler jHandler;

    public Controller(Client client) {
        this.networkUtil = client.getNetworkUtil();
        jHandler = new JsonHandler();
    }

    public List<Car> readAllCars() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "getallcar");
        JSONObject rObject = send(jsonObject);
        List<Car> cars = new ArrayList<Car>();
        JSONArray allCars = (JSONArray) rObject.get("data");
        for (int i = 0; i < allCars.size(); i++) {
            Car car = jHandler.getCar((JSONObject) allCars.get(i));
            cars.add(car);
        }
        System.out.println(rObject.toJSONString());
        return cars;
    }

    public boolean addCar(Car car) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "addcar");
        JSONObject carObject = jHandler.getJSON(car);
        jsonObject.put("data", carObject);
        JSONObject rObject = send(jsonObject);
        String isTure = rObject.get("isdone").toString();
        if (isTure.equals("0"))
            return false;
        else
            return true;
    }

    public boolean updateCar(Car car) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "updatecar");
        JSONObject carObject = jHandler.getJSON(car);
        jsonObject.put("data", carObject);
        JSONObject rObject = send(jsonObject);
        String isTure = rObject.get("isdone").toString();
        if (isTure.equals("0"))
            return false;
        else
            return true;
    }

    public boolean deleteCar(String regNo) {
        JSONObject request = new JSONObject();
        request.put("command", "deletecar");
        request.put("regNo", regNo);
        JSONObject rObject = send(request);
        String isTure = rObject.get("isdone").toString();
        if (isTure.equals("0"))
            return false;
        else
            return true;
    }

    public boolean login(String username, String password) {
        JSONObject request = new JSONObject();
        request.put("command", "login");
        request.put("username", username);
        request.put("password", password);
        JSONObject rObject = send(request);
        String isTure = rObject.get("isUser").toString();
        if (isTure.equals("false"))
            return false;
        else
            return true;
    }

    public JSONObject send(JSONObject jsonObject) {
        networkUtil.write(jsonObject.toJSONString());
        Object o = networkUtil.read();
        String data = (String) o;
        JSONParser parser = new JSONParser();
        try {
            JSONObject jObject = (JSONObject) parser.parse(data);
            return jObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
