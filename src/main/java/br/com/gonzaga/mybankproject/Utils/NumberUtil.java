package br.com.gonzaga.mybankproject.Utils;

import java.util.Random;

public class NumberUtil {

    public static Long generateRandomNumber() {
        return (long) (new Random().nextInt((1999 - 1001) + 1) + 1001);
    }

    public static Long generateRandomNumber(int quantity){
        return 0L;
    }
}
