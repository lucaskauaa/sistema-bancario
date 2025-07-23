package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccessService {

	private static Scanner scanner = new Scanner(System.in);

	public static void accessAccount(Bank bank) {

		Account account = findAccountByEmail("Insira o email da conta: ", bank);

		if (account == null)
			return;

		boolean validPassword = authenticatePassword(account);

		if (validPassword) {
			AccountMenu.carryOutOperationsOnTheAccount(account, bank);
		}
	}

	static Account findAccountByEmail(String message, Bank bank) {
		System.out.print(message);
		String email = scanner.nextLine();

		Account account = bank.getAccountByEmail(email);

		if (account == null) {
			System.out.println();
			System.out.println("Email incorreto ou conta inexistente!");
			System.out.println("Tente novamente.");
			System.out.println();

			return null;
		} else {
			return account;
		}

	}

	static Boolean authenticatePassword(Account account) {
		System.out.print("Digite a senha da conta: ");
		String password = scanner.nextLine();

		System.out.println();

		boolean validPassword = password.equals(account.getPassword());

		if (validPassword) {
			return true;

		} else {
			System.out.println("Senha incorreta!");
			System.out.println("Tente novamente.");
			System.out.println();
			return false;
		}
	}
}
