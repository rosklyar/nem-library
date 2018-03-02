package io.nem.client.transaction.encode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;

import static java.nio.charset.Charset.forName;

public class DefaultHexConverter implements HexConverter {

    private final Charset charset = forName("UTF-8");

    @Override
    public byte[] getBytes(String hexString) {
        final Hex codec = new Hex();
        final String paddedHexString = 0 == hexString.length() % 2 ? hexString : "0" + hexString;
        final byte[] encodedBytes = paddedHexString.getBytes(charset);
        try {
            return codec.decode(encodedBytes);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getString(final byte[] bytes) {
        final Hex codec = new Hex();
        final byte[] decodedBytes = codec.encode(bytes);
        return new String(decodedBytes, charset);
    }
}
