package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by skad on 14/09/15.
 */
public class FileUtils {

    private FileUtils(){}

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * Check if a file exist
     *
     * @param name
     * @return boolean value
     */
    public static boolean fileExist(String name) {
        File file = new File(name);
        return file.exists();
    }

    public static String getFile(Context context, String fileName){
        String filePath = null;
        if(isExternalStorageReadable()){
            filePath = context.getExternalFilesDir(null)+File.separator+fileName;
        }
        return filePath;
    }

}
