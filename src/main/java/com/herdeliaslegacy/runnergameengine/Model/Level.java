package com.herdeliaslegacy.runnergameengine.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by skad on 03/09/15.
 */
public class Level extends Observable {
    public static final String TAG = "Level";
    private static Level mInstance;
    private Player mPlayer;

    private Level() {
    }

    /**
     * This class is a singleton, instead of calling its (private) constructor, use this method
     *
     * @return The one and only instance of this class
     */
    public static Level getInstance() {
        if (mInstance == null) {
            synchronized (Level.class) {
                mInstance = new Level();
            }
        }
        return mInstance;
    }

    /**
     * Retrieve all the sprites!
     * Note: The order matters as the first object is drawn first (followers will be "above")
     *
     * @return An (Array)List containing all sprites
     */
    public List<SpriteObject> getAllSprites() {
        List<SpriteObject> spriteList = new ArrayList<SpriteObject>();

        spriteList.add(mPlayer);

        return spriteList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (final SpriteObject mSpriteObject : getAllSprites()) {
            sb.append(mSpriteObject + "\n");
        }

        return sb.toString();
    }

    /**
     * Events to be reported to observers
     */
    public static enum EVENT {
        GAME_OVER, GAME_SUCCESS
    }

    /**
     * Update methode for refreshing the level
     */
    public void update(){

    }

    /**
     * Add an item to the level
     *
     * @param newObject The SpriteObject to add to our level
     */
    public void add(SpriteObject newObject) {
        if (newObject instanceof Player) {
            mPlayer = (Player) newObject;
        }
        setChanged();
        notifyObservers();
    }
}
