package operations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Account;
import entities.Bank;
import entities.Customer;

public class BankOperations {

	static Scanner scanner = new Scanner(System.in);

	public static void createAccount(Bank bank) {

		System.out.println("Criação de conta:");

		Customer customer = registerCustomer();

		Account account = registerAccount(customer, bank);

		customer.setAccount(account);

		bank.addCustomer(customer);

		bank.addAccount(account);

		System.out.println();
		System.out.println("Conta criada com sucesso!");
		System.out.println();
	}
	
	public static void accessAccount(Bank bank) {
		
		System.out.print("Insira o email da conta que você quer acessar: ");
		String email = scanner.nextLine();
		
		Account account = bank.getAccountLog().stream().filter(x -> x.getCustomer().getEmail().equals(email)).findFirst().orElse(null);

		if (account == null) {
			System.out.println();
			System.out.println("Email incorreto ou conta inexistente!");
			System.out.println("Tente novamente.");
			System.out.println();

		} else {
			authenticatePassword(account);
		}
	}
	
	private static void authenticatePassword (Account account) {
		System.out.print("Digite a senha da conta: ");
		String password = scanner.nextLine();
		
		System.out.println();
		
		Boolean validPassword = password.equals(account.getPassword());
		
		if (validPassword) {
			carryOutOperationsOnTheAccount(account);
			
		} else {
			System.out.println("Senha incorreta!");
			System.out.println();
		}
	}

	private static Customer registerCustomer() {

		System.out.print("Insira o nome do titular da conta: ");
		String customerName = scanner.nextLine();

		System.out.print("Data de nascimento (DD/MM/YYYY): ");
		String birthDate = scanner.nextLine();

		System.out.print("Email: ");
		String email = scanner.nextLine();

		return new Customer(customerName, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), email);
	}

	private static Account registerAccount(Customer customer, Bank bank) {
		
		Integer number = bank.getAccountLog().size() + 1;

		System.out.print("Defina uma senha numérica de 04 dígitos: ");
		String password = scanner.nextLine();

		System.out.print("Deseja fazer um depósito inicial? s/n ");
		char hasInitialDeposit = scanner.next().charAt(0);
		hasInitialDeposit = Character.toLowerCase(hasInitialDeposit);
		scanner.nextLine();
		
		Double depositValue;

		if (hasInitialDeposit == 's') {

			System.out.print("Insira o valor do depósito: ");
			depositValue = scanner.nextDouble();
			scanner.nextLine();

			return new Account(customer, number, password, depositValue);

		} else {
			return new Account(customer, number, password);
		}

	}

	private static void carryOutOperationsOnTheAccount(Account account) {

		int operation;

		do {
			System.out.println(account);
			System.out.println();
			System.out.println("[1] Sacar dinheiro");
			System.out.println("[2] Depositar dinheiro");
			System.out.println("[3] Voltar para o menu inicial");
			operation = scanner.nextInt();
			scanner.nextLine();

			System.out.println();

			switch (operation) {
			case 1 -> makeWithdrawal(account);
			case 2 -> makeDeposit(account);
			}

		} while (operation != 3);

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
