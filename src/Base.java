public enum Base {
    HEX(16, "HEX (16)"),
    DEC(10, "DEC (10)"),
    OCT(10, "OCT (8)"),
    BIN(10, "BIN (2)");

    public final int radix;
    public final String displayName;

    Base(int radix, String displayName){
        this.radix = radix;
        this.displayName = displayName;
    }
}
