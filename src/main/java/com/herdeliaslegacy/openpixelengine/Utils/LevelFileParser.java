package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;

import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;
import com.herdeliaslegacy.openpixelengine.Ui.IUiElement;
import com.herdeliaslegacy.openpixelengine.Ui.TextBox;

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

    public static SpriteObject JsonToSprite(Context context,SpriteObject obj,JSONObject jsonObject) throws JSONException {
        JSONArray size;
        JSONArray pos;
        if(!jsonObject.isNull("size")){
            size = jsonObject.getJSONArray("size");
        }
        else {
            size = new JSONArray("[1,1]");
        }

        if(!jsonObject.isNull("pos")){
            pos = jsonObject.getJSONArray("pos");
        }
        else {
            pos = new JSONArray("[1,1]");
        }

        obj.setSize(size.getInt(0), size.getInt(1));
        obj.setPosition(pos.getInt(0),pos.getInt(1));
        obj.setSprite(FileUtils.getFile(context, jsonObject.getString("sprite")));
        //setting the animations list
        if(!jsonObject.isNull("animations")){
            obj = addAnimationToElement(context,obj,jsonObject.getJSONArray("animations"));
        }
        return  obj;
    }

    public static SpriteObject addAnimationToElement(Context context,SpriteObject object,JSONArray animationArray) throws JSONException {
        for (int i = 0 ; i < animationArray.length(); i++) {
            JSONObject animation = animationArray.getJSONObject(i);
            object.addAnimation(animation.getString("name"),FileUtils.getFile(context,animation.getString("sprite")),animation.getDouble("time"));
        }
        return object;
    }

    /**
     * Populate the elem from the json passed into param
     * @param elem
     * @param jsonObject
     * @return
     */
    public static IUiElement JsonToUiElement(IUiElement elem, JSONObject jsonObject) throws JSONException {
        JSONArray size;
        JSONArray pos;

        if(!jsonObject.isNull("size")){
            size = jsonObject.getJSONArray("size");
        }
        else {
            size = new JSONArray("[1,1]");
        }

        if(!jsonObject.isNull("pos")){
            pos = jsonObject.getJSONArray("pos");
        }
        else {
            pos = new JSONArray("[1,1]");
        }

        if(elem instanceof TextBox){
            elem.setSize(size.getInt(0), size.getInt(1));
        }

        elem.setId(jsonObject.getString("id"));
        elem.setPos(pos.getInt(0),pos.getInt(1));
        elem.setTexture(jsonObject.getString("sprite"));

        return elem;
    }

}
