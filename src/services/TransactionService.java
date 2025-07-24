package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class TransactionService {
	static Scanner scanner = new Scanner(System.in);
	
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

		boolean hasSufficientBalance = AccountService.checkIfYouHaveSufficientBalance(payerAccount, transferAmount);

		if (!hasSufficientBalance)
			return;

		boolean validPassword = AccessService.authenticatePassword(payerAccount);

		if (!validPassword)
			return;

		payerAccount.withdrawal(transferAmount);

		receiverAccount.deposit(transferAmount);

		payerAccount.addItemToActivityLog("Transferência para " + receiverAccount.getCustomer().getEmail() + " | - R$"
				+ String.format("%.2f", transferAmount));

		receiverAccount.addItemToActivityLog("Transferência recebida de " + payerAccount.getCustomer().getEmail()
				+ " | + R$" + String.format("%.2f", transferAmount));

		System.out.println();
		System.out.println("Transferência realizada com sucesso!");
		System.out.printf("Transferido R$%.2f para %s.%n", transferAmount, receiverAccount.getCustomer().getName());
		System.out.println();

	}
}
