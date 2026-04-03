public class NumberSystem {
    public void printAllSystems(long result) {
        System.out.println("Результат в разных системах счисления:");
        System.out.println("DEC (10): " + result);
        System.out.println("BIN (2): " + Long.toBinaryString(result));
        System.out.println("OCT (8): " + Long.toOctalString(result));
        System.out.println("HEX (16): " + Long.toHexString(result).toUpperCase() + "\n");
    }
}