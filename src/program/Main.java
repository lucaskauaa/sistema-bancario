package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Account;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Account> accountLog = new ArrayList<>();
		
		char menu = '0';
		
		while (menu != '3') {
			
			System.out.println("========================");
			System.out.println("O que você deseja fazer?");
			System.out.println("");
			System.out.println("[1] Criar conta");
			System.out.println("[2] Acessar conta");
			System.out.println("[3] Encerrar aplicativo");
			menu = sc.next().charAt(0);
			System.out.println("");
			
			if (menu == '1') {
				System.out.println("Criação de conta:");
				System.out.print("Insira o número da conta (0 - 1000): ");
				Integer accountNumber = sc.nextInt();
				
				System.out.print("Insira o nome do proprietário da conta: ");
				sc.nextLine();
				String accountOwnerName = sc.nextLine();
				
				System.out.print("Deseja fazer um depósito inicial? s/n ");
				Character initialDeposit = sc.next().charAt(0);
				
				Account account;
				
				double depositValue = 0.0;
				
				if (Character.toUpperCase(initialDeposit) == 'S') {
					
					System.out.print("Insira o valor do depósito: ");
					depositValue = sc.nextDouble();
					
					account = new Account(accountNumber, accountOwnerName, depositValue);
					
				} else {
					account = new Account(accountNumber, accountOwnerName);
				}
				
				accountLog.add(account);
				System.out.println();
				System.out.println("Conta criada com sucesso!");
				System.out.println();
				
			} else if (menu == '2'){
				
				System.out.print("Insira o número da conta que você quer acessar: ");
				Integer accountNumber = sc.nextInt();
				System.out.println();
				
				Account account = accountLog.stream().filter(x -> x.getNUMBER().equals(accountNumber)).findFirst().orElse(null);
				
				if (account == null) {
					System.out.println("Conta inexistente!");
					System.out.println();
					
				} else {
					
					int operation = 0;
					
					while (operation != 3) {
						
						System.out.println(account);
						System.out.println();
						System.out.println("[1] Sacar dinheiro");
						System.out.println("[2] Depositar dinheiro");
						System.out.println("[3] Voltar para o menu inicial");
						operation = sc.nextInt();
						
						Double value;
						
						switch (operation) {
						case 1: 
							System.out.print("Insira o valor que você quer sacar: ");
							value = sc.nextDouble();
							account.withdrawal(value);
							System.out.println("Saque realizado com sucesso!");
							break;
						case 2:
							System.out.print("Insira o valor que você quer depositar: ");
							value = sc.nextDouble();
							account.deposit(value);
							System.out.println("Deposito realizado com sucesso!");
							break;
						default:
							break;
						}
					}
				}
			}
		}

		sc.close();
	}

}