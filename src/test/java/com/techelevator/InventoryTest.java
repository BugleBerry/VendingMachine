package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
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


}
