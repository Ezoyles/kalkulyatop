import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(calc(input));
    }
    static String[] actions = {String.valueOf('+'), "-", "/", String.valueOf('*')};
    static String[] regexActions = {"\\+", "-", "/", "\\*"};
    public static String calc(String input) throws Exception {
        int index = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new Exception("Неизвестный оператор");
        }
        String[] data = input.split(regexActions[index]);
        Converter converter = new Converter();
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                if (a > 10 || a < 1) {
                    throw new Exception("На входе обнаружено число >10");
                }
                b = converter.romanToInt(data[1]);
                if (b > 10 || b < 1) {
                    throw new Exception("На входе обнаружено число >10");
                }

            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                if (a > 10 || a < 1) {
                    throw new Exception("На входе обнаружено число >10");
                }
                b = Integer.parseInt(data[1]);
                if (b > 10 || b < 1) {
                    throw new Exception("На входе обнаружено число >10");
                }
            }
            int result;
            switch (actions[index]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                return (converter.intToRoman(result));
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                return String.valueOf(result);
            }
        }
        throw new Exception ("Числа должны быть в одном формате");
    }
}