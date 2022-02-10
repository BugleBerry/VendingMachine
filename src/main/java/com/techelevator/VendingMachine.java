package com.techelevator;

import java.math.BigDecimal;

public class VendingMachine {
    private BigDecimal balance;
    private Inventory inventory;

    public VendingMachine() {
        this.balance = new BigDecimal("0.00");
        this.inventory = new Inventory();
        inventory.createItems();
    }

    public void addBalance(BigDecimal amountToAdd) {
        this.balance = balance.add(amountToAdd);
    }

    public void subtractBalance(Item itemToPurchase) {
        this.balance = balance.subtract(new BigDecimal(itemToPurchase.getPrice()));
    }

    public void purchase(Item itemToPurchase) {
        if (balance.compareTo(new BigDecimal(itemToPurchase.getPrice())) == -1) {
            System.out.println("Not enough funds");
        } else {
            if (inventory.getAmountPerItem().get(itemToPurchase) < 1) {
                System.out.println("Sorry, the item is sold out");
            } else {
                subtractBalance(itemToPurchase);

            }
        }
    }
}
