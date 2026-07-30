package com.ammarkanani.banking_app.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random RANDOM = new Random();

    public static String generate() {

        long number = 1000000000L
                + (long)(RANDOM.nextDouble() * 9000000000L);

        return "PK" + number;
    }

}
