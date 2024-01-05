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
    public static String animation(int frame) {
        String ret = "";
        for (int i = 1; i <= frame; i ++) {
            if (i <= wide) {
                ret += "*";
                continue;
            }
            if (i > wide && i > tall) {
                continue;
            }
            if (i > wide) {
                ret += "\n";
                for (int m = 0; m < wide - 1; m ++) {
                    ret += " ";
                }
                ret += "*";
                continue;
            }
        }
        return ret;
    }
    public static void start() {
        System.out.println(animation(12));
    }
}
