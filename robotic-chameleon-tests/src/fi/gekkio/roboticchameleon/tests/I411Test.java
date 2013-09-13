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
                dst, dstStrideARGB, WIDTH, HEIGHT);
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
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testSliceI411ToARGB() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        byte[] resultData = runOneWay(inputData, SliceI411ToARGB);
        writeToFilesDir("SliceI411ToARGB.rgb", resultData);
    }

    public void testI411ToARGB() {
        byte[] inputData = getAssetBytes("frames/I411.yuv");
        byte[] resultData = runOneWay(inputData, I411ToARGB);
        writeToFilesDir("I411ToARGB.rgb", resultData);
    }

}
