import java.io.Console;
import java.util.Scanner;

public class ATM {
    private static Scanner scan;
    private static Customer customer;
    private static Account balance;
    private static Account savings;

    private static void out(String s) {
        System.out.println(s);
    }

    private static String in(String s) {
        System.out.print(s);
        return scan.nextLine();
    }


    public static void createAccount(Scanner scan) {
        System.out.print("We don't have you on file, please enter your name: ");
        String _name = scan.nextLine();
        System.out.print("Enter a 4 digit pin for this account: ");
        int _pin = Integer.parseInt(scan.nextLine());
        customer = new Customer(_name, _pin);
        balance = new Account("Checkings", 0);
        savings = new Account("Savings", 0);
        boolean success = false;
        System.out.println("We need you to reenter your pin to login");
        while (!success) {
            System.out.print("Pin: ");
            int entry = Integer.parseInt(scan.nextLine());
            success = customer.check(entry);
            if (!success) {
                System.out.println("Not the correct pin, try again!");
            }
        }
        System.out.println("Welcome " + customer.getName());
        mainMenu(scan);
    }
    public static void start() {
        Scanner _scan = new Scanner(System.in);
        scan = _scan;
        createAccount(scan);
    }

    public static void mainMenu(Scanner scan) {
        boolean exit = false;
        while (!exit) {
            ConsoleUtility.clearScreen();
            System.out.print("Options\n--------\n1. Withdraw money\n2. Deposit money\n3. Transfer money\n4. Account balances\n5. Transaction history\n6. Change PIN\n7. Exit ");
            String option = scan.nextLine();
            ConsoleUtility.clearScreen();
            if (option.equals("1")) {
                boolean oneExit = false;
                while (!oneExit) {
                    System.out.print("How many 5s? ");
                    int fives = Integer.parseInt(scan.nextLine());
                    System.out.print("How many 20s? ");
                    int twenties = Integer.parseInt(scan.nextLine());
                    int transactionCost = twenties * 20 + fives * 5;
                    if (transactionCost > balance.getMoney()) {
                        TransactionHistory.add(
                                String.format(
                                        "Attempted to withdraw $%d in %d fives and %d twenties. Insufficient funds.\nID: %s",
                                        transactionCost,
                                        fives,
                                        twenties,
                                        TransactionHistory.transactionID()
                                )
                        );
                        System.out.print("Invalid amount, exit? ");
                        String answer = scan.nextLine();
                        if (answer.toLowerCase().equals("y")) {
                            oneExit = true;
                        }
                    } else {
                        TransactionHistory.add(
                                String.format(
                                        "Withdrawn $%d successfully. \nID: %s\nCHECKINGS BEFORE: %d\nCHECKINGS AFTER: %d",
                                        transactionCost,
                                        TransactionHistory.transactionID(),
                                        balance.getMoney(),
                                        balance.getMoney() - transactionCost
                                )
                        );
                        balance.setMoney(balance.getMoney() - transactionCost);
                        System.out.println(String.format("Withdrawn %d successfully", transactionCost));
                        oneExit = true;
                    }
                }
            }
            if (option.equals("2")) {
                ConsoleUtility.clearScreen();
                System.out.print("How much? ");
                double amount = Double.parseDouble(scan.nextLine());
                balance.setMoney(balance.getMoney() + amount);
                System.out.println(String.format("Successfully deposited $%.3f", amount));
                TransactionHistory.add(
                        String.format(
                                "Deposited $%.2f successfully.\nID: %s\nCHECKINGS BEFORE: %.2f\nCHECKINGS AFTER: %.2f",
                                amount,
                                TransactionHistory.transactionID(),
                                balance.getMoney() - amount,
                                balance.getMoney()
                        )
                );
            }
            if (option.equals("3")) {
                ConsoleUtility.clearScreen();
                System.out.println("SAVINGS: " + savings.getMoney());
                System.out.println("CHECKINGS: " + balance.getMoney());
                String from = in("FROM? ");
                String to = in("TO? ");
                if (from.toLowerCase().equals("savings") && to.toLowerCase().equals("checkings")) {
                    double amount = Double.parseDouble(in("How much? "));
                    if (amount <= savings.getMoney()) {
                        balance.setMoney(balance.getMoney() + amount);
                        savings.setMoney(savings.getMoney() - amount);
                        TransactionHistory.add(
                                String.format(
                                        "Moved $%.2f from savings.getMoney() to checkings.\nID: %s\nsavings BEFORE: %.2f\nsavings AFTER: %.2f\nCHECKINGS BEFORE: %.2f\n CHECKINGS AFTER: %.2f",
                                        amount,
                                        TransactionHistory.transactionID(),
                                        savings.getMoney() + amount,
                                        savings.getMoney(),
                                        balance.getMoney() - amount,
                                        balance.getMoney()
                                )
                        );
                        System.out.println(String.format("Successfully moved $%.2f from savings to checkings", amount));
                    } else {
                        out("Insufficient funds");
                    }
                }
                if (to.toLowerCase().equals("savings") && from.toLowerCase().equals("checkings")) {
                    int amount = Integer.parseInt(in("How much? "));
                    if (amount <= balance.getMoney()) {
                        savings.setMoney(savings.getMoney() + amount);
                        balance.setMoney(balance.getMoney() - amount);
                        TransactionHistory.add(
                                String.format(
                                        "Moved $%.2f from checkings to savings.\nID: %s\nsavings BEFORE: %.2f\nsavings AFTER: %.2f\nCHECKINGS BEFORE: %.2f\n CHECKINGS AFTER: %.2f",
                                        amount,
                                        TransactionHistory.transactionID(),
                                        savings.getMoney() - amount,
                                        savings.getMoney(),
                                        balance.getMoney() + amount,
                                        balance.getMoney()
                                )
                        );
                        System.out.println(String.format("Successfully moved $%.2f from checkings to savings.getMoney()", amount));
                    } else {
                        out("Insufficient funds");
                    }
                }
            }
            if (option.equals("4")) {
                System.out.println("savings: " + savings.getMoney());
                System.out.println("CHECKINGS: " + balance.getMoney());
                TransactionHistory.add(
                        String.format(
                                "Checked savings\n%s",
                                TransactionHistory.securityTransactionId()
                        )
                );
            }
            if (option.equals("5")) {
                TransactionHistory.print();
                TransactionHistory.add(
                        String.format(
                                "Checked TransactionHistory\n%s",
                                TransactionHistory.securityTransactionId()
                        )
                );
            }
            if (option.equals("6")) {
                int newPin = Integer.parseInt(in("Enter your new four digit pin: "));
                TransactionHistory.add(
                        String.format(
                                "Changed pin from %d to %d\n%s",
                                customer.getPin(), newPin, TransactionHistory.securityTransactionId()
                        )
                );
                customer.setPin(newPin);
                System.out.println("Changed pin");
            }
            if (option.equals("7")) {
                exit = true;
            }
            if (!exit) {
                String choice = in("Continue using ATM? ");
                if (choice.equals("y")) {
                    int attempt = Integer.parseInt(in("Enter pin: "));
                    if (attempt == customer.getPin()) {
                        System.out.println("Pin entered successfully!");
                    } else {
                        out("You've been locked out for entering the wrong PIN");
                        exit = true;
                    }
                }
            }
        }
        System.out.println("Thanks for your patronage " + customer.getName());
    }
}
