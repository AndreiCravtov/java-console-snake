import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class KeyListener {

    private static String ttyConfig;

    public static void readableTerminal(boolean choice) {
        if (choice) {
            try {
                setTerminalToCBreak();
            }
            catch (IOException e) {
                System.err.println("IOException");
            }
            catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        } else {
            try {
                stty( ttyConfig.trim() );
            }
            catch (Exception e) {
                System.err.println("Exception restoring tty config");
            }
        }
    }

    public static ArrayList<Integer> readChars(int[] filter) {
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        try {
            if (System.in.available() == 0) {
                inputs.add(-1);
                return inputs;
            }

            int charIn = System.in.read();
            if (filter.length == 0) {
                inputs.add(charIn);
                while (System.in.available() != 0) {
                    charIn = System.in.read();
                    if (!(inputs.contains(charIn)))
                        inputs.add(charIn);
                }
                return inputs;
            }

            if (ArrayUtils.contains(filter, charIn))
                inputs.add(charIn);
            while (System.in.available() != 0) {
                charIn = System.in.read();
                if (ArrayUtils.contains(filter, charIn) && !(inputs.contains(charIn))) {
                    inputs.add(charIn);
                }
            }
            if (inputs.size() == 0)
                inputs.add(-1);
            return inputs;
        } catch (IOException e) {
            System.err.println("IOException");
            return inputs;
        }

    }

    private static void setTerminalToCBreak() throws IOException, InterruptedException {
        ttyConfig = stty("-g");
        stty("-icanon min 1");
        stty("-echo");
    }

    private static String stty(final String args) throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";
        return exec(new String[] {"sh","-c",cmd});
    }

    private static String exec(final String[] cmd) throws IOException, InterruptedException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();
        while ((c = in.read()) != -1) {
            bout.write(c);
        }
        in = p.getErrorStream();
        while ((c = in.read()) != -1) {
            bout.write(c);
        }
        p.waitFor();
        return bout.toString();
    }
}