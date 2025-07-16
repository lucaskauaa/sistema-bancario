package entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Customer> customerList = new ArrayList<>();
	
	private List<Account> accountLog = new ArrayList<>();

	public List<Customer> getCustomerList() {
		return customerList;
	}
	
	public List<Account> getAccountLog() {
		return accountLog;
	}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public void addAccount(Account account) {
		accountLog.add(account);
	}

	public Double totalBalance() {
		Double total = 0.0;

		for (Account account : accountLog) {
			total += account.getBalance();
		}

		return total;
	}
}
