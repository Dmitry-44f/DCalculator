public enum Operation {
    ADD("+") {
        public long apply(long num1, long num2) {
            return num1 + num2;
        }
    },
    SUB("-") {
        public long apply(long num1, long num2) {
            return num1 - num2;
        }
    },
    MUL("*") {
        public long apply(long num1, long num2) {
            return num1 * num2;
        }
    },
    DIV("/") {
        public long apply(long num1, long num2) {
            if (num2 == 0) {
                throw new ArithmeticException("Деление на ноль!\n");
            }

            return num1 / num2;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract long apply(long num1, long num2);
    public String getSymbol() { return symbol; }
}
