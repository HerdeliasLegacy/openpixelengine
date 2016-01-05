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

import android.os.SystemClock;

/**
 * Represent a SpriteObject which use gravity to move
 *
 * @author skad
 * @date 14/09/15.
 */
public abstract class GravitatingMovingSpriteObject extends SpriteObject{
    private Vector2D mGravity;                      //gravity applying during the game
    private long mTime;                             //time for applying the force
    private Vector2D mV0;                           //initial force
    private Vector2D mPos0;                         //initial position for applying the force

    /**
     * Default constructor
     */
    public GravitatingMovingSpriteObject(){
        this(0,0,0,0);
    }

    /**
     * Constructor of the GravitatingMovingSpriteObject
     *
     * @param x position of the sprite
     * @param y position of the sprite
     * @param width of the sprite
     * @param height of the sprite
     */
    public GravitatingMovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    /**
     * Constructor of the GravitatingMovingSpriteObject but take an Vector2D instead of X and Y
     *
     * @param pos Vector2D position
     * @param width of the sprite
     * @param height of the sprite
     */
    public GravitatingMovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        setNewForce(new Vector2D(0, 0));
    }

    /**
     * Copy constructor
     * @param object GravitatingMovingSpriteObject to copy
     */
    public GravitatingMovingSpriteObject(GravitatingMovingSpriteObject object) {
        super(object);
        setNewForce(object.mV0);
    }

    /**
     * Setting the gravity of the object
     * @param gravity gravity
     */
    public void setGravity(Vector2D gravity){
        mGravity = gravity;
    }

    /**
     * Applying a force to the object
     * @param mvt force to aply
     */
    public void setNewForce(Vector2D mvt){
        mTime = SystemClock.elapsedRealtime();
        mPos0 = mPosition;
        mV0 = mvt;
    }

    /**
     * Update method
     * Compute the position each time it's called
     */
    @Override
    protected void updateChild(){
        mPosition = computingPos();
    }

    /**
     * Compute the pos of the object with gravity and force at a t time
     * @return position of the object
     */
    protected Vector2D computingPos(){
        long time =  (SystemClock.elapsedRealtime() - mTime)/50;
        Vector2D temp = new Vector2D(mPosition.getX(),0);
        temp.setX(mV0.getX() * Math.cos(90) * time);
        temp.setY(-0.5 * mGravity.getY() * Math.pow(time, 2) - mV0.getY() * Math.sin(90) * time + mPos0.getY());
        return temp;
    }

    /**
     * Return the String of the SpriteObject like that : super.tostring + gravity + force
     *
     * @return Return the String of the SpriteObject
     * @see Scene#toString() for the utiliti
     */
    public String toString(){
        return super.toString()+ ", gravity: " + mGravity + ", force: [ initial position: "+mPos0+": force: "+mV0+"]";
    }
}
