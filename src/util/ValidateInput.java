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
				System.out.println("\nInsira um valor numérico!\n");
			}
		}

	}

	public static double getAmount(String message) {

		while (true) {
			try {
				double amount = InputReader.readDouble(message);

				if (amount <= 0.0) {
					System.out.println("\nInsira um valor maior que zero!\n");
					continue;
				}

				return amount;

			} catch (InputMismatchException e) {
				InputReader.scanner.nextLine();
				System.out.println("\nInsira um valor numérico válido!\n");
			}
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
				System.out.println("\nData inválida! Insira no padrão (dd/mm/aaaa)\n");
			}

		}

	}

}
