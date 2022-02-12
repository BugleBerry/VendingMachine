package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void setUp(){
        vendingMachine = new VendingMachine();
    }

    @Test
    public void should_not_allow_purchase_if_no_more_items(){
        Random random = new Random();
        vendingMachine.addBalance(new BigDecimal("10000000"));
        Item itemToPurchase = vendingMachine.getInventory().getItems().
                get(random.nextInt(vendingMachine.getInventory().getItems().size()));
        for (int i = 0; i < 5; i++) {
            vendingMachine.purchase(itemToPurchase);
        }
        Assert.assertFalse("Should be false",vendingMachine.purchase(itemToPurchase));
    }

    @Test
    public void check_add_subtract_correctly_from_balance(){
        vendingMachine.addBalance(new BigDecimal("10.00"));
        Assert.assertEquals("Should be 10.00", new BigDecimal("10.00"), vendingMachine.getBalance());
        BigDecimal balance = vendingMachine.getBalance();

        Item item = vendingMachine.getInventory().getItems().get(0);
        BigDecimal amountToRemove = new BigDecimal(item.getPrice());
        balance = balance.subtract(amountToRemove);
        vendingMachine.subtractBalance(item);

        Assert.assertEquals("Please work", balance,vendingMachine.getBalance());
    }


    @Test
    public void get_correct_change(){
        vendingMachine.addBalance(new BigDecimal("10.00"));
        Item item = new Item("A1","Potato Crisps","8.35","Chip");
        BigDecimal amountToRemove = new BigDecimal(item.getPrice());
        vendingMachine.subtractBalance(item);
        String expectedOutput = ("Quarters: " + 6 + " Dimes: " + 1 + " Nickels: " + 1);
        Assert.assertEquals(expectedOutput, expectedOutput,vendingMachine.calculateChange());
    }

    @Test
    public void should_start_at_zero_balance(){
        Assert.assertEquals("Should be 0.00",new BigDecimal("0.00"), vendingMachine.getBalance());
    }

    @Test
    public void item_stock_should_go_down_when_purchase(){
        vendingMachine.addBalance(new BigDecimal("10.00"));
        Item item = vendingMachine.getInventory().getItems().get(0);
        vendingMachine.purchase(item);
        int itemsLeft = vendingMachine.getInventory().getAmountPerItem().get(item);
        Assert.assertEquals("Should be 4", 4, itemsLeft);
    }

}
