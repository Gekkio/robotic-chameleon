package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I420Test extends TestBase {

    static final Conversion SliceI420ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI420().toARGB(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion I420ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI420().toARGB(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceI420ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI420().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI420().toI420(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI420().toI420(
                src, srcStrideY, srcStrideU, srcStrideV,
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

    static final Conversion SliceI420ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI420().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
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

    public void testSliceI420ToARGB() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToARGB);
        writePngToFilesDir("SliceI420ToARGB.png", resultData);
    }

    public void testI420ToARGB() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, I420ToARGB);
        writePngToFilesDir("I420ToARGB.png", resultData);
    }

    public void testI420ToI420() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, I420ToI420);
        writeToFilesDir("I420ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToI420() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToI420);
        writeToFilesDir("SliceI420ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, I420ToI420Slice);
        writeToFilesDir("I420ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToI420Slice);
        writeToFilesDir("SliceI420ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
