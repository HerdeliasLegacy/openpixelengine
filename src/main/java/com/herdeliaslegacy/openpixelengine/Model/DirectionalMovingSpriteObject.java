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
 * @author skad on 16/09/15.
 * Represent a Sprite Object which can be moved around the scene witch a directional vector
 */
public class DirectionalMovingSpriteObject extends SpriteObject {
    private Vector2D mMovingDirection;
    private double mVelocity;

    public DirectionalMovingSpriteObject(){
        this(0,0,0,0);
    }
    public DirectionalMovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public DirectionalMovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        mMovingDirection = new Vector2D(0,0);
        mVelocity = 0;
    }

    public DirectionalMovingSpriteObject(DirectionalMovingSpriteObject object) {
        super(object);
        mMovingDirection = object.getMovingDirection();
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
        mPosition = mPosition.add(mMovingDirection.multiplyByNumber(mVelocity));
    }

    /**
     * Move backward into the direction
     */
    public void backward(){
        mPosition = mPosition.sub(mMovingDirection.multiplyByNumber(mVelocity));
    }

    @Override
    protected void updateChild() {
        forward();
    }

    public String toString(){
        return super.toString()+ ", direction: (" + mMovingDirection + "), velocity: "+mVelocity;
    }
}
