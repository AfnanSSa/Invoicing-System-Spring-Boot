package com.TRA.TRA24Sprintboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class LearningProjectApplication {
	private static Scanner scanner = new Scanner(System.in);
	private static Menu mainMenu = new Menu(); //instance of Menu class
	private static Shop shop = new Shop();
	private static Item item = new Item();
	private static Invoice invoice = new Invoice();

	//hashmap to store program statistics
	private static Map<Integer, Integer> menuOptionsCounts = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(LearningProjectApplication.class, args);
		Boolean exit = Boolean.FALSE;
		while (!exit) {
			//display main menu from Menu class
			Integer option = mainMenu.displayMainMenu();

			switch (option) {
				case 1:
					handleShopSetting();
					break;
				case 2:
					handleManageItems();
					break;
				case 3:
					invoice.createInvoice();
					break;
				case 4:
					invoice.reportItemStatistics();
					break;
				case 5:
					invoice.reportAllInvoices();
					break;
				case 6:
					invoice.searchInvoices();
					break;
				case 7:
					handleProgramStatistics();
					break;
				case 8:
					exit = confirmExit();
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
			recordOptionsSelected(option);
		}
		System.out.println("Exiting Invoicing System. Bye!");
		scanner.close();
	}

	//method to handle option 1 (Shop Settings)
	private static void handleShopSetting() {
		Boolean back = Boolean.FALSE;

		while (!back) {

			Integer option = mainMenu.displayShopSettingsMenu();

			switch (option) {
				case 1:
					shop.loadData(); //implementing Load Data functionality
					break;
				case 2:
					shop.setShopName(); //implementing Set Shop Name functionality
					break;
				case 3:
					shop.setInvoiceHeader(); // Implement Set Invoice Header functionality
					break;
				case 4:
					back = Boolean.TRUE;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}

			//recording selected options
			recordOptionsSelected(option);
		}
	}

	//method to handle option 2 in Main Menu (Manage Shop Items)
	private static void handleManageItems() {
		Boolean back = Boolean.FALSE;

		while (!back) {

			Integer option = mainMenu.displayManageItemsMenu();

			switch (option) {
				case 1:
					item.addItem(); //adding item
					break;
				case 2:
					item.deleteItem(); //deleting item
					break;
				case 3:
					item.changeItemPrice(); //changing item price
					break;
				case 4:
					item.reportAllItems(); //report all items
					break;
				case 5:
					back = Boolean.TRUE;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
			//recording selected options
			recordOptionsSelected(option);
		}
	}

	//method to handle option 7 in Main Menu (Program Statistics)
	private static void handleProgramStatistics() {
		System.out.println("Displaying Program Statistics...");

		if (menuOptionsCounts.isEmpty()) {
			System.out.println("No menu options have been selected");
			return;
		}

		System.out.println("Menu Option Statistics:");
		System.out.println("----------------------------");
		for (Map.Entry<Integer, Integer> entry : menuOptionsCounts.entrySet()) {
			Integer option = entry.getKey();
			Integer count = entry.getValue();
			System.out.println("Option " + option + " had been selected " + count + " times");
		}

		System.out.println("----------------------------");

	}

	//method to record options selected
	private static void recordOptionsSelected(Integer option) {
		menuOptionsCounts.put(option, menuOptionsCounts.getOrDefault(option, 0) + 1);
	}

	//method to handle option 8 in Main Menu (Exit)
	private static Boolean confirmExit() {
		System.out.print("Are you sure you want to exit? (yes/no): ");
		String confirmOption = scanner.nextLine();
		return confirmOption.equalsIgnoreCase("yes");
	}

}
