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

/**
 * Class representing a point into an plan
 * @author skad
 * @date 15/01/16
 */
public class Point2D {
    protected double mX; // x value of the vector
    protected double mY; // y value of the vector

    /**
     * default constructor
     */
    public Point2D() {
        this(0,0);
    }

    /**
     * Constructor of the Point2D class
     *
     * @param x value
     * @param y value
     */
    public Point2D(double x, double y) {
        mX = x;
        mY = y;
    }

    /**
     * Copy constructor
     * @param pts pts to duplicate
     */
    public Point2D(Point2D pts){
        mX = pts.getX();
        mY = pts.getY();
    }

    /**
     * Return the X value of the actual instance of the vector
     * @return x of the vector
     */
    public double getX() {
        return mX;
    }

    /**
     * Set the X value of the actual instance of the vector
     * @param x of the vector
     */
    public void setX(double x) {
        mX = x;
    }

    /**
     * Return the Y value of the actual instance of the vector
     * @return y of the vector
     */
    public double getY() {
        return mY;
    }

    /**
     * Set the Y value of the actual instance of the vector
     * @param y of the vector
     */
    public void setY(double y) {
        mY = y;
    }

    /**
     * Return the String of the actual instance of the vector
     * @return Return the String of the vector2D
     */
    public String toString() {
        return "X " + mX + " Y " + mY;
    }


    /**
     * Clone the object
     */
    @Override
    public Point2D clone(){
        return new Point2D(this);
    }
}
