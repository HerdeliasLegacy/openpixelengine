package com.herdeliaslegacy.runnergameengine.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by skad on 05/10/15.
 * COPYRIGHT
 */
public class Animation {

    /**
     * The sprite wich contain the animation
     */
    protected Bitmap mAnimatedSprite;

    /**
     * Curent step off the animation
     */
    protected int mStepAnimation;

    /**
     * Max animation step
     */
    protected int mMaxStepAnimation;


    public Animation(){
        this.mStepAnimation = 0;
        this.mMaxStepAnimation = 0;
    }

    public Animation(String mAnimatedSprite) {
        this.setSprite(mAnimatedSprite);
        this.mStepAnimation = 0;
        this.mMaxStepAnimation = 0;
    }

    public void setSprite(String spriteFile) {
        this.mAnimatedSprite = BitmapFactory.decodeFile(spriteFile);
    }

    /**
     * Return the current bitmap of this animation
     * @param width
     * @param height
     * @return
     */
    public Bitmap animate(int width,int height){
        Bitmap Retour = Bitmap.createBitmap(mAnimatedSprite, mStepAnimation*width, mStepAnimation*height, width, height);
        mStepAnimation++;
        if(mStepAnimation > mMaxStepAnimation){
            mStepAnimation = 0;
        }

        return Retour;
    }
}
