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
 * Represent a Sprite Object which can be moved around the scene witch a directional vector
 *
 * @author skad
 * @date 16/09/15.
 */
public class DirectionalMovingSpriteObject extends SpriteObject {
    private Vector2D mMovingDirection; // Directional 2d Vector
    private double mVelocity;          // Velocity of the movement

    /**
     * Default constructor
     */
    public DirectionalMovingSpriteObject(){
        this(0,0,0,0);
    }

    /**
     * Constructor of the DirectionalMovingObject
     *
     * @param x mPosition of the sprite
     * @param y mPosition of the sprite
     * @param width of the sprite
     * @param height of the sprite
     */
    public DirectionalMovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    /**
     * Constructor of the DirectionalMovingObject but take an Vector2D instead of X and Y
     *
     * @param pos Vector2D mPosition
     * @param width of the sprite
     * @param height of the sprite
     */
    public DirectionalMovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        mMovingDirection = new Vector2D(0,0);
        mVelocity = 0;
    }

    /**
     * Copy constructor
     * @param object DirectionalMovingObject to copy
     */
    public DirectionalMovingSpriteObject(DirectionalMovingSpriteObject object) {
        super(object);
        mMovingDirection = object.getMovingDirection();
        mVelocity = object.getVelocity();
    }

    /**
     * Get the directional vector
     * @return direction
     */
    public Vector2D getMovingDirection() {
        return mMovingDirection;
    }

    /**
     * Set the directional vector
     * @param mMovingDirection direction
     */
    public void setMovingDirection(Vector2D mMovingDirection) {
        this.mMovingDirection = mMovingDirection;
    }

    /**
     * Get the velocity of the movement
     * @return velocity
     */
    public double getVelocity() {
        return mVelocity;
    }

    /**
     * Set the velocity of the movement
     * @param mVelocity velocity
     */
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

    /**
     * Update method
     * Just do a step in forward
     */
    @Override
    protected void updateChild() {
        forward();
    }

    /**
     * Return the String of the SpriteObject like that : super.tostring + direction + velocity
     *
     * @return Return the String of the SpriteObject
     * @see Scene#toString() for the utiliti
     */
    public String toString(){
        return super.toString()+ ", direction: " + mMovingDirection + ", velocity: "+mVelocity;
    }
}
