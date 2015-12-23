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
 * @author skad on 05/10/15.
 */
class Animation {

    /**
     * Name of the file
     */
    private String mAnimationFile;

    /**
     * The sprite which contain the animation
     */
    private Bitmap mAnimatedSprite;

    /**
     * Current step off the animation
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

    public Animation(String animatedSprite) {
        this.setSprite(animatedSprite);
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

    /**
     * Compute the number of frame for the animation and the time for each frame
     * @param width of one frame
     * @param animationTime
     */
    public void computeMaxStepAnimation(int width, double animationTime){
        mMaxFrames = mAnimatedSprite.getWidth()/width;
        mTimeFrame = (int)(1000*animationTime)/mMaxFrames;
    }

    /**
     * set the file for the animation (need to call computeMaxStepAnimation after)
     * @param spriteFile
     */
    private void setSprite(String spriteFile) {
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
}
