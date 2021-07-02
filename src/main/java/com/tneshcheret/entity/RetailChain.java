package com.tneshcheret.entity;

public class RetailChain extends BaseEntity {

    private String name;
    private Region region;
    private double commercialTerms;

    public RetailChain() {
    }

    public RetailChain(String name, Region region, int commercialTerms, Integer id) {
        super(id);
        this.name = name;
        this.region = region;
        this.commercialTerms = commercialTerms;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public double getCommercialTerms() {
        return commercialTerms;
    }

    public void setCommercialTerms(double commercialTerms) {
        this.commercialTerms = commercialTerms;
    }

    @Override
    public String toString() {
        return "RetailChain{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", commercialTerms=" + commercialTerms +
                '}';
    }
}
