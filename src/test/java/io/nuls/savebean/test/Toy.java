package io.nuls.savebean.test;

public class Toy {
    private String toyName;
    private double price;

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{toyName: " + toyName + ", price: " + price + "}";
    }
}
