package services;

import java.time.LocalDate;

import entities.Account;
import entities.Bank;
import entities.Customer;
import util.InputReader;
import util.ValidateInput;

public class RegistrationService {

	public static void createAccount(Bank bank) {

		System.out.println("===================================");
		System.out.println("Criação de conta:\n");

		Customer customer = registerCustomer();

		boolean emailAlreadyRegistered = checkIfEmailIsAlreadyRegistered(bank, customer);

		if (emailAlreadyRegistered) {
			System.out.println("\nJá existe uma conta vinculada a esse email!\n");

			return;
		}

		Account account = registerAccount(customer, bank);

		customer.setAccount(account);

		bank.addCustomer(customer);

		bank.addAccount(account);

		System.out.println("\nConta criada com sucesso!\n");
	}

	private static Customer registerCustomer() {
		
		String name = ValidateInput.getName("Insira o nome do titular da conta: ");
		
		LocalDate birthDate = ValidateInput.getBirthDate("Data de nascimento (dd/mm/aaaa): ");
		
		String email = ValidateInput.getEmail("Email: ");

		return new Customer(name, birthDate, email);
	}

	private static Boolean checkIfEmailIsAlreadyRegistered(Bank bank, Customer customer) {
		Account account = bank.getAccountByEmail(customer.getEmail());

		return account != null;
	}

	private static Account registerAccount(Customer customer, Bank bank) {

		Integer number = bank.accountListSize() + 1;
		
		String password = ValidateInput.getPassword("Defina uma senha numérica com 04 dígitos: ");
		
		while (true) {
			char hasInitialDeposit = InputReader.readChar("Deseja fazer um depósito inicial? s/n ");
			
			switch (hasInitialDeposit) {
			case 's':
				Double amount = ValidateInput.getAmount("Insira o valor do depósito: ");

				return new Account(customer, number, password, amount);
			case 'n':
				return new Account(customer, number, password);
			default :
				System.out.println("\n[Erro] -> Opção inválida!\n");
			}
		}

	}

}
