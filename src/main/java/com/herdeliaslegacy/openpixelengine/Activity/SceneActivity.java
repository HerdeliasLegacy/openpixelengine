package com.herdeliaslegacy.openpixelengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import com.herdeliaslegacy.openpixelengine.Manager.SoundManager;
import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Thread.SceneThread;
import com.herdeliaslegacy.openpixelengine.View.SceneView;
import com.herdeliaslegacy.openpixelengine.R;

import java.util.Observable;
import java.util.Observer;


/**
 * Scene activity.
 * It will the scene object and the scene thread.
 */
public abstract class SceneActivity extends Activity implements Observer {
    protected static final String TAG = "Scene Activity";
    protected SceneView mSceneView;
    private boolean mObserving;
    private Scene mScene;
    private SceneThread msceneThread;
    protected SoundManager msoundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneactivity);
        //load componnant for the child
        msoundManager = new SoundManager(this);
        mSceneView = (SceneView) findViewById(R.id.level_view);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mSceneView.setScreenMetric(metrics);

        //load from the child
        mScene = this.loadResource();

        //start the thread
        msceneThread = new SceneThread(mSceneView, mScene);
    }

    @Override
    protected void onStart(){
        super.onStart();
        registerObserver();
        if(msceneThread.isActivityPause())
        {
            msceneThread.activityResume();
        }else{
            msceneThread.start();
        }
        msoundManager.PlayBackground();
    }
    /**
     * Starts the activity and the level
     */
    @Override
    protected void onResume(){
        super.onResume();
    }

    /**
     * On quit stop thread game
     */
    @Override
    protected void onPause() {
        super.onPause();
        msceneThread.activityPause();
        msoundManager.StopBackground();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        msceneThread.interrupt();
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
     * Load all Resource
     * Must be overided by the child activity into the game
     */
    public abstract Scene loadResource();
    @Override
    public abstract void update(Observable observable, Object o);


    /**
     * Attach the scene to the activity. Must be do before onStart (during the onCreate of the child class)
     */
    protected void attachLevel(Scene scene){

    }

    protected void dettachLevel(){
        msceneThread.interrupt();
    }


    /**
     *  Add an Ui element to the LevelView
     * @param elem Ui element to add into the layout
     */
    public void addUiElement(View elem){
        FrameLayout ui = (FrameLayout) findViewById(R.id.iulayout);
        ui.addView(elem);
    }

    protected void Pause(){
        msceneThread.pause();
    }

    /**
     * Called method for ending the scene
     */
    public void endScene(){
        this.finish();
    }

}
