package ru.nsu.fit.ykhdr.smartupshark.config;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;

public class ConfigParser {
    private static final @NotNull ConfigParser instance = new ConfigParser();

    private ConfigParser() {}

    public @NotNull GameConfig parse(@NotNull String configPath){
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(configPath)) {
            return gson.fromJson(reader, GameConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull ConfigParser getInstance(){
        return instance;
    }
}
