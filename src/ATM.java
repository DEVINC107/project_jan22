import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class ATM {
    private static String name;
    private static int pin;
    private static double balance = 0;
    private static double savings = 0;
    private static int securityId = 0;
    private static int accountId = 0;
    private static ArrayList<String> transactions = new ArrayList<>();
    private static Scanner scan;

    private static void out(String s) {
        System.out.println(s);
    }

    private static String in(String s) {
        System.out.print(s);
        return scan.nextLine();
    }

    private static String transactionID() {
        accountId = 9998;
        accountId ++;
        int a = accountId;
        String ret = "";
        while (a < 10000) {
            a *= 10;
            if (a < 10000) {
                ret += "0";
            }
        }
        ret += accountId;
        return "A" + ret;
    }


    public static void createAccount(Scanner scan) {
        System.out.print("We don't have you on file, please enter your name: ");
        String _name = scan.nextLine();
        name = _name;
        System.out.print("Enter a 4 digit pin for this account: ");
        String _pin = scan.nextLine();
        pin = Integer.parseInt(_pin);
        boolean success = false;
        System.out.println("We need you to reenter your pin to login");
        while (!success) {
            System.out.print("Pin: ");
            String entry = scan.nextLine();
            success = Integer.parseInt(entry) == pin;
            if (!success) {
                System.out.println("Not the correct pin, try again!");
            }
        }
        System.out.println("Welcome " + name);
        mainMenu(scan);
    }
    public static void start() {
        System.out.println(transactionID());
        Scanner _scan = new Scanner(System.in);
        scan = _scan;
        createAccount(scan);
    }

    public static void mainMenu(Scanner scan) {
        boolean exit = false;
        while (!exit) {
            System.out.print("Options\n--------\n1. Withdraw money\n2. Deposit money\n3. Transfer money\n4. Account balances\n5. Transaction history\n6. Change PIN\n7. Exit ");
            String option = scan.nextLine();
            if (option.equals("1")) {
                boolean oneExit = false;
                while (!oneExit) {
                    System.out.print("How many 5s? ");
                    int fives = Integer.parseInt(scan.nextLine());
                    System.out.print("How many 20s? ");
                    int twenties = Integer.parseInt(scan.nextLine());
                    int transactionCost = twenties * 20 + fives * 5;
                    if (transactionCost > balance) {
                        transactions.add(
                                String.format(
                                        "Attempted to withdraw $%d in %d fives and %d twenties. Insufficient funds.\nID: %s",
                                        transactionCost,
                                        fives,
                                        twenties,
                                        transactionID()
                                )
                        );
                        System.out.print("Invalid amount, exit? ");
                        String answer = scan.nextLine();
                        if (answer.toLowerCase().equals("y")) {
                            oneExit = true;
                        }
                    } else {
                        transactions.add(
                                String.format(
                                        "Withdrawn $%d successfully. \nID: %s\nCHECKINGS BEFORE: %d\nCHECKINGS AFTER: %d",
                                        transactionCost,
                                        transactionID(),
                                        balance,
                                        balance - transactionCost
                                )
                        );
                        balance -= transactionCost;
                        System.out.println(String.format("Withdrawn %d successfully", transactionCost));
                        oneExit = true;
                    }
                }
            }
            if (option.equals("2")) {
                System.out.print("How much? ");
                double amount = Double.parseDouble(scan.nextLine());
                balance += amount;
                System.out.println(String.format("Successfully deposited $%.3f", amount));
                transactions.add(
                        String.format(
                                "Deposited $%.2f successfully.\nID: %s\nCHECKINGS BEFORE: %.2f\nCHECKINGS AFTER: %.2f",
                                amount,
                                transactionID(),
                                balance - amount,
                                balance
                        )
                );
            }
            if (option.equals("3")) {
                System.out.println("SAVINGS: " + savings);
                System.out.println("CHECKINGS: " + balance);
                String from = in("FROM? ");
                String to = in("TO? ");
                if (from.toLowerCase().equals("savings") && to.toLowerCase().equals("checkings")) {
                    double amount = Double.parseDouble(in("How much? "));
                    if (amount <= savings) {
                        balance += amount;
                        savings -= amount;
                        transactions.add(
                                String.format(
                                        "Moved $%.2f from savings to checkings.\nID: %s\nSAVINGS BEFORE: %.2f\nSAVINGS AFTER: %.2f\nCHECKINGS BEFORE: %.2f\n CHECKINGS AFTER: %.2f",
                                        amount,
                                        transactionID(),
                                        savings + amount,
                                        savings,
                                        balance - amount,
                                        balance
                                )
                        );
                        System.out.println(String.format("Successfully moved $%.2f from savings to checkings", amount));
                    } else {
                        out("Insufficient funds");
                    }
                }
                if (to.toLowerCase().equals("savings") && from.toLowerCase().equals("checkings")) {
                    int amount = Integer.parseInt(in("How much? "));
                    if (amount <= balance) {
                        savings += amount;
                        balance -= amount;
                        transactions.add(
                                String.format(
                                        "Moved $%.2f from checkings to savings.\nID: %s\nSAVINGS BEFORE: %.2f\nSAVINGS AFTER: %.2f\nCHECKINGS BEFORE: %.2f\n CHECKINGS AFTER: %.2f",
                                        amount,
                                        transactionID(),
                                        savings - amount,
                                        savings,
                                        balance + amount,
                                        balance
                                )
                        );
                        System.out.println(String.format("Successfully moved $%.2f from checkings to savings", amount));
                    } else {
                        out("Insufficient funds");
                    }
                }
            }
            if (option.equals("4")) {
                System.out.println("SAVINGS: " + savings);
                System.out.println("CHECKINGS: " + balance);
            }
            if (option.equals("5")) {
                System.out.println("------ TRANSACTIONS -------");
                int a = 1;
                for (int i = transactions.size() - 1; i >= 0; i --) {
                    System.out.println(a + ". " + transactions.get(i));
                }
                System.out.println("---------------------------");
            }
            if (option.equals("6")) {
                int newPin = Integer.parseInt("Enter your new four digit pin: ");
                pin = newPin;
                System.out.println("Changed pin");
            }
            if (option.equals("7")) {
                exit = true;
            }
            if (!exit) {
                String choice = in("Continue using ATM? ");
                if (choice.equals("y")) {
                    int attempt = Integer.parseInt(in("Enter pin: "));
                    if (attempt == pin) {
                        System.out.println("Pin entered successfully!");
                    } else {
                        out("You've been locked out for entering the wrong PIN");
                    }
                }
            }
        }
        System.out.println("Thanks for your patronage " + name);
    }
}
