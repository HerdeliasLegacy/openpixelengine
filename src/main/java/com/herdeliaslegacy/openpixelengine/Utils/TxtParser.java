package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by skad on 14/09/15.
 */
public class TxtParser {

    private TxtParser() {
    }

    /**
     * Read a txt file and return the content
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String getFileContent(Context context, String fileName) throws IOException {
        String content = "";

        String filepath = FileUtils.getFile(context,fileName);
        File file = new File(filepath);
        InputStream fileStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader( fileStream));

        String line;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        return  content;
    }
}
