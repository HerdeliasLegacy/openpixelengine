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
 * Copyright (c) 2016
 */

package com.herdeliaslegacy.openpixelengine.Model;

import android.util.Log;

/**
 * This is camera class
 * it's represent the view of the screen (0,0) is the bottom left of the object
 * @author skad
 * @date 15/01/16
 */
public class Camera {
    Point2D mPosition;                                     //mPosition of the camera into the scene
    int mWidth;                                             //with of the camera
    int mHeight;                                             //mHeight of the camera

    /**
     * Default constructor
     */
    public Camera() {
        this(new Vector2D(0,0),0,0);
    }

    /**
     * Constructor with only int
     * @param x pos
     * @param y pos
     * @param width size
     * @param height size
     */
    public Camera(int x,int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    /**
     * Constructor
     * @param position of the camera
     * @param width width of the camera in pixel
     * @param height height of the camera in pixel
     */
    public Camera(Vector2D position, int width, int height) {
        this.mPosition = position;
        this.mWidth = width;
        this.mHeight = height;
    }

    /**
     * Return the position of the camera
     * @return cam position
     */
    public Point2D getPosition() {
        return mPosition;
    }

    /**
     * Set the camera postion
     * @param mPosition position
     */
    public void setPosition(Point2D mPosition) {
        this.mPosition = mPosition;
    }

    /**
     * Return the Width of the camera
     * @return width in px
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * Set the width of the camera
     * @param mWidth in px
     */
    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    /**
     * Return the Height of the camera
     * @return height in px
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * Set the height of the camera
     * @param mHeight in px
     */
    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }


    /**
     * Test if the vector pass in parameters is into the view of the camera
     * @param position Vector to test
     * @return true if the vector in into the camera
     */
    public boolean contains(Vector2D position){
        return position.getX() >= mPosition.getX() &&
                position.getX() <= (mPosition.getX() + mWidth) &&
                position.getY() >= mPosition.getY() &&
                position.getY() <= (mPosition.getY() + mHeight);
    }

    /**
     * To String Method in case off
     * @return desctiption string of the camera
     */
    public String toString(){
        return "Camera pos: ("+mPosition+ "), size: [" + getWidth() + "x" + getHeight() + "]";
    }
}
