package emmathemartian.energylib.api;

public class NumberUtil {
    public static boolean doNumbersOverflow(int x, int y) {
        int r = x + y;
        return (((x ^ r) & (y ^ r)) < 0);
    }

    public static int addWithMax(int x, int y) {
        return doNumbersOverflow(x, y) ? Integer.MAX_VALUE : (x + y);
    }
}
