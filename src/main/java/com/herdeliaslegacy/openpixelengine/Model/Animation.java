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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

/**
 * This class represent an animation
 *
 * @author skad
 * @date 05/10/15.
 */
class Animation {

    private String mAnimationFile;              // Name of the file
    private Bitmap mAnimatedSprite;             // The sprite which contain the animation
    private int mNumFrames;                     // Current step off the animation
    private int mMaxFrames;                     // Max animation step
    private int mTimeFrame;                     // Time for one frame in millisecond
    private long mTime;                         // Actual frame clock time


    /**
     * Default constructor,
     */
    public Animation(){
        this("");
    }

    /**
     * Constructor with file
     *
     * Construct an animation from animatedSprite and set all param to 0
     * @param animatedSprite                    // File name (must be a absolute path)
     */
    public Animation(String animatedSprite) {
        this.setSprite(animatedSprite);
        this.mNumFrames = 0;
        this.mMaxFrames = 0;
        this.mTimeFrame = 0;
    }

    /**
     * Constructor by copy
     *
     * Duplicate the animation in params
     * @param anim Animation to duplicate
     */
    public Animation (Animation anim){
        this.setSprite(anim.mAnimationFile);
        this.mNumFrames = anim.mNumFrames;
        this.mMaxFrames = anim.mMaxFrames;
        this.mTimeFrame = anim.mTimeFrame;
    }

    /**
     * Return the filename of the animation
     * @return filename
     */
    public String getAnimationFile() {
        return this.mAnimationFile;
    }

    /**
     * Return the time of the animation in second
     * @return animation time
     */
    public double getAnimationTime() {
        return (mMaxFrames*mTimeFrame)/1000;
    }

    /**
     * Compute the number of frame for the animation and the time for each frame
     * @param width of one frame
     * @param animationTime time of the animation
     */
    public void computeMaxStepAnimation(int width, double animationTime){
        mMaxFrames = mAnimatedSprite.getWidth()/width;
        mTimeFrame = (int)(1000*animationTime)/mMaxFrames;
    }

    /**
     * set the file for the animation (need to call computeMaxStepAnimation after)
     * @param spriteFile Filename
     */
    private void setSprite(String spriteFile) {
        if(!spriteFile.equals("")){
            this.mAnimationFile = spriteFile;
            this.mAnimatedSprite = BitmapFactory.decodeFile(spriteFile);
        }
    }

    /**
     * Return the current bitmap of this animation
     * @param width mWidth of the sprite to animate
     * @param height hight of the sprite to animate
     * @return new bitmap form the animation
     */
    public Bitmap animate(int width,int height){
        Bitmap temp = null;
        long time  = SystemClock.elapsedRealtime();
        if((time - mTime)>mTimeFrame){
            temp = Bitmap.createBitmap(mAnimatedSprite, mNumFrames*width, 0, width, height);
            mNumFrames++;
            mTime = time;
            if(mNumFrames >= mMaxFrames){
                mNumFrames = 0;
            }

        }
        return temp;
    }

    /**
     * To String Method in case off
     * @return desctiption string of the animation
     */
    public String toString(){
        return "Name: "+mAnimationFile+" Step: "+mNumFrames+" on "+mMaxFrames+" Frame time: "+mTimeFrame;
    }
}
