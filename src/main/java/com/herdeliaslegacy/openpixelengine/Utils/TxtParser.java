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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Static class with somme utilities methods for parsing txtFile
 * @author skad
 * @date 14/09/15.
 */
public class TxtParser {

    private TxtParser() {
    }

    /**
     * Read a txt file and return the content
     * @param context of the acvtivity
     * @param fileName to read
     * @return content of the file
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
