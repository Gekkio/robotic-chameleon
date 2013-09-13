package fi.gekkio.roboticchameleon.formats;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.lowlevel.RoboticChameleonJNI;

public final class NV12 {

    public static final NV12 INSTANCE = new NV12();

    private NV12() {
    }

    public void toARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height) {

        RoboticChameleonJNI.NV12ToARGB(
            srcY, srcStrideY,
            srcUV, srcStrideUV,
            dstARGB, dstStrideARGB,
            width, height);
    }

    public void toARGB(
        ByteBuffer srcNV12, int srcStrideY, int srcStrideUV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height) {

        RoboticChameleonJNI.NV12ToARGB(
            srcNV12, srcStrideY, srcStrideUV,
            dstARGB, dstStrideARGB,
            width, height);
    }

    public void toI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height) {

        RoboticChameleonJNI.NV12ToI420(
            srcY, srcStrideY,
            srcUV, srcStrideUV,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

    public void toYV12(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstV, int dstStrideV,
        ByteBuffer dstU, int dstStrideU,
        int width, int height) {

        RoboticChameleonJNI.NV12ToI420(
            srcY, srcStrideY,
            srcUV, srcStrideUV,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

}
