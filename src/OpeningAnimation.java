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
    public static String animation(int f) {
        String[][] frame = new String[tall][wide];
        for (int i = 0; i < f; i ++) {
            if (i < wide) {
                frame[0][i] = "*";
                continue;
            }
            if (i <= wide + tall - 2) {
                frame[i - wide][wide - 1] = "*";
                continue;
            }

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
    public static void start() {
        System.out.println(animation(20));
    }
}
