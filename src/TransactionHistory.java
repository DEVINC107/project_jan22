import java.util.ArrayList;

public class TransactionHistory {
    private static int securityId = 0;
    private static int accountId = 0;
    private static ArrayList<String> transactions = new ArrayList<>();
    public static String transactionID() {
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

    //spaghetti
    public static String securityTransactionId() {
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
        return "B" + ret;
    }

    public static void add(String transaction) {
        transactions.add(transaction);
    }

    public static void print() {
        System.out.println("------ TRANSACTIONS -------");
        int a = 1;
        for (int i = transactions.size() - 1; i >= 0; i --) {
            System.out.println(a + ". " + transactions.get(i));
            a ++;
        }
        System.out.println("---------------------------");
    }
}
