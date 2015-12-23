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

import com.herdeliaslegacy.openpixelengine.Utils.MathUtils;

/**
 * Created by skad on 03/09/15.
 * Original from pschmitt
 */


public class Vector2D {
    private double mX;
    private double mY;

    /**
     * Constructor of the Vector3D class
     *
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        mX = x;
        mY = y;
    }

    /**
     * Compute and return the length (norm) of the vector
     * @return Double
     */
    public double length() {
        return Math.sqrt((mX * mX) + (mY * mY));
    }

    /**
     * Normalize the Vector2D and return it
     */
    public Vector2D normalize() {
        double normX = 0.0;
        double normY = 0.0;
        double len = length();
        if (Math.abs(len) > 0.0) {
            normX = mX / len;
            normY = mY / len;
        }

        return new Vector2D(normX,normY);
    }

    /**
     * Addition the actual instance of the vector with the param Vector2D
     * @param vector
     */
    public Vector2D add(Vector2D vector) {
        return new Vector2D(mX+vector.getX(),mY+vector.getY());
    }

    /**
     * Remove the actual instance of the vector with the param Vector2D
     * @param vector
     */
    public Vector2D sub(Vector2D vector) {
        return new Vector2D(mX-vector.getX(),mY-vector.getY());
    }


    /**
     * Multiply the actual instance of the vector by a number
     * @param number
     * @return
     */
    public Vector2D multiplyByNumber(double number){
        return multiplyByVector2D(new Vector2D(number, number));
    }

    /**
     * Multiply the actual instance of the vector with a Vector2D
     * @param vector
     */
    public Vector2D multiplyByVector2D(Vector2D vector) {
        return new Vector2D(mX*vector.getX(),mY*vector.getY())
;    }

    /**
     * Divide the actual instance of the vector with the param Vector2D
     * @param vector
     */
    public Vector2D divideByVector2D(Vector2D vector) {
        return new Vector2D(mX/vector.getX(),mY/vector.getY());
    }

    /**
     * Return the scalar product of the actual instance of the vector and the param
     * @param vector
     * @return new Vector2D
     */
    public double scalar(Vector2D vector) {

        return (mX * vector.getX()) + (mY * vector.getY());
    }

    /**
     * Return a new vector witch is the normal vector of the actual instance of vector
     * @return new Vector2D
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public Vector2D getNormalVector() {
        return new Vector2D(mY, -mX);
    }

    /**
     * Return a new vector witch is the invert of the actual instance of the vector
     * @return new Vector2D
     */
    public Vector2D invert() {
        return new Vector2D(-mX, -mY);
    }

    /**
     * Return the angle with the actual instance of the vector and the I vector of the norm
     * @return float
     */
    public float angle() {
        float angle = (float) Math.atan2(mY, mX) * MathUtils.radiansToDegrees;
        if (angle < 0) angle += 360;
        return angle;
    }

    /**
     * Return the X value of the actual instance of the vector
     * @return int
     */
    public double getX() {
        return mX;
    }

    /**
     * Set the X value of the actual instance of the vector
     * @param x
     */
    public void setX(double x) {
        mX = x;
    }

    /**
     * Return the Y value of the actual instance of the vector
     * @return int
     */
    public double getY() {
        return mY;
    }

    /**
     * Set the Y value of the actual instance of the vector
     * @param y
     */
    public void setY(double y) {
        mY = y;
    }

    /**
     * Set the X and Y value of the actual instance of the vector
     * @param x
     * @param y
     */
    public void setXAndY(double x, double y) {
        mX = x;
        mY = y;
    }

    /**
     * Return a string of the actual instance of the vector
     * @return String
     */
    public String toString() {
        return "X " + mX + " Y " + mY;
    }
}
