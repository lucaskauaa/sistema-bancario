package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccessService {

	private static Scanner scanner = new Scanner(System.in);

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
		System.out.print(message);
		String email = scanner.nextLine();

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
		System.out.print("Digite a senha da conta: ");
		String password = scanner.nextLine();

		System.out.println();

		return  password.equals(account.getPassword());
	}
	
	static void invalidPasswordMessage() {
		System.out.println("Senha incorreta. Tente novamente.");
		System.out.println();
	}
}
