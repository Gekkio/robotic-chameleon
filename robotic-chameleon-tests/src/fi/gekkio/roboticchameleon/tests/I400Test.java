package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I400Test extends TestBase {

    public I400Test() {
        super("frames/I400.yuv");
    }

    static final Conversion I400ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromI400().toARGB(
                src, srcStrideY,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    static final Conversion I400ToI420Slice = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;
            ByteBuffer[] dsts = ByteBuffers.slice(dst, dstStrideY * HEIGHT, dstStrideU * HEIGHT / 2, dstStrideV * HEIGHT / 2);

            RoboticChameleon.fromI400().toI420(
                src, srcStrideY,
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

    static final Conversion I400ToI420 = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideY = WIDTH;

            int dstStrideY = WIDTH;
            int dstStrideU = WIDTH / 2;
            int dstStrideV = WIDTH / 2;

            RoboticChameleon.fromI400().toI420(
                src, srcStrideY,
                dst, dstStrideY, dstStrideU, dstStrideV,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT + (WIDTH / 2 * HEIGHT);
        }
    };

    public void testI400ToARGB() {
        ByteBuffer resultData = runOneWay(inputData, I400ToARGB);
        writePngToFilesDir("I400ToARGB.png", resultData);
    }

    public void testI400ToI420Slice() {
        ByteBuffer resultData = runOneWay(inputData, I400ToI420Slice);
        writeToFilesDir("I400ToI420Slice.yuv", ByteBuffers.asByteArray(resultData));
    }

    public void testI400ToI420() {
        ByteBuffer resultData = runOneWay(inputData, I400ToI420);
        writeToFilesDir("I400ToI420.yuv", ByteBuffers.asByteArray(resultData));
    }
}
