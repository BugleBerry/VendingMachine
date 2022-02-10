package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {

    Map<Item,Integer> amountPerItem = new HashMap<>();

    List<Item> items = new ArrayList<>();
    File inventoryFile = new File("ExampleFiles/VendingMachine.txt");

    public void createItems(){
        String[] itemsArray = new String[4];
        try(Scanner fileReader = new Scanner(inventoryFile)){
            while (fileReader.hasNextLine()){
                String s = fileReader.nextLine();
                itemsArray = s.split("\\|");
                Item newItem = new Item(itemsArray[0],itemsArray[1],itemsArray[2],itemsArray[3]);
                items.add(newItem);
                amountPerItem.put(newItem, 5);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File wasn't found");
        }
    }

    public void printInventory(){
        for (Item item : items){
            if(amountPerItem.get(item) > 0){
                System.out.println(item.printItem());
            }
            else{
                System.out.println(item.getName()+ " " + item.getPosition() + " " + "Sold out");
            }

        }
    }

    public Map<Item, Integer> getAmountPerItem() {
        return amountPerItem;
    }

    public void setAmountPerItem(Map<Item, Integer> amountPerItem) {
        this.amountPerItem = amountPerItem;
    }
}
