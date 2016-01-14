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

package com.herdeliaslegacy.openpixelengine.Thread;

import android.os.SystemClock;
import android.util.Log;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.View.SceneView;

/**
 * Running thread for updating graphics and logic of the game
 * @author  skad
 * @date 10/09/15.
 * Original from pschmitt
 */
public class SceneThread extends Thread {
    private static final String TAG = "SceneThread";                        // Tag for login echo
    private boolean mRunning;
    private final Object mActivityPauseLock = new Object();                 // Activity pause lock
    private boolean mActivityPause;                                         // Activity pause
    private boolean mGamePause;                                             // Pause for update but running display
    private long mDelay = 16;                                               // Time for 1 loop in ms, default value for running at 60HZ
    private final Scene mScene;                                             // Scene object
    private final SceneView mSceneView;                                     // View where draw the scene

    /**
     * Consructor
     * @param sceneview scene were draw
     * @param scene scene to draw
     */
    public SceneThread(SceneView sceneview, Scene scene) {
        mActivityPause = false;
        mRunning = false;
        mGamePause = false;
        mSceneView = sceneview;
        mScene = scene;
    }

    /**
     * Get the seted time of 1 loop
     * @return time in ms
     */
    public long getDelay() {
        return mDelay;
    }

    /**
     * Set the time wanted for 1 loop
     * @param mDelay time in ms
     */
    public void setDelay(long mDelay) {
        this.mDelay = mDelay;
    }

    /**
     * Start running the thread
     */
    @Override
    public synchronized void start() {
        this.mRunning = true;
        super.start();
        Log.d(TAG, "start running");

    }

    /**
     * Running method
     * if running
     *  if not pause
     *      update scene
     *  draw scene
     *  if time < time for 1 loop
     *      wait
     *  if locking pause from activity
     *      wait
     */
    @Override
    public void run() {

        synchronized (this) {
            while (mRunning) {

                long time = SystemClock.elapsedRealtime();

                //update
                if (!mGamePause) {
                    mScene.update();
                }
                //drawing
                mSceneView.drawScene(mScene);

                //wait for running at 60Hz
                time = mDelay -(SystemClock.elapsedRealtime() - time);
                if(time>0){
                    try {
                        sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //pause activity
                synchronized (mActivityPauseLock) {
                    while (mActivityPause) {
                        try {
                            mActivityPauseLock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            }
        }
    }



    /**
     * Activity resume
     */
    public void activityResume() {
        Log.d(TAG, "resume activity");
        synchronized (mActivityPauseLock) {
            mActivityPause = false;
            mActivityPauseLock.notifyAll();
        }
    }

    public boolean isActivityPause(){
        return this.mActivityPause;
    }

    /**
     * Activity pause
     */
    public void activityPause() {
        Log.d(TAG, "pause activity");
        synchronized (mActivityPauseLock) {
            mActivityPause = true;
        }
    }

    /**
     * Set Game pause (just block update but not display drawing)
     */
    public void pause(){
        mGamePause = true;
        Log.d(TAG, "Pause scene");
    }

    /**
     *
     */
    public void unPause(){
        mGamePause = false;
        Log.d(TAG, "UnPause scene");
    }

    /**
     * Stop thread
     */
    @Override
    public void interrupt() {
        this.mRunning = false;
        Log.d(TAG, "interrupt running");
    }

}
