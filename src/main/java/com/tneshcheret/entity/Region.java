package com.tneshcheret.entity;

public class Region extends BaseEntity {
    private String name;


    public Region(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Region() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                '}';
    }
}
