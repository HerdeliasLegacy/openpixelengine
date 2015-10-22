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
 * Represent a generique object that can be drawing on the screen
 * Created by skad on 08/09/15.
 * Original by skad
 */
abstract public class SpriteObject {
    /**
     * Position of the SpriteObject into screen repert
     */
    protected Vector2D mPosition;
    /**
     * With of the SpriteObject
     */
    protected int mWidth;
    /**
     * Height of the SpriteObject
     */
    protected int mHeight;
    /**
     * Width Ratio between the original screen size were the lvl was design and the actual screen size
     */
    protected double mRatioWidth;
    /**
     * Height Ratio between the original screen size were the lvl was design and the actual screen size
     */
    protected double mRatioHeight;
    /**
     * Scaled BitmapElement with is the normal picture of the sprite
     */
    protected Bitmap mScaledSprite;
    /**
     * Original BitmapElement with is the normal picture of the sprite (needed for the rotation)
     */
    protected Bitmap mOriginalSprite;

    /**
     * Map for having some animation
     */
    protected Map<String,Animation> mAnimationMap;

    /**
     * Actual rotation of the sprite
     */
    protected float mAngle;

    /**
     * Default constructor
     */
    public SpriteObject(){
        this(0,0,0,0);
    }

    /**
     * Constructor of the SpriteObject
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public SpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x, y),width,height);
    }

    /**
     * Constructor of the SpriteObject but take an Vector2D instead of X and Y
     *
     * @param pos
     * @param width
     * @param height
     */
    public SpriteObject(Vector2D pos, int width, int height) {
        mPosition = pos;
        mWidth = width;
        mHeight = height;
        mAnimationMap = new HashMap<String, Animation>();
    }

    protected SpriteObject(SpriteObject object)
    {
        mPosition = object.getPosition();
        mWidth = object.getWidth();
        mHeight = object.getHeight();
        mOriginalSprite = object.mOriginalSprite;
        mAnimationMap  = object.mAnimationMap;
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
     * @param position
     * @see com.herdeliaslegacy.openpixelengine.Model.Vector2D
     */
    public void setPosition(Vector2D position) {
        mPosition = position;
    }

    /**
     * Set the actual position of the SpriteObject with the param
     *
     * @param x
     * @param y
     * @see com.herdeliaslegacy.openpixelengine.Model.Vector2D
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
     * @return Return the ScaledSprite BitmapElement or if(mShowAlternateSprite) return the mAlternateSprite
     */
    public Bitmap getScaledSprite() {
        return mScaledSprite;
    }

    /**
     * Calculate if a point is into the bounding rectangle of the sprite
     *
     * @param x
     * @param y
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
     * @param width
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
     * @param height
     */
    public void setHeight(int height) {
        mHeight = height;
    }

    /**
     * Set the size of the SpriteObject
     * @param width
     * @param height
     */
    public void setSize(int width,int height){
        mWidth = width;
        mHeight = height;
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float mAngle) {
        this.mAngle = mAngle;
    }

    /**
     * Load Sprite from file.
     * Need the full path for the file
     * @param spriteFile
     */
    public void setSprite(String spriteFile) {
        mOriginalSprite = BitmapFactory.decodeFile(spriteFile);
        resize();
    }

    public void setRatioWidth(double ratioWidth) {
        mRatioWidth = ratioWidth;
    }

    public void setRatioHeight(double ratioHeight) {
        mRatioHeight = ratioHeight;
    }

    public void addAnimation(String name, String animation,double time) {
        Animation anim = new Animation(animation);
        anim.computeMaxStepAnimation(mWidth,time);
        mAnimationMap.put(name, anim);
    }

    public void removeAnimation(String name) {
        mAnimationMap.remove(name);
    }
    /**
     * Resize the OriginalSprites with the with and the height of the SpriteObject
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
     * @see com.herdeliaslegacy.openpixelengine.Model.Vector2D
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
                    if (PixelisFilled(bitmap1Pixel) && PixelisFilled(bitmap2Pixel)) {
                        return new Vector2D(deltaX1, deltaY1);
                    }
                }
            }
        }
        return null;
    }

    public int whereColide(Vector2D colision) {
        return 0;
    }
    /**
     * Calcultate the collision rectangle between two rect and return it
     *
     * @param rect1
     * @param rect2
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
     * @param pixel
     * @return True if transparente else false
     */
    private boolean PixelisFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }

    /**
     * Rotate the actual SpriteObject into the target direction
     * this one only compute the angle needed by the rotation.
     *
     * @param targetX
     * @param targetY
     * @see com.herdeliaslegacy.openpixelengine.Model.SpriteObject#rotate(float) for the real rotation
     */
    public void rotate(int targetX, int targetY) {
        rotate(MathUtils.angleFromTwoPoint(getBoundingRectangle().centerX(), getBoundingRectangle().centerY(), targetX, targetY));
    }

    /**
     * Make the ScaledSprite
     * Need an angle for the rotation
     *
     * @param angle
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
     * @see Level#toString() for the utiliti
     */
    @Override
    public String toString() {
        return "pos : ("+mPosition+ "), size: [" + getWidth() + "x" + getHeight() + "], angle:" + getAngle();
    }

    /**
     * update object
     */
    public void update(){
       if(mAnimationMap.containsKey("default")){Animation anim = mAnimationMap.get("default");
           Bitmap frame =  anim.animate(mWidth,mHeight);
           if(frame != null){
               mOriginalSprite = frame;
               resize();
           }
       }
        updateChild();
    }

    /**
     * Updating method for the child
     */
    protected abstract void updateChild();
}
