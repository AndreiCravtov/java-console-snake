import java.util.Arrays;

public class Snake {
    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    int[][] snakeChain;
    private static Direction direction;
    private static boolean snakeAlive;
    private static boolean ateFood;

    Snake() {
        snakeAlive = true;
        ateFood = false;
        snakeChain = new int[][]{{(int)(Math.random()*(GameSettings.WIDTH)), (int)(Math.random()*(GameSettings.HEIGHT))}};
        genInitDirection();
    }

    private void genInitDirection() {
        Direction[] directions = new Direction[2];
        if (snakeChain[0][0] < GameSettings.WIDTH/2) {
            directions[0] = Direction.RIGHT;
        } else {
            directions[0] = Direction.LEFT;
        }
        if (snakeChain[0][1] < GameSettings.HEIGHT/2) {
            directions[1] = Direction.DOWN;
        } else {
            directions[1] = Direction.UP;
        }
        direction = directions[(int)(Math.random()*(2))];
    }

    public void updateState(int[] food) {
        // calculate new head position
        int[] newHead;
        switch (direction) {
            case RIGHT:
                newHead = Helper.addIntArrayVector(snakeChain[0], new int[]{1, 0});
                break;
            case LEFT:
                newHead = Helper.addIntArrayVector(snakeChain[0], new int[]{-1, 0});
                break;
            case DOWN:
                newHead = Helper.addIntArrayVector(snakeChain[0], new int[]{0, 1});
                break;
            case UP:
                newHead = Helper.addIntArrayVector(snakeChain[0], new int[]{0, -1});
                break;
            default:
                newHead = new int[]{};
        }

        // create new snake
        int[][] tempSnake;
        ateFood = Arrays.equals(food, newHead);
        if (ateFood) {
            tempSnake = new int[snakeChain.length+1][2];
        } else {
            tempSnake = new int[snakeChain.length][2];
        }
        tempSnake[0] = newHead;
        System.arraycopy(snakeChain, 0, tempSnake, 1, tempSnake.length - 1);

        // collision check
        boolean collide = (
                Helper.containsIntArr(Arrays.copyOfRange(tempSnake, 1, tempSnake.length), newHead) || // self collision check
                (((newHead[0]<0) || (newHead[1]<0)) || ((newHead[0]>=GameSettings.WIDTH)  || (newHead[1]>=GameSettings.HEIGHT))) // border collision check
        );

        // commit changes
        if (collide)
            snakeAlive = false;
        else
            snakeChain = tempSnake;
    }

    public int[][] getSnake() {
        return snakeChain;
    }

    public boolean getSnakeAlive() {
        return snakeAlive;
    }

    public boolean getAteFood() {
        return ateFood;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setAteFood(boolean value) {
        ateFood=value;
    }

    public void setDirection(Input key) {
        if (!snakeAlive)
            return;

        if (key == Input.NULL)
            return;

        switch (key) {
            case RIGHT:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.LEFT;
                break;
            case DOWN:
                direction = Direction.DOWN;
                break;
            case UP:
                direction = Direction.UP;
                break;
            default:
                assert true;
        }
    }
}
