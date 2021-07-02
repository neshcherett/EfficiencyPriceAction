package com.tneshcheret.entity;

public class Product extends BaseEntity {
    private Integer id;
    private String name;
    private BrandPackage brandPackage;
    private double price;
    private double primeCost;

    public Product(String name, BrandPackage brandPackage, double primeCost, double price, Integer id) {
        super(id);
        this.name = name;
        this.brandPackage = brandPackage;
        this.primeCost = primeCost;
        this.price = price;
    }

    public Product() {
    }

    public BrandPackage getBrandPackage() {
        return brandPackage;
    }

    public void setBrandPackage(BrandPackage brandPackage) {
        this.brandPackage = brandPackage;
    }

    public double getPrimeCost() {
        return primeCost;
    }

    public void setPrimeCost(double primeCost) {
        this.primeCost = primeCost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brandPackage='" + brandPackage + '\'' +
                ", price=" + price +
                ", primeCost=" + primeCost +
                '}';
    }
}
