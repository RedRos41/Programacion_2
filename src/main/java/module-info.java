module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires static lombok;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.simplejavamail.core;
    requires org.simplejavamail;

    opens co.edu.uniquindio.clinica.modelo to javafx.base, javafx.fxml;

    exports co.edu.uniquindio.clinica;
    exports co.edu.uniquindio.clinica.controladores;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;
}
