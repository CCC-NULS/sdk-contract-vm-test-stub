package io.nuls.savebean.test;

import java.util.List;

public class Child {
    private String name;
    private int tall;
    private  double weight;
    private  List<Toy> toys;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public String toString() {
        return "{name: " + name + ", tall: " + tall + ", weight: " + weight + ", toys: " + toys + "}";
    }
}
