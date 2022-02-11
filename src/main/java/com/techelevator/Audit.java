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

    public void auditMoneyAdded(BigDecimal amount, BigDecimal balance){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        Date date = new Date();
        String outputString = String.format("%.2f", amount);
        String output = formatter.format(date) + " FEED MONEY: $" + outputString + " $" + balance;
        writeToFile(output);
    }
    public void auditPurchaseMade(Item itemPurchased, BigDecimal balance){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        Date date = new Date();
        String output = formatter.format(date) + " " + itemPurchased.getName() +
                " " + itemPurchased.getPosition() +
                " $" + itemPurchased.getPrice() + " $" + balance;
        writeToFile(output);
    }
    public void auditCompletedTransaction(BigDecimal previousBalance, BigDecimal newBalance){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        Date date = new Date();
        String outputString = String.format("%.2f", newBalance);
        String output = formatter.format(date) + " GIVE CHANGE: $" + previousBalance + " $" + outputString;
        writeToFile(output);
    }
}
