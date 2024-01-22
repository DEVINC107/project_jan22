public class OpeningAnimation {
    private static int wide = 10;
    private static int tall = 3;
    public static String asterisks(int n) {
        String ret = "";
        for (int i = 0; i < n; i++ ) {
            ret += "*";
        }
        return ret;
    }
    public static String animation(int f, String text) {
        String[][] frame = new String[tall][wide];
        for (int i = 0; i < f; i ++) {
            if (i < wide) {
                frame[0][i] = "*";
                continue;
            }
            if (i <= wide + (tall - 2 - 1)) {
                frame[(i - wide) + 1][wide - 1] = "*";
                continue;
            }
            if (i < (wide + (tall - 2)) + wide) {
                frame[tall - 1][wide + wide + (tall - 2) - i - 1] = "*";
                continue;
            }
            frame[(tall - (i - (wide + wide + tall - 2)) ) - 1][0] = "*";
        }
        for (int i = 1; i <= text.length(); i ++) {
            frame[1][i] = text.substring(i - 1, i);
        }
        String ret = "";
        for (String[] y : frame) {
            for (String x : y) {
                ret += x == null ? " " : x;
            }
            ret += "\n";
        }
        return ret;
    }
    public static void start(String text) {
        for (int i = 1; i < 25; i ++) {
            ConsoleUtility.clearScreen();
            System.out.println(animation(i, text));
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println("err");
            };
        }
    }
}
