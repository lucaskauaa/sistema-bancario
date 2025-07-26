package util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class ValidateInput {

	public static int selectOperation(String message) {

		while (true) {
			try {
				int operation = InputReader.readInt(message);

				return operation;
			} catch (InputMismatchException e) {
				InputReader.scanner.nextLine();
				System.out.println("\n[Erro] -> Insira um valor numérico!\n");
			}
		}

	}

	public static double getAmount(String message) {

		while (true) {
			try {
				double amount = InputReader.readDouble(message);

				if (amount <= 0.0) {
					System.out.println("\n[Erro] -> Insira um valor maior que zero!\n");
					continue;
				}

				return amount;

			} catch (InputMismatchException e) {
				InputReader.scanner.nextLine();
				System.out.println("\n[Erro] -> nInsira um valor numérico válido!\n");
			}
		}

	}
	
	public static String getName(String message) {
		while (true) {
			String name = InputReader.readString(message);
			
			name = name.trim();
			
			if (!name.toLowerCase().matches("[a-zçãõâêîôûáéíóúàèìòùäëïöü.,' ]+")) {
				System.out.println("\n[Erro] -> Este campo não pode conter números ou caracteres especiais!\n");
				continue;
			}
			
			return name;
		}
	}

	public static LocalDate getBirthDate(String message) {

		LocalDate birthDate = null;

		while (true) {

			try {
				String date = InputReader.readString(message);

				birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

				return birthDate;

			} catch (DateTimeException e) {
				System.out.println("\n[Erro] -> Data inválida! Insira no padrão (dd/mm/aaaa)\n");
			}

		}

	}
	
	public static String getEmail(String message) {
		
		while (true) {
			String email = InputReader.readString(message);
			
			if (!email.toLowerCase().matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
				System.out.println("\n[Erro] -> Email invalido! Insira no padrão: nome@email.com\n");
				continue;
			}
			
			return email;
		}
		
	}
	
	public static String getPassword(String message) {
		String password;
		
		while (true) {
			password = InputReader.readString(message);
			
			if (password.length() < 4 || password.length() > 4 || !password.matches("[0123456789]+")) {
				System.out.println("\n[Erro] -> A senha deve conter 04 dígitos numéricos!\n");
			
				continue;
			}
			
			return password;
		}
	}

}
