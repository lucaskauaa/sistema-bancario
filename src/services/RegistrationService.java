package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Account;
import entities.Bank;
import entities.Customer;

public class RegistrationService {

	private static Scanner scanner = new Scanner(System.in);

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

		Integer number = bank.accountListSize() + 1;

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

}
