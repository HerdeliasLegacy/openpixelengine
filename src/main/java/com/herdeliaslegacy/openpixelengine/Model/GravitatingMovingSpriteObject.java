package com.herdeliaslegacy.openpixelengine.Model;

import android.os.SystemClock;

/**
 * Created by skad on 14/09/15.
 * Represent an object witch use gravity to move
 */
public abstract class GravitatingMovingSpriteObject extends SpriteObject{
    private Vector2D mGravity;                      //gravity applying during the game
    private long mTime;                             //time for applying the force
    private Vector2D mV0;                           //initial force
    private Vector2D mPos0;                         //initial position for applying the force

    public GravitatingMovingSpriteObject(){
        this(0,0,0,0);
    }
    public GravitatingMovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public GravitatingMovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        setNewForce(new Vector2D(0,0));
    }

    public GravitatingMovingSpriteObject(GravitatingMovingSpriteObject object) {
        super(object);
        setNewForce(object.mV0);
    }

    /**
     * Applying the pass gravity to the object
     */
    public void setGravity(Vector2D gravity){

        mGravity = gravity;
    }

    public void setNewForce(Vector2D mvt){
        mTime = SystemClock.elapsedRealtime();
        mPos0 = mPosition;
        mV0 = mvt;
    }


    @Override
    protected void updateChild(){
        mPosition = computingPos();
    }

    /**
     * Compute the pos of the object with gravity and force
     * @return
     */
    protected Vector2D computingPos(){
        long time =  (SystemClock.elapsedRealtime() - mTime)/50;
        Vector2D temp = new Vector2D(mPosition.getX(),0);
        temp.setX(mV0.getX() * Math.cos(90) * time);
        temp.setY(-0.5 * mGravity.getY() * Math.pow(time, 2) - mV0.getY() * Math.sin(90) * time + mPos0.getY());
        return temp;
    }
}
