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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by skad on 14/09/15.
 */
public class JsonParser{

    private JsonParser() {
    }

    /**
     * Read a json file and return a json Object from it
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject getFileContent(Context context,String fileName) throws IOException, JSONException {
        String fileContent = TxtParser.getFileContent(context,fileName);
        return new JSONObject(fileContent);
    }

}
