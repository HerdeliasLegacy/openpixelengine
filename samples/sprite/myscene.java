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
 * Copyright (c) 2016
 */

import android.util.Log;

import com.herdeliaslegacy.openpixelengine.Model.Scene

public class MyLevel extends Scene {

    private List<SpriteObject> myspritelist //spritelist
    public MyLevel() {
    }

    /**
     * Method for adding a sprite to the sprite list for the decors
     * @param sprite to add
     */
    public void addSprite(MySprite sprite){
        myspritelist.add(sprite);
    }

    public List<SpriteObject> getAllSprites(){
        List<SpriteObject> spriteList = new ArrayList<SpriteObject>();

        spriteList.addAll(myspritelist);

        return spriteList;
    }

    public void update(){
        //todo do your game logic here
    }
}