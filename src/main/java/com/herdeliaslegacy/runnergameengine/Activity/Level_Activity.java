package com.herdeliaslegacy.runnergameengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.herdeliaslegacy.runnergameengine.Model.Level;
import com.herdeliaslegacy.runnergameengine.R;
import com.herdeliaslegacy.runnergameengine.View.LevelView;

import java.util.Observable;
import java.util.Observer;

public class Level_Activity extends Activity implements Observer {
    private static final String TAG = "LevelActivity";
    private LevelView mLevelView;
    private boolean mObserving;
    private Level mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mLevelView = (LevelView) findViewById(R.id.level_view);
        mLevel = Level.getInstance();
        startLevel();
    }

    /**
     * Starts the level
     */
    private void startLevel() {
        //mLevelView.startLevelThread();
        registerObserver();
    }

    /**
     * Pauses the game
     */
    private void pauseGame() {
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
