package fi.gekkio.roboticchameleon.formats;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.lowlevel.RoboticChameleonJNI;

public final class ARGB1555 {

    public static final ARGB1555 INSTANCE = new ARGB1555();

    private ARGB1555() {
    }

    public void toARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height) {

        RoboticChameleonJNI.ARGB1555ToARGB(
            srcFrame, srcStrideFrame,
            dstARGB, dstStrideARGB,
            width, height);
    }

    public void toI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height) {

        RoboticChameleonJNI.ARGB1555ToI420(
            srcFrame, srcStrideFrame,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

    public void toYV12(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstV, int dstStrideV,
        ByteBuffer dstU, int dstStrideU,
        int width, int height) {

        RoboticChameleonJNI.ARGB1555ToI420(
            srcFrame, srcStrideFrame,
            dstY, dstStrideY,
            dstU, dstStrideU,
            dstV, dstStrideV,
            width, height);
    }

}
