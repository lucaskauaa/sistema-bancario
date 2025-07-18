package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
	
	private String name;
	private final LocalDate BIRTH_DATE;
	private String email;
	private Account account;

	public Customer(String name, LocalDate birthDate, String email) {
		this.name = name;
		this.BIRTH_DATE = birthDate;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return BIRTH_DATE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		
		text.append("Nome do titular: ");
		text.append(getName() + "\n");
		text.append("Data de nascimento: ");
		text.append(getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
		text.append("Email: ");
		text.append(getEmail() + "\n");
		
		return text.toString();
	}
}
