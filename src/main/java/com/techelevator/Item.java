package com.techelevator;

import java.math.BigDecimal;

public class Item {
    private String position;
    private String name;
    private BigDecimal price;
    private String category;

    public Item(String position, String name, BigDecimal price, String category) {
        this.position = position;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String printItem() {
        return getName() + " " + getPosition() + " " + getPrice();
    }
}
