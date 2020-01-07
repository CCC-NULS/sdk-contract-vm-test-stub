package io.nuls.savebean.test;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Father {

    private String name;
    private int tall;
    private double weight;

    private Child child;
    private List<Car> cars= new ArrayList<Car>();
    private Map<Integer, Integer> totalDepositIndex = new LinkedHashMap<Integer, Integer>();

    public Father(String name){
    }

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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }


}
