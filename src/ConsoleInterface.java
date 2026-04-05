import java.util.Scanner;

public class ConsoleInterface implements UserInterface {
    private Scanner sc = new Scanner(System.in);
//    private Calculator calc = new Calculator();
//    private NumberSystem ns = new NumberSystem();

    public void start() {
        System.out.println("Консольный калькулятор систем счисления");
        while (true) {
            try {
                Base base = selectBase();
                if (base == null) break;

                long num1 = readNumber(base, "Введите первое число: ");
                if (num1 == Long.MIN_VALUE) break;

                Operation op = readOperation();
                if (op == null) break;

                long num2 = readNumber(base, "Введите второе число: ");
                if (num2 == Long.MIN_VALUE) break;

                long result = op.apply(num1, num2);
                printAllSystems(result);
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
    }

    private Base selectBase() {
        for (Base b: Base.values()) {
            System.out.printf("%d. %s\n", b.ordinal() + 1, b.displayName);
        }
        System.out.println("0. Выход");

        String input = requestInput("Выберите систему счисления (индекс): ");

        if (input == null || input.equals("0")) return null;

        try{
            int idx = Integer.parseInt(input) - 1;
            Base base = Base.values()[idx];
            System.out.println("Сейчас " + base.displayName);
            return base;
        } catch (Exception e) {
            throw new IllegalArgumentException("Выберите сисетму счислеия из списка!");
        }
    }

    private long readNumber(Base base, String number) {
        while (true) {
            String input = requestInput(number);
            if (input == null) {
                return Long.MIN_VALUE;
            }

            if (input.isEmpty()) {
                System.out.println("Число не может быть пустым! Введите еще раз.");
                continue;
            }

            try {
                return Long.parseLong(input, base.radix);
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка: некорректное число '%s' для системы %s!\n", input, base.displayName);
                System.out.println("Введите еще раз.");
            }
        }
    }

    private Operation readOperation() {
        while (true) {
            String oper = requestInput("Выберите операцию (+, -, *, /): ");
            if (oper == null) return null;

            for (Operation op: Operation.values()) {
                if (op.getSymbol().equals(oper)) {
                    return op;
                }
            }

            System.out.println("Ошибка: Такой операции нет! Попробуйте снова.");
        }
    }

    private String requestInput(String number) {
        System.out.print(number);
        String input = sc.nextLine().trim();
        return input.equalsIgnoreCase("q") ? null : input;
    }

    public void printAllSystems(long result) {
        System.out.println("Результат в разных системах счисления:");

        for (Base b : Base.values()) {
            System.out.println(b.displayName + ": " + b.format(result));
        }
        System.out.println();
    }
}
