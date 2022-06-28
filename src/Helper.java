import java.util.Arrays;

class Helper {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void sleep(int a) {
        try {
            Thread.sleep(a);
        } catch (Exception e) {
            assert true;
        }
    }

    public static boolean containsIntArr(int[][] arr, int[] key) {
        for (int[] ints : arr) {
            if (Arrays.equals(ints, key)) {
                return true;
            }
        }
        return false;
    }

    public static int[] addIntArrayVector(int[] a, int[] b) {
        return new int[] {a[0]+b[0], a[1]+b[1]};
    }
}