package server.model;

public class Car {
    private String registrationNumber;
    private Number yearMade;
    private String colorOfCar;
    private String carMake;
    private String carModel;
    private Number priceOfCar;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Number getPriceOfCar() {
        return priceOfCar;
    }

    public void setPriceOfCar(Number number) {
        this.priceOfCar = number;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getColorOfCar() {
        return colorOfCar;
    }

    public void setColorOfCar(String colorOfCar) {
        this.colorOfCar = colorOfCar;
    }

    public Number getYearMade() {
        return yearMade;
    }

    public void setYearMade(Number number) {
        this.yearMade = number;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "The regNo is " + registrationNumber + ",yearMade is " + yearMade + ",color is  " + colorOfCar
                + ", carMake is " + carMake + ", carModel is " + carModel + " and the price is " + priceOfCar;
    }
}
