package fi.gekkio.roboticchameleon.lowlevel;

import java.nio.ByteBuffer;

public final class RoboticChameleonJNI {

    static {
        System.loadLibrary("robotic-chameleon");
    }

    private RoboticChameleonJNI() {
    }

    public static native void I444ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I444ToI420(
        ByteBuffer srcI444, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I444ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I444ToI420(
        ByteBuffer srcI444, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I422ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I422ToI420(
        ByteBuffer srcI422, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I422ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I422ToI420(
        ByteBuffer srcI422, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I411ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I411ToI420(
        ByteBuffer srcI411, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I411ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I411ToI420(
        ByteBuffer srcI411, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I400ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I400ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I420Copy(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I420Copy(
        ByteBuffer srcI420, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void I420Copy(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void I420Copy(
        ByteBuffer srcI420, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void NV12ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void NV12ToI420(
        ByteBuffer srcNV12, int srcStrideY, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void NV12ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void NV12ToI420(
        ByteBuffer srcNV12, int srcStrideY, int srcStrideUV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void NV21ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcVU, int srcStrideVU,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void NV21ToI420(
        ByteBuffer srcNV21, int srcStrideY, int srcStrideUV,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void NV21ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void NV21ToI420(
        ByteBuffer srcNV21, int srcStrideY, int srcStrideUV,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void YUY2ToI420(
        ByteBuffer srcYUY2, int srcStrideYUY2,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void YUY2ToI420(
        ByteBuffer srcYUY2, int srcStrideYUY2,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void UYVYToI420(
        ByteBuffer srcUYVY, int srcStrideUYVY,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void UYVYToI420(
        ByteBuffer srcUYVY, int srcStrideUYVY,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void M420ToI420(
        ByteBuffer srcM420, int srcStrideM420,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void M420ToI420(
        ByteBuffer srcM420, int srcStrideM420,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void Q420ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcYUY2, int srcStrideYUY2,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void Q420ToI420(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcYUY2, int srcStrideYUY2,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void ARGBToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void ARGBToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void BGRAToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void BGRAToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void ABGRToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void ABGRToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void RGBAToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void RGBAToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void RGB24ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void RGB24ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void RAWToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void RAWToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void RGB565ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void RGB565ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void ARGB1555ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void ARGB1555ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void ARGB4444ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstY, int dstStrideY,
        ByteBuffer dstU, int dstStrideU,
        ByteBuffer dstV, int dstStrideV,
        int width, int height);

    public static native void ARGB4444ToI420(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstI420, int dstStrideY, int dstStrideU, int dstStrideV,
        int width, int height);

    public static native void ARGBCopy(
        ByteBuffer srcARGB, int srcStrideARGB,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I420ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I420ToARGB(
        ByteBuffer srcI420, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I422ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I422ToARGB(
        ByteBuffer srcI422, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I444ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I444ToARGB(
        ByteBuffer srcI444, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I411ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcU, int srcStrideU,
        ByteBuffer srcV, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I411ToARGB(
        ByteBuffer srcI411, int srcStrideY, int srcStrideU, int srcStrideV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void I400ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void NV12ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcUV, int srcStrideUV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void NV12ToARGB(
        ByteBuffer srcNV12, int srcStrideY, int srcStrideUV,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void NV21ToARGB(
        ByteBuffer srcY, int srcStrideY,
        ByteBuffer srcVU, int srcStrideVU,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void NV21ToARGB(
        ByteBuffer srcNV21, int srcStrideY, int srcStrideVU,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void M420ToARGB(
        ByteBuffer srcM420, int srcStrideM420,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void YUY2ToARGB(
        ByteBuffer srcYUY2, int srcStrideYUY2,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void UYVYToARGB(
        ByteBuffer srcUYVY, int srcStrideUYVY,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void BGRAToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void ABGRToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void RGBAToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void RGB24ToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void RAWToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void RGB565ToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void ARGB1555ToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

    public static native void ARGB4444ToARGB(
        ByteBuffer srcFrame, int srcStrideFrame,
        ByteBuffer dstARGB, int dstStrideARGB,
        int width, int height);

}