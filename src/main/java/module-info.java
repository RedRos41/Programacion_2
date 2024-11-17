module co.edu.uniquindio.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires jakarta.activation;

    opens co.edu.uniquindio.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal;
    exports co.edu.uniquindio.proyectofinal.controladores;
    opens co.edu.uniquindio.proyectofinal.controladores to javafx.fxml;
    opens co.edu.uniquindio.proyectofinal.modelos to javafx.base;

}
