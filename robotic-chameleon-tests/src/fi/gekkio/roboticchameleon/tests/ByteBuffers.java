package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import com.google.common.base.Preconditions;

public final class ByteBuffers {

    private ByteBuffers() {
    }

    public static ByteBuffer asDirectBuffer(byte[] data) {
        ByteBuffer result = ByteBuffer.allocateDirect(data.length);
        result.put(data);
        result.clear();
        return result;
    }

    public static byte[] asByteArray(ByteBuffer buf) {
        byte[] data = new byte[buf.capacity()];
        buf.get(data);
        return data;
    }

    public static ByteBuffer[] slice(ByteBuffer buf, int... sliceLengths) {
        buf.clear();
        buf.limit(0);

        ByteBuffer[] results = new ByteBuffer[sliceLengths.length];

        for (int i = 0; i < sliceLengths.length; i++) {
            int sliceLength = sliceLengths[i];
            buf.position(buf.limit());
            buf.limit(buf.limit() + sliceLength);
            results[i] = buf.slice();
        }

        Preconditions.checkState(buf.capacity() == buf.limit(), "buffer capacity is bigger than expected");

        buf.clear();

        return results;
    }

}
