package services;

import entities.Account;
import entities.Bank;
import util.ValidateInput;

public class AccountService {

	static void makeWithdrawal(Account account) {
		
		Double amount = ValidateInput.getAmount("Valor do saque: ");

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

		System.out.println("Saque realizado com sucesso!");
		System.out.printf("Valor sacado: R$%.2f%n%n", amount);
	
	}

	static Boolean checkIfYouHaveSufficientBalance(Account account, Double amount) {

		return account.getBalance() >= amount;

	}

	static void insufficientBalanceMessage(Account account) {
		System.out.println("\nSaldo insuficiente!");
		System.out.printf("Saldo atual: R$%.2f%n%n", account.getBalance());
	}

	static void makeDeposit(Account account) {
		
		Double amount =  ValidateInput.getAmount("Valor do depósito: ");

		account.deposit(amount);

		account.addItemToActivityLog("Depósito | + R$" + String.format("%.2f", amount));

		System.out.println("\nDeposito realizado com sucesso!");
		System.out.printf("Valor depositado: R$%.2f%n%n", amount);
	}

	static void displayActivityLog(Account account) {

		if (account.activityLogSize() == 0) {
			System.out.println("-> A conta ainda não possui nehuma atividade!\n");

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
			System.out.printf("Saldo atual: R$%.2f%n%n", account.getBalance());

			return;
		}

		boolean validPassword = AccessService.authenticatePassword(account);

		if (!validPassword) {
			AccessService.invalidPasswordMessage();
			return;
		}

		bank.removeAccount(account);
		bank.removeCustomer(account.getCustomer());
		System.out.println("Conta encerrada com sucesso!\n");

	}
}
