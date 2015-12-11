package com.herdeliaslegacy.openpixelengine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;

import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;
import com.herdeliaslegacy.openpixelengine.Model.Vector2D;

import java.security.PrivateKey;

/**
 * Created by skad on 08/09/15.
 */
public class SceneView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "LevelView";
    private DisplayMetrics mScreenSize=null;


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


    public void setScreenMetric(DisplayMetrics metric){
        this.mScreenSize = metric;
    }
    public void drawScene(Scene scene){
        drawSpriteElements(scene);

    }

    /**
     * Draw the scene's content in our view
     * @param scene what to draw
     */
    private void drawSpriteElements(Scene scene) {
        SurfaceHolder holder =  this.getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            for (final SpriteObject sprite : scene.getAllSprites()) {
                Vector2D temp = this.computeScreenPosition(sprite);
                canvas.drawBitmap(sprite.getScaledSprite(), (int)temp.getX(), (int)temp.getY(), null);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Compute the screen position for the Sprite pass into params
     * @param spriteObject
     * @return
     */
    private Vector2D computeScreenPosition(SpriteObject spriteObject){
        Vector2D screenpos = new Vector2D(0,0);
        screenpos.setX(spriteObject.getXPos());
        screenpos.setY(mScreenSize.heightPixels - spriteObject.getYPos() - spriteObject.getHeight());

        return screenpos;
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
