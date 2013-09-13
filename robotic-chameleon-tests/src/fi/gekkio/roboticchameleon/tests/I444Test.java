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
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
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
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceI444ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI444().toI420(
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

    static final Conversion I444ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI444().toI420(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I444ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI444().toI420(
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

    static final Conversion SliceI444ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH;
            int srcStrideV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI444().toI420(
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

    public void testSliceI444ToARGB() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI444ToARGB);
        writePngToFilesDir("SliceI444ToARGB.png", resultData);
    }

    public void testI444ToARGB() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, I444ToARGB);
        writePngToFilesDir("I444ToARGB.png", resultData);
    }

    public void testI444ToI420() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, I444ToI420);
        writeToFilesDir("I444ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI444ToI420() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI444ToI420);
        writeToFilesDir("SliceI444ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI444ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, I444ToI420Slice);
        writeToFilesDir("I444ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI444ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I444.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI444ToI420Slice);
        writeToFilesDir("SliceI444ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
