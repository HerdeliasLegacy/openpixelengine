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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.herdeliaslegacy.openpixelengine.Utils.MathUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent a Sprite object that can be drawing on the screen
 * @author skad
 * @date 08/09/15.
 * Original by skad
 */
abstract public class SpriteObject {

    public enum CollisionPosition {
        TOP_LEFT,TOP_CENTER,TOP_RIGHT,
        CENTER_LEFT,CENTER_CENTER,CENTER_RIGHT,
        BOTTOM_LEFT,BOTTOM_CENTER,BOTTOM_RIGHT,
        NONE
    }
    /**
     *
     */
    protected Vector2D mPosition;                        // Position of the SpriteObject into level norm
    protected int mWidth;                                // With of the SpriteObject
    protected int mHeight;                               // Height of the SpriteObject
    protected Bitmap mScaledSprite;                      // Scaled BitmapElement with is the normal picture of the sprite
    protected Bitmap mOriginalSprite;                    // Original BitmapElement with is the normal picture of the sprite (needed for the rotation)
    protected final Map<String,Animation> mAnimationMap; // Map of animation ["Name":Animation]
    protected float mAngle;                              // Actual rotation of the sprite

    /**
     * Default constructor
     */
    public SpriteObject(){
        this(0,0,0,0);
    }

