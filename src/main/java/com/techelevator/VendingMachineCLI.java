package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private boolean hasAddedBalance;
	private VendingMachine vendingMachine;
	private boolean hasEnded;
	Audit audit;

	public VendingMachineCLI() {
		this.hasAddedBalance = false;
		vendingMachine = new VendingMachine();
		audit = new Audit();
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
			displayOptions(input);
		}
		input.close();
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

	public void displayOptions(Scanner input){
		System.out.println();
		System.out.println("1) Display Items");
		System.out.println("2) Purchase Items");
		System.out.println("3) Exit");
		customerChoice(input);
	}

	public void customerChoice(Scanner input){
		String answer = input.nextLine().trim();

		switch (answer){
			case "1":
				vendingMachine.getInventory().printInventory();
				break;
			case "2":
				customerActions(input);
				break;
			case "3":
				System.out.println("Goodbye!");
				hasEnded = true;
				break;
			case "4":
				SalesReport salesReport = new SalesReport(vendingMachine.
						getInventory().calculateItemSold(), vendingMachine.getTotalAmountSpent());
				break;
			default:
				System.out.println("Input Valid Choice");
				break;
		}
	}

	public void customerActions(Scanner input){
		System.out.println("1) Add Money");
		System.out.println("2) Select Product");
		System.out.println("3) Finish Transaction");

		String answer = input.nextLine().trim();

		switch (answer){
			case "1":
				System.out.println("How much do you want to add: ");
				String amount = input.nextLine();

				BigDecimal amountToAdd = new BigDecimal(amount);
				amountToAdd = new BigDecimal(String.format("%.2f",amountToAdd));

				if(isWholeNumber(amountToAdd)){
					vendingMachine.addBalance(amountToAdd);
					hasAddedBalance = true;
					audit.auditMoneyAdded(amountToAdd, vendingMachine.getBalance());
					System.out.println("Updated Balance: $" + vendingMachine.getBalance());
					customerActions(input);
					break;
				}else{
					System.out.println("Money added must be whole number.");
					customerActions(input);
					break;
				}

			case "2":
				if(!hasAddedBalance){
					System.out.println("First Add Funds");
					break;
				}
				System.out.println("Which product do you want to buy (EX:A1): ");
				String itemPosition = input.nextLine().replace(" ", "");
				Item itemToPurchase = vendingMachine.getInventory().getItemByPosition(itemPosition);
				if (itemToPurchase == null) {
					System.out.println("Item was not found");
				} else {
					if(vendingMachine.purchase(itemToPurchase)){
						audit.auditPurchaseMade(itemToPurchase, vendingMachine.getBalance());
						System.out.println("Updated Balance: $" + vendingMachine.getBalance());
					}
				}
				customerActions(input);
				break;
			case "3":
				BigDecimal previousBalance = vendingMachine.getBalance();
				System.out.println(vendingMachine.calculateChange());
				audit.auditCompletedTransaction(previousBalance, vendingMachine.getBalance());
				break;
			default:
				System.out.println("Input Valid Choice");
				customerActions(input);
				break;
		}

	}

	public boolean isWholeNumber(BigDecimal amountToAdd){
		return amountToAdd.remainder(new BigDecimal("1.00")).
				equals(new BigDecimal("0.00"));
	}

	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}
}
