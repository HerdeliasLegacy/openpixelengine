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

package com.herdeliaslegacy.openpixelengine.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * This class represent a scene for the game. It's must be extend
 *
 * @author skad
 * @date 03/09/15.
 */
public abstract class Scene extends Observable {


    public static final String TAG = "Scene"; // tag for login echo
    protected Camera mCamera = null;                   // Camera which represent what to draw on the screen

    /**
     * Events to be reported to observers
     */
    public enum Event {
        SCENE_START,
        SCENE_END,
        SCENE_PAUSE,
        SCENE_UNPAUSE
    }

    /**
     * Default constructor
     */
    public Scene() {
    }


    /**
     * Set the camera
     * @param cam camera for the scene
     */
    public void setCamera(Camera cam){
        mCamera = cam;
    }

    /**
     * Retturn the camera of the scene
     * @return the camera
     */
    public Camera getCamera(){
        return mCamera;
    }

    /**
     * Test if the Scene has a camera
     * @return true if the camera is not null
     */
    public boolean hasCamera(){
        return (mCamera != null);
    }
    /**
     * Select from all sprite only theses into the camera view
     * @return Sprite list displayable
     */
    public List<SpriteObject> getAllDisplayedSprites() {
        ArrayList<SpriteObject> temp = new ArrayList<>();
        for(SpriteObject sprite: getAllSprites()) {
            if(mCamera.contains(sprite.getPosition()) || mCamera.contains(sprite.getPositionSize())){
                temp.add(sprite);
            }
        }
        return temp;
    }
    /**
     * Retrieve all the sprites!
     * Note: The order matters as the first object is drawn first (followers will be "above")
     * @return An List containing all sprites
     */
    public abstract List<SpriteObject> getAllSprites();

    /**
     * Method for updating all the element. By default do nothing
     * This is you level logic
     */
    public abstract void update();


    /**
     * Notify All observer
     * @param evt Enum send
     */
    public void notifyObs(Enum evt){
        setChanged();
        notifyObservers(evt);
    }

    /**
     * To String Method in case off
     * @return desctiption string of the scene with all sprite
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (final SpriteObject mSpriteObject : getAllSprites()) {
            sb.append(mSpriteObject).append("\n");
        }

        return sb.toString()+"Camera: "+mCamera;
    }
}
