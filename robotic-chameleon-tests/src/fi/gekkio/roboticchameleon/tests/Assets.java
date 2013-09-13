package fi.gekkio.roboticchameleon.tests;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

public final class Assets {

    private Assets() {
    }

    public static byte[] getAssetBytes(Context context, String fileName) {
        try {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(fileName);

                return ByteStreams.toByteArray(inputStream);
            } finally {
                Closeables.close(inputStream, true);
            }
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

}
