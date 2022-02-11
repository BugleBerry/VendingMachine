package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private boolean hasAddedBalance;
	private VendingMachine vendingMachine;
	private boolean hasEnded;

	public VendingMachineCLI() {
		this.hasAddedBalance = false;
		vendingMachine = new VendingMachine();
		hasEnded = false;
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();

		cli.run();
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		displayWelcomeMessage();

		while(!hasEnded){
			displayOptions();
			customerChoice(input);
		}




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
		System.out.println();
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
				break;
			case "3":
				System.out.println("Goodbye!");
				hasEnded = true;
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
				System.out.println("How much do you want to add: ");
				String amount = input.nextLine();
				BigDecimal amountToAdd = new BigDecimal(amount);
				vendingMachine.addBalance(amountToAdd);
				hasAddedBalance = true;
				break;
			case "2":
				if(!hasAddedBalance){
					System.out.println("First Add Funds");
					break;
				}
				System.out.println("Which product do you want to buy (EX:A1): ");
				String itemPosition = input.nextLine();
				Item itemToPurchase = vendingMachine.getInventory().getItemByPosition(itemPosition);
				if (itemToPurchase == null) {
					System.out.println("Item was not found");
				} else {
					vendingMachine.purchase(itemToPurchase);
				}
				break;
			case "3":
				System.out.println(vendingMachine.calculateChange());
				break;
			default:
				System.out.println("Input Valid Choice");
				break;
		}

	}

}
