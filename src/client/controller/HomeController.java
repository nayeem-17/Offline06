package client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.Main;
import client.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class HomeController {
    public Main main;
    public Controller controller;
    ObservableList<Car> oCars;
    @FXML
    private Stage stage;
    public AnchorPane anchorPane;
    private FXMLLoader fxmlLoader;
    @FXML
    TableView<Car> tableview;
    @FXML
    TableColumn<Car, String> regNo;
    @FXML
    TableColumn<Car, String> carmake;
    @FXML
    TableColumn<Car, String> carmodel;
    @FXML
    TableColumn<Car, String> colorOfCar;
    @FXML
    TableColumn<Car, Number> price;
    @FXML
    TableColumn<Car, Number> yearMade;

    @FXML
    HBox hbox;
    @FXML
    TextField makeSearch;
    @FXML
    TextField modelSearch;
    @FXML
    Button buyButton;
    @FXML
    Button backButton;
    @FXML
    Button searchButton;
    @FXML
    VBox vbox;
    @FXML
    TextField regtf;
    @FXML
    TextField modeltf;
    @FXML
    TextField maketf;
    @FXML
    TextField yeartf;
    @FXML
    TextField colortf;
    @FXML
    TextField pricetf;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button refreshButton;
    @FXML
    Button logoutButton;

    public HomeController(Stage stage, Main main) throws IOException {
        this.stage = stage;
        this.main = main;
        controller = new Controller(main.client);
        fxmlLoader = new FXMLLoader(getClass().getResource("../fxmls/home.fxml"));
        fxmlLoader.setController(this);
    }

    public void initialize() {
        System.out.println(main.isAdmin);
        System.out.println(main.isLogin);
        if (main.isAdmin == true) {
            vbox.setVisible(true);
            tableview.setEditable(true);
            backButton.setVisible(false);
            hbox.setVisible(false);
            buyButton.setVisible(false);

        } else {
            vbox.setVisible(false);
            tableview.setEditable(false);
            addButton.setVisible(false);
            deleteButton.setVisible(false);
            logoutButton.setVisible(false);
        }
        getItems();
        regNo.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        yearMade.setCellValueFactory(new PropertyValueFactory<Car, Number>("yearMade"));
        carmake.setCellValueFactory(new PropertyValueFactory<Car, String>("carMake"));
        carmodel.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
        colorOfCar.setCellValueFactory(new PropertyValueFactory<Car, String>("colorOfCar"));
        price.setCellValueFactory(new PropertyValueFactory<Car, Number>("priceOfCar"));

        tableview.setItems(oCars);

        carmake.setCellFactory(TextFieldTableCell.forTableColumn());
        carmake.setOnEditCommit(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            oCars.get(index).setCarMake(e.getNewValue());
            controller.updateCar(oCars.get(index));
            tableview.refresh();
        });

        carmodel.setCellFactory(TextFieldTableCell.forTableColumn());
        carmodel.setOnEditCommit(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            oCars.get(index).setCarModel(e.getNewValue());
            controller.updateCar(oCars.get(index));
            tableview.refresh();
        });

        colorOfCar.setCellFactory(TextFieldTableCell.forTableColumn());
        colorOfCar.setOnEditCommit(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            oCars.get(index).setColorOfCar(e.getNewValue());
            controller.updateCar(oCars.get(index));
            tableview.refresh();
        });

        price.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        price.setOnEditCommit(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            oCars.get(index).setPriceOfCar(e.getNewValue());
            controller.updateCar(oCars.get(index));
            tableview.refresh();
        });

        yearMade.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        yearMade.setOnEditCommit(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            oCars.get(index).setYearMade(e.getNewValue());
            controller.updateCar(oCars.get(index));
            tableview.refresh();
        });
        addButton.setOnAction(e -> {
            Car car = new Car();
            car.setRegistrationNumber(regtf.getText());
            car.setCarMake(maketf.getText());
            car.setCarModel(modeltf.getText());
            car.setColorOfCar(colortf.getText());
            car.setPriceOfCar(Integer.parseInt(pricetf.getText()));
            car.setYearMade(Integer.parseInt(yeartf.getText()));
            oCars.add(car);

            regtf.clear();
            modeltf.clear();
            maketf.clear();
            pricetf.clear();
            yeartf.clear();
            colortf.clear();
            controller.addCar(car);
        });

        deleteButton.setOnAction(e -> {

            int index = tableview.getSelectionModel().getSelectedIndex();
            System.out.println("deleting at in index " + index);
            System.out.println(oCars.get(index).getRegistrationNumber());
            controller.deleteCar(oCars.get(index).getRegistrationNumber());
            oCars.remove(index);

        });

        refreshButton.setOnAction(e -> {
            tableview.setItems(getItems());
        });

        backButton.setOnAction(e -> {
            goback();
        });

        logoutButton.setOnAction(e -> {
            goback();
        });

        buyButton.setOnAction(e -> {
            int index = tableview.getSelectionModel().getSelectedIndex();
            Car c = oCars.get(index);
            if (controller.deleteCar(c.getRegistrationNumber())) {
                oCars.remove(c);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Successful!!!");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Something's wrong. Please try again");
                alert.show();
            }
        });

        searchButton.setOnAction(e -> {
            System.out.println(modelSearch.getText());
            System.out.println(makeSearch.getText());
            if (modelSearch.getText().equals("") && makeSearch.getText().equals("")) {
                tableview.setItems(getItems());
            } else if (modelSearch.getText().equals("")) {
                tableview.setItems(getItems());
                List<Car> sCars = new ArrayList<>();
                for (Car c : oCars) {
                    if (c.getCarMake().equals(makeSearch.getText())) {
                        sCars.add(c);
                    }
                }
                this.oCars = FXCollections.observableArrayList();
                for (int i = 0; i < sCars.size(); i++) {
                    oCars.add(sCars.get(i));
                }
                tableview.setItems(oCars);
            } else if (makeSearch.getText().equals("")) {
                tableview.setItems(getItems());
                List<Car> sCars = new ArrayList<>();
                for (Car c : oCars) {
                    if (c.getCarModel().equals(modelSearch.getText())) {
                        sCars.add(c);
                    }
                }
                this.oCars = FXCollections.observableArrayList();
                for (int i = 0; i < sCars.size(); i++) {
                    oCars.add(sCars.get(i));
                }
                tableview.setItems(oCars);
            } else {
                tableview.setItems(getItems());
                List<Car> sCars = new ArrayList<>();
                for (Car c : oCars) {
                    if (c.getCarMake().equals(makeSearch.getText()) && c.getCarModel().equals(modelSearch.getText())) {
                        sCars.add(c);
                    }
                }
                this.oCars = FXCollections.observableArrayList();
                for (int i = 0; i < sCars.size(); i++) {
                    oCars.add(sCars.get(i));
                }
                tableview.setItems(oCars);
            }
        });

    }

    private ObservableList<Car> getItems() {

        this.oCars = FXCollections.observableArrayList();
        List<Car> cars = new Controller(main.client).readAllCars();
        for (int i = 0; i < cars.size(); i++) {
            oCars.add(cars.get(i));
        }

        return oCars;
    }

    private void goback() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("./fxmls/main.fxml"));
        RootController rootController = new RootController(stage, main);
        fxmlLoader.setController(rootController);
        AnchorPane page;
        try {
            page = (AnchorPane) fxmlLoader.load();
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }
}
