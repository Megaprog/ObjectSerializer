package org.jmmo.serializer;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CharsBytesConverterTest {

    @Test
    public void testToBytes() throws Exception {
        final String string = "Hello девушка Аня, тебе 18 ?";
        final char[] chars = string.toCharArray();
        final byte[] bytes = CharsBytesConverter.toBytes(chars);
        final char[] convertedChars = CharsBytesConverter.toChars(bytes);

        assertArrayEquals(chars, convertedChars);
        assertEquals(string, new String(convertedChars));
    }

    @Test
    public void testToChars() throws Exception {
        final byte[] bytes = {0, 1, 127, -128, -1, 60};
        final char[] chars = CharsBytesConverter.toChars(bytes);
        final byte[] convertedBytes = CharsBytesConverter.toBytes(chars);

        assertArrayEquals(bytes, convertedBytes);
    }

    @Test
    public void testToFromString() throws Exception {
        final byte[] bytes = {0, 1, 127, -128, -1, 60};
        final String string = CharsBytesConverter.toString(bytes);
        final byte[] convertedBytes = CharsBytesConverter.fromString(string);

        assertArrayEquals(bytes, convertedBytes);
    }
}