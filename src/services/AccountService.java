package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccountService {

	private static Scanner scanner = new Scanner(System.in);

	static void makeTransaction(Account payerAccount, Bank bank) {

		Account receiverAccount = AccessService.findAccountByEmail("Insira o email da conta para a qual você deseja transferir ", bank);

		if (receiverAccount == null)
			return;

		if (receiverAccount.equals(payerAccount)) {
			System.out.println();
			System.out.println("Você não pode transferir para a própria conta!");
			System.out.println();
			return;
		}

		System.out.print("Valor a ser transferido: ");
		Double amountToBeTransfered = scanner.nextDouble();
		scanner.nextLine();

		boolean hasSufficientBalance = checkIfYouHaveSufficientBalance(payerAccount, amountToBeTransfered);

		if (!hasSufficientBalance)
			return;

		boolean validPassword = AccessService.authenticatePassword(payerAccount);

		if (!validPassword)
			return;

		payerAccount.withdrawal(amountToBeTransfered);

		receiverAccount.deposit(amountToBeTransfered);

		System.out.println();
		System.out.println("Transferência realizada com sucesso!");
		System.out.printf("Valor transferido: R$%.2f%n", amountToBeTransfered);
		System.out.println();

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
		System.out.println("Conta encerrada com sucesso!");
		System.out.println();

	}

	static void makeWithdrawal(Account account) {
		System.out.print("Insira o valor que você quer sacar: ");
		Double amountToBeTransfered = scanner.nextDouble();
		scanner.nextLine();

		boolean hasSufficientBalance = checkIfYouHaveSufficientBalance(account, amountToBeTransfered);

		if (!hasSufficientBalance)
			return;

		boolean validPassword = AccessService.authenticatePassword(account);

		if (!validPassword)
			return;

		account.withdrawal(amountToBeTransfered);

		System.out.println();
		System.out.println("Saque realizado com sucesso!");
		System.out.println();
	}

	private static Boolean checkIfYouHaveSufficientBalance(Account account, Double amount) {

		if (account.getBalance() < amount) {
			System.out.println();
			System.out.println("Saldo insuficiente!");
			System.out.println();
			return false;
		} else {
			return true;
		}
	}

	static void makeDeposit(Account account) {
		System.out.print("Insira o valor que você quer depositar: ");
		Double value = scanner.nextDouble();
		scanner.nextLine();

		account.deposit(value);

		System.out.println();
		System.out.println("Deposito realizado com sucesso!");
		System.out.println();
	}
}
