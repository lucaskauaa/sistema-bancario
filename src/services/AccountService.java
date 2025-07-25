package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccountService {

	private static Scanner scanner = new Scanner(System.in);

	static void makeWithdrawal(Account account) {
		System.out.print("Valor do saque: ");
		Double amount = scanner.nextDouble();
		scanner.nextLine();

		boolean hasSufficientBalance = checkIfYouHaveSufficientBalance(account, amount);

		if (!hasSufficientBalance) {
			insufficientBalanceMessage(account);
			return;
		}

		boolean validPassword = AccessService.authenticatePassword(account);

		if (!validPassword) {
			AccessService.invalidPasswordMessage();
			return;
		}

		account.withdrawal(amount);

		account.addItemToActivityLog("Saque | - R$" + String.format("%.2f", amount));

		System.out.println();
		System.out.println("Saque realizado com sucesso!");
		System.out.printf("Valor sacado: R$%.2f%n", amount);
		System.out.println();
	}

	static Boolean checkIfYouHaveSufficientBalance(Account account, Double amount) {

		return account.getBalance() >= amount;

	}

	static void insufficientBalanceMessage(Account account) {
		System.out.println();
		System.out.println("Saldo insuficiente!");
		System.out.printf("Saldo atual: R$%.2f%n", account.getBalance());
		System.out.println();
	}

	static void makeDeposit(Account account) {
		System.out.print("Valor do depósito: ");
		Double amount = scanner.nextDouble();
		scanner.nextLine();

		account.deposit(amount);

		account.addItemToActivityLog("Depósito | + R$" + String.format("%.2f", amount));

		System.out.println();
		System.out.println("Deposito realizado com sucesso!");
		System.out.printf("Valor depositado: R$%.2f%n", amount);
		System.out.println();
	}

	static void displayActivityLog(Account account) {

		if (account.activityLogSize() == 0) {
			System.out.println("-> A conta ainda não possui nehuma atividade!");
			System.out.println();

		} else {

			for (String activity : account.getActivityLog()) {
				System.out.println(activity);
			}

			System.out.println();
		}
	}

	static void closeAccount(Account account, Bank bank) {

		if (account.getBalance() != 0.0) {
			System.out.println("É necessário sacar ou transferir todo o dinheiro da conta antes de encerrá-la!");
			System.out.printf("Saldo atual: R$%.2f%n", account.getBalance());
			System.out.println();

			return;
		}

		boolean validPassword = AccessService.authenticatePassword(account);

		if (!validPassword) {
			AccessService.invalidPasswordMessage();
			return;
		}

		bank.removeAccount(account);
		bank.removeCustomer(account.getCustomer());
		System.out.println("Conta encerrada com sucesso!");
		System.out.println();

	}
}
