package fi.gekkio.roboticchameleon.tests;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.test.AndroidTestCase;

import com.google.common.base.Throwables;
import com.google.common.io.Files;

class TestBase extends AndroidTestCase {

    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;

    protected final byte[] getAssetBytes(String fileName) {
        return Assets.getAssetBytes(getContext(), fileName);
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

    protected byte[] runOneWay(byte[] srcData, Conversion conversion) {
        ByteBuffer src = ByteBuffers.asDirectBuffer(srcData);

        ByteBuffer dst = ByteBuffer.allocateDirect(conversion.getDstCapacity());
        conversion.convert(src, dst);
        src.clear();
        dst.clear();

        return ByteBuffers.asByteArray(dst);
    }

    protected byte[] runTwoWay(byte[] srcData, Conversion firstConversion, Conversion secondConversion) {
        ByteBuffer src = ByteBuffers.asDirectBuffer(srcData);

        ByteBuffer firstDst = ByteBuffer.allocateDirect(firstConversion.getDstCapacity());
        firstConversion.convert(src, firstDst);
        src.clear();
        firstDst.clear();

        ByteBuffer secondDst = ByteBuffer.allocateDirect(secondConversion.getDstCapacity());
        secondConversion.convert(firstDst, secondDst);
        firstDst.clear();
        secondDst.clear();

        return ByteBuffers.asByteArray(secondDst);
    }

}
