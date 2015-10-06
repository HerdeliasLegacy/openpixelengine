package com.herdeliaslegacy.runnergameengine.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

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
    protected int mNumFrames;

    /**
     * Max animation step
     */
    protected int mMaxFrames;

    /**
     * Time for one frame in millisecond
     */
    protected int mTimeFrame;

    /**
     * actual frame clock time
     */
    protected long mTime;


    public Animation(){
        this.mNumFrames = 0;
        this.mMaxFrames = 0;
        this.mTimeFrame = 0;
    }

    public Animation(String mAnimatedSprite) {
        this.setSprite(mAnimatedSprite);
        this.mNumFrames = 0;
        this.mMaxFrames = 0;
        this.mTimeFrame = 0;
    }

    public void computeMaxStepAnimation(int width, double animationtime){
        mMaxFrames = mAnimatedSprite.getWidth()/width;
        mTimeFrame = (int)(1000*animationtime)/mMaxFrames;
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
        Bitmap Retour = null;
        long time  = SystemClock.elapsedRealtime();
        if((time - mTime)>mTimeFrame){
            Retour = Bitmap.createBitmap(mAnimatedSprite, mNumFrames *width, 0, width, height);
            mNumFrames++;
            mTime = time;
            if(mNumFrames >= mMaxFrames){
                mNumFrames = 0;
            }

        }
        return Retour;
    }
}
