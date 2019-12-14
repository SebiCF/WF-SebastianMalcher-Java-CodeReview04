package com.sebastian.foodSale;

public class Product {

    private String name;
    private String amount;
    private double oldPrice;
    private double newPrice;
    private String imgPath;
    private String description;

    public Product(String name, String amount, double oldPrice, double newPrice, String imgPath, String description) {
        this.name = name;
        this.amount = amount;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imgPath = imgPath;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "{ " + "\'" + name + '\'' +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice + " }";
    }
}
