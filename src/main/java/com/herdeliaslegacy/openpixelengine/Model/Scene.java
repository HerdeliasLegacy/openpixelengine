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
