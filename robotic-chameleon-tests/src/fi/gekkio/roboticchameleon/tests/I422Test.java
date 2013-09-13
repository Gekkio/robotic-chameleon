package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I422Test extends TestBase {

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
                dst, dstStrideARGB, WIDTH, HEIGHT);
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
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testSliceI422ToARGB() {
        byte[] inputData = getAssetBytes("frames/I422.yuv");
        byte[] resultData = runOneWay(inputData, SliceI422ToARGB);
        writeToFilesDir("SliceI422ToARGB.rgb", resultData);
    }

    public void testI422ToARGB() {
        byte[] inputData = getAssetBytes("frames/I422.yuv");
        byte[] resultData = runOneWay(inputData, I422ToARGB);
        writeToFilesDir("I422ToARGB.rgb", resultData);
    }

}
