package com.tneshcheret.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestAction extends BaseEntity {
    private RetailChain retailChain;
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;
    private double discount;
    private MethodGrantingDiscount methodGrantingDiscount;
    private int costAction;

    public RequestAction() {
    }

    public RequestAction(RetailChain retailChain, Product product, LocalDate startDate, LocalDate endDate, double discount, MethodGrantingDiscount methodGrantingDiscount, int costAction) {
        this.retailChain = retailChain;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.methodGrantingDiscount = methodGrantingDiscount;
        this.costAction = costAction;
    }

    public RetailChain getRetailChain() {
        return retailChain;
    }

    public void setRetailChain(RetailChain retailChain) {
        this.retailChain = retailChain;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public MethodGrantingDiscount getMethodGrantingDiscount() {
        return methodGrantingDiscount;
    }

    public void setMethodGrantingDiscount(MethodGrantingDiscount methodGrantingDiscount) {
        this.methodGrantingDiscount = methodGrantingDiscount;
    }

    public int getCostAction() {
        return costAction;
    }

    public void setCostAction(int costAction) {
        this.costAction = costAction;
    }

    @Override
    public String toString() {
        return "RequestAction{" +
                "retailChain=" + retailChain +
                ", product=" + product +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discount=" + discount +
                ", methodGrantingDiscount=" + methodGrantingDiscount +
                ", costAction=" + costAction +
                '}';
    }
}
