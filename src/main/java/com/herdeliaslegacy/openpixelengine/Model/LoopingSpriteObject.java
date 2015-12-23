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

package com.herdeliaslegacy.openpixelengine.Model;

/**
 * @author skad on 27/10/15.
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
     * @return step of the loop
     */
    public int getStep(){
        return mStep;
    }

    /**
     * Set the step of the loop
     * @param pixel step of the loop
     */
    public void setStep(int pixel){
        mStep = pixel;
    }

    /**
     * Loop the picture on X from pixels
     * @// TODO: 23/12/15 find a way to loop bitmap without being so ram hungry
     */
    private void Slide(){
        /**
        Bitmap temp = Bitmap.createBitmap(mOriginalSprite.getWidth(), mOriginalSprite.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas combiningCanvas = new Canvas(temp);
        combiningCanvas.drawBitmap(mOriginalSprite, 0 - mStep, 0, null);
        combiningCanvas.drawBitmap(mOriginalSprite, temp.getWidth() - mStep, 0, null);
        mOriginalSprite.recycle();
        mOriginalSprite = temp;
        super.resize();
         */
        super.setXPos((int)super.getXPos()-mStep);
    }

    @Override
    protected void updateChild() {
        this.Slide();
    }
}
