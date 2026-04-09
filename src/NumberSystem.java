public enum NumberSystem {
    HEX(16, "HEX (16)"),
    DEC(10, "DEC (10)"),
    OCT(8, "OCT (8)"),
    BIN(2, "BIN (2)");

    private final int radix;
    private final String displayName;

    NumberSystem(int radix, String displayName){
        this.radix = radix;
        this.displayName = displayName;
    }

    public int getRadix() {
        return radix;
    }
    public String getDisplayName() {
        return displayName;
    }

    public String format(long value) {
        String s = Long.toString(value, radix);
        return (this == HEX) ? s.toUpperCase() : s;
    }
}
