package com.pip;

/**
 * Abstract animal class
 */
public abstract class Animal implements Comparable<Animal> {
    int legs;
    String name;
    String color;
    String species;
    double weight;

    public Animal(int legs, String name, String color, String species, double weight) {
        this.legs = legs;
        this.name = name;
        this.color = color;
        this.species = species;
        this.weight = weight;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int newLegs) {
        legs = newLegs;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String newColor) {
        color = newColor;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String newSpecies) {
        species = newSpecies;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public abstract void grow();

    public int compareTo(Animal otherAnimal) {
        if (this.getWeight() < otherAnimal.getWeight()) {
            return -1;
        } else if (this.getWeight() == otherAnimal.getWeight()) {
            return 0;
        } else {
            return 1;
        }
    }
}
