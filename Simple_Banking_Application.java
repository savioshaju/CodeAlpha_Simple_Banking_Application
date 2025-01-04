import java.util.Scanner;

class deposit extends Thread {
    float amount;
    bank b;
    deposit(float amount, bank b) {
        this.amount = amount;
        this.b = b;
    }

    public void run() {
        b.depo(amount);
    }
}

class withdraw extends Thread {
    float amount;
    bank b;
    withdraw(float amount, bank b) {
        this.amount = amount;
        this.b = b;
    }
    public void run() {
        b.with(amount);
    }
}

class bank {
    float balance = 0;
    synchronized void depo(float amount) {
        System.out.println("Amount deposited: " + amount);
        balance = balance + amount;
        System.out.println("Balance after deposit: " + balance);
    }
    synchronized void with(float amount) {
        if (balance >= amount) {
            System.out.println("Amount withdrawn: " + amount);
            balance = balance - amount;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
    synchronized void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
    synchronized float Balance() {
        return balance;
    }
}

public class Simple_Banking_Application {
    public static void main(String[] args) {

        bank b = new bank();
        float amount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String userName = sc.nextLine();
        System.out.println("Welcome, " + userName + "!");
        System.out.println("Enter the initial balance:");
        b.balance = sc.nextInt();
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            int ch = sc.nextInt();
            try {
                switch (ch) {
                    case 1:
                        System.out.println("Enter the amount to deposit:");
                        amount = sc.nextFloat();
                        if (amount > 0) {
                            deposit d = new deposit(amount, b);
                            d.start();
                            d.join();
                        } else {
                            System.out.println("Deposit amount must be greater than 0.");
                        }
                        break;

                    case 2:
                        if ((b.Balance()) != 0) {
                            System.out.println("Enter the amount to withdraw:");
                            amount = sc.nextInt();
                            if (amount > 0) {
                                withdraw w = new withdraw(amount, b);
                                w.start();
                                w.join();
                            } else {
                                System.out.println("Withdrawal amount must be greater than 0.");
                            }
                        } else {
                            System.out.println("Insufficient balance for withdrawal.");
                        }
                        break;
                    case 3:
                        b.checkBalance();
                        break;

                    case 4:
                        System.out.println("Thank you "+userName+" for using the Banking Application. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please select a valid option from the menu.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
