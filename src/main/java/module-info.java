module iti.iti_simulation {
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
    requires java.sql;

    opens iti.iti_simulation to javafx.fxml;
    exports iti.iti_simulation;
    opens iti.iti_simulation.student_controller;
    opens iti.iti_simulation.login_controller;
    opens iti.iti_simulation.instructor_controller;
    opens iti.models;
    opens iti.data_access_layer;
    opens iti.services;

}