package com.herdeliaslegacy.openpixelengine.Thread;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.herdeliaslegacy.openpixelengine.Model.Level;
import com.herdeliaslegacy.openpixelengine.View.LevelView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by skad on 10/09/15.
 * Original from pschmitt
 */
public class GameThread extends Thread implements Observer{
    private static final String TAG = "GameThread";

    private boolean mRunning;
    private boolean mPause;

    private Level mLevel;
    private LevelView mLevelView;
    private SurfaceHolder mSurfaceHolder;
    private Context mContext;

    public GameThread(SurfaceHolder surfaceHolder,Context context ,LevelView levelView) {
        mSurfaceHolder = surfaceHolder;
        mContext = context;
        mRunning = false;
        mPause = false;
        mLevelView = levelView;
        mLevel = Level.getInstance();
        mLevel.addObserver(this);
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
                if(!mPause){
                    mLevel.update();
                }
                Canvas canvas = mSurfaceHolder.lockCanvas();
                if (canvas != null) {
                    mLevelView.drawGameElements(canvas);
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setPause(){
        Log.d(TAG, "set pause");
        mPause = true;
    }

    public void unPause()
    {
        Log.d(TAG, "unset pause");
        mPause = false;
    }
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof Level && data instanceof Level.EVENT) {
            Level.EVENT event = (Level.EVENT) data;
            switch (event) {
                case GAME_OVER:
                case GAME_SUCCESS:
                    interrupt();
                    break;
            }
        }
    }

    @Override
    public void interrupt() {
        this.mRunning = false;
        Log.d(TAG, "interrupt running");
    }
}
