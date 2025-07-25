package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entities.Account;
import entities.Bank;
import entities.Customer;
import util.InputReader;

public class RegistrationService {

	public static void createAccount(Bank bank) {

		System.out.println("===================================");
		System.out.println("Criação de conta:");

		Customer customer = registerCustomer();

		boolean emailAlreadyRegistered = checkIfEmailIsAlreadyRegistered(bank, customer);

		if (emailAlreadyRegistered) {
			System.out.println();
			System.out.println("Já existe uma conta vinculada a esse email!");
			System.out.println();

			return;
		}

		Account account = registerAccount(customer, bank);

		customer.setAccount(account);

		bank.addCustomer(customer);

		bank.addAccount(account);

		System.out.println();
		System.out.println("Conta criada com sucesso!");
		System.out.println();
	}

	private static Customer registerCustomer() {
		
		String name = InputReader.readString("Insira o nome do titular da conta: ");
		
		String birthDate = InputReader.readString("Data de nascimento (DD/MM/YYYY): ");
		
		String email = InputReader.readString("Email: ");

		return new Customer(name, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), email);
	}

	private static Boolean checkIfEmailIsAlreadyRegistered(Bank bank, Customer customer) {
		Account account = bank.getAccountByEmail(customer.getEmail());

		return account != null;
	}

	private static Account registerAccount(Customer customer, Bank bank) {

		Integer number = bank.accountListSize() + 1;
		
		String password = InputReader.readString("Defina uma senha numérica de 04 dígitos: ");
		
		char hasInitialDeposit = InputReader.readChar("Deseja fazer um depósito inicial? s/n ");

		if (hasInitialDeposit == 's') {
			
			Double depositAmount = InputReader.readDouble("Insira o valor do depósito: ");

			return new Account(customer, number, password, depositAmount);

		} else {
			return new Account(customer, number, password);
		}

	}

}
