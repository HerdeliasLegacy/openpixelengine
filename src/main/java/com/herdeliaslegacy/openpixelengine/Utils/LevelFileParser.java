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

import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;
import com.herdeliaslegacy.openpixelengine.Ui.IUiElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Static class with somme utilities methods for working on level file from json element
 *
 * @// TODO: 05/01/16 lots of work
 * @author skad
 * @date 14/09/15.
 */
public class LevelFileParser {
    private LevelFileParser(){}

    /**
     * Populate a SpriteObject from JSONObject
     *
     * @param context of the activity
     * @param object to populate
     * @param jsonObject with data
     * @return obj populated
     *
     * @throws JSONException
     */
    public static SpriteObject JsonToSprite(Context context,SpriteObject object,JSONObject jsonObject) throws JSONException {
        JSONArray size = getSizeFromJson(jsonObject);
        JSONArray pos =  getPosFromJson(jsonObject);

        object.setSize(size.getInt(0), size.getInt(1));
        object.setPosition(pos.getInt(0), pos.getInt(1));
        object.setSprite(FileUtils.getFile(context, jsonObject.getString("sprite")));
        object = addAnimationToElement(context,object,jsonObject);

        return  object;
    }

    /**
     * Populate the elem from the json passed into param
     * @param elem to populate
     * @param jsonObject with data
     * @return elem populated
     */
    public static IUiElement JsonToUiElement(IUiElement elem, JSONObject jsonObject) throws JSONException {
        JSONArray size = getSizeFromJson(jsonObject);
        JSONArray pos = getPosFromJson(jsonObject);

        elem.setId(jsonObject.getString("id"));
        elem.setSize(size.getInt(0), size.getInt(1));
        elem.setPos(pos.getInt(0), pos.getInt(1));
        elem.setTexture(jsonObject.getString("sprite"));

        return elem;
    }

    /**
     * Extract size of element from JSONOBJECT
     * @param jsonObject object to read
     * @return size
     */
    private static JSONArray getSizeFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray size;
        if(!jsonObject.isNull("size")){
            size = jsonObject.getJSONArray("size");
        }
        else {
            size = new JSONArray("[1,1]");
        }

        return size;
    }

    /**
     * Extract position of element from JSONOBJECT
     * @param jsonObject object to read
     * @return pos
     */
    private static JSONArray getPosFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray size;
        if(!jsonObject.isNull("pos")){
            size = jsonObject.getJSONArray("size");
        }
        else {
            size = new JSONArray("[1,1]");
        }

        return size;
    }

    /**
     * Add animation for the sprite from json
     * @param context of the activity
     * @param object to populate
     * @param jsonObject with data
     * @return Sprite with animations
     * @throws JSONException
     */
    public static SpriteObject addAnimationToElement(Context context,SpriteObject object,JSONObject jsonObject) throws JSONException {
        if(!jsonObject.isNull("animations")) {
            JSONArray animationArray = jsonObject.getJSONArray("animations");
            for (int i = 0; i < animationArray.length(); i++) {
                JSONObject animation = animationArray.getJSONObject(i);
                object.addAnimation(animation.getString("name"), FileUtils.getFile(context, animation.getString("sprite")), animation.getDouble("time"));
            }
        }
        return object;
    }
}
