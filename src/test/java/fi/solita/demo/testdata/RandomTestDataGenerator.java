package fi.solita.demo.testdata;

import java.util.Random;

public class RandomTestDataGenerator {

    private static final String alphanumericChars = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomString(int length) {
        return randomString(length, alphanumericChars);
    }

    public static String randomString(int length, String characters) {
        Random random = new Random();

        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return new String(text);
    }


}
