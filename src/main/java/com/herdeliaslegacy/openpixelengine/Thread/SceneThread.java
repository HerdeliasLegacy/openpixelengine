package com.herdeliaslegacy.openpixelengine.Thread;

import android.os.SystemClock;
import android.util.Log;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.View.SceneView;

/**
 * Created by skad on 10/09/15.
 * Original from pschmitt
 */
public class SceneThread extends Thread {
    private static final String TAG = "SceneThread";

    private boolean mRunning;
    private Object mActivityPauseLock; //activty pause lock
    private boolean mActivityPause; //activty pause
    private boolean mGamePause; //pause for update but running display

    private long mDelay = 16; //default value for running at 60HZ


    private Scene mScene;
    private SceneView mSceneView;

    public SceneThread(SceneView sceneview, Scene scene) {
        mActivityPauseLock = new Object();
        mActivityPauseLock = false;
        mRunning = false;
        mGamePause = false;
        mSceneView = sceneview;
        mScene = scene;
    }

    @Override
    public synchronized void start() {
        this.mRunning = true;
        super.start();
        Log.d(TAG, "start running");
    }

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
                        } catch (InterruptedException e) {
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
        Log.d(TAG, "set activity in pause");
        this.pause();
        synchronized (mActivityPauseLock) {
            mActivityPause = true;
        }
    }

    /**
     * Update pause
     */
    public void pause(){
        mGamePause = !mGamePause;
        Log.d(TAG, "set game pause to "+mGamePause);
    }

    @Override
    public void interrupt() {
        this.mRunning = false;
        Log.d(TAG, "interrupt running");
    }

}
