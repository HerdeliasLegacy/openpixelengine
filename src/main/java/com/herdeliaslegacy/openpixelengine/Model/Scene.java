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

import java.util.List;
import java.util.Observable;

/**
 * Created by skad on 03/09/15.
 * This class represent a scene for the game. It's must be extend
 */
public abstract class Scene extends Observable {


    public static final String TAG = "Scene";

    /**
     * Events to be reported to observers
     */
    public enum EVENT {
        SCENE_END
    }

    public Scene() {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (final SpriteObject mSpriteObject : getAllSprites()) {
            sb.append(mSpriteObject).append("\n");
        }

        return sb.toString();
    }
}
