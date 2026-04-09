public class MathProcessor implements CalculatorOperations {
    private long add(long a, long b) {
        return a + b;
    }

    private long subtract(long a, long b) {
        return a - b;
    }

    private long multiply(long a, long b) {
        return a * b;
    }

    private long divide(long a, long b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return a / b;
    }

    private long mod(long a, long b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return a % b;
    }

    @Override
    public long calculate(long a, long b, String op) {
        return switch (op) {
            case "+" -> add(a, b);
            case "-" -> subtract(a, b);
            case "*" -> multiply(a, b);
            case "/" -> divide(a, b);
            case "%" -> mod(a, b);
            default -> throw new IllegalArgumentException("Неверная операция");
        };
    }
}