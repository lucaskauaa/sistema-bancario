package entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Customer> customerList = new ArrayList<>();
	
	private List<Account> accountList = new ArrayList<>();

	public List<Customer> getCustomerList() {
		return customerList;
	}
	
	public List<Account> getAccountList() {
		return accountList;
	}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		customerList.remove(customer);
	}

	public void addAccount(Account account) {
		accountList.add(account);
	}
	
	public void removeAccount(Account account) {
		accountList.remove(account);
	}

//	public Double totalBalance() {
//		Double total = 0.0;
//
//		for (Account account : accountList) {
//			total += account.getBalance();
//		}
//
//		return total;
//	}
}
