package com.herdeliaslegacy.openpixelengine.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

import org.json.JSONObject;

import java.util.InputMismatchException;

/**
 * Created by skad on 05/10/15.
 * COPYRIGHT
 */
public class Animation {

    /**
     * Name of the file
     */
    private String mAnimationFile;

    /**
     * The sprite wich contain the animation
     */
    private Bitmap mAnimatedSprite;

    /**
     * Curent step off the animation
     */
    private int mNumFrames;

    /**
     * Max animation step
     */
    private int mMaxFrames;

    /**
     * Time for one frame in millisecond
     */
    private int mTimeFrame;

    /**
     * actual frame clock time
     */
    private long mTime;


    public Animation(){
        this.mNumFrames = 0;
        this.mMaxFrames = 0;
        this.mTimeFrame = 0;
    }

    public Animation(String animatedsprite) {
        this.setSprite(animatedsprite);
        this.mNumFrames = 0;
        this.mMaxFrames = 0;
        this.mTimeFrame = 0;
    }

    public Animation (Animation anim){
        this.setSprite(anim.mAnimationFile);
        this.mNumFrames = anim.mNumFrames;
        this.mMaxFrames = anim.mMaxFrames;
        this.mTimeFrame = anim.mTimeFrame;
    }

    public String getAnimationFile() {
        return this.mAnimationFile;
    }

    public double getAnimationTime() {
        return (mMaxFrames*mTimeFrame)/1000;
    }
    public void computeMaxStepAnimation(int width, double animationtime){
        mMaxFrames = mAnimatedSprite.getWidth()/width;
        mTimeFrame = (int)(1000*animationtime)/mMaxFrames;
    }
    public void setSprite(String spriteFile) {
        this.mAnimationFile = spriteFile;
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
