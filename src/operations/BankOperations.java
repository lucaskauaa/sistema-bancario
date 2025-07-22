package operations;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class BankOperations {

	static Scanner scanner = new Scanner(System.in);

	public static void accessAccount(Bank bank) {

		Account account = findAccountByEmail("Insira o email da conta: ", bank);

		if (account != null) {
			boolean validPassword = authenticatePassword(account);

			if (validPassword) {
				carryOutOperationsOnTheAccount(account, bank);
			}
		}
	}

	private static Account findAccountByEmail(String message, Bank bank) {
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

	private static Boolean authenticatePassword(Account account) {
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

	private static void carryOutOperationsOnTheAccount(Account account, Bank bank) {

		int operation;

		boolean accountStillActive;

		do {
			System.out.println(account);
			System.out.println();
			System.out.println("[1] Transferir");
			System.out.println("[2] Sacar dinheiro");
			System.out.println("[3] Depositar dinheiro");
			System.out.println("[4] Encerrar conta");
			System.out.println("[5] Voltar para o menu inicial");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			operation = scanner.nextInt();
			scanner.nextLine();

			System.out.println();

			switch (operation) {
			case 1 -> transaction(account, bank);
			case 2 -> makeWithdrawal(account);
			case 3 -> makeDeposit(account);
			case 4 -> closeAccount(account, bank);
			case 5 -> System.out.println("Voltando para o menu inical...\n");
			default -> System.out.println("Opção inválida. Tente novamente.\n");
			}

			accountStillActive = bank.checkIfTheAccountIsActive(account);

		} while (operation != 5 && accountStillActive);

	}

	private static void transaction(Account payerAccount, Bank bank) {

		Account receiverAccount = findAccountByEmail("Insira o email da conta para a qual você deseja transferir ",
				bank);

		if (receiverAccount != null) {

			System.out.print("Valor a ser transferido: ");
			Double amountToBeTransfered = scanner.nextDouble();
			scanner.nextLine();

			if (payerAccount.getBalance() < amountToBeTransfered) {
				System.out.println("Saldo insuficiente!");
				
			} else {
				
				boolean validPassword = authenticatePassword(payerAccount);

				if (validPassword) {

					payerAccount.withdrawal(amountToBeTransfered);

					receiverAccount.deposit(amountToBeTransfered);

					System.out.println();
					System.out.println("Transferência realizada com sucesso!");
					System.out.printf("Valor transferido: R$%.2f%n", amountToBeTransfered);
					System.out.println();
				}
			}
		}
	}

	private static void closeAccount(Account account, Bank bank) {

		if (account.getBalance() != 0.0) {
			System.out.println("É necessário sacar ou transferir todo o dinheiro da conta antes de encerrá-la!");
			System.out.printf("Saldo atual: R$%.2f%n", account.getBalance());
			System.out.println();

		} else {

			boolean validPassword = authenticatePassword(account);

			if (validPassword) {
				bank.removeAccount(account);

				System.out.println("Conta encerrada com sucesso!");
				System.out.println();
			}

		}
	}

	private static void makeWithdrawal(Account account) {
		System.out.print("Insira o valor que você quer sacar: ");
		Double value = scanner.nextDouble();
		scanner.nextLine();

		account.withdrawal(value);

		System.out.println();
		System.out.println("Saque realizado com sucesso!");
		System.out.println();
	}

	private static void makeDeposit(Account account) {
		System.out.print("Insira o valor que você quer depositar: ");
		Double value = scanner.nextDouble();
		scanner.nextLine();

		account.deposit(value);

		System.out.println();
		System.out.println("Deposito realizado com sucesso!");
		System.out.println();
	}

}
