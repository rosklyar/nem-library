package io.nem.utils;

public class ByteUtils {


    /**
     * Constant-time byte comparison. The constant time behavior eliminates side channel attacks.
     *
     * @param b One byte.
     * @param c Another byte.
     * @return 1 if b and c are equal, 0 otherwise.
     */
    public static int isEqualConstantTime(final int b, final int c) {
        int result = 0;
        final int xor = b ^ c;
        for (int i = 0; i < 8; i++) {
            result |= xor >> i;
        }

        return (result ^ 0x01) & 0x01;
    }

    /**
     * Constant-time check if byte is negative. The constant time behavior eliminates side channel attacks.
     *
     * @param b The byte to check.
     * @return 1 if the byte is negative, 0 otherwise.
     */
    public static int isNegativeConstantTime(final int b) {
        return (b >> 8) & 1;
    }

    /**
     * Creates a human readable representation of an array of bytes.
     *
     * @param bytes The bytes.
     * @return An string representation of the bytes.
     */
    public static String toString(final byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (final byte b : bytes) {
            builder.append(String.format("%02X ", (byte) (0xFF & b)));
        }

        builder.append("}");
        return builder.toString();
    }
}
