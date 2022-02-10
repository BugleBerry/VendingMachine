package com.techelevator;

public class VendingMachineCLI {

	private boolean hasAddedBalance;

	public VendingMachineCLI() {
		this.hasAddedBalance = false;
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		VendingMachine vendingMachine = new VendingMachine();
		cli.run();
	}

	public void run() {
		displayWelcomeMessage();
		if (!hasAddedBalance) {
			System.out.println("Please insert money");
		}
		// ToDo - Add Code here to show menu, etc.
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

}
