package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

@FunctionalInterface
public interface FishFactory {

    List<FishFactory> FISH_FACTORIES = Arrays.asList(
            d -> new SmallFishObject(d, 42),
            d -> new LongFishObject(d, 42)
    );

    static FishObject generate(double d) {
        Random random = new Random();
        return FISH_FACTORIES.get(random.nextInt()).getFish(d);
    }

    FishObject getFish(double scale);
}

class Foo {

    void test() {
    }
}