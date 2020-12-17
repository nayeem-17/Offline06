package client.controller;

import java.io.IOException;

import client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RootController {
    public Main main;
    public Controller controller;
    @FXML
    private Stage stage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbutton;

    public RootController(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        controller = new Controller(main.client);

    }

    public void initialize() {
        loginbutton.setOnAction(e -> {
            HomeController homeController;
            if (password.getText().equals("")) {
                System.out.println("hola");
                if (username.getText().equals(""))
                    main.isLogin = false;
                else
                    main.isLogin = true;
                main.isAdmin = false;
            } else
                main.isAdmin = controller.login(username.getText(), password.getText());
            System.out.println(main.isAdmin);
            System.out.println(main.isLogin);
            if (main.isAdmin == true || main.isLogin == true) {
                main.isLogin = true;
                try {
                    homeController = new HomeController(stage, this.main);
                    FXMLLoader fxmlLoader = homeController.getFxmlLoader();
                    Scene scene = new Scene((AnchorPane) fxmlLoader.load());
                    this.stage.setScene(scene);
                    this.stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Invalid login");
                alert.show();
            }

        });
    }
}
