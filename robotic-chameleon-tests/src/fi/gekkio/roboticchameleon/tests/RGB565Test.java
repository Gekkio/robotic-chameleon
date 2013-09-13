package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class RGB565Test extends TestBase {

    public RGB565Test() {
        super("frames/RGB565.rgb");
    }

    static final Conversion RGB565ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB565 = WIDTH * 2;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromRGB565().toARGB(
                src, srcStrideRGB565,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion RGB565ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB565 = WIDTH * 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromRGB565().toI420(
                src, srcStrideRGB565,
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

    static final Conversion RGB565ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB565 = WIDTH * 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromRGB565().toI420(
                src, srcStrideRGB565,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    public void testRGB565ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, RGB565ToARGB);
        writePngToFilesDir("RGB565ToARGB.png", resultData);
    }

    public void testRGB565ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, RGB565ToI420Slice);
        writeToFilesDir("RGB565ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testRGB565ToI420() {
        ByteBuffer resultData = runOneWay(inputData, RGB565ToI420);
        writeToFilesDir("RGB565ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

}
