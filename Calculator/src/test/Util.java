package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public class Util {
    private Util() {
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumber> romanNumbers = RomanNumber.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumbers.size())) {
            RomanNumber symbol = romanNumbers.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " нельзя перевести в римские");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " цифра не в диапозоне (0,4000]");
        }

        List<RomanNumber> romanNumbers = RomanNumber.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumbers.size())) {
            RomanNumber currentSymbol = romanNumbers.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static String getConsoleString () {
        String result = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            result= reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void validate(String example){
        example = example.toUpperCase();
        if (!Pattern.matches("^\\w+\\s\\W\\s\\w+$",example)) {
            throw new IllegalArgumentException("Не верный формат примера");
        }
        if (!(Pattern.matches("^\\d+\\s\\W\\s\\d+$",example) || Pattern.matches("^[IVXLCDM]+\\s\\W\\s[IVXLCDM]+$",example))) {
            throw new IllegalArgumentException("Можно использовать только 1 вид цифр в примере");
        }
        if (!Pattern.matches("^\\w+\\s[+\\-*/]\\s\\w+$",example)) {
            throw new IllegalArgumentException("Неподдерживаемая операция");
        }
    }

    private static boolean isRoman(String example){
        example = example.toUpperCase();
        return Pattern.matches("^[IVXLCDM]+\\s\\W\\s[IVXLCDM]+$", example);
    }

    public static void checkBounds(int i1, int i2){
        if (isNotBetween1and10(i1) || isNotBetween1and10(i2)) throw new IllegalArgumentException("Нельзя использовать цифры меньше 1 или больше 10");
    }

    private static boolean isNotBetween1and10(int i){
        return i < 1 || i > 10;
    }

    public static Integer[] intsFromString (String sample){
        String[] sampleParts = sample.toUpperCase().split(" ");
        Integer[] ints = new Integer[2];
        if (Util.isRoman(sample)){
            ints[0]= Util.romanToArabic(sampleParts[0]);
            ints[1] = Util.romanToArabic(sampleParts[2]);
        } else {
            ints[0] = Integer.parseInt(sampleParts[0]);
            ints[1] = Integer.parseInt(sampleParts[2]);
        }
        return ints;
    }

    public static void printResult(int result,String sample){
        if(isRoman(sample)){
            System.out.println(arabicToRoman(result));
        } else {
            System.out.println(result);
        }
    }
}
