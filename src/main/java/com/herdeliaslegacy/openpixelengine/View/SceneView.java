package com.herdeliaslegacy.openpixelengine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;

/**
 * Created by skad on 08/09/15.
 */
public class SceneView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "LevelView";


    public SceneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SurfaceHolder holder = super.getHolder();
        if (holder != null) {
            holder.addCallback(this);
        }
        // Make transparent so that we can see our background
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
    }

    /**
     * Draw the scene's content in our view
     * @param scene what to draw
     */
    public void drawGameElements(Scene scene) {
        SurfaceHolder holder =  this.getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            for (final SpriteObject sprite : scene.getAllSprites()) {
                canvas.drawBitmap(sprite.getScaledSprite(), (int) sprite.getXPos(), (int) sprite.getYPos(), null);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

}
