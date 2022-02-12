package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class InventoryTest {

    Inventory testInventory;
    File inventoryFile;

    @Before
    public void setup() {
        testInventory = new Inventory();
        testInventory.createItems();
        inventoryFile = new File("ExampleFiles/VendingMachine.txt");
    }

    @Test
    public void inventory_size_should_match_lines_from_input_file() {
        int expectedLineCount = 0;
        try(Scanner fileReader = new Scanner(inventoryFile)){
            while (fileReader.hasNextLine()){
                fileReader.nextLine();
                expectedLineCount++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File wasn't found");
        }
        int actualInventorySize = testInventory.getItems().size();
        Assert.assertEquals("Expect the item count to be " + expectedLineCount,
                expectedLineCount, actualInventorySize);
    }

    @Test
    public void inventory_items_should_not_have_null_properties() {
        for (Item item : testInventory.getItems()) {
            Assert.assertNotNull("Item position should not be null", item.getPosition());
            Assert.assertNotNull("Item name should not be null", item.getName());
            Assert.assertNotNull("Item price should not be null", item.getPrice());
            Assert.assertNotNull("Item category should not be null", item.getCategory());
        }
    }

    @Test
    public void inventory_file_should_exist() {
        Assert.assertTrue("Should find text file in specified directory",
                inventoryFile.exists());
    }

    @Test
    public void item_information_should_display_correctly() {
        Random random = new Random();
        Item item = testInventory.getItems().get(random.nextInt(
                testInventory.getItems().size()));
        String itemDisplay = item.getName() + " " +
                item.getPosition() + " " + item.getPrice();
        Assert.assertEquals("Item should display in menu properly",
                itemDisplay, item.printItem());
    }

}
