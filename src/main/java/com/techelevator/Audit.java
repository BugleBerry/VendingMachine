package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Audit {

    private File auditFile = new File("ExampleFiles/Audit.txt");

    public Audit(){
        if(!auditFile.exists()){
            try {
                auditFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("File couldn't be created");
            }
        }
    }

    private void writeToFile(String thingToWrite){
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(auditFile, true))) {
            printWriter.println(thingToWrite);
        }
        catch (FileNotFoundException e) {
            System.out.println("File couldn't be found.");
        }
    }

    public void auditMoneyAdded(String amount){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        Date date = new Date();
        String output = formatter.format(date) + " FEED MONEY: $" + amount + " newBalance";
        writeToFile(output);
    }
    public void auditPurchaseMade(){
        String output = "Date Time ItemName ItemSlot ItemPrice RemainingBalance";
        writeToFile(output);
    }
    public void auditCompletedTransaction(){
        String output = "Date Time AmountOfChance, RemainingBalance(should be 0)";
        writeToFile(output);
    }
}
