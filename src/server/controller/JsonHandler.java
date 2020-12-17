package server.controller;

import org.json.simple.JSONObject;

import server.model.Car;

public class JsonHandler {
    public JSONObject getJSON(Car car) {
        JSONObject jObject = new JSONObject();

        jObject.put("registrationNumber", car.getRegistrationNumber());
        jObject.put("yearMade", car.getYearMade());
        jObject.put("carMake", car.getCarMake());
        jObject.put("carModel", car.getCarModel());
        jObject.put("priceOfCar", car.getPriceOfCar());
        jObject.put("colorOfCar", car.getColorOfCar());

        return jObject;
    }

    public Car getCar(JSONObject jObject) {
        Car car = new Car();
        car.setCarMake(jObject.get("carMake").toString());
        car.setCarModel(jObject.get("carModel").toString());
        car.setPriceOfCar((Number) jObject.get("priceOfCar"));
        car.setRegistrationNumber(jObject.get("registrationNumber").toString());
        car.setColorOfCar(jObject.get("colorOfCar").toString());
        car.setYearMade((Number) jObject.get("yearMade"));
        return car;
    }
}
