package co.edu.uniquindio.seguimiento2.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactosApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/contacto_view.fxml"));

        // Crear la escena con el contenido del FXML
        Scene scene = new Scene(root);

        // Configurar y mostrar el escenario principal
        primaryStage.setTitle("Gestor de Contactos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
