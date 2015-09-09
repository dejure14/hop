package model;

public class Purchase {

    private int id;
    private String name;
    private double price;
    private int category;
    private int store;

    public Purchase() {
    }

    public Purchase(int id, String name, double price, int category, int store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.store = store;
    }

    public Purchase(String name, double price, int category, int store) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
}
