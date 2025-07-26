package services;

import entities.Account;
import entities.Bank;
import menus.AccountMenu;
import util.ValidateInput;

public class AccessService {

	public static void accessAccount(Bank bank) {
		
		System.out.println("Acessar conta:\n");

		Account account = findAccountByEmail("Insira o email da conta: ", bank);

		if (account == null) {
			invalidEmailMessage();
			return;
		}
			
		boolean validPassword = authenticatePassword(account);

		if (!validPassword) {
			invalidPasswordMessage();
			return;
		}
		
		AccountMenu.carryOutOperationsOnTheAccount(account, bank);
	}

	static Account findAccountByEmail(String message, Bank bank) {
		
		String email = ValidateInput.getEmail(message);

		Account account = bank.getAccountByEmail(email);

		return account;

	}
	
	static void invalidEmailMessage() {
		System.out.println("\nEmail incorreto ou conta inexistente!");
		System.out.println("Tente novamente.\n");
	}

	static Boolean authenticatePassword(Account account) {
		
		String password = ValidateInput.getPassword("Digite a senha da conta: ");

		System.out.println();

		return  password.equals(account.getPassword());
	}
	
	static void invalidPasswordMessage() {
		System.out.println("Senha incorreta. Tente novamente.\n");
	}
}
