package program;

import entities.Bank;
import menus.BankMenu;

public class Main {

	public static void main(String[] args) {
		
		Bank bank = new Bank();
		
		BankMenu.start(bank);
	}
}