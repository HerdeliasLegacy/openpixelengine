package com.herdeliaslegacy.openpixelengine.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.herdeliaslegacy.openpixelengine.Manager.SoundManager;
import com.herdeliaslegacy.openpixelengine.Model.Level;
import com.herdeliaslegacy.openpixelengine.Thread.GameThread;
import com.herdeliaslegacy.openpixelengine.View.SceneView;
import com.herdeliaslegacy.openpixelengine.R;

import java.util.Observable;
import java.util.Observer;

public abstract class SceneActivity extends Activity implements Observer {
    protected static final String TAG = "sceneactivity";
    protected SceneView mSceneView;
    private boolean mObserving;
    private Level mLevel;
    private GameThread mgameThread;
    protected SoundManager msoundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneactivity);
        mSceneView = (SceneView) findViewById(R.id.level_view);
        msoundManager = new SoundManager(this);
    }

    /**
     * Attach the level to the activity. Must be do before onStart (during the onCreate of the child class)
     */
    protected void attachLevel(Level level){
        mLevel = level;
        mgameThread = new GameThread(mSceneView,mLevel);
    }
    /**
     * Starts the activity and the level
     */
    @Override
    protected void onResume(){
        super.onResume();
        registerObserver();
        mgameThread.start();
        msoundManager.PlayBackground();
    }

    /**
     * On quit stop thread game
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterObserver();
        mgameThread.interrupt();
        msoundManager.StopBackground();
    }

    /**
     * Register LevelActivity to observe both the Level
     */
    private void registerObserver() {
        if (!mObserving) {
            mLevel.addObserver(this);
            mObserving = true;
        }
    }

    /**
     * Unregisters LevelActivity as an observer of Level
     */
    private void unregisterObserver() {
        if (mObserving) {
            mLevel.deleteObserver(this);
            mObserving = false;
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
