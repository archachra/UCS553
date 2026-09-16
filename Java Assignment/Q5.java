import java.util.Scanner;
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
class BankAccount {
    private int accountNumber;
    private double balance;
    BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be greater than zero"
            );
        }
        balance += amount;
        System.out.println("Deposit successful");
    }
    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than zero"
            );
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance"
            );
        }
        balance -= amount;
        System.out.println("Withdrawal successful");
    }
    public double getBalance() {
        return balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
}
public class Q5 {

    static BankAccount findAccount(
            BankAccount account, int accountNumber)
            throws AccountNotFoundException {

        if (account.getAccountNumber() != accountNumber) {
            throw new AccountNotFoundException(
                "Account not found"
            );
        }

        return account;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount(101, 5000);

        try {

            System.out.print("Enter account number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            BankAccount foundAccount =
                    findAccount(account, accNo);

            System.out.print("Enter deposit amount: ");
            double deposit = Double.parseDouble(sc.nextLine());

            foundAccount.deposit(deposit);   
               System.out.print("Enter withdrawal amount: ");
            double withdrawal =
                    Double.parseDouble(sc.nextLine());
            foundAccount.withdraw(withdrawal);
            System.out.println(
                "Current Balance = " +
                foundAccount.getBalance()
            );
        }
           catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input");
        }
          catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
           catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
           catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}