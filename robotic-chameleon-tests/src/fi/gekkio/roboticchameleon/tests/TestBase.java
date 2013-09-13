package fi.gekkio.roboticchameleon.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.test.AndroidTestCase;

import com.google.common.base.Throwables;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

class TestBase extends AndroidTestCase {

    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;

    private Bitmap bitmap;

    protected final byte[] getAssetBytes(String fileName) {
        return Assets.getAssetBytes(getContext(), fileName);
    }

    protected final void writePngToFilesDir(String fileName, ByteBuffer pixels) {
        if (bitmap == null)
            bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(pixels);
        pixels.rewind();

        File targetFile = new File(getContext().getFilesDir(), fileName);

        try {
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(targetFile);
                assertTrue(bitmap.compress(CompressFormat.PNG, 100, output));
            } finally {
                Closeables.close(output, true);
            }
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    protected final void writeToFilesDir(String fileName, byte[] data) {
        File targetFile = new File(getContext().getFilesDir(), fileName);

        try {
            Files.write(data, targetFile);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    static interface Conversion {
        int getDstCapacity();

        void convert(ByteBuffer src, ByteBuffer dst);
    }

    protected ByteBuffer runOneWay(byte[] srcData, Conversion conversion) {
        ByteBuffer src = ByteBuffers.asDirectBuffer(srcData);

        ByteBuffer dst = ByteBuffer.allocateDirect(conversion.getDstCapacity());
        conversion.convert(src, dst);
        src.clear();
        dst.clear();

        return dst;
    }

    protected ByteBuffer runTwoWay(byte[] srcData, Conversion firstConversion, Conversion secondConversion) {
        ByteBuffer src = ByteBuffers.asDirectBuffer(srcData);

        ByteBuffer firstDst = ByteBuffer.allocateDirect(firstConversion.getDstCapacity());
        firstConversion.convert(src, firstDst);
        src.clear();
        firstDst.clear();

        ByteBuffer secondDst = ByteBuffer.allocateDirect(secondConversion.getDstCapacity());
        secondConversion.convert(firstDst, secondDst);
        firstDst.clear();
        secondDst.clear();

        return secondDst;
    }

}
