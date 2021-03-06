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

package com.herdeliaslegacy.openpixelengine.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.herdeliaslegacy.openpixelengine.Manager.SoundManager;
import com.herdeliaslegacy.openpixelengine.Model.Camera;
import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Thread.SceneThread;
import com.herdeliaslegacy.openpixelengine.View.SceneView;
import com.herdeliaslegacy.openpixelengine.R;

import java.util.Observable;
import java.util.Observer;


/**
 * Scene activity.
 * This activity manage the scene object and the scene thread. With that the dev don't need to manage it
 * This class must be extended for each scene (lvl) to display
 *
 * @author skad
 * @date 28/10/2015
 */
public abstract class SceneActivity extends Activity implements Observer {

    protected static final String TAG = "Scene Activity"; // Tag for login echo
    protected SceneView mSceneView;                       // View for the Scene object
    private boolean mObserving;                           // State of the observation
    private Scene mScene;                                 // Scene object
    private SceneThread mSceneThread;                     // Scene Thread of updating the Scene object
    protected SoundManager mSoundManager;                 // Sound manager for background playback and fx

    /**
     * Set the content view
     * Create the SoundManager
     * Load the stuff from the game @link #loadResource()
     * Create the Scene thread
     * @param savedInstanceState // same as android OnCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneactivity);
        mSoundManager = new SoundManager(this);
        mSceneView = (SceneView) findViewById(R.id.scene_drawing_frame);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mSceneView.setScreenMetric(metrics);

        //load from the child
        mScene = this.loadResource();

        //if the camera is null set one with the screen size
        if(!mScene.hasCamera()){
            mScene.setCamera(new Camera(0,0,metrics.widthPixels,metrics.heightPixels));
        }

        //Create the thread
        mSceneThread = new SceneThread(mSceneView, mScene);
    }

    /**
     * Add a frame to the activity (like ui)
     * @param frame view to add
     */
    public void addFrame(View frame){
        RelativeLayout container = (RelativeLayout) findViewById(R.id.scene_container);
        container.addView(frame);
    }

    /**
     * OnStart of the activity
     *
     * Start (restart) all the stuff
     *  - background Playback
     *  - scene thread
     *  - observer
     */
    @Override
    protected void onStart(){
        super.onStart();
        registerObserver();
        if(mSceneThread.isActivityPause())
        {
            mSceneThread.activityResume();
        }else{
            mSceneThread.start();
        }
        mSoundManager.PlayBackground();
    }

    /**
     * OnPause
     * Stop all the stuff
     *  - background Playback
     *  - scene thread
     */
    @Override
    protected void onPause() {
        this.Pause();
        mSceneThread.activityPause();
        mSoundManager.StopBackground();
        super.onPause();
    }

    /**
     * OnQuit
     * Destroy all the stuff
     *  - background Playback
     *  - scene thread
     *  - remove observer
     *  - activity
     */
    @Override
    protected void onDestroy(){
        mSceneThread.interrupt();
        unregisterObserver();
        super.onDestroy();
    }

    /**
     * Register LevelActivity to observe both the Scene
     */
    private void registerObserver() {
        if (!mObserving) {
            mScene.addObserver(this);
            mObserving = true;
        }
    }

    /**
     * Unregisters LevelActivity as an observer of Scene
     */
    private void unregisterObserver() {
        if (mObserving) {
            mScene.deleteObserver(this);
            mObserving = false;
        }
    }


    /**
     * Load all Resource for the game
     * Must be override by the child activity into the game
     * Must return the Scene
     * @return Scene
     */
    public abstract Scene loadResource();

    /**
     * Update method for the observing
     * Must be override by the child activity into the game
     *
     * @param observable // The object which is observed
     * @param o          // Data from the object
     */
    @Override
    public abstract void update(Observable observable, Object o);


    /**
     * Pause the Scene thread
     */
    protected void Pause(){
        mScene.notifyObs(Scene.Event.SCENE_PAUSE);
        mSceneThread.pause();
    }

    /**
     * UnPause the Scene thread
     */
    protected void UnPause(){
        mScene.notifyObs(Scene.Event.SCENE_UNPAUSE);
        mSceneThread.unPause();
    }

    /**
     * Manual ending method for closing the activity
     */
    public void endScene(){
        this.finish();
    }

}
