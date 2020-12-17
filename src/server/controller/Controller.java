package server.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.model.Car;
import server.model.User;

public class Controller {
    private DBController dbController;
    private JsonHandler jHandler;

    public Controller(DBController dbController) {
        this.dbController = dbController;
        jHandler = new JsonHandler();
    }

    public String crud(JSONObject request) {
        JSONObject response;
        String command = request.get("command").toString();
        switch (command) {

            case "login":
                response = new JSONObject();
                List<User> users = dbController.getAllUser();
                boolean isUser = false;
                for (User u : users) {
                    if (u.getPassword().equals(request.get("password").toString())
                            && u.getUsername().equals(request.get("username").toString())) {
                        System.out.println(u);
                        isUser = true;
                    }
                }
                response.put("isUser", isUser);
                return response.toJSONString();

            case "getallcar":
                JSONObject jObject = new JSONObject();
                JSONArray jArray = new JSONArray();
                List<Car> cars = dbController.getAlllCar();
                for (int i = 0; i < cars.size(); i++) {
                    jArray.add(jHandler.getJSON(cars.get(i)));
                }
                jObject.put("data", jArray);
                return jObject.toJSONString();

            case "addcar":
                response = new JSONObject();
                JSONObject carObject = (JSONObject) request.get("data");
                Car newCar = jHandler.getCar(carObject);
                if (dbController.addCar(newCar))
                    response.put("isdone", "1");
                else
                    response.put("isdone", "0");
                return response.toJSONString();

            case "updatecar":
                response = new JSONObject();
                JSONObject carJsonObject = (JSONObject) request.get("data");
                Car updateCar = jHandler.getCar(carJsonObject);
                if (dbController.updateCar(updateCar))
                    response.put("isdone", "1");
                else
                    response.put("isdone", "0");

                return response.toJSONString();

            case "deletecar":
                response = new JSONObject();
                String regNo = request.get("regNo").toString();
                if (dbController.deleteCar(regNo))
                    response.put("isdone", "1");
                else
                    response.put("isdone", "0");

                return response.toJSONString();

            default:
                System.out.println("no bruh");
                return "no bruh";
        }
    }
}
