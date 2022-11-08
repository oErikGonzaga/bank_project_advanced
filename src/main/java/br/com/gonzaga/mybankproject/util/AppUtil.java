package br.com.gonzaga.mybankproject.util;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AppUtil {

    public static boolean isCpfValid(String value) {
        if (!isCpfFormatValid(value)) {
            return false;
        }

        if (invalidCpfValues().stream().anyMatch(s -> s.equals(value))) {
            return false;
        }

        try {
            int num;
            char dig10;
            char dig11;

            int i = 0;
            int sm = 0;
            int peso = 10;

            for (; i < 9; i++) {
                num = (value.charAt(i) - 48);
                sm += (num * peso);
                peso -= 1;
            }

            int r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (value.charAt(i) - 48);
                sm += (num * peso);
                peso -= 1;
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            return dig10 == value.charAt(9) && dig11 == value.charAt(10);

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCpfFormatValid(String value) {
        if (isBlank(value)) {
            return false;
        }

        return value.matches("\\d{11}");
    }

    private static List<String> invalidCpfValues() {
        return asList(m('0'), m('1'), m('2'), m('3'), m('4'), m('5'), m('6'), m('7'), m('8'), m('9'));
    }

    private static String m(char l) {
        char[] list = new char[11];
        fill(list, l);
        return new String(list);
    }
}
