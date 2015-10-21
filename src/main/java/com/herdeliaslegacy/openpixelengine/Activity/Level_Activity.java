package com.herdeliaslegacy.openpixelengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.herdeliaslegacy.openpixelengine.Model.Level;
import com.herdeliaslegacy.openpixelengine.Thread.GameThread;
import com.herdeliaslegacy.openpixelengine.View.LevelView;
import com.herdeliaslegacy.openpixelengine.R;

import java.util.Observable;
import java.util.Observer;

public abstract class Level_Activity extends Activity implements Observer {
    protected static final String TAG = "LevelActivity";
    private LevelView mLevelView;
    private boolean mObserving;
    protected Level mLevel;
    private GameThread mgameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mLevelView = (LevelView) findViewById(R.id.level_view);
        mLevelView.setOnTouchListener(mLevelView);
        mLevel = Level.getInstance();
        mgameThread = new GameThread(mLevelView.getHolder(),mLevelView.getContext(),mLevelView);
    }

    /**
     * Starts the activity and the level
     */
    @Override
    protected void onStart(){
        super.onStart();
        registerObserver();
        mgameThread.start();
    }

    /**
     * On quit stop thread game
     */
    @Override
    protected void onStop() {
        super.onStop();
        mgameThread.interrupt();
        unregisterObserver();
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
