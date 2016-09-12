package com.pip;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Farm consisting of multiple Animals
 */
public class Farm {
    ArrayList<Animal> animals;

    public Farm() {
        animals = new ArrayList<Animal>(10);
    }

    public void addAnimal(Animal newAnimal) {
        animals.add(newAnimal);
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public ArrayList<Animal> getHeaviestAnimals() {
        ArrayList<Animal> heavy = animals;

        // Maybe skipping some steps, but...
        Collections.sort(heavy);
        Collections.reverse(heavy);

        return heavy;
    }

    public void printCatNames() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getSpecies().equals("Cat")) {
                System.out.println("Cat: " + animals.get(i).getName());
            }
        }
    }

    public double averageLegs() {
        int total = 0;
        int count = 0;

        for (int i = 0; i < animals.size(); i++) {
            total += animals.get(i).getLegs();
            count++;
        }

        return total / count;
    }

    public static void main(String[] args) {
        Farm f1 = new Farm();
        f1.addAnimal(new Cat("Meow", "Blue"));
        f1.addAnimal(new Cat("Borf", "Chartruuuse"));
        f1.addAnimal(new Dog("Purr", "Brown"));
        f1.printCatNames();

        ArrayList<Animal> sort = f1.getHeaviestAnimals();
        for (int i = 0; i < sort.size(); i++) {
            System.out.println(sort.get(i).getName() + " with weight " + sort.get(i).getWeight());
        }

        System.out.println(f1.averageLegs());


        System.out.println("----------------Prof Tests----------------");
        Cat c = new Cat("Meowth", "black");
        Dog d = new Dog("Puppy", "brown");
        Cow cow = new Cow("Mooer", "white");
        System.out.println("Test 1 Passed: " + (c.getWeight() >= 0 && c.getWeight() <= 25));
        System.out.println("Test 2 Passed: " + (d.getWeight() >= 0 && d.getWeight() <= 25));
        System.out.println("Test 3 Passed: " + (cow.getWeight() >= 100 && cow.getWeight() <= 200));

        double old_weight_cat = c.getWeight();
        double old_weight_dog = d.getWeight();
        double old_weight_cow = cow.getWeight();
        c.grow();
        d.grow();
        cow.grow();
        System.out.println("Test 4 Passed: " + (c.getWeight() / old_weight_cat == 3));
        System.out.println("Test 5 Passed: " +
                (Math.abs(d.getWeight() / old_weight_dog - 1.5) < 0.01));
        System.out.println("Test 6 Passed: " + (cow.getWeight() / old_weight_cow == 5));

        Farm farm = new Farm();
        farm.addAnimal(c);
        farm.addAnimal(d);
        farm.addAnimal(cow);

        ArrayList<Animal> sorted = farm.getHeaviestAnimals();
        for(int i = 0; i < sorted.size() - 1; i++) {
            System.out.println("Test " + (i + 7) + " Passed: " + (sorted.get(i).getWeight() > sorted.get(i + 1).getWeight()));
        }
        // Are these tests right? Because comparing objects ids?
        System.out.println("Test 9 Passed: " + (farm.getAnimal(0) == c));
        System.out.println("Test 10 Passed: " + (farm.getAnimal(1) == d));
        System.out.println("Test 11 Passed: " + (farm.getAnimal(2) == cow));
        // Ha, 7 legs. What would that even look like?!?
        c.setLegs(7);  // lol 7 legged cat
        System.out.println("Test 12 Passed: " + (farm.averageLegs() == 5));

        System.out.println("Printing 'Meowth'...");
        farm.printCatNames();
    }
}
