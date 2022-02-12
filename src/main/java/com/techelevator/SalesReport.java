package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {

    private File salesReporFile = new File("ExampleFiles/SalesReport.txt");

    public SalesReport(Map<Item, Integer> itemMap, BigDecimal totalSalesPrice){
        try(PrintWriter printWriter = new PrintWriter(salesReporFile)) {
            if(!salesReporFile.exists()){
                salesReporFile.createNewFile();
            }
            for (Map.Entry<Item, Integer> item: itemMap.entrySet()) {
                printWriter.println(item.getKey().getName() + "|" + item.getValue());
            }
            printWriter.println();
            printWriter.println("**TOTAL SALES** $" + totalSalesPrice);

        }catch (IOException e) {
            e.printStackTrace();
        }



    }


}
