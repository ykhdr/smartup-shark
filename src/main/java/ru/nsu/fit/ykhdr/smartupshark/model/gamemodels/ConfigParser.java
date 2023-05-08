package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.FishType;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigParser {
    private final @NotNull Properties properties = new Properties();

    public ConfigParser(@NotNull String path) {
        try {
            properties.load(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull Map<FishType, Position> parse() {
        try {
            return properties.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            key -> FishType.valueOf(((String) key.getKey()).toUpperCase()),
                            value -> {
                                String[] split = ((String) value.getValue()).split(" ");
                                return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
