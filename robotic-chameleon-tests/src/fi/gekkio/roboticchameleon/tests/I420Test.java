package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class I420Test extends TestBase {

    static final Conversion I420ToARGB = new Conversion() {
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
                dst, dstStrideARGB, WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testI420ToARGB() {
        byte[] inputData = getAssetBytes("frames/I420.yuv");
        byte[] resultData = runOneWay(inputData, I420ToARGB);
        writeToFilesDir("I420ToARGB.rgb", resultData);
    }

}
