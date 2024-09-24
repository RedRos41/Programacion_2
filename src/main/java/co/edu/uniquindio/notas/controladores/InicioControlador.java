package co.edu.uniquindio.notas.controladores;

import co.edu.uniquindio.notas.modelos.Nota;
import co.edu.uniquindio.notas.modelos.NotaPrincipal;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;


import java.awt.event.ActionEvent;
import java.awt.*;
public class InicioControlador {

    private final NotaPrincipal notaPrincipal;


    public InicioControlador() {
        notaPrincipal = new NotaPrincipal()J;

    }

    public void crearNota(ActionEvent e){
        try {
            notaPrincipal.agregarNota(txtTitulo.getText(), txtNota.getText(), txtCategoria.getText());


            mostrarAlerta("Nota creada correctamente", Alert.AlertType.INFORMATION);


            txtTitulo.clear();
            txtNota.clear();
            txtCategoria.clear();

        }catch (Exception ex){
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }


    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){


        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();


    }
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextArea txtNota;

}
