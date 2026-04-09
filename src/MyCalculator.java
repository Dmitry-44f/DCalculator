import java.util.Scanner;

public class MyCalculator {
    private final Scanner sc = new Scanner(System.in);
    private final CalculatorOperations math = new MathProcessor();

    public void start() {
        System.out.println("Консольный калькулятор систем счисления");
        while (true) {
            NumberSystem system = selectSystem();
            if (system == null) break;

            Long num1 = readNumber(system, "Введите первое число: ");
            if (num1 == null) break;

            String op = readOperation();
            if (op == null) break;

            Long num2 = readNumber(system, "Введите второе число: ");
            if (num2 == null) break;

            try {
                long result = math.calculate(num1, num2, op);
                printResult(result);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат числа");
            }
            catch (ArithmeticException e){
                System.out.println("Арифметическая ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("Вы вышли из калькулятора!");
        sc.close();
    }

    private String requestInput(String prt) {
        System.out.print(prt);
        String input = sc.nextLine().trim();

        return input.equalsIgnoreCase("q") ? null : input;
    }

    private NumberSystem selectSystem() {
        for (NumberSystem b: NumberSystem.values()) {
            System.out.printf("%d. %s\n", b.ordinal() + 1, b.getDisplayName());
        }
        System.out.println("(q - Выход)");

        String input = requestInput("Выберите систему счисления (индекс): ");
        if (input == null) return null;

        try{
            int idx = Integer.parseInt(input) - 1;
            NumberSystem system = NumberSystem.values()[idx];
            System.out.println("Сейчас " + system.getDisplayName());

            return system;
        } catch (Exception e) {
            throw new IllegalArgumentException("Выберите сисетму счислеия из списка!");
        }
    }

    private Long readNumber(NumberSystem system, String prt) {
        while (true) {
            String input = requestInput(prt);
            if (input == null) return null;

            try {
                return Long.parseLong(input, system.getRadix());
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка: некорректное число '%s' для системы %s!\n", input, system.getDisplayName());
                System.out.println("Введите еще раз.");
            }
        }
    }

    private String readOperation() {
        while (true) {
            String oper = requestInput("Выберите операцию (+, -, *, /, %): ");
            if (oper == null) return null;

            if (oper.matches("[+\\-*/%]")) {
                return oper;
            }

            System.out.println("Неверная операция!\nВведите еще раз.");
        }
    }

        public static void printResult(long result) {
        System.out.println("Результат в разных системах счисления:");

        for (NumberSystem b : NumberSystem.values()) {
            System.out.println(b.getDisplayName() + ": " + b.format(result));
        }
        System.out.println();
    }

}
