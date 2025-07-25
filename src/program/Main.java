package program;

import java.util.Scanner;

import entities.Bank;
import services.AccessService;
import services.RegistrationService;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Bank bank = new Bank();
		
		int operation;
		
		do {
			System.out.println("==========================");
			System.out.println("O que você deseja fazer?");
			System.out.println();
			System.out.println("[1] Criar conta");
			System.out.println("[2] Acessar conta");
			System.out.println("[3] Encerrar programa");
			operation = scanner.nextInt();
			scanner.nextLine();
			
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

		scanner.close();
	}
}