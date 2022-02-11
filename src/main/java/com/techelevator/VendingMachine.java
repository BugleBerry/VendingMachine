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
        if (balance.compareTo(new BigDecimal(itemToPurchase.getPrice())) < 0) {
            System.out.println("Not enough funds");
        } else {
            if (inventory.getAmountPerItem().get(itemToPurchase) < 1) {
                System.out.println("Sorry, the item is sold out");
            } else {
                subtractBalance(itemToPurchase);
                inventory.subtractItem(itemToPurchase);

                switch(itemToPurchase.getCategory()){
                    case "Chip":
                        System.out.println("Crunch Crunch, Yum!");
                        break;
                    case "Candy":
                        System.out.println("Munch Munch, Yum!");
                        break;
                    case "Drink":
                        System.out.println("Glug Glug, Yum!");
                        break;
                    case "Gum":
                        System.out.println("Chew Chew, Yum!");
                        break;
                    default:
                        System.out.println("Unknown Category");
                        break;
                }
            }
        }
    }

    public String calculateChange(){
        int quarters;
        int dimes;
        int nickels;
        BigDecimal remainder;
        quarters = balance.divide(new BigDecimal("0.25")).intValue();
        remainder = balance.remainder(new BigDecimal("0.25"));

        dimes = remainder.divide(new BigDecimal("0.10")).intValue();
        remainder = remainder.remainder(new BigDecimal("0.10"));

        nickels = remainder.divide(new BigDecimal("0.05")).intValue();

        balance = BigDecimal.ZERO;
        return ("Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels);

    }

    public Inventory getInventory(){
        return inventory;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
