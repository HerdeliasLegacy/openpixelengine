package com.herdeliaslegacy.openpixelengine.Thread;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.View.SceneView;

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

    private Scene mScene;
    private SceneView mSceneView;
    private SurfaceHolder mSurfaceHolder;

    public GameThread(SceneView sceneview,Scene scene) {
        mRunning = false;
        mPause = false;
        mSceneView = sceneview;
        mSurfaceHolder = sceneview.getHolder();
        mScene = scene;
        mScene.addObserver(this);
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
                    mScene.update();
                }
                Canvas canvas = mSurfaceHolder.lockCanvas();
                if (canvas != null) {
                    mSceneView.drawGameElements(canvas, mScene);
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
        if (observable instanceof Scene && data instanceof Scene.EVENT) {
            Scene.EVENT event = (Scene.EVENT) data;
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
