import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // Проверка формата введенных чисел и их сопоставимость true - арабские false - римские
    public static boolean checkInputNumberFormat(String input) {
        Pattern pattern_digit_first = Pattern.compile("[1-9]");
        Pattern pattern_digit_last = Pattern.compile("[0-9]");
        Matcher matcher_digit_first = pattern_digit_first.matcher(input.substring(0,1));
        Matcher matcher_digit_last = pattern_digit_last.matcher(input.substring(input.length() - 1));
        Pattern pattern_roman_first_and_last = Pattern.compile("[IVX]");
        Matcher matcher_roman_first = pattern_roman_first_and_last.matcher(input.substring(0,1));
        Matcher matcher_roman_last = pattern_roman_first_and_last.matcher(input.substring(input.length() - 1));
        if (matcher_digit_first.matches() && matcher_digit_last.matches() || matcher_roman_first.matches() && matcher_roman_last.matches()) {
            return true;
        } else return false;
    }

    public static int romanToInt(String str) {
        int result = 0;
        int[] decimal = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < decimal.length; i++ ) {
            while (str.indexOf(roman[i]) == 0) {
                result += decimal[i];
                str = str.substring(roman[i].length());
            }
        }
        return result;
    }

    public static String intToRoman(int number) {
        TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();
        arabianKeyMap.put(1000, "M");
        arabianKeyMap.put(900, "CM");
        arabianKeyMap.put(500, "D");
        arabianKeyMap.put(400, "CD");
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return roman;
    }

    public static String calc(String input) {
        Pattern pattern_digit_first = Pattern.compile("[1-9]");
        Matcher matcher_digit_first = pattern_digit_first.matcher(input.substring(0,1));
        Pattern pattern_roman_first = Pattern.compile("[IVX]");
        Matcher matcher_roman_first = pattern_roman_first.matcher(input.substring(0,1));

        if (checkInputNumberFormat(input)) {
            if (matcher_digit_first.matches() && input.length() >= 5 && input.length() <= 7) {
                System.out.println("Работаю с арабскими числами");
                if (input.length() == 5) {
                    if (input.charAt(2) == '+' || input.charAt(2) == '-' || input.charAt(2) == '*' || input.charAt(2) == '/') {
                        int a = Integer.parseInt(input.substring(0,1));
                        int b = Integer.parseInt(input.substring(4));
                        if (input.charAt(2) == '+') {
                            return String.valueOf(a+b);
                        } else if (input.charAt(2) == '-') {
                            return String.valueOf(a-b);
                        } else if (input.charAt(2) == '*') {
                            return String.valueOf(a*b);
                        } else if (input.charAt(2) == '/') {
                            return String.valueOf(a/b);
                        }
                    } else return "Неверный математический оператор на формат длина 5";

                } else if (input.length() == 6) {
                    if (input.charAt(1) == '0') {
                        if (input.charAt(3) == '+' || input.charAt(3) == '-' || input.charAt(3) == '*' || input.charAt(3) == '/') {
                            int a = Integer.parseInt(input.substring(0,2));
                            int b = Integer.parseInt(input.substring(5));
                            if (input.charAt(3) == '+') {
                                return String.valueOf(a+b);
                            } else if (input.charAt(3) == '-') {
                                return String.valueOf(a-b);
                            } else if (input.charAt(3) == '*') {
                                return String.valueOf(a*b);
                            } else if (input.charAt(3) == '/') {
                                return String.valueOf(a/b);
                            }
                        } else return "Неверный математический оператор на формат длина 6 и 10 первое число";
                    } else if (input.charAt(5) == '0') {
                        if (input.charAt(2) == '+' || input.charAt(2) == '-' || input.charAt(2) == '*' || input.charAt(2) == '/') {
                            int a = Integer.parseInt(input.substring(0,1));
                            int b = Integer.parseInt(input.substring(4));
                            if (input.charAt(2) == '+') {
                                return String.valueOf(a+b);
                            } else if (input.charAt(2) == '-') {
                                return String.valueOf(a-b);
                            } else if (input.charAt(2) == '*') {
                                return String.valueOf(a*b);
                            } else if (input.charAt(2) == '/') {
                                return String.valueOf(a/b);
                            }
                        } else return "Неверный математический оператор на формат длина 6 и 10 второе число";
                    }
                } else if (input.length() == 7) {
                    if (input.charAt(3) == '+' || input.charAt(3) == '-' || input.charAt(3) == '*' || input.charAt(3) == '/') {
                        int a = Integer.parseInt(input.substring(0,2));
                        int b = Integer.parseInt(input.substring(5));
                        if (a <= 10 && b <= 10) {
                            if (input.charAt(3) == '+') {
                                return String.valueOf(a+b);
                            } else if (input.charAt(3) == '-') {
                                return String.valueOf(a-b);
                            } else if (input.charAt(3) == '*') {
                                return String.valueOf(a*b);
                            } else if (input.charAt(3) == '/') {
                                return String.valueOf(a/b);
                            }
                        } else return "Введенные числа > 10";
                    } else return "Неверный формат";
                }
                return "Неверный формат 2";
            } else if (matcher_roman_first.matches() && input.length() >= 5 && input.length() <= 11) {
                String[] roma = input.split(" ");
                int a = romanToInt(roma[0]);
                int b = romanToInt(roma[2]);
                if (roma[1].equals("+")) {
                    return intToRoman(a+b);
                } else if (roma[1].equals("-")) {
                    if (a-b >= 1) {
                        return intToRoman(a-b);
                    } else return "В римских цифрах нельзя выразить значение <1";
                } else if (roma[1].equals("*")) {
                    return intToRoman(a*b);
                } else if (roma[1].equals("/")) {
                    return intToRoman(a/b);
                }
            } else return "Неверный формат";
        } else return "Неверный формат";
        return "Неверный формат";
    }

    public static void main (String [] args) {
        System.out.println("Введите выражение для расчета в виде 1 + 1 или X - V");
        Scanner console = new Scanner(System.in);
        System.out.println("Выполняю вычисления ...");
        System.out.println(calc(console.nextLine()));
    }
}
