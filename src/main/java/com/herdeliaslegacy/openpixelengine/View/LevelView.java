package com.herdeliaslegacy.openpixelengine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.herdeliaslegacy.openpixelengine.Model.Level;
import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;
import com.herdeliaslegacy.openpixelengine.Model.Vector2D;
import com.herdeliaslegacy.openpixelengine.Thread.GameThread;
import com.herdeliaslegacy.openpixelengine.Utils.MathUtils;

/**
 * Created by skad on 08/09/15.
 */
public class LevelView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private static final String TAG = "LevelView";
    private GameThread mGameThread;
    private SurfaceHolder mHolder;
    private float downX;
    private float downY;


    public LevelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SurfaceHolder holder = getHolder();
        if (holder != null) {
            holder.addCallback(this);
        }
        // Make transparent so that we can see our background
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
    }

    /**
     * Draw the level's content in our view
     * @param canvas Where to draw
     */
    public void drawGameElements(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (final SpriteObject sprite : Level.getInstance().getAllSprites()) {
            canvas.drawBitmap(sprite.getScaledSprite(), (int) sprite.getXPos(), (int) sprite.getYPos(), null);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mHolder = surfaceHolder;
        startLevelThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mGameThread.interrupt();
    }

    /**
     * Start LevelThread
     */
    public void startLevelThread(){
        mGameThread = new GameThread(mHolder, getContext(), this);
        mGameThread.start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    this.downX = event.getX();
                    this.downY = event.getY();
                    return true;
                }
                case MotionEvent.ACTION_UP:
                    Vector2D swipe = MathUtils.vectorFromPoint(downX, downY, event.getX(), event.getY());
                    Level.getInstance().setForceToPlayer(swipe);
                    return true;
            }
            return false;
    }
}