    /**
     * Constructor of the SpriteObject
     *
     * @param x position of the sprite
     * @param y position of the sprite
     * @param width of the sprite
     * @param height of the sprite
     */
    public SpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x, y),width,height);
    }

    /**
     * Constructor of the SpriteObject but take an Vector2D instead of X and Y
     *
     * @param pos Vector2D position
     * @param width of the sprite
     * @param height of the sprite
     */
    public SpriteObject(Vector2D pos, int width, int height) {
        mPosition = pos;
        mWidth = width;
        mHeight = height;
        mAnimationMap = new HashMap<>();
    }

    /**
     * Copy constructor
     * @param object SpriteObject to copy
     */
    protected SpriteObject(SpriteObject object)
    {
        mPosition = object.getPosition();
        mWidth = object.getWidth();
        mHeight = object.getHeight();
        mOriginalSprite = object.mOriginalSprite;
        mAnimationMap  = new HashMap<>();
        //Iterate over HashMap
        for(String key: object.mAnimationMap.keySet()){
            this.addAnimation(key, object.mAnimationMap.get(key).getAnimationFile(), object.mAnimationMap.get(key).getAnimationTime());
        }
        resize();
    }

    /**
     * Return the actual position of the object
     *
     * @return Return the actual position of the object
     * @see com.herdeliaslegacy.openpixelengine.Model.Vector2D
     */
    public Vector2D getPosition() {
        return mPosition;
    }

    /**
     * Set the actual position of the SpriteObject with the param
     *
     * @param position Vector2D position
     */
    public void setPosition(Vector2D position) {
        mPosition = position;
    }

    /**
     * Set the actual position of the SpriteObject with the param
     *
     * @param x position
     * @param y position
     */
    public void setPosition(double x, double y){
        this.setPosition(new Vector2D(x, y));
    }

    /**
     * Return the X position of the SpriteObject
     *
     * @return Return the X position of the SpriteObject
     */
    public double getXPos() {
        return mPosition.getX();
    }

    /**
     * Set the X position with the param
     *
     * @param xPos Set the X position with the param
     */
    public void setXPos(int xPos) {
        mPosition.setX(xPos);
    }

    /**
     * Return the Y position of the SpriteObject
     *
     * @return Return the Y position of the SpriteObject
     */
    public double getYPos() {
        return mPosition.getY();
    }

    /**
     * Set the Y position with the param
     *
     * @param yPos Set the Y position with the param
     */
    public void setYPos(double yPos) {
        mPosition.setY(yPos);
    }

    /**
     * Return the ScaledSprite BitmapElement
     *
     * @return Return the ScaledSprite BitmapElement
     */
    public Bitmap getScaledSprite() {
        return mScaledSprite;
    }

    /**
     * Calculate if a point is into the bounding rectangle of the sprite
     *
     * @param x position of the point
     * @param y position of the point
     * @return Return true if X and Y is into the bounding rectangle of the sprite else false
     */
    public boolean isInto(int x, int y) {
        return getBoundingRectangle().contains(x, y);
    }

    /**
     * Return the Width of the SpriteObject
     *
     * @return Return the Width of the SpriteObject
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * Set the Width of the SpriteObject
     *
     * @param width of the sprite
     */
    public void setWidth(int width) {
        mWidth = width;
    }

    /**
     * Return the Height of the SpriteObject
     *
     * @return Return the Height of the SpriteObject
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * Set the Height of the SpriteObject
     *
     * @param height of the sprite
     */
    public void setHeight(int height) {
        mHeight = height;
    }

    /**
     * Set the size of the SpriteObject
     * @param width of the sprite
     * @param height of the sprite
     */
    public void setSize(int width,int height){
        mWidth = width;
        mHeight = height;
    }

    /**
     * Return the rotation of the sprite
     * @return rotation
     */
    public float getAngle() {
        return mAngle;
    }

    /**
     * Set the rotation of the sprite
     * @param mAngle angle to set
     */
    public void setAngle(float mAngle) {
        this.mAngle = mAngle;
    }

    /**
     * Load Sprite from file.
     * Need the full path for the file
     * @param spriteFile sprite name
     */
    public void setSprite(String spriteFile) {
        mOriginalSprite = BitmapFactory.decodeFile(spriteFile);
        resize();
    }

    /**
     * Add an animation to the sprite into the internal hasmap
     * @param name of the animation
     * @param animation filename of the animation
     * @param time of the animation
     */
    public void addAnimation(String name, String animation,double time) {
        Animation anim = new Animation(animation);
        anim.computeMaxStepAnimation(mOriginalSprite.getWidth(),time);
        mAnimationMap.put(name, anim);
    }

    /**
     * Remove an animation from the internal hasmap
     * @param name of the animation
     */
    public void removeAnimation(String name) {
        mAnimationMap.remove(name);
    }

    /**
     * Resize the OriginalSprites with the with and the height of the SpriteObject and duplicate it into another var
     */
    public void resize() {
        if (mOriginalSprite != null) {
            mScaledSprite = Bitmap.createScaledBitmap(mOriginalSprite, getWidth(), getHeight(), false);
        }
    }

    /**
     * Return the bounding rectangle (aka the rectangle where the bitmap is on the screen) of the SpriteObject.
     *
     * @return new bounding rectangle
     */
    public Rect getBoundingRectangle() {
        return new Rect((int) mPosition.getX(), (int) mPosition.getY(), (int) mPosition.getX() + mWidth, (int) mPosition.getY() + mHeight);
    }

    /**
     * Check whether two SpriteObjects are intersecting
     *
     * @param object The other SpriteObject we may be intersecting with
     * @return A Vector2D representing the intersection between the two SpriteObjects, null if there's no intersection
     */
    public Vector2D intersects(SpriteObject object) {
        if (Rect.intersects(getBoundingRectangle(), object.getBoundingRectangle())) {
            Rect collisionBounds = getCollisionBounds(getBoundingRectangle(), object.getBoundingRectangle());
            for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
                for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                    // Deltas
                    int deltaX1 = MathUtils.maxOrZero(i - (int) mPosition.getX(), mScaledSprite.getWidth() - 1);
                    int deltaY1 = MathUtils.maxOrZero(j - (int) mPosition.getY(), mScaledSprite.getHeight() - 1);
                    int deltaX2 = MathUtils.maxOrZero(i - (int) object.getXPos(), object.getScaledSprite().getWidth() - 1);
                    int deltaY2 = MathUtils.maxOrZero(j - (int) object.getYPos(), object.getScaledSprite().getHeight() - 1);
                    // Pixel content
                    int bitmap1Pixel = mScaledSprite.getPixel(deltaX1, deltaY1);
                    int bitmap2Pixel = object.getScaledSprite().getPixel(deltaX2, deltaY2);
                    // If both pixels are filled (ie. non-transparent) the 2 bitmaps are intersecting
                    if (PixelIsFilled(bitmap1Pixel) && PixelIsFilled(bitmap2Pixel)) {
                        return new Vector2D(deltaX1, deltaY1);
                    }
                }
            }
        }
        return null;
    }

    /**
     * return the enum for the position of collision
     * @param collision Vector 2D from this.intersect
     * @return ColitionPosition
     */
    public CollisionPosition whereCollide(Vector2D collision) {
        String temp;
        int widthOntThree = this.getWidth()/3;
        int heightOnThree = this.getHeight()/3;

        if(collision.getY() < heightOnThree){
            temp = "BOTTOM";
        }else if (collision.getY() >= heightOnThree && collision.getY() < 2*heightOnThree){
            temp = "CENTER";
        }
        else {
            temp = "TOP";
        }

        temp += "_";

        if(collision.getX() < widthOntThree){
            temp += "LEFT";
        }else if (collision.getX() >= widthOntThree && collision.getX() < 2*widthOntThree){
            temp += "CENTER";
        }
        else {
            temp += "RIGHT";
        }

        return CollisionPosition.valueOf(temp.toUpperCase());
    }

    /**
     * Compute the collision rectangle between two rect and return it
     *
     * @param rect1 to test
     * @param rect2 to test
     * @return collision rectangle
     */
    private Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    /**
     * Calculate if the param pixel contain a color
     *
     * @param pixel to test
     * @return boolean
     */
    private boolean PixelIsFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }

    /**
     * Rotate the actual SpriteObject into the target direction
     * this one only compute the angle needed by the rotation.
     *
     * @param targetX of the target
     * @param targetY of the target
     * @see com.herdeliaslegacy.openpixelengine.Model.SpriteObject#rotate(float) for the real rotation
     */
    public void rotate(int targetX, int targetY) {
        rotate(MathUtils.angleFromTwoPoint(getBoundingRectangle().centerX(), getBoundingRectangle().centerY(), targetX, targetY));
    }

    /**
     * Rotate the sprite from the angle
     * @param angle to rotate
     */
    public void rotate(float angle) {
        Matrix matrix = new Matrix();
        mAngle = angle;
        matrix.postRotate(mAngle);
        resize();
        if (mScaledSprite != null) {
            mScaledSprite = Bitmap.createBitmap(mOriginalSprite, 0, 0, mWidth, mHeight, matrix, true);
        }
    }

    /**
     * Return the String of the SpriteObject like that : x/y/width/height/angle
     *
     * @return Return the String of the SpriteObject
     * @see Scene#toString() for the utiliti
     */
    @Override
    public String toString() {
        return "pos: ("+mPosition+ "), size: [" + getWidth() + "x" + getHeight() + "], angle:" + getAngle();
    }

    /**
     * Update the sprite with the animation and call the updatechild method
     */
    public void update(){
       if(mAnimationMap.containsKey("default")){Animation anim = mAnimationMap.get("default");
           Bitmap frame =  anim.animate(mOriginalSprite.getWidth(),mOriginalSprite.getHeight());
           if(frame != null){
               mOriginalSprite = frame;
               resize();
           }
       }
        updateChild();
    }

    /**
     * Update method for the child.
     * Must be overided
     */
    protected abstract void updateChild();
}
