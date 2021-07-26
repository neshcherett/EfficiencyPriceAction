package com.tneshcheret.entity;

import java.time.LocalDate;
import java.util.List;

public class RequestAction extends BaseEntity {
    private RetailChain retailChain;
    private List<Product> products;
    private LocalDate startDate;
    private LocalDate endDate;
    private double discount;
    private MethodGrantingDiscount methodGrantingDiscount;
    private int costAction;
    private User user;
    private Integer id;

    private RequestAction() {
    }

    public static Builder newBuilder() {
        return new RequestAction().new Builder();
    }

    public RetailChain getRetailChain() {
        return retailChain;
    }

    public void setRetailChain(RetailChain retailChain) {
        this.retailChain = retailChain;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public MethodGrantingDiscount getMethodGrantingDiscount() {
        return methodGrantingDiscount;
    }

    public int getCostAction() {
        return costAction;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RequestAction{" +
                "retailChain=" + retailChain +
                ", product=" + products +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discount=" + discount +
                ", methodGrantingDiscount=" + methodGrantingDiscount +
                ", costAction=" + costAction +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setRetailChain(RetailChain retailChain) {
            RequestAction.this.retailChain = retailChain;
            return this;
        }

        public Builder setProduct(List<Product> products) {
            RequestAction.this.products = products;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            RequestAction.this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            RequestAction.this.endDate = endDate;
            return this;
        }

        public Builder setDiscount(double discount) {
            RequestAction.this.discount = discount;
            return this;
        }

        public Builder setMethodGrantingDiscount(MethodGrantingDiscount methodGrantingDiscount) {
            RequestAction.this.methodGrantingDiscount = methodGrantingDiscount;
            return this;
        }

        public Builder setCostAction(int costAction) {
            RequestAction.this.costAction = costAction;
            return this;
        }

        public Builder setUser(User user) {
            RequestAction.this.user = user;
            return this;
        }

        public Builder setId(Integer id) {
            RequestAction.this.id = id;
            return this;
        }

        public RequestAction build() {
            return RequestAction.this;
        }
    }
}
