module ru.nsu.fit.ykhdr.smartupshark {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.csv;
    requires org.jetbrains.annotations;

    opens ru.nsu.fit.ykhdr.smartupshark to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.controller to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.model to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.sprite to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy to javafx.fxml;
    opens ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy to javafx.fxml;

    exports ru.nsu.fit.ykhdr.smartupshark;
    exports ru.nsu.fit.ykhdr.smartupshark.controller;
    exports ru.nsu.fit.ykhdr.smartupshark.model;
    exports ru.nsu.fit.ykhdr.smartupshark.sprite;
    exports ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;
    exports ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy;
}