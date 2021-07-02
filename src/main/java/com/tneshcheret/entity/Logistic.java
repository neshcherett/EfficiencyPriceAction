package com.tneshcheret.entity;

public class Logistic extends BaseEntity {
    private Region region;
    private BrandPackage brandPackage;
    private double deliveryCost;

    public Logistic() {
    }

    public Logistic(Region region, BrandPackage brandPackage, double deliveryCost) {
        this.region = region;
        this.brandPackage = brandPackage;
        this.deliveryCost = deliveryCost;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public BrandPackage getBrandPackage() {
        return brandPackage;
    }

    public void setBrandPackage(BrandPackage brandPackage) {
        this.brandPackage = brandPackage;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public String toString() {
        return "Logistic{" +
                "region=" + region +
                ", brandPackage=" + brandPackage +
                ", deliveryCost=" + deliveryCost +
                '}';
    }
}
