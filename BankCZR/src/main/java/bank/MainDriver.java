package bank;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import dao.CustomerDAOImp;

public class MainDriver {

	final static Logger logger = Logger.getLogger(MainDriver.class);
	HashMaps hashMaps;

	MainDriver() {
		this.hashMaps = new HashMaps();
	}

	public static void main(String[] args) {
		MainDriver main = new MainDriver();
		
		main.menu();

	}

	public void menu() {
		Scanner sc_user = new Scanner(System.in);
		
		while(true) {		
		System.out.println("Apply for an account[1], Customer sign in[2], Employee sign in[3], Admin sign in[4], Log out[5]");
		System.out.println(" Please make a selection");
		String userInput = sc_user.nextLine();
		
		switch (userInput) {
		case "1":
			System.out.println(" Single account? (Y/N)");
			String singleAcc = sc_user.nextLine();
			if (singleAcc.equals("Y") || singleAcc.equals("y")) {
				applySingleAccount();
			} else {
				applyJointAccount();
			}
			break;
		case "2":
			Customers loggedInCust = fetchCustomer();
			selectCustomerAccount(loggedInCust);
			break;
		case "3":
			Employee.verifyEmployee();
			Employee employee = new Employee();
			hashMaps = employee.getEmpMenu(hashMaps);

			break;
		case "4":
			Admin.verifyAdmin();
			Admin admin = new Admin();
			hashMaps = admin.getAdminMenu(hashMaps);
			break;
		case "5":
			System.out.println("Adios");
			return;
		default:
			System.out.println("Invalid input.");
			menu();
		}
		hashMaps.dataObject.truncateAcc();
		hashMaps.dataObject.truncateKeys();
		hashMaps.dataObject.truncateCust();
		hashMaps.dataObject.insertAllCust(hashMaps.hashMapCust);
		hashMaps.dataObject.insertAllAcc(hashMaps.hashMapAcc);
	}
		}

	public void applySingleAccount() {
		String person = "Person";
		Customers customerInfo = makeCustomer(person);
		Accounts single_acc = new Accounts(customerInfo);
		customerInfo.arrayAcc.add(single_acc.accNum);
		hashMaps.hashMapAcc.put(single_acc.accNum, single_acc);
		hashMaps.hashMapCust.put(customerInfo.id, customerInfo);
		Accounts.printAccInfo(single_acc);
		logger.info("New Application: " + "account #: " + single_acc.accNum);
		// menu();
	}

	public void applyJointAccount() {
		String person1 = "Person #1";
		String person2 = "Person #2";
		Customers customerInfo1 = loginCustomer(person1);
		Customers customerInfo2 = loginCustomer(person2);
		Accounts joint_acc = new Accounts(customerInfo1, customerInfo2);
		customerInfo1.arrayAcc.add(joint_acc.accNum);
		customerInfo2.arrayAcc.add(joint_acc.accNum);
		hashMaps.hashMapCust.put(customerInfo1.id, customerInfo1);
		hashMaps.hashMapCust.put(customerInfo2.id, customerInfo2);
		hashMaps.hashMapAcc.put(joint_acc.accNum, joint_acc);
		Accounts.printAccInfo(joint_acc);
		logger.info("New Application: " + "account #: " + joint_acc.accNum);
		// menu();
	}

	private Customers makeCustomer(String person_num) {
		Scanner sc = new Scanner(System.in);
		String userName2;
		String userPassword2;
		String userId2;
		int ssn2;
		System.out.println(person_num + ", what is your name?");
		userName2 = sc.nextLine();
		userId2 = checkId(person_num);
		System.out.println(person_num + ", password?");
		userPassword2 = sc.nextLine();
		ssn2 = checkSSN(person_num);
		Customers customerInfo2 = new Customers(userId2, userPassword2, userName2, ssn2);
		return customerInfo2;
	}

	private Customers loginCustomer(String person_num) {
		String userId;
		String userPassword;
		String existingAccount;
		Customers customerInfo = null;
		boolean correct = false;
		String verify;
		Scanner sc = new Scanner(System.in);
		while (correct == false) {
			System.out.println(person_num + ", do you have an account? (Y/N)");
			existingAccount = sc.nextLine();
			if (existingAccount.equals("N") == true) {
				customerInfo = makeCustomer(person_num);
				correct = true;
			} else if (existingAccount.equals("Y") == true) {
				System.out.println(person_num + ", what is your account username?");
				userId = sc.nextLine();
				System.out.println(person_num + ", what is your password?");
				userPassword = sc.nextLine();
				verify = verifyCustomer(userId, userPassword);
				if (verify.equals("pass") == true) {
					customerInfo = hashMaps.hashMapCust.get(userId);
					correct = true;
				} else
					correct = false;
			} else {
				System.out.println("Invalid input. Please try again.");
				correct = false;
			}
		}
		return customerInfo;
	}

