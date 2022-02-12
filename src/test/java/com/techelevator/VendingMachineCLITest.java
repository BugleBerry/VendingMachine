package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineCLITest {

    VendingMachineCLI vendingMachineCLI;

    @Before
    public void setUp(){
        vendingMachineCLI = new VendingMachineCLI();
    }

    @Test
    public void is_money_added_whole_Number(){
        BigDecimal amountToAdd = new BigDecimal("10.00");
        BigDecimal nonWholeNunber = new BigDecimal("6.74");
        Assert.assertTrue("Should be true", vendingMachineCLI.isWholeNumber(amountToAdd));
        Assert.assertFalse("Should be false", vendingMachineCLI.isWholeNumber(nonWholeNunber));
    }
}
