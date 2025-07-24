package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private Customer customer;
	private final Integer NUMBER;
	private String password;
	private double balance;

	private List<String> activityLog = new ArrayList<>();

	public Account(Customer customer, Integer number, String password, Double balance) {
		this.customer = customer;
		this.NUMBER = number;
		this.password = password;
		this.balance = balance;
	}

	public Account(Customer customer, Integer number, String password) {
		this.customer = customer;
		this.NUMBER = number;
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getNumber() {
		return NUMBER;
	}

	public Double getBalance() {
		return balance;
	}
	
	public List<String> getActivityLog() {
		return new ArrayList<>(activityLog);
	}

	public void addItemToActivityLog(String operationText) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

		activityLog.add("-> " + LocalDateTime.now().format(dateTimeFormatter) + " | " + operationText);
	}

	public int activityLogSize() {
		return activityLog.size();
	}

	public void withdrawal(Double value) {
		balance -= value;
	}

	public void deposit(Double value) {
		balance += value;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();

		text.append("================================\n");
		text.append("Conta: \n");
		text.append(customer + "\n");
		text.append("Número da conta: #");
		text.append(getNumber() + "\n");
		text.append("Saldo: R$");
		text.append(String.format("%.2f", getBalance()));

		return text.toString();

	}
}
