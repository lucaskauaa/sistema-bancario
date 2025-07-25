package services;

import entities.Account;
import entities.Bank;
import util.ValidateInput;

public class TransactionService {

	public static void transferToAnotherAccount(Account payerAccount, Bank bank) {
		
		System.out.println("Transferência:\n");

		Account receiverAccount = AccessService
				.findAccountByEmail("Insira o email da conta para a qual você deseja transferir: ", bank);

		if (receiverAccount == null) {
			AccessService.invalidEmailMessage();
			return;
		}

		if (receiverAccount.equals(payerAccount)) {
			System.out.println("\nVocê não pode transferir para a própria conta!\n");
			return;
		}

		Double amount = ValidateInput.getAmount("Valor a ser transferido: ");

		boolean hasSufficientBalance = AccountService.checkIfYouHaveSufficientBalance(payerAccount, amount);

		if (!hasSufficientBalance) {
			AccountService.insufficientBalanceMessage(payerAccount);
			return;
		}

		boolean validPassword = AccessService.authenticatePassword(payerAccount);

		if (!validPassword) {
			AccessService.invalidPasswordMessage();
			return;
		}

		payerAccount.withdrawal(amount);

		receiverAccount.deposit(amount);

		payerAccount.addItemToActivityLog("Transferência para " + receiverAccount.getCustomer().getEmail() + " | - R$"
				+ String.format("%.2f", amount));

		receiverAccount.addItemToActivityLog("Transferência recebida de " + payerAccount.getCustomer().getEmail()
				+ " | + R$" + String.format("%.2f", amount));

		System.out.println("Transferência realizada com sucesso!");
		System.out.printf("Transferido R$%.2f para %s%n%n", amount, receiverAccount.getCustomer().getName());

	}
}
