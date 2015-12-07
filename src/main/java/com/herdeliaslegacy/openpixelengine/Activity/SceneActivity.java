package com.herdeliaslegacy.openpixelengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.herdeliaslegacy.openpixelengine.Manager.SoundManager;
import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Thread.SceneThread;
import com.herdeliaslegacy.openpixelengine.View.SceneView;
import com.herdeliaslegacy.openpixelengine.R;

import java.util.Observable;
import java.util.Observer;

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
        mSceneView = (SceneView) findViewById(R.id.level_view);
        msoundManager = new SoundManager(this);
    }

    /**
     * Attach the scene to the activity. Must be do before onStart (during the onCreate of the child class)
     */
    protected void attachLevel(Scene scene){
        mScene = scene;
        msceneThread = new SceneThread(mSceneView, mScene);
    }
    /**
     * Starts the activity and the level
     */
    @Override
    protected void onResume(){
        super.onResume();
        registerObserver();
        msceneThread.start();
        msoundManager.PlayBackground();
    }

    /**
     * On quit stop thread game
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterObserver();
        msceneThread.interrupt();
        msoundManager.StopBackground();
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

    @Override
    public abstract void update(Observable observable, Object o);

    /**
     *  Add an Ui element to the LevelView
     * @param elem Ui element to add into the layout
     */
    public void addUiElement(View elem){
        FrameLayout ui = (FrameLayout) findViewById(R.id.iulayout);
        ui.addView(elem);
    }


}
