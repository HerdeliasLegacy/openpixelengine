package com.herdeliaslegacy.openpixelengine.Model;

import android.os.SystemClock;

/**
 * Created by skad on 14/09/15.
 * Represent an object witch use gravity to move
 */
public abstract class GravitingMovingSpriteObject extends SpriteObject{
    private Vector2D mGravity; //gravity applying during the game
    private long mTime; //time for applying the force
    private Vector2D mV0; //force initial
    private Vector2D mPos0; // posinitial for appling the force

    public GravitingMovingSpriteObject(){
        this(0,0,0,0);
    }
    public GravitingMovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public GravitingMovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        setNewForce(new Vector2D(0,0));
    }

    public GravitingMovingSpriteObject(GravitingMovingSpriteObject object) {
        super(object);
        setNewForce(object.mV0);
    }

    /**
     * Applying the pass gravity to the object
     */
    public void setGravity(Vector2D grav){

        mGravity = grav;
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
        Vector2D retour = new Vector2D(mPosition.getX(),0);
        retour.setX(mV0.getX() * Math.cos(90) * time);
        retour.setY(-0.5*mGravity.getY()*Math.pow(time, 2)-mV0.getY()*Math.sin(90)*time+mPos0.getY());
        return retour;
    }
}
