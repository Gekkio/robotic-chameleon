package fi.gekkio.roboticchameleon.tests;

import java.nio.ByteBuffer;

import fi.gekkio.roboticchameleon.RoboticChameleon;

public class RGB565Test extends TestBase {

    static final Conversion RGB565ToARGB = new Conversion() {
        @Override
        public void convert(ByteBuffer src, ByteBuffer dst) {
            int srcStrideRGB565 = WIDTH * 2;

            int dstStrideARGB = WIDTH * 4;

            RoboticChameleon.fromRGB565().toARGB(
                src, srcStrideRGB565,
                dst, dstStrideARGB,
                WIDTH, HEIGHT);
        }

        @Override
        public int getDstCapacity() {
            return WIDTH * HEIGHT * 4;
        }
    };

    public void testRGB565ToARGB() {
        byte[] inputData = getAssetBytes("frames/RGB565.rgb");
        ByteBuffer resultData = runOneWay(inputData, RGB565ToARGB);
        writePngToFilesDir("RGB565ToARGB.png", resultData);
    }

}
