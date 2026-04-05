import java.util.Scanner;

public class ConsoleInterface implements UserInterface {
    private Scanner sc = new Scanner(System.in);
    private Calculator calc = new Calculator();
    private NumberSystem ns = new NumberSystem();

    public void start() {
        System.out.println("Консольный калькулятор систем счисления");
        while (true) {
            try {
                Integer radix = selectRadix();
                if (radix == null) break;

                String input1 = requestInput("Введите первое число: ");
                if (input1 == null) break;
                long n1 = Long.parseLong(input1, radix);

//                System.out.print("Выберите операцию (+, -, *, /): ");
                String op = readOperation();
                if (op == null) break;

                String input2 = requestInput("Введите второе число: ");
                if (input2 == null) break;
                long n2 = Long.parseLong(input2, radix);

                long res = execute(n1, n2, op);
                ns.printAllSystems(res);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private String readOperation() throws Exception {
        while (true) {
            String op = requestInput("Выберите операцию (+, -, *, /): ");
            if (op == null) return null;

            if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%")) {
                return op;
            } else {
                System.out.println("Ошибка: Такой операции нет! Попробуйте снова.");
            }
        }
    }

    private String requestInput(String number) {
        System.out.print(number);
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("q")) {
            return null;
        }
        return input;
    }

    private Integer selectRadix() throws Exception {
        System.out.println("1. HEX (16)\n2. DEC (10) \n3. OCT (8) \n4. BIN (2)\n0. Выход");
        String input = requestInput("Выберите систему счисления (индекс): ");

        if (input == null || input.equals("0")) return null;

        return switch (input) {
            case "1" -> {
                System.out.println("Сейчас HEX (16)");
                yield 16;
            }
            case "2" -> {
                System.out.println("Сейчас DEC (10)");
                yield 10;
            }
            case "3" -> {
                System.out.println("Сейчас OCT (8)");
                yield 8;
            }
            case "4" -> {
                System.out.println("Сейчас BIN (2)");
                yield 2;
            }
            default -> throw new Exception("Выберните систему счисления из списка! (индекс)\n");
        };
    }

//    private void printExit(){
//        System.out.println("Вы вышли из калькулятора!");
//    }

    private long execute(long num1, long num2, String oper) throws Exception {
        return switch (oper) {
            case "+" -> calc.add(num1, num2);
            case "-" -> calc.subtract(num1, num2);
            case "*" -> calc.multiply(num1, num2);
            case "/" -> calc.divide(num1, num2);
            case "%" -> calc.remains(num1, num2);
            default -> throw new Exception("Такой операции нет!\n");
        };
    }
}
