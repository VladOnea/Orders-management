package model;

public class Product {
    private int id;
    private String name;
    private float price;
    private int availableStock;

    public Product(int id, String name, float price, int availableStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableStock = availableStock;
    }

    public Product( String name, float price, int availableStock) {
        this.name = name;
        this.price = price;
        this.availableStock = availableStock;
    }

    public Product(){};

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {this.availableStock = availableStock;
    }

}
//Ordermanagementapp.order

