package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class NV21Test extends TestBase {

    public NV21Test() {
        super("frames/NV21.yuv");
    }

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
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
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
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceNV21ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideVU * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromNV21().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideVU,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion NV21ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromNV21().toI420(
                src, srcStrideY, srcStrideVU,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion NV21ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromNV21().toI420(
                src, srcStrideY, srcStrideVU,
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

    static final Conversion SliceNV21ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideVU = WIDTH;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideVU * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromNV21().toI420(
                srcs[0], srcStrideY,
                srcs[1], srcStrideVU,
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

    public void testSliceNV21ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV21ToARGB);
        writePngToFilesDir("SliceNV21ToARGB.png", resultData);
    }

    public void testNV21ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, NV21ToARGB);
        writePngToFilesDir("NV21ToARGB.png", resultData);
    }

    public void testNV21ToI420() {
        ByteBuffer resultData = runOneWay(inputData, NV21ToI420);
        writeToFilesDir("NV21ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceNV21ToI420() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV21ToI420);
        writeToFilesDir("SliceNV21ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testNV21ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, NV21ToI420Slice);
        writeToFilesDir("NV21ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceNV21ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceNV21ToI420Slice);
        writeToFilesDir("SliceNV21ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
