package co.edu.uniquindio.reservasuq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservaApplication.class.getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,600 );
        stage.setTitle("Reservas UQ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}