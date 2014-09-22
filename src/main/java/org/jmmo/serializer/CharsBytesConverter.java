package org.jmmo.serializer;

import java.util.function.Function;

public class CharsBytesConverter {
    private CharsBytesConverter() {}

    public static char[] toChars(byte[] bytes) {
        if (bytes.length % 2 > 0) {
            throw new IllegalArgumentException("Bytes array must be even length");
        }

        char[] chars = new char[bytes.length/2];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (((bytes[i*2] & 0xff) << 8) + (bytes[i*2 + 1] & 0xff));
        }

        return chars;
    }

    public static byte[] toBytes(char[] chars) {
        byte[] bytes = new byte[chars.length * 2];
        for(int i = 0; i < chars.length; i++) {
            bytes[i*2] = (byte) (chars[i] >> 8);
            bytes[i*2 + 1] = (byte) chars[i];
        }

        return bytes;
    }

    public static String toString(byte[] bytes) {
        return new String(toChars(bytes));
    }

    public static byte[] fromString(String string) {
        return toBytes(string.toCharArray());
    }

    public static final Function<byte[], char[]> BYTES_TO_CHARS = CharsBytesConverter::toChars;
    public static final Function<byte[], String> BYTES_TO_STRING = CharsBytesConverter::toString;

    public static final Function<char[], byte[]> CHARS_TO_BYTES = CharsBytesConverter::toBytes;
    public static final Function<String, byte[]> STRING_TO_BYTES = CharsBytesConverter::fromString;
}
