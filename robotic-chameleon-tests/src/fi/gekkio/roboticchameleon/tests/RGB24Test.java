package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class RGB24Test extends TestBase {

    static final Conversion RGB24ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB24 = WIDTH * 3;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromRGB24().toARGB(
                src, srcStrideRGB24,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testRGB24ToARGB() {
        byte[] inputData = getAssetBytes("frames/RGB24.rgb");
        ByteBuffer resultData = runOneWay(inputData, RGB24ToARGB);
        writePngToFilesDir("RGB24ToARGB.png", resultData);
    }

}
