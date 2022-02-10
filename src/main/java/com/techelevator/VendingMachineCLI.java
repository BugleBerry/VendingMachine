package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private boolean hasAddedBalance;
	private VendingMachine vendingMachine;

	public VendingMachineCLI() {
		this.hasAddedBalance = false;
		vendingMachine = new VendingMachine();
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();

		cli.run();
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		displayWelcomeMessage();
		displayOptions();

		customerChoice(input);



		// ToDo - Add Code here to show menu, etc.
		input.close();
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

	public void displayOptions(){
		System.out.println("1) Display Items");
		System.out.println("2) Purchase Items");
		System.out.println("3) Exit");
	}

	public void customerChoice(Scanner input){
		String answer = input.nextLine().trim();

		switch (answer){
			case "1":
				vendingMachine.getInventory().printInventory();
				break;
			case "2":
				System.out.println("1) Add Money");
				System.out.println("2) Select Product");
				System.out.println("3) Finish Transaction");
				customerActions(input);
				// select new choice
				break;
			case "3":
				System.out.println("Exit");
				break;
			case "4":
				// print sales report
				break;
			default:
				System.out.println("Input Valid Choice");
				break;
		}
	}


	public void customerActions(Scanner input){
		String answer = input.nextLine().trim();

		switch (answer){
			case "1":
				System.out.println("How much do yoy want to add: ");
				String amount = input.nextLine();
				// fix
				BigDecimal amountToAdd = BigDecimal.ZERO;
				vendingMachine.addBalance(amountToAdd);
				System.out.println();
				break;
			case "2":
				System.out.println("Which product do yuo want to buy (EX:A1): ");
				String itemPosition = input.nextLine();
				// purchase the item
			case "3":
				System.out.println(vendingMachine.calculateChange());
		}

	}

}
