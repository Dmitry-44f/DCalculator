public class Calculator {
    public long add(long a, long b) {
        return a + b;
    }
    public long subtract(long a, long b) {
        return a - b;
    }
    public long multiply(long a, long b) {
        return a * b;
    }
    public long divide(long a, long b) {
        if (b == 0) throw new ArithmeticException("Деление на ноль!\n");
        return a / b;
    }
    public long remains(long a, long b) {
        if (b == 0) throw new ArithmeticException("Деление на ноль!\n");
        return a % b;
    }
}