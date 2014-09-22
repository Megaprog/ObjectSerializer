package org.jmmo;

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
        final String storage = new String(chars);
        final byte[] convertedBytes = CharsBytesConverter.toBytes(storage.toCharArray());

        assertArrayEquals(bytes, convertedBytes);
    }
}