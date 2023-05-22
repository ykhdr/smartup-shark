module ru.nsu.fit.ykhdr.smartupshark {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.csv;
    requires org.jetbrains.annotations;
    requires com.google.gson;

    exports ru.nsu.fit.ykhdr.smartupshark to com.google.gson, javafx.graphics;
    exports ru.nsu.fit.ykhdr.smartupshark.controller;
    exports ru.nsu.fit.ykhdr.smartupshark.model;
    exports ru.nsu.fit.ykhdr.smartupshark.gameobjects to com.google.gson;
    exports ru.nsu.fit.ykhdr.smartupshark.strategy;
    exports ru.nsu.fit.ykhdr.smartupshark.model.utils;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes;
    exports ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes to com.google.gson;
    exports ru.nsu.fit.ykhdr.smartupshark.score;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gameutils;
    exports ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;
    exports ru.nsu.fit.ykhdr.smartupshark.config to com.google.gson;

}