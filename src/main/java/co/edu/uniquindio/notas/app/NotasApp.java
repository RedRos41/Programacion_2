package co.edu.uniquindio.notas.app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NotasApp extends Application {


    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(NotasApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Mis Notas");
        stage.show();
    }


    public static void main(String[] args) {
        launch(NotasApp.class, args);
    }
}

