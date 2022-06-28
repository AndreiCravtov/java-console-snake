import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // init readable terminal
        KeyListener.readableTerminal(true);

        // init high-score
        int highScore = 0;

        welcome(); // welcome on startup
        while (true) { // begin main loop
            int option = mainMenu();
            if (option == 0) {
                countdown();
                int tempScore = gameLoop(highScore);
                if (tempScore > highScore)
                    highScore = tempScore;
            } else if (option == 1)
                highScore = 0;
            else
                break;
        }

        // stop readable terminal
        KeyListener.readableTerminal(false);
    }

    private static void welcome() {
        String fullPrint;

        Helper.clear();
        fullPrint ="⌜― ― ― ― ―⌝\n";
        fullPrint+="|         |\n";
        fullPrint+="|  HELLO  |\n";
        fullPrint+="|         |\n";
        fullPrint+="⌞― ― ― ― ―⌟\n";
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);

        Helper.clear();
        fullPrint ="⌜― ― ― ― ― ― ― ― ― ― ―⌝\n";
        fullPrint+="|                     |\n";
        fullPrint+="|   WELCOME TO SNAKE  |\n";
        fullPrint+="|                     |\n";
        fullPrint+="⌞― ― ― ― ― ― ― ― ― ― ―⌟\n";
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);

        Helper.clear();
        fullPrint ="⌜― ― ― ― ― ―⌝\n";
        fullPrint+="|           |\n";
        fullPrint+="|     BY    |\n";
        fullPrint+="|   ANDREI  |\n";
        fullPrint+="|           |\n";
        fullPrint+="⌞― ― ― ― ― ―⌟\n";
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);
    }

    private static int mainMenu() {
        boolean[] optionSelector = {true, false, false};
        int choice = -1;
        String fullPrint = "";

        while (choice == -1) {
            Helper.clear();
            fullPrint ="⌜― ― ― ― ― ― ― ― ― ― ― ― ― ― ―⌝\n";
            fullPrint+="|                             |\n";
            fullPrint+="|                             |\n";
            fullPrint+="|    Main Menu:               |\n";
            fullPrint+="|                             |\n";
            fullPrint+=String.format("|        Start New Game %s     |\n", optionSelector[0] ? '◀' : ' ');
            fullPrint+=String.format("|        Reset Highscore %s    |\n", optionSelector[1] ? '◀' : ' ');
            fullPrint+=String.format("|        Quit %s               |\n", optionSelector[2] ? '◀' : ' ');
            fullPrint+="|                             |\n";
            fullPrint+="|                             |\n";
            fullPrint+="⌞― ― ― ― ― ― ― ― ― ― ― ― ― ― ―⌟\n";
            System.out.print(fullPrint);

            while (true) {
                Input input = Input.getLatestInput(new Input[]{Input.UP, Input.DOWN, Input.ENTER});
                if (input == Input.NULL) {
                    Helper.sleep(GameSettings.SLEEP_TIME.MAIN_MENU_REFRESH.time);
                    continue;
                }

                int selectedOptionPosition = ArrayUtils.indexOf(optionSelector, true);
                if (input == Input.UP && !(optionSelector[0])) {
                    optionSelector[selectedOptionPosition] = false;
                    optionSelector[selectedOptionPosition-1] = true;
                } else if (input == Input.DOWN && !(optionSelector[optionSelector.length - 1])) {
                    optionSelector[selectedOptionPosition] = false;
                    optionSelector[selectedOptionPosition+1] = true;
                } else if (input ==  Input.ENTER)
                    choice = selectedOptionPosition;
                break;
            }
        }

        if (choice == 1) {
            Helper.clear();
            fullPrint ="⌜― ― ― ― ― ― ― ― ― ― ― ― ― ― ―⌝\n";
            fullPrint+="|                             |\n";
            fullPrint+="|  RESET HIGHSCORE COMPLETE!  |\n";
            fullPrint+="|                             |\n";
            fullPrint+="⌞― ― ― ― ― ― ― ― ― ― ― ― ― ― ―⌟\n";
            System.out.print(fullPrint);
            Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);
        } else if (choice == 2) {
            Helper.clear();
            fullPrint ="⌜― ― ― ― ― ―⌝\n";
            fullPrint+="|           |\n";
            fullPrint+="|  GOODBYE  |\n";
            fullPrint+="|           |\n";
            fullPrint+="⌞― ― ― ― ― ―⌟\n";
            System.out.print(fullPrint);
            Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);
            Helper.clear();
        }

        return choice;
    }

    private static void countdown() {
        String fullPrint;
        for (int i=3; i>0; i--) {
            Helper.clear();
            fullPrint = "⌜― ― ― ― ―⌝\n";
            fullPrint+="|         |\n";
            fullPrint+=String.format("|    %s    |\n", i);
            fullPrint+="|         |\n";
            fullPrint+="⌞― ― ― ― ―⌟\n";
            System.out.print(fullPrint);
            Helper.sleep(GameSettings.SLEEP_TIME.ONE_SECOND.time);
        }
        Helper.clear();
        fullPrint = "⌜― ― ― ― ―⌝\n";
        fullPrint+="|         |\n";
        fullPrint+="|  START  |\n";
        fullPrint+="|         |\n";
        fullPrint+="⌞― ― ― ― ―⌟\n";
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.ONE_SECOND.time);
    }

    private static int gameLoop(int highScore) {
        int score = 0;
        Snake snake = new Snake();
        Food food  = new Food(snake.getSnake());

        // start game loop
        while (snake.getSnakeAlive()) {
            // new input
            snake.setDirection(Input.getLatestInput(new Input[]{Input.UP, Input.DOWN, Input.RIGHT, Input.LEFT}));

            // update snake and food
            snake.updateState(food.getFood());
            if (snake.getAteFood()){
                food.updateState(snake.getSnake());
                snake.setAteFood(false);
                score++;
            }

            // draw on screen
            displayImage(renderImage(snake.getSnake(), food.getFood(), snake.getDirection()));
            System.out.printf("SCORE: %s%n", score);

            // sleep
            Helper.sleep(GameSettings.SLEEP_TIME.GAME_REFRESH.time);
        }

        gameoverSequence(snake, food, score, highScore);
        return score;
    }

    private static void gameoverSequence(Snake snake, Food food, int score, int highScore) {
        // flash the ended game screen
        Helper.sleep(GameSettings.SLEEP_TIME.GAME_OVER_START.time);
        boolean display = false;
        for (int i=0; i<5;i++) {
            if (display) {
                displayImage(renderImage(snake.getSnake(), food.getFood(), snake.getDirection()));
                System.out.printf("SCORE: %s%n", score);
                display=false;
            } else {
                Helper.clear();
                display=true;
            }
            Helper.sleep(GameSettings.SLEEP_TIME.GAME_OVER_FLASH.time);
        }

        // display game over sequence
        StringBuilder fullPrint = new StringBuilder();

        Helper.clear();
        fullPrint.append("⌜― ― ― ― ― ― ―⌝\n");
        fullPrint.append("|             |\n");
        fullPrint.append("|  GAME OVER  |\n");
        fullPrint.append("|             |\n");
        fullPrint.append("⌞― ― ― ― ― ― ―⌟\n");
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);

        Helper.clear();
        fullPrint.setLength(0);
        fullPrint.append("⌜― ― ― ― ― ―⌝\n");
        fullPrint.append("|           |\n");
        fullPrint.append("|   SCORE   |\n");
        fullPrint.append("|           |\n");
        if (String.valueOf(score).length()%2==0) {
            int right = String.valueOf(score).length()/2;
            int left = right-1;
            fullPrint.append("|");
            for (int i=0;i<(5-left);i++) {
                fullPrint.append(" ");
            }
            fullPrint.append(score);
            for (int i=0;i<(5-right);i++) {
                fullPrint.append(" ");
            }
            fullPrint.append("|\n");
        } else {
            int right = String.valueOf(score).length()/2;
            int left = right;
            fullPrint.append("|");
            for (int i=0;i<(5-left);i++) {
                fullPrint.append(" ");
            }
            fullPrint.append(score);
            for (int i=0;i<(5-right);i++) {
                fullPrint.append(" ");
            }
            fullPrint.append("|\n");
        }
        fullPrint.append("|           |\n");
        fullPrint.append("⌞― ― ― ― ― ―⌟\n");
        System.out.print(fullPrint);
        Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);

        if (score > highScore) {
            Helper.clear();
            fullPrint.setLength(0);
            fullPrint.append("⌜― ― ― ― ― ― ― ― ―⌝\n");
            fullPrint.append("|                 |\n");
            fullPrint.append("|  NEW HIGHSCORE  |\n");
            fullPrint.append("|                 |\n");
            fullPrint.append("⌞― ― ― ― ― ― ― ― ―⌟\n");
            System.out.print(fullPrint);
            Helper.sleep(GameSettings.SLEEP_TIME.GENERAL_TRANSITION.time);
        }
    }

    private static CharSet[] renderImage(int[][] snake, int[] food, Snake.Direction direction) {
        CharSet[] image  = new CharSet[GameSettings.WIDTH * GameSettings.HEIGHT];

        for (int i=0; i<snake.length; i++) {
            if (i==0) { // render head
                if (direction == Snake.Direction.UP) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.HEAD_UP;
                } else if (direction == Snake.Direction.DOWN) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.HEAD_DOWN;
                } else if (direction == Snake.Direction.LEFT) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.HEAD_LEFT;
                } else if (direction == Snake.Direction.RIGHT) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.HEAD_RIGHT;
                }
            } else if (i==(snake.length-1)) { // render tail
                if ((snake[i][0]==snake[i-1][0])&&(snake[i][1]<snake[i-1][1])) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.TAIL_UP;
                } else if ((snake[i][0]==snake[i-1][0])&&(snake[i][1]>snake[i-1][1])) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.TAIL_DOWN;
                } else if ((snake[i][0]<snake[i-1][0])&&(snake[i][1]==snake[i-1][1])) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.TAIL_LEFT;
                } else if ((snake[i][0]>snake[i-1][0])&&(snake[i][1]==snake[i-1][1])) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.TAIL_RIGHT;
                }
            } else { // render body
                if (
                        ((snake[i-1][0]<snake[i][0])&&(snake[i][0]<snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]==snake[i+1][1])) ||
                        ((snake[i-1][0]>snake[i][0])&&(snake[i][0]>snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]==snake[i+1][1])) ||
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]<snake[i][1])&&(snake[i][1]<snake[i+1][1])) ||
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]>snake[i][1])&&(snake[i][1]>snake[i+1][1]))
                ) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.BODY;
                } else if (
                        ((snake[i-1][0]<snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]>snake[i+1][1])) ||
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]>snake[i+1][0])&&(snake[i-1][1]<snake[i][1])&&(snake[i][1]==snake[i+1][1]))
                ) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.DOWN_LEFT_BODY;
                } else if (
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]<snake[i+1][0])&&(snake[i-1][1]>snake[i][1])&&(snake[i][1]==snake[i+1][1])) ||
                        ((snake[i-1][0]>snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]<snake[i+1][1]))
                ) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.UP_RIGHT_BODY;
                } else if (
                        ((snake[i-1][0]>snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]>snake[i+1][1])) ||
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]<snake[i+1][0])&&(snake[i-1][1]<snake[i][1])&&(snake[i][1]==snake[i+1][1]))
                ) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.LEFT_UP_BODY;
                } else if (
                        ((snake[i-1][0]<snake[i][0])&&(snake[i][0]==snake[i+1][0])&&(snake[i-1][1]==snake[i][1])&&(snake[i][1]<snake[i+1][1])) ||
                        ((snake[i-1][0]==snake[i][0])&&(snake[i][0]>snake[i+1][0])&&(snake[i-1][1]>snake[i][1])&&(snake[i][1]==snake[i+1][1]))
                ) {
                    image[GameSettings.WIDTH * snake[i][1] + snake[i][0]] = CharSet.RIGHT_DOWN_BODY;
                }
            }
        }

        if (!Arrays.equals(food, new int[]{-1, -1})) { // render food
            image[GameSettings.WIDTH * food[1] + food[0]] = CharSet.FOOD;
        }

        for (int i=0; i<image.length; i++) {
            if (image[i] == null) {
                image[i] = CharSet.BLANK;
            }
        }

        return image;
    }

    private static void displayImage(CharSet[] image) {
        Helper.clear();
        StringBuilder fullPrint = new StringBuilder();

        // top line
        fullPrint.append("⌜― ");
        for (int i=0; i<GameSettings.WIDTH-1; i++) {
            fullPrint.append("― ");
        }
        fullPrint.append("―⌝\n");

        // body
        StringBuilder line = new StringBuilder();
        for (int i=0; i<GameSettings.HEIGHT; i++) {
            for (int j=0; j<GameSettings.WIDTH; j++) {
                line.append(image[GameSettings.HEIGHT * i + j].character).append(" ");
            }
            fullPrint.append("| ").append(line).append("|\n");
            line.setLength(0);
        }

        // bottom line
        fullPrint.append("⌞― ");
        for (int i=0; i<GameSettings.WIDTH-1; i++) {
            fullPrint.append("― ");
        }
        fullPrint.append("―⌟\n");

        // display final result
        System.out.print(fullPrint);
    }
}
