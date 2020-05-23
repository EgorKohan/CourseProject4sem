package Valid;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidInput implements Serializable {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(String pattern) {
        String inputString;
        while (true) {
            try {
                inputString = scanner.next(pattern);
                return inputString;
            } catch (InputMismatchException e) {
                System.out.print("Неверный ввод! Повторите попытку: ");
                scanner.next();
            }
        }
    }

    public static int chooseAnAnswer(int begin, int end) {
        int answer;
        while (true) {
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                if (answer >= begin && answer <= end) return answer;
            }
            System.out.print("Неверный ввод! Повторите попытку: ");
            scanner.next();
        }
    }

    public static int inputInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();

            } else {
                scanner.next();
                System.out.print("Неверный ввод! Повторите попытку: ");
            }
        }
    }
}
