package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
This might sometimes fail
because the time it
takes to run might be off by
a second when date is being called
 */

public class AuditTest {
    Audit audit;
    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        audit = new Audit();
        vendingMachine = new VendingMachine();
    }

    @Test
    public void audit_should_display_correctly() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
        Date date = new Date();
        audit.clearAudit();
        audit.auditMoneyAdded(new BigDecimal("8.00"), new BigDecimal("10.00"));
        audit.auditPurchaseMade(vendingMachine.getInventory().getItems().
                get(0), new BigDecimal("7.00"));
        audit.auditCompletedTransaction(new BigDecimal("7.00"), new BigDecimal("0.00"));

        String moneyAddedAuditMessage = formatter.format(date) + " FEED MONEY: $8.00 $10.00";
        String purchaseAuditMessage = formatter.format(date) + " Potato Crisps A1 $3.05 $7.00";
        String giveChangeAuditMessage = formatter.format(date) + " GIVE CHANGE: $7.00 $0.00";
        String[] expectedMessages = new String[3];
        expectedMessages[0] = moneyAddedAuditMessage;
        expectedMessages[1] = purchaseAuditMessage;
        expectedMessages[2] = giveChangeAuditMessage;
        int index = 0;

        File auditFile = new File("ExampleFiles/Audit.txt");
        try(Scanner fileReader = new Scanner(auditFile)){
            while (fileReader.hasNextLine()){
                String lineBeingRead = fileReader.nextLine();
                Assert.assertEquals("Message in audit should be formatted correctly",
                        expectedMessages[index], lineBeingRead);
                index++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File wasn't found");
        }
    }
}
