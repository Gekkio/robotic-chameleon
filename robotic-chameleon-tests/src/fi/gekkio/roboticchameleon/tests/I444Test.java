package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I444Test extends TestBase {

    static final Conversion SliceI444ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI444().toARGB(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion I444ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI444().toARGB(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testSliceI444ToARGB() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        byte[] resultData = runOneWay(inputData, SliceI444ToARGB);
        writeToFilesDir("SliceI444ToARGB.rgb", resultData);
    }

    public void testI444ToARGB() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        byte[] resultData = runOneWay(inputData, I444ToARGB);
        writeToFilesDir("I444ToARGB.rgb", resultData);
    }

}
