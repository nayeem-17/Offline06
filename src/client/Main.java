package client;

import client.controller.RootController;
import client.controller.network.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public Client client;
    public boolean isAdmin = false, isLogin = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new Client("0.0.0.0", 8088);
        primaryStage.setTitle("Car Showroom");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("./fxmls/main.fxml"));
        RootController rootController = new RootController(primaryStage, this);
        fxmlLoader.setController(rootController);
        AnchorPane page = (AnchorPane) fxmlLoader.load();
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
