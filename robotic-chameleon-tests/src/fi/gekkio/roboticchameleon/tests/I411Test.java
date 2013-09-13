package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I411Test extends TestBase {

    static final Conversion SliceI411ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI411().toARGB(
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

    static final Conversion I411ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI411().toARGB(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceI411ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI411().toI420(
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

    static final Conversion I411ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI411().toI420(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I411ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI411().toI420(
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

    static final Conversion SliceI411ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 4;
            int srcStrideV = WIDTH / 4;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI411().toI420(
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

    public void testSliceI411ToARGB() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI411ToARGB);
        writePngToFilesDir("SliceI411ToARGB.png", resultData);
    }

    public void testI411ToARGB() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, I411ToARGB);
        writePngToFilesDir("I411ToARGB.png", resultData);
    }

    public void testI411ToI420() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, I411ToI420);
        writeToFilesDir("I411ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI411ToI420() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI411ToI420);
        writeToFilesDir("SliceI411ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI411ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, I411ToI420Slice);
        writeToFilesDir("I411ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI411ToI420Slice() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        ByteBuffer resultData = runOneWay(inputData, SliceI411ToI420Slice);
        writeToFilesDir("SliceI411ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
