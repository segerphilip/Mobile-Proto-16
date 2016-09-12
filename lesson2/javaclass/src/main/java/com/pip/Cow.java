package com.pip;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Cow subclass of Animal
 */
public class Cow extends Animal {
    public Cow(String name, String color) {
        super(4, name, color, "Cow", ThreadLocalRandom.current().nextInt(100, 200 + 1));
    }

    public void grow() {
        setWeight(getWeight() * 5);
    }
}
