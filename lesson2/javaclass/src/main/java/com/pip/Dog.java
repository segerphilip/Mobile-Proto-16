package com.pip;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dog subclass of Animal
 */
public class Dog extends Animal {
    public Dog(String name, String color) {
        super(4, name, color, "Dog", ThreadLocalRandom.current().nextInt(0, 25 + 1));
    }

    public void grow() {
        setWeight(getWeight() * 1.5);
    }
}
