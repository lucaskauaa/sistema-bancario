package services;

import entities.Account;
import entities.Bank;
import util.ValidateInput;

public class AccountMenu {

	static void carryOutOperationsOnTheAccount(Account account, Bank bank) {

		StringBuilder menuText = new StringBuilder();

		menuText.append(account + "\n");
		menuText.append("\n");
		menuText.append("[1] Transferir\n");
		menuText.append("[2] Sacar dinheiro\n");
		menuText.append("[3] Depositar dinheiro\n");
		menuText.append("[4] Registro de atividades\n");
		menuText.append("[5] Encerrar conta\n");
		menuText.append("\n");
		menuText.append("[0] Voltar para o menu inicial\n");
		menuText.append("\n");
		menuText.append("Escolha uma opção: ");

		int operation;

		boolean accountStillActive;

		do {

			operation = ValidateInput.selectOperation(menuText.toString());
			System.out.println();

			switch (operation) {
			case 1 -> TransactionService.transferToAnotherAccount(account, bank);
			case 2 -> AccountService.makeWithdrawal(account);
			case 3 -> AccountService.makeDeposit(account);
			case 4 -> AccountService.displayActivityLog(account);
			case 5 -> AccountService.closeAccount(account, bank);
			case 0 -> {
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
