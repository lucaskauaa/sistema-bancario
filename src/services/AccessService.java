package services;

import entities.Account;
import entities.Bank;
import util.InputReader;

public class AccessService {

	public static void accessAccount(Bank bank) {

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
		
		String email = InputReader.readString(message);

		Account account = bank.getAccountByEmail(email);

		return account;

	}
	
	static void invalidEmailMessage() {
		System.out.println();
		System.out.println("Email incorreto ou conta inexistente!");
		System.out.println("Tente novamente.");
		System.out.println();
	}

	static Boolean authenticatePassword(Account account) {
		
		String password = InputReader.readString("Digite a senha da conta: ");

		System.out.println();

		return  password.equals(account.getPassword());
	}
	
	static void invalidPasswordMessage() {
		System.out.println("Senha incorreta. Tente novamente.");
		System.out.println();
	}
}
