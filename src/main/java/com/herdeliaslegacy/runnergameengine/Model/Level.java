package com.herdeliaslegacy.runnergameengine.Model;

import android.util.Log;

import com.herdeliaslegacy.runnergameengine.Utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by skad on 03/09/15.
 */
public class Level extends Observable {
    public static final String TAG = "Level";
    private static Level mInstance;

    /** Limit of the screen */
    private static int mMaxXDraw = 0;
    private static int mMaxYDraw = 0;

    /** Player */
    private Player mPlayer;
    /** SpriteObject list for the decors wich will be displayed on the screen */
    private List<MovingSpriteObject> mDecors = new ArrayList<MovingSpriteObject>();

    /** SpriteObject list with only the element composing the decor. This list will never be drawed */
    private List<DecorsElement> mDecorsListElements = new ArrayList<DecorsElement>();

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


    public static void setmMaxXDraw(int screenWidth) {
        mMaxXDraw = screenWidth;
    }


    public static void setmMaxYDraw(int screenHeight) {
        mMaxYDraw = screenHeight;
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
        spriteList.addAll(mDecors);

        return spriteList;
    }


    /**
     * Generate the begening of the level for start running
     */
    public void generateLevelStart()
    {
        int i = 0;
        while(i < mMaxXDraw)
        {
            i = addRandomElementToDecor(i);
        }
    }

    /**
     * Add a new Element to the DecoList
     */
    private int addRandomElementToDecor(int posx){
        int sizeDecors = mDecors.size()-1;
        int pos = MathUtils.randomInt(mDecorsListElements.size());
        MovingSpriteObject element = new MovingSpriteObject(mDecorsListElements.get(pos));
        Vector2D elementPos = new Vector2D(posx,mMaxYDraw-element.getHeight());
        element.setPosition(elementPos);
        mDecors.add(element);
        posx += element.getWidth();
        return posx;
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
     * Add an item to the level
     *
     * @param newObject The SpriteObject to add to our level
     */
    public void add(SpriteObject newObject) {
        if (newObject instanceof Player) {
            mPlayer = (Player) newObject;
        }else if (newObject instanceof DecorsElement){
            mDecorsListElements.add((DecorsElement) newObject);
        }


        setChanged();
        notifyObservers();
    }

    /**
     * Update methode for refreshing the level
     */
    public void update(){

    }

}
