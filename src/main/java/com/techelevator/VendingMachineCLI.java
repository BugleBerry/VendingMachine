package com.techelevator;

public class VendingMachineCLI {

	public VendingMachineCLI() {

	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {
		displayWelcomeMessage();
		// ToDo - Add Code here to show menu, etc.
		Inventory inventory = new Inventory();
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

}
