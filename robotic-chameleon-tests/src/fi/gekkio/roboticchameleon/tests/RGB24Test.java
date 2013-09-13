package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class RGB24Test extends TestBase {

    public RGB24Test() {
        super("frames/RGB24.rgb");
    }

    static final Conversion RGB24ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB24 = WIDTH * 3;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromRGB24().toARGB(
                src, srcStrideRGB24,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion RGB24ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB24 = WIDTH * 3;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromRGB24().toI420(
                src, srcStrideRGB24,
                dsts[0], dstStrideY,
                dsts[1], dstStrideU,
                dsts[2], dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion RGB24ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB24 = WIDTH * 3;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromRGB24().toI420(
                src, srcStrideRGB24,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    public void testRGB24ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, RGB24ToARGB);
        writePngToFilesDir("RGB24ToARGB.png", resultData);
    }

    public void testRGB24ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, RGB24ToI420Slice);
        writeToFilesDir("RGB24ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testRGB24ToI420() {
        ByteBuffer resultData = runOneWay(inputData, RGB24ToI420);
        writeToFilesDir("RGB24ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

}
