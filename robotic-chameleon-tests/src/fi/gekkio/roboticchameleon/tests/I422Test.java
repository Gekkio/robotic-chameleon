package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I422Test extends TestBase {

    public I422Test() {
        super("frames/I422.yuv");
    }

    static final Conversion SliceI422ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI422().toARGB(
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

    static final Conversion I422ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI422().toARGB(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion SliceI422ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI422().toI420(
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

    static final Conversion I422ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI422().toI420(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I422ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI422().toI420(
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

    static final Conversion SliceI422ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT, srcStrideV * HEIGHT);

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI422().toI420(
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

    public void testSliceI422ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, SliceI422ToARGB);
        writePngToFilesDir("SliceI422ToARGB.png", resultData);
    }

    public void testI422ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, I422ToARGB);
        writePngToFilesDir("I422ToARGB.png", resultData);
    }

    public void testI422ToI420() {
        ByteBuffer resultData = runOneWay(inputData, I422ToI420);
        writeToFilesDir("I422ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI422ToI420() {
        ByteBuffer resultData = runOneWay(inputData, SliceI422ToI420);
        writeToFilesDir("SliceI422ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI422ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, I422ToI420Slice);
        writeToFilesDir("I422ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI422ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceI422ToI420Slice);
        writeToFilesDir("SliceI422ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

}
