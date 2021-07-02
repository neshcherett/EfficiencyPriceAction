package com.tneshcheret.entity;

public class BrandPackage extends BaseEntity {
    private String name;

    public BrandPackage() {
    }

    public BrandPackage(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandPackage{" +
                "name='" + name + '\'' +
                '}';
    }
}
