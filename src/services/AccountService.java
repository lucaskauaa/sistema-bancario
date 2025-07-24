package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccountService {

	private static Scanner scanner = new Scanner(System.in);

	static void makeTransaction(Account payerAccount, Bank bank) {

		Account receiverAccount = AccessService
				.findAccountByEmail("Insira o email da conta para a qual você deseja transferir: ", bank);

		if (receiverAccount == null)
			return;

		if (receiverAccount.equals(payerAccount)) {
			System.out.println();
			System.out.println("Você não pode transferir para a própria conta!");
			System.out.println();
			return;
		}

		System.out.print("Valor a ser transferido: ");
		Double transferAmount = scanner.nextDouble();
		scanner.nextLine();

		boolean hasSufficientBalance = checkIfYouHaveSufficientBalance(payerAccount, transferAmount);

		if (!hasSufficientBalance)
			return;

		boolean validPassword = AccessService.authenticatePassword(payerAccount);

		if (!validPassword)
			return;

		payerAccount.withdrawal(transferAmount);

		receiverAccount.deposit(transferAmount);

		payerAccount.addItemToActivityLog("Transferência para "
				+ receiverAccount.getCustomer().getEmail() + " | - R$" + String.format("%.2f", transferAmount));

		receiverAccount.addItemToActivityLog("Transferência recebida de "
				+ payerAccount.getCustomer().getEmail() + " | + R$" + String.format("%.2f", transferAmount));

		System.out.println();
		System.out.println("Transferência realizada com sucesso!");
		System.out.printf("Transferido R$%.2f para %s.%n", transferAmount, receiverAccount.getCustomer().getName());
		System.out.println();

	}

	static void makeWithdrawal(Account account) {
		System.out.print("Valor do saque: ");
		Double withdrawalAmount = scanner.nextDouble();
		scanner.nextLine();

		boolean hasSufficientBalance = checkIfYouHaveSufficientBalance(account, withdrawalAmount);

		if (!hasSufficientBalance)
			return;

		boolean validPassword = AccessService.authenticatePassword(account);

		if (!validPassword)
			return;

		account.withdrawal(withdrawalAmount);

		account.addItemToActivityLog("Saque | - R$" + String.format("%.2f", withdrawalAmount));

		System.out.println();
		System.out.println("Saque realizado com sucesso!");
		System.out.printf("Valor sacado: R$%.2f%n", withdrawalAmount);
		System.out.println();
	}

	private static Boolean checkIfYouHaveSufficientBalance(Account account, Double amount) {

		if (account.getBalance() < amount) {
			System.out.println();
			System.out.println("Saldo insuficiente!");
			System.out.printf("Saldo atual: R$%.2f%n", account.getBalance());
			System.out.println();
			return false;
		} else {
			return true;
		}
	}

	static void makeDeposit(Account account) {
		System.out.print("Valor do depósito: ");
		Double depositAmount = scanner.nextDouble();
		scanner.nextLine();

		account.deposit(depositAmount);

		account.addItemToActivityLog("Depósito | + R$" + String.format("%.2f", depositAmount));

		System.out.println();
		System.out.println("Deposito realizado com sucesso!");
		System.out.printf("Valor depositado: R$%.2f%n", depositAmount);
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

		if (!validPassword)
			return;

		bank.removeAccount(account);
		bank.removeCustomer(account.getCustomer());
		System.out.println("Conta encerrada com sucesso!");
		System.out.println();

	}
}
