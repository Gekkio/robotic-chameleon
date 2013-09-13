package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class NV21Test extends TestBase {

    static final Conversion SliceNV21ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideVU * HEIGHT / 2);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromNV21().toARGB(
                srcs[0], srcStrideY,
                srcs[1], srcStrideVU,
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion NV21ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromNV21().toARGB(
                src, srcStrideY, srcStrideVU,
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testSliceNV21ToARGB() {
        byte[] inputData = getAssetBytes("frames/NV21.yuv");
        byte[] resultData = runOneWay(inputData, SliceNV21ToARGB);
        writeToFilesDir("SliceNV21ToARGB.rgb", resultData);
    }

    public void testNV21ToARGB() {
        byte[] inputData = getAssetBytes("frames/NV21.yuv");
        byte[] resultData = runOneWay(inputData, NV21ToARGB);
        writeToFilesDir("NV21ToARGB.rgb", resultData);
    }

}
