import java.util.ArrayList;

public enum Input {
    UP('w'),
    DOWN('s'),
    LEFT('a'),
    RIGHT('d'),
    ENTER('\n'),
    NULL(-1);

    public final int code;
    private Input(int code) {
        this.code = code;
    }

    private static Input getInputFromCode(int code) {
        for (Input input: Input.values()) {
            if (code == input.code)
                return input;
        }
        return NULL;
    }

    public static Input[] getInputs(Input[] filter) {
        if (filter.length == 0)
            filter = new Input[]{UP,DOWN,LEFT,RIGHT,ENTER,NULL};

        int[] intFilter = new int[filter.length];
        for (int i=0; i<filter.length; i++) {
            intFilter[i] = filter[i].code;
        }

        ArrayList<Integer> charsIn = KeyListener.readChars(intFilter);
        Input[] inputs = new Input[charsIn.size()];
        for (int i=0; i<charsIn.size(); i++) {
            inputs[i] = getInputFromCode(charsIn.get(i));
        }

        return inputs;
    }

    public static Input getLatestInput(Input[] filter) {
        Input[] inputs = getInputs(filter);
        if (inputs.length == 0)
            return NULL;
        return inputs[inputs.length-1];
    }
}