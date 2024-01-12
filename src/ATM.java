import java.util.Scanner;
public class ATM {
    private static String name;
    private static int pin;
    private static int balance = 0;

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
    }
    public static void start() {
        Scanner scan = new Scanner(System.in);
        createAccount(scan);
    }

    public static void mainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.print("Options\n--------\n1. See balance\n2. Deposit money\n3. Withdraw money\n4. Transfer from checkings to savings\n5. Transfer from savings to checkings");

        }
    }
}
