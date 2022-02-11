package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SalesReport {

    private File salesReporFile = new File("ExampleFiles/SalesReport.txt");

    public SalesReport(){
        try(PrintWriter printWriter = new PrintWriter(salesReporFile)) {
            if(!salesReporFile.exists()){
                salesReporFile.createNewFile();
            }

            

        }catch (IOException e) {
            e.printStackTrace();
        }



    }


}
