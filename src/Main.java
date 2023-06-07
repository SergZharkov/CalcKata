import java.util.Scanner; // импортируем библиотеку сканнера

class Main {

    public static void main(String[] args) throws Exception {
        // Создание объекта Scanner для считывания ввода пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа в формате a+b: ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));

    }

    // Метод calc осуществляет вычисления и возвращает результат в виде строки
    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        String operand;
        String result;
        boolean isRoman;

        // Разделение введенного выражения на операнды
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда или корректная математическая операция");
        operand = detectOperation(input); // Определение операции (+, -, *, /) в выражении

        if (Romania.isRoman(operands[0]) && Romania.isRoman(operands[1])) {
            // Если оба операнда являются римскими числами
            number1 = Romania.convertToArabian(operands[0]); // Преобразование римского числа в арабское число
            number2 = Romania.convertToArabian(operands[1]);
            isRoman = true; // Установка флага, что используются римские числа
        }
        else if (!Romania.isRoman(operands[0]) && !Romania.isRoman(operands[1])) {
            // Если оба операнда являются арабскими числами
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false; // Установка флага, что используются арабские числа
        }
        else {
            throw new Exception("Числа должны быть в одном формате");
        }

        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        assert operand != null;
        // Вычисление результата с помощью метода calc(int a, int b, String operand)
        int arabian = calc(number1, number2, operand);

        if (isRoman) {
            // Если использовались римские числа, преобразуем результат в римское число
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Romania.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }

        return result;
    }

    // Метод detectOperation определяет операцию (+, -, *, /) в выражении
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    // Метод calc выполняет математическую операцию над двумя числами
    static int calc(int a, int b, String operand) {

        // Вычисление результата в зависимости от операции
        return switch (operand) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

}

class Romania {
    // Массив, содержащий римские числа от 0 до 100
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    // Метод isRoman проверяет, является ли переданное значение римским числом
    public static boolean isRoman(String val) {
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Метод convertToArabian преобразует римское число в арабское число
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    // Метод convertToRoman преобразует арабское число в римское число
    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}
