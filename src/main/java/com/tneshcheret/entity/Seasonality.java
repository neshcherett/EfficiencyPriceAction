package com.tneshcheret.entity;

public class Seasonality extends BaseEntity {
    private int monthNumber;
    private BrandPackage brandPackage;
    private double coefficient;

    public Seasonality() {
    }

    public Seasonality(int monthNumber, BrandPackage brandPackage, double coefficient) {
        this.monthNumber = monthNumber;
        this.brandPackage = brandPackage;
        this.coefficient = coefficient;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BrandPackage getBrandPackage() {
        return brandPackage;
    }

    public void setBrandPackage(BrandPackage brandPackage) {
        this.brandPackage = brandPackage;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Seasonality{" +
                "monthNumber=" + monthNumber +
                ", brandPackage='" + brandPackage + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}