	String verifyCustomer(String userId, String userPassword) {
		if (hashMaps.hashMapCust.containsKey(userId) == true) {
			Customers customer = hashMaps.hashMapCust.get(userId);
			if (customer.password.equals(userPassword) == true) {
				return "pass";
			} else {
				System.out.println(" Wrong password.");
			}
		} else {
			System.out.println(" The username does not exist.");
		}
		System.out.println(" Verification failed.");
		return "fail";
	}

	Customers fetchCustomer() {
		Scanner sc = new Scanner(System.in);
		Customers customerInfo;
		String pass;
		System.out.println(" Account ID?");
		String userInputId = sc.nextLine();
		System.out.println(" Account Password?");
		String userInputPswd = sc.nextLine();
		pass = verifyCustomer(userInputId, userInputPswd);
		if (pass.equals("pass")) {
			customerInfo = hashMaps.hashMapCust.get(userInputId);
			return customerInfo;
		} else {
			System.out.println(" Invalid input. Please try again.");
			customerInfo = fetchCustomer();
		}
		return customerInfo;
	}

	void selectCustomerAccount(Customers loggedInCust) {
		Scanner sc = new Scanner(System.in);
		if (loggedInCust.arrayAcc.size() == 1) {
			getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(0));
		} else if (loggedInCust.arrayAcc.size() == 2) {
			String accOption;
			System.out.println(" Please choose account: " + loggedInCust.arrayAcc.get(0) + "[1] or "
					+ loggedInCust.arrayAcc.get(1) + "[2]");
			accOption = sc.nextLine();
			switch (accOption) {
			case "1":
				getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(0));
				break;
			case "2":
				getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(1));
				break;
			}
		} else {
			String accOption;
			System.out.println(" Please choose account: " + loggedInCust.arrayAcc.get(0) + "[1] or "
					+ loggedInCust.arrayAcc.get(1) + "[2] or " + loggedInCust.arrayAcc.get(2) + "[3]");
			accOption = sc.nextLine();
			switch (accOption) {
			case "1":
				getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(0));
				break;
			case "2":
				getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(1));
				break;
			case "3":
				getCustomerMenu(loggedInCust, loggedInCust.arrayAcc.get(2));
				break;
			}
		}
	}

	void getCustomerMenu(Customers cust, String accNum) {
		boolean logout = false;
		Scanner sc = new Scanner(System.in);
		while (logout == false) {
			System.out.println(" View account: info[1], Deposit[2], Withdraw[3], Transfer[4], Sign Out[5]");
			System.out.println(" Please make a selection");
			String userInput = sc.nextLine();
			if (userInput.equals("1")) {
				Accounts.printAccInfo(hashMaps.hashMapAcc.get(accNum));
			} else if (userInput.equals("2")) {
				hashMaps.hashMapAcc = cust.depositCustomer(hashMaps.hashMapAcc, accNum);
			} else if (userInput.equals("3")) {
				hashMaps.hashMapAcc = cust.withdrawCustomer(hashMaps.hashMapAcc, accNum);
			} else if (userInput.equals("4")) {
				hashMaps.hashMapAcc = cust.transferCustomer(hashMaps.hashMapAcc, accNum);
			} else if (userInput.equals("5")) {
				System.out.println(" Have a great day!");
				logout = true;
				break;
			} else {
				System.out.println(" Invalid input. Please try again.");
			}
		}
	}

	public static double checkInputDouble() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" How much?");
		double amount;
		try {
			amount = sc.nextDouble();
			if (amount >= 0) {
				return amount;
			} else {
				System.out.println(" Negative amount not allowed. Try again.");
				amount = checkInputDouble();
			}
		} catch (Exception e) {
			System.out.println(" Invalid input. Numbers only. Try again.");
			amount = checkInputDouble();
		}
		return amount;
	}

	public int checkSSN(String person_num) {
		Scanner sc = new Scanner(System.in);
		int ssn;
		System.out.println(" " + person_num + ", what is your SSN?");
		try {
			ssn = sc.nextInt();
			int ssn2 = ssn * ssn;
			if (ssn > 0 && hashMaps.hashMapAcc.containsKey(Integer.toString(ssn2)) == false) {
				return ssn;
			} else {
				System.out.println(
						" This social security # already exists in the system.");
				ssn = checkSSN(person_num);
			}
		} catch (Exception e) {
			System.out.println(" Invalid input. Numbers only. Please try again.");
			ssn = checkSSN(person_num);
		}
		return ssn;
	}

	public String checkId(String person_num) {
		Scanner sc = new Scanner(System.in);
		String id;
		System.out.println(" " + person_num + ", choose username?");
		id = sc.nextLine();
		if (hashMaps.hashMapCust.containsKey(id) == false) {
			return id;
		} else {
			System.out.println(" The username already exists. Please try again.");
			id = checkId(person_num);
		}
		return id;
	}

}
