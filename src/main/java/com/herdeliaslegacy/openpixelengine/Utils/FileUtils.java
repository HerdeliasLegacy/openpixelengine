/*
 *  This file is part of  OpenPixelEngine.
 *
 *     OpenPixelEngine is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     OpenPixelEngine is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with OpenPixelEngine.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2015
 */

package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Static class with sommes utilities methodes for working on files
 *
 * @author skad
 * @date 14/09/15.
 */
public class FileUtils {

    private FileUtils(){}

    /**
     * Checks if external storage is available for read and write
     * @return true if external storage is writable else no
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Checks if external storage is available to at least read
     * @return true if external storage is readable else no
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * Check if a file exist
     *
     * @param name file name to test need an absolut path
     * @return true if exist
     */
    public static boolean fileExist(String name) {
        File file = new File(name);
        return file.exists();
    }

    /**
     * Return the full path of the file
     * @param context of the activity
     * @param fileName file name to test
     * @return return the full path
     */
    public static String getFile(Context context, String fileName){
        String filePath = null;
        if(isExternalStorageReadable()){
            filePath = context.getExternalFilesDir(null)+File.separator+fileName;
        }
        return filePath;
    }

}
