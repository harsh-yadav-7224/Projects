import java.util.*;

// Custom Exception
class NoMoneyException extends Exception {
    NoMoneyException(String message) {
        super(message);
    }
}

public class BankAccount {

    static void withdraw(int balance, int amount) throws NoMoneyException {
        if (amount > balance) {
            throw new NoMoneyException("Insufficient Balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful");
            System.out.println("Remaining Balance: " + balance);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account balance: ");
        int balance = sc.nextInt();

        System.out.print("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        try {
            withdraw(balance, amount);
        } catch (NoMoneyException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        sc.close();
    }
}