module co.edu.uniquindio.reservasuq {
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

    opens co.edu.uniquindio.reservasuq to javafx.fxml;
    exports co.edu.uniquindio.reservasuq;
}