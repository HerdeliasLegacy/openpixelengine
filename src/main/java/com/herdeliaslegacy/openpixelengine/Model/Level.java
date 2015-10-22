package com.herdeliaslegacy.openpixelengine.Model;

import java.util.List;
import java.util.Observable;

/**
 * Created by skad on 03/09/15.
 * This class represent a level for the game (maybe need to be rename)
 * it must be extend and by defaut only define a methode for get all element to be drawing and
 * for updating the level
 */
public abstract class Level extends Observable {
    public static final String TAG = "Level";

    public Level() {
    }

    /**
     * Retrieve all the sprites!
     * Note: The order matters as the first object is drawn first (followers will be "above")
     * TODO: need to find a better way to serve files because it will be quickly ram hungry
     * @return An (Array)List containing all sprites
     */
    public abstract List<SpriteObject> getAllSprites();

    /**
     * Method for updating all the element. By default do nothing but must be overrided
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

    /**
     * Events to be reported to observers
     */
    public enum EVENT {
        GAME_OVER, GAME_SUCCESS
    }
}
