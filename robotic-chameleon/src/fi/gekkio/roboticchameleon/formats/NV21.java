package fi.gekkio.roboticchameleon.formats;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.lowlevel.RoboticChameleonJNI;

public final class NV21 {

    public static final NV21 INSTANCE = new NV21();

    private NV21() {
    }

    public void toARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcVU, int srcStrideVU,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height) {

        RoboticChameleonJNI.NV21ToARGB(
            srcY, srcStrideY,
            srcVU, srcStrideVU,
            dstARGB, dstStrideARGB,
            width, height);
    }

    public void toARGB(
        ByteBuffer srcNV21, int srcStrideY, int srcStrideVU,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height) {

        RoboticChameleonJNI.NV21ToARGB(
            srcNV21, srcStrideY, srcStrideVU,
            dstARGB, dstStrideARGB,
            width, height);
    }

    public void toI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcVU, int srcStrideVU,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height) {

        RoboticChameleonJNI.NV21ToI420(
            srcY, srcStrideY,
            srcVU, srcStrideVU,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

    public void toI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcVU, int srcStrideVU,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height) {

        RoboticChameleonJNI.NV21ToI420(
            srcY, srcStrideY,
            srcVU, srcStrideVU,
            dstI420, dstStrideY, dstStrideU, dstStrideV,
            width, height);
    }

    public void toYV12(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstV, int dstStrideV,
        ByteBuffer dstU, int dstStrideU,
        int width, int height) {

        RoboticChameleonJNI.NV21ToI420(
            srcY, srcStrideY,
            srcUV, srcStrideUV,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

}
