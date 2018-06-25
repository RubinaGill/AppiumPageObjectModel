package AppiumSupport;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppiumLauncher {
    private static final String[] WIN_RUNTIME = { "cmd.exe", "/C" };
    private static final String[] OS_LINUX_RUNTIME = { "/bin/bash", "-c" };

    /**
     * This method will concatenate the command with arguments passed
     * @param first
     * @param second
     * @param <T>
     * @return complete command to run windows or Unix commandLine
     */
    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Use this method to launch Appium from commandLine
     * @param isWin, if platform is Windows, values can be true or false
     * @param command, varargs for command and its arguments
     */
    public static void runProcess(boolean isWin, String... command) {
        String[] allCommand;
        try {
            if (isWin) {
                allCommand = concat(WIN_RUNTIME, command);
            } else {
                allCommand = concat(OS_LINUX_RUNTIME, command);
            }
            ProcessBuilder pb = new ProcessBuilder(allCommand);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor(5000, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
