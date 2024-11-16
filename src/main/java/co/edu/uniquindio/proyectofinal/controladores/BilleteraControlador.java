package co.edu.uniquindio.proyectofinal.controladores;

import co.edu.uniquindio.proyectofinal.modelos.Sesion;
import co.edu.uniquindio.proyectofinal.modelos.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BilleteraControlador {

    @FXML
    private Label lblSaldoActual;

    @FXML
    private TextField txtMontoRecarga;

    @FXML
    private Button btnRecargar;

    private final ControladorPrincipal controladorPrincipal;

    public BilleteraControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    public void initialize() {
        cargarSaldoActual();
    }

    private void cargarSaldoActual() {
        Usuario cliente = Sesion.getInstancia().getUsuario();
        if (cliente != null && cliente instanceof co.edu.uniquindio.proyectofinal.modelos.Cliente clienteEspecifico) {
            lblSaldoActual.setText(String.format("%.2f", clienteEspecifico.getBilleteraCliente().getSaldoBilletera()));
        }
    }

    @FXML
    private void recargarSaldo() {
        try {
            double monto = Double.parseDouble(txtMontoRecarga.getText());
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor a 0.");
            }

            Usuario cliente = Sesion.getInstancia().getUsuario();
            if (cliente != null && cliente instanceof co.edu.uniquindio.proyectofinal.modelos.Cliente clienteEspecifico) {
                controladorPrincipal.recargarBilletera(cliente, monto);
                cargarSaldoActual();
                txtMontoRecarga.clear();
                controladorPrincipal.mostrarAlerta("Recarga exitosa", "Billetera", Alert.AlertType.INFORMATION);
            }

            controladorPrincipal.notificarObservadores();
            controladorPrincipal.cerrarVentana(lblSaldoActual);

        } catch (NumberFormatException e) {
            controladorPrincipal.mostrarAlerta("Ingrese un monto válido.", "Error de formato", Alert.AlertType.WARNING);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }
}
