package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class NV12Test extends TestBase {

    public NV12Test() {
        super("frames/NV12.yuv");
    }

    static final Conversion SliceNV12ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideUV * HEIGHT / 2);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromNV12().toARGB(
                srcs[0], srcStrideY,
                srcs[1], srcStrideUV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion NV12ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromNV12().toARGB(
                src, srcStrideY, srcStrideUV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceNV12ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideUV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromNV12().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideUV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion NV12ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromNV12().toI420(
                src, srcStrideY, srcStrideUV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion NV12ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromNV12().toI420(
                src, srcStrideY, srcStrideUV,
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

    static final Conversion SliceNV12ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideUV = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideUV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromNV12().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideUV,
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

    public void testSliceNV12ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV12ToARGB);
        writePngToFilesDir("SliceNV12ToARGB.png", resultData);
    }

    public void testNV12ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, NV12ToARGB);
        writePngToFilesDir("NV12ToARGB.png", resultData);
    }

    public void testNV12ToI420() {
        ByteBuffer resultData = runOneWay(inputData, NV12ToI420);
        writeToFilesDir("NV12ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceNV12ToI420() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV12ToI420);
        writeToFilesDir("SliceNV12ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testNV12ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, NV12ToI420Slice);
        writeToFilesDir("NV12ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceNV12ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV12ToI420Slice);
        writeToFilesDir("SliceNV12ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
