public enum Base {
    HEX(16, "HEX (16)"),
    DEC(10, "DEC (10)"),
    OCT(8, "OCT (8)"),
    BIN(2, "BIN (2)");

    public final int radix;
    public final String displayName;

    Base(int radix, String displayName){
        this.radix = radix;
        this.displayName = displayName;
    }

    public String format(long value) {
        String s = Long.toString(value, radix);
        return (this == HEX) ? s.toUpperCase() : s;
    }
}
