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
        String txtcontent = TxtParser.getFileContent(context,fileName);
        return new JSONObject(txtcontent);
    }

}
