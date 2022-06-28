import java.util.Arrays;

public class Food {
    private static int[] position;

    Food(int[][] snake) {
        genPosition(snake);
    }

    private void genPosition(int[][] available) {
        position = available[(int)(Math.random()*(available.length))];
    }

    private static int[][] getAvailablePos(int[][] snake) {
        int[][] avaliable = {};
        for (int i=0;i<GameSettings.WIDTH;i++) {
            for (int j=0;j<GameSettings.HEIGHT;j++) {
                int[] temp = {i,j};
                if (!Helper.containsIntArr(snake, temp)){
                    avaliable = Arrays.copyOf(avaliable, avaliable.length+1);
                    avaliable[avaliable.length-1] = temp;
                }
            }
        }
        return avaliable;
    }

    public void updateState(int[][] snake) {
        int [][] available = getAvailablePos(snake);
        if (!(available.length==0)){
            genPosition(available);
        } else {
            position = new int[]{-1, -1};
        }
    }

    public int[] getFood() {
        return position;
    }
}
