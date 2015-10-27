package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;

import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by skad on 27/10/15.
 * This class provide somme tools for parsing Json and create OpenPixel Elements like sprite and animations
 * COPYRIGHT
 */
public class LevelFileParser {
    private LevelFileParser(){}

    public static SpriteObject addAnimationToElement(Context context,SpriteObject object,JSONArray animationArray) throws JSONException {
        for (int i = 0 ; i < animationArray.length(); i++) {
            JSONObject animation = animationArray.getJSONObject(i);
            object.addAnimation(animation.getString("name"),FileUtils.getFile(context,animation.getString("sprite")),animation.getDouble("time"));
        }
        return object;
    }

    public static SpriteObject JsonToSprite(Context context,SpriteObject obj,JSONObject jsonObject) throws JSONException {
        JSONArray size = jsonObject.getJSONArray("size");
        obj.setSize(size.getInt(0), size.getInt(1));
        obj.setSprite(FileUtils.getFile(context, jsonObject.getString("sprite")));
        //setting the animations list
        if(!jsonObject.isNull("animations")){
            obj = addAnimationToElement(context,obj,jsonObject.getJSONArray("animations"));
        }
        return  obj;
    }
}
