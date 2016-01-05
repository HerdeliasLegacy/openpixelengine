/*
 *  This file is part of  OpenPixelEngine.
 *
 *     OpenPixelEngine is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or any later version.
 *
 *     OpenPixelEngine is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with OpenPixelEngine.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2015
 */

package com.herdeliaslegacy.openpixelengine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.herdeliaslegacy.openpixelengine.Model.Scene;
import com.herdeliaslegacy.openpixelengine.Model.SpriteObject;
import com.herdeliaslegacy.openpixelengine.Model.Vector2D;

/**
 * Surface view for drawing the scene
 * @author skad
 * @date 08/09/15.
 */
public class SceneView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "LevelView"; // Tag for login echo
    private DisplayMetrics mScreenSize=null;       // Metrics of the screen


    /**
     * Constructor
     *
     * @param context Parent activity
     * @param attributeSet Params activity
     */
    public SceneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SurfaceHolder holder = super.getHolder();
        if (holder != null) {
            holder.addCallback(this);
        }
        // Make transparent so that we can see our background
        setZOrderOnTop(true);
        assert holder != null;
        holder.setFormat(PixelFormat.TRANSPARENT);
    }


    /**
     * Set the metric of the screen
     * @param metric from the activity
     */
    public void setScreenMetric(DisplayMetrics metric){
        this.mScreenSize = metric;
    }

    /**
     * General drawing method.
     * Can call multiple drawing method
     * @param scene scene to draw
     */
    public void drawScene(Scene scene){
        drawSpriteElements(scene);
    }

    /**
     * Draw the scene's content in our view
     * @param scene scene to draw
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
     * @param spriteObject to compute
     * @return new position
     */
    private Vector2D computeScreenPosition(SpriteObject spriteObject){
        Vector2D position = new Vector2D(0,0);
        position.setX(spriteObject.getXPos());
        position.setY(mScreenSize.heightPixels - spriteObject.getYPos() - spriteObject.getHeight());

        return position;
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
