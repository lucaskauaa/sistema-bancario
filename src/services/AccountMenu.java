package services;

import java.util.Scanner;

import entities.Account;
import entities.Bank;

public class AccountMenu {

	private static Scanner scanner = new Scanner(System.in);

	static void carryOutOperationsOnTheAccount(Account account, Bank bank) {

		int operation;

		boolean accountStillActive;

		do {
			System.out.println(account);
			System.out.println();
			System.out.println("[1] Transferir");
			System.out.println("[2] Sacar dinheiro");
			System.out.println("[3] Depositar dinheiro");
			System.out.println("[4] Registro de atividades");
			System.out.println("[5] Encerrar conta");
			System.out.println();
			System.out.println("[0] Voltar para o menu inicial");
			System.out.println();
			System.out.print("Escolha uma opção: ");
			operation = scanner.nextInt();
			scanner.nextLine();

			System.out.println();

			switch (operation) {
			case 1 -> AccountService.makeTransaction(account, bank);
			case 2 -> AccountService.makeWithdrawal(account);
			case 3 -> AccountService.makeDeposit(account);
			case 4 -> AccountService.displayActivityLog(account);
			case 5 -> AccountService.closeAccount(account, bank);
			case 6 -> {
				System.out.println("Voltando para o menu inical...\n");
			}
			default -> {
				System.out.println("Opção inválida. Tente novamente.\n");
			}
			}

			accountStillActive = bank.checkIfTheAccountIsActive(account);

		} while (operation != 0 && accountStillActive);

	}
}
