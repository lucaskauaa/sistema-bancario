package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Account;

public class Main {
	
	private static List<Account> accountLog = new ArrayList<>();

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		
		int operation;
		
		do {
			
			System.out.println("========================");
			System.out.println("O que você deseja fazer?");
			System.out.println();
			System.out.println("[1] Criar conta");
			System.out.println("[2] Acessar conta");
			System.out.println("[3] Encerrar aplicativo");
			operation = scanner.nextInt();
			
			System.out.println();
			
			switch (operation) {
			case 1: 
				createAccount(scanner);
				break;
			case 2:
				accessAccount(scanner);
				break;
			}
			
		} while (operation != 3);

		scanner.close();
	}
	
	static void createAccount (Scanner scanner) {
		
		System.out.println("Criação de conta:");
		
		System.out.print("Insira o número da conta (0 - 1000): ");
		Integer accountNumber = scanner.nextInt();
		
		System.out.print("Insira o nome do proprietário da conta: ");
		scanner.nextLine();
		String accountOwnerName = scanner.nextLine();
		
		System.out.print("Deseja fazer um depósito inicial? s/n ");
		Character hasInitialDeposit = scanner.next().charAt(0);
		hasInitialDeposit = Character.toLowerCase(hasInitialDeposit);
		
		Account account;
		
		double depositValue;
		
		if (hasInitialDeposit == 's') {
			
			System.out.print("Insira o valor do depósito: ");
			depositValue = scanner.nextDouble();
			
			account = new Account(accountNumber, accountOwnerName, depositValue);
			
		} else {
			account = new Account(accountNumber, accountOwnerName);
		}
		
		accountLog.add(account);
		
		System.out.println();
		System.out.println("Conta criada com sucesso!");
		System.out.println();
	}
	
	static void accessAccount(Scanner scanner) {
		
		System.out.print("Insira o número da conta que você quer acessar: ");
		int accountNumber = scanner.nextInt();
		System.out.println();
		
		Account account = accountLog.stream().filter(x -> x.getNUMBER() == accountNumber).findFirst().orElse(null);
		
		if (account == null) {
			System.out.println("Conta inexistente!");
			System.out.println();
			
		} else {
			carryOutOperationsOnTheAccount(scanner, account);
			
		}
	}
	
	static void carryOutOperationsOnTheAccount (Scanner scanner, Account account) {
		
		int operation;
		
		do  {
			
			System.out.println(account);
			System.out.println();
			System.out.println("[1] Sacar dinheiro");
			System.out.println("[2] Depositar dinheiro");
			System.out.println("[3] Voltar para o menu inicial");
			operation = scanner.nextInt();
			
			System.out.println();
			
			switch (operation) {
			case 1: 
				makeWithdrawal(account, scanner);
				break;
				
			case 2:
				makeDeposit(account, scanner);
				break;
			}
			
		} while (operation != 3);
		
	}
	
	static void makeWithdrawal (Account account, Scanner scanner) {
		System.out.print("Insira o valor que você quer sacar: ");
		Double value = scanner.nextDouble();
		
		account.withdrawal(value);
		
		System.out.println();
		System.out.println("Saque realizado com sucesso!");
		System.out.println();
	}
	
	static void makeDeposit(Account account, Scanner scanner) {
		System.out.print("Insira o valor que você quer depositar: ");
		Double value = scanner.nextDouble();
		
		account.deposit(value);
		
		System.out.println();
		System.out.println("Deposito realizado com sucesso!");
		System.out.println();
	}
	
}