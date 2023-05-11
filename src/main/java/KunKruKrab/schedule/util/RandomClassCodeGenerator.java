package KunKruKrab.schedule.util;

import java.util.Random;

public class RandomClassCodeGenerator {
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int CODE_LENGTH_MIN = 6;
    private static final int CODE_LENGTH_MAX = 7;
    private static final Random random = new Random();

    public static String generateCode() {
        int codeLength = random.nextInt(CODE_LENGTH_MAX - CODE_LENGTH_MIN + 1) + CODE_LENGTH_MIN;
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }
}
