package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SalesTest {


    @Test
    public void sales_prints_correctly(){

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addBalance(new BigDecimal("10.00"));
        vendingMachine.purchase(vendingMachine.getInventory().getItems().get(2));
        vendingMachine.purchase(vendingMachine.getInventory().getItems().get(2));
        Map<Item,Integer> actualMap = vendingMachine.getInventory().calculateItemSold();
        SalesReport actualReport = new SalesReport(actualMap,vendingMachine.getTotalAmountSpent());


        Inventory expectedInv = new Inventory();
        expectedInv.setItems(vendingMachine.getInventory().getItems());
        Map<Item,Integer> expectedMap = new HashMap<>();
        for(Item item : expectedInv.getItems()){
            expectedMap.put(item,0);
        }
        expectedMap.put(vendingMachine.getInventory().getItems().get(2), 2);

        SalesReport expectedReport = new SalesReport(expectedMap,new BigDecimal("5.50"));

        for (Map.Entry<Item,Integer> item : expectedMap.entrySet()){
            Assert.assertEquals("Im Tired",item.getValue(), actualMap.get(item.getKey()));
        }

        Assert.assertEquals(new BigDecimal("5.50"),vendingMachine.getTotalAmountSpent());
    }
}
