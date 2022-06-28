import java.lang.annotation.ElementType;

public class GameSettings {
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    public enum SLEEP_TIME {
        GAME_REFRESH(200),
        MAIN_MENU_REFRESH(50),
        GENERAL_TRANSITION(2000),
        GAME_OVER_START(500),
        GAME_OVER_FLASH(250),
        ONE_SECOND(1000);

        public final int time;

        private SLEEP_TIME(int time) {
            this.time = time;
        }
    }
}

