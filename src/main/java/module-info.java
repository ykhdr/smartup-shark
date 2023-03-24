module ru.nsu.fit.ykhdr.smartupshark {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires org.apache.commons.csv;

    opens ru.nsu.fit.ykhdr.smartupshark to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.controller to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.model to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.sprtemodels to javafx.fxml;

    exports ru.nsu.fit.ykhdr.smartupshark ;
    exports ru.nsu.fit.ykhdr.smartupshark.controller;
    exports ru.nsu.fit.ykhdr.smartupshark.model;
    exports ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

}