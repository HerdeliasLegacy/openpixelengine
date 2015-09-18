package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
public abstract class MovingSpriteObject extends SpriteObject{

    private Vector2D mMovingDirection;
    private double mVelocity;

    public MovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public MovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        mMovingDirection = new Vector2D(0,0);
        mVelocity = 0;
    }

    public MovingSpriteObject(MovingSpriteObject object) {
        super(object);
        mVelocity = object.getVelocity();
    }

    public Vector2D getMovingDirection() {
        return mMovingDirection;
    }

    public void setMovingDirection(Vector2D mMovingDirection) {
        this.mMovingDirection = mMovingDirection;
    }

    public double getVelocity() {
        return mVelocity;
    }

    public void setVelocity(double mVelocity) {
        this.mVelocity = mVelocity;
    }

    /**
     * Move forward into the direction
     */
    public void forward()
    {
        mPosition = mPosition.add(mMovingDirection.multBynumber(mVelocity));
    }

    /**
     * Move backward into the direction
     */
    public void backward(){
        mPosition = mPosition.sub(mMovingDirection.multBynumber(mVelocity));
    }

    /**
     * Applying the pass gravity to the object
     */
    public void applyGravity(Vector2D grav){

        mPosition = mPosition.add(grav);
    }
}
