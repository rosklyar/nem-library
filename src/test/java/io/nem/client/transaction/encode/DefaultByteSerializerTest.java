package io.nem.client.transaction.encode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DefaultByteSerializerTest {

    private final ByteSerializer byteSerializer = new DefaultByteSerializer();

    @Test
    void serializeInt() {
        byte[] bytes = byteSerializer.intToByte(257);
        byte[] expectedBytes = new byte[]{0x01, 0x01, 0x00, 0x00};
        assertArrayEquals(expectedBytes, bytes);

        byte[] secondBytes = byteSerializer.intToByte(0x09513510);
        byte[] expectedSecondBytes = new byte[]{0x10, 0x35, 0x51, 0x09};
        assertArrayEquals(expectedSecondBytes, secondBytes);
    }

    @Test
    void serializeLong() {
        byte[] bytes = byteSerializer.longToByte(12000000);
        byte[] expectedBytes = new byte[]{0x00, 0x1b, -73, 0x00, 0x00, 0x00, 0x00, 0x00};
        assertArrayEquals(expectedBytes, bytes);
    }

    @Test
    void serializeAddress() {
        byte[] bytes = byteSerializer.addressToByte("NACCH2WPJYVQ3PLGMVZVRK5JI6POTJXXHLUG3P4J");
        byte[] expectedBytes = new byte[]{0x4e, 0x41, 0x43, 0x43, 0x48, 0x32, 0x57, 0x50, 0x4a, 0x59, 0x56, 0x51, 0x33, 0x50, 0x4c, 0x47, 0x4d, 0x56, 0x5a, 0x56, 0x52, 0x4b, 0x35, 0x4a, 0x49, 0x36, 0x50, 0x4f, 0x54, 0x4a, 0x58, 0x58, 0x48, 0x4c, 0x55, 0x47, 0x33, 0x50, 0x34, 0x4a};
        assertArrayEquals(expectedBytes, bytes);
    }
}