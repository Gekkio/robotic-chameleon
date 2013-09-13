package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class NV12Test extends TestBase {

    static final Conversion NV12ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideUV * HEIGHT / 2);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromNV12().toARGB(
                srcs[0], srcStrideY,
                srcs[1], srcStrideUV,
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testNV12ToARGB() {
        byte[] inputData = getAssetBytes("frames/NV12.yuv");
        byte[] resultData = runOneWay(inputData, NV12ToARGB);
        writeToFilesDir("NV12ToARGB.rgb", resultData);
    }

}
