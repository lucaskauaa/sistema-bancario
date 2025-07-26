package program;

import entities.Bank;
import services.AccessService;
import services.RegistrationService;
import util.ValidateInput;

public class Main {

	public static void main(String[] args) {
		
		Bank bank = new Bank();
		
		StringBuilder menuText = new StringBuilder();
		
		menuText.append("==========================\n");
		menuText.append("O que você deseja fazer?\n");
		menuText.append("\n");
		menuText.append("[1] Criar conta\n");
		menuText.append("[2] Acessar conta\n");
		menuText.append("[3] Encerrar programa\n");
		
		int operation;
		
		do {
			operation = ValidateInput.selectOperation(menuText.toString());
			System.out.println();
			
			switch (operation) {
			case 1 -> RegistrationService.createAccount(bank);
			case 2 -> AccessService.accessAccount(bank);
			case 3 -> {
				System.out.println("Programa encerrado.");
			}
			default -> {
				System.out.println("Opção inválida. Tente novamente.\n");
			}
			}
			
		} while (operation != 3);
	}
}