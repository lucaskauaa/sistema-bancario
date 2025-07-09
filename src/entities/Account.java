package entities;

public class Account {
	private final Integer NUMBER;
	private String name;
	private double balance;
	
	public Account(Integer number, String name) {
		NUMBER = number;
		this.name = name;
	}
	
	public Account(Integer number, String name, Double balance) {
		NUMBER = number;
		this.name = name;
		this.balance = balance;
	}
	
	public Integer getNUMBER() {
		return NUMBER;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}
	
	public void deposit(Double value) {
		balance += value;
	}

	public void withdrawal(Double value) {
		balance -= value;
	}
	
	public String toString() {
		return "======================\n" +
				"Conta: " + getNUMBER()+ "\n"
				+ "Nome: " + getName() + "\n" 
				+ "Saldo: R$ " + String.format("%.2f", getBalance()) + "\n"
				+ "======================";
	}
}
