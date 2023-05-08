module ru.nsu.fit.ykhdr.smartupshark {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.csv;
    requires org.jetbrains.annotations;

    exports ru.nsu.fit.ykhdr.smartupshark;
    exports ru.nsu.fit.ykhdr.smartupshark.controller;
    exports ru.nsu.fit.ykhdr.smartupshark.model;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;
    exports ru.nsu.fit.ykhdr.smartupshark.strategy;
    opens ru.nsu.fit.ykhdr.smartupshark.model.gameobjects to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.model.gamemodels to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.model to javafx.fxml;
}