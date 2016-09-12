package com.pip;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Cat subclass of Animal
 */
public class Cat extends Animal {
    public Cat(String name, String color) {
        super(4, name, color, "Cat", ThreadLocalRandom.current().nextInt(0, 25 + 1));
    }

    public void grow() {
        setWeight(getWeight() * 3);
    }
}
