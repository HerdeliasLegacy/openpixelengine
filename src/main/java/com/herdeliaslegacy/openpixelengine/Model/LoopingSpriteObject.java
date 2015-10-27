package com.herdeliaslegacy.openpixelengine.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by skad on 27/10/15.
 * COPYRIGHT
 */
public class LoopingSpriteObject extends SpriteObject {

    private int mStep;

    public LoopingSpriteObject() {
    }

    public LoopingSpriteObject(int x, int y, int width, int height, int step) {
        this(new Vector2D(x, y),width,height,step);
    }

    public LoopingSpriteObject(Vector2D pos, int width, int height, int step) {
        super(pos, width, height);
        this.mStep = step;
    }

    public LoopingSpriteObject(LoopingSpriteObject object) {
        super(object);
        this.mStep = object.mStep;
    }

    /**
     * return the step of the loop
     * @return
     */
    public int getStep(){
        return mStep;
    }

    /**
     * Set the step of the loop
     * @param pixel
     */
    public void setStep(int pixel){
        mStep = pixel;
    }
    /**
     * Loop the picture on X from pixel pixels
     * well it's work but need to find better way (timed update ?)
     */
    private void Slide(){
        Bitmap temp = Bitmap.createBitmap(mOriginalSprite.getWidth(), mOriginalSprite.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas conbiningcanvas = new Canvas(temp);
        conbiningcanvas.drawBitmap(mOriginalSprite, 0 - mStep, 0, null);
        conbiningcanvas.drawBitmap(mOriginalSprite, temp.getWidth() - mStep, 0, null);
        mOriginalSprite.recycle();
        mOriginalSprite = temp;
        super.resize();
    }

    @Override
    protected void updateChild() {
        this.Slide();
    }
}
