import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;

public enum CharSet {
    HEAD_LEFT('('),
    HEAD_UP('⌢'),
    HEAD_RIGHT(')'),
    HEAD_DOWN('⌣'),
    BODY('■'),
    LEFT_UP_BODY('◥'),
    UP_RIGHT_BODY('◢'),
    RIGHT_DOWN_BODY('◣'),
    DOWN_LEFT_BODY('◤'),
    TAIL_LEFT('◀'),
    TAIL_UP('▲'),
    TAIL_RIGHT('▶'),
    TAIL_DOWN('▼'),
    FOOD('⦿'),
    BLANK(' '),
    UNKNOWN(' ');

    public final char character;

    private CharSet(char character) {
        this.character = character;
    }
}
