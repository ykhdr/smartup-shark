module ru.nsu.fit.ykhdr.smartupshark {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.csv;
    requires org.jetbrains.annotations;

    exports ru.nsu.fit.ykhdr.smartupshark;
    exports ru.nsu.fit.ykhdr.smartupshark.controller;
    exports ru.nsu.fit.ykhdr.smartupshark.model;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;
    exports ru.nsu.fit.ykhdr.smartupshark.strategy;
    opens ru.nsu.fit.ykhdr.smartupshark.model.gameobjects to javafx.fxml;
}