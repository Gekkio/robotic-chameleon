package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I420Test extends TestBase {

    public I420Test() {
        super("frames/I420.yuv");
    }

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

    static final Conversion SliceI420ToNV12 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;

            RoboticChameleon.fromI420().toNV12(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dst, dstStrideY, dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToNV12 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;

            RoboticChameleon.fromI420().toNV12(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToNV12Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideUV * HEIGHT / 2);

            RoboticChameleon.fromI420().toNV12(
                src, srcStrideY, srcStrideU, srcStrideV,
                dsts[0], dstStrideY,
                dsts[1], dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion SliceI420ToNV12Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideUV * HEIGHT / 2);

            RoboticChameleon.fromI420().toNV12(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dsts[0], dstStrideY,
                dsts[1], dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion SliceI420ToNV21 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;

            RoboticChameleon.fromI420().toNV21(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dst, dstStrideY, dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToNV21 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;

            RoboticChameleon.fromI420().toNV21(
                src, srcStrideY, srcStrideU, srcStrideV,
                dst, dstStrideY, dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion I420ToNV21Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideUV * HEIGHT / 2);

            RoboticChameleon.fromI420().toNV21(
                src, srcStrideY, srcStrideU, srcStrideV,
                dsts[0], dstStrideY,
                dsts[1], dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    static final Conversion SliceI420ToNV21Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;
            int srcStrideU = WIDTH / 2;
            int srcStrideV = WIDTH / 2;
            ByteBuffer[] srcs = ByteBuffers.slice(src, srcStrideY * HEIGHT, srcStrideU * HEIGHT / 2, srcStrideV * HEIGHT / 2);

            int dstStrideY = WIDTH;
            int dstStrideUV = WIDTH;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideUV * HEIGHT / 2);

            RoboticChameleon.fromI420().toNV21(
                srcs[0], srcStrideY,
                srcs[1], srcStrideU,
                srcs[2], srcStrideV,
                dsts[0], dstStrideY,
                dsts[1], dstStrideUV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    public void testSliceI420ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToARGB);
        writePngToFilesDir("SliceI420ToARGB.png", resultData);
    }

    public void testI420ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, I420ToARGB);
        writePngToFilesDir("I420ToARGB.png", resultData);
    }

    public void testI420ToI420() {
        ByteBuffer resultData = runOneWay(inputData, I420ToI420);
        writeToFilesDir("I420ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToI420() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToI420);
        writeToFilesDir("SliceI420ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, I420ToI420Slice);
        writeToFilesDir("I420ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToI420Slice);
        writeToFilesDir("SliceI420ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToNV12() {
        ByteBuffer resultData = runOneWay(inputData, I420ToNV12);
        writeToFilesDir("I420ToNV12.NV12", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToNV12() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToNV12);
        writeToFilesDir("SliceI420ToNV12.NV12", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToNV12Slice() {
        ByteBuffer resultData = runOneWay(inputData, I420ToNV12Slice);
        writeToFilesDir("I420ToNV12Slice.NV12", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToNV12Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToNV12Slice);
        writeToFilesDir("SliceI420ToNV12Slice.NV12", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToNV21() {
        ByteBuffer resultData = runOneWay(inputData, I420ToNV21);
        writeToFilesDir("I420ToNV21.NV21", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToNV21() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToNV21);
        writeToFilesDir("SliceI420ToNV21.NV21", ByteBuffers.asByteArray(resultData));
    }

    public void testI420ToNV21Slice() {
        ByteBuffer resultData = runOneWay(inputData, I420ToNV21Slice);
        writeToFilesDir("I420ToNV21Slice.NV21", ByteBuffers.asByteArray(resultData));
    }

    public void testSliceI420ToNV21Slice() {
        ByteBuffer resultData = runOneWay(inputData, SliceI420ToNV21Slice);
        writeToFilesDir("SliceI420ToNV21Slice.NV21", ByteBuffers.asByteArray(resultData));
    }

}