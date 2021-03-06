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

package com.herdeliaslegacy.openpixelengine.Utils;

/**
 * @author skad
 * @date 03/09/15.
 * Original from pschmitt
 */
import com.herdeliaslegacy.openpixelengine.Model.Vector2D;

public class MathUtils {

    static public final float PI = 3.1415927f;                      // PI constant
    static public final float radiansToDegrees = 180f / PI;         // RadiansToDegrees ration const

    private MathUtils() {
    }

    /**
     * This method returns zero if input is negative
     * or max - 1 if input > max
     *
     * @param number   Input number
     * @param maxValue Maximum value
     * @return Either 0, max - 1 or input
     */
    public static int maxOrZero(int number, int maxValue) {
        if (number < 0) {
            return 0;
        }
        if (number >= maxValue) {
            return maxValue - 1;
        }
        return number;
    }

    /**
     * Generate a random vector Vector2D between 0 and Max
     *
     * @param xMax value
     * @param yMax value
     * @return a new vector2D
     */
    public static Vector2D randomVector(int xMax, int yMax) {
        int lower = 0;
        return new Vector2D((int) (Math.random() * (xMax - lower)) + lower, (int) (Math.random() * (yMax - lower)) + lower);
    }

    /**
     * Generate a double into [O - xMax]
     * @param xMax value
     * @return random double
     */
    public static double randomDouble(int xMax) {
        int lower = 0;
        return (Math.random() * (xMax - lower)) + lower;
    }

    /**
     * Generate a int into [O - xMax]
     * @param xMax value
     * @return random int
     */
    public static int randomInt(int xMax) {
        int lower = 0;
        return (int) ((Math.random() * (xMax - lower)) + lower);
    }

    /**
     * Generate a new Vector2D from X1/Y1 to X2/Y2 (2 points)
     * @param X1 x point 1
     * @param Y1 y point 1
     * @param X2 x point 2
     * @param Y2 y point 2
     * @return new Vector2D from pt1 to pt2
     */
    public static Vector2D vectorFromPoint(double X1, double Y1, double X2, double Y2) {
        Vector2D vector = new Vector2D(X2 - X1, Y2 - Y1);
        return vector.normalize();
    }

    /**
     * Compute the angle between two point.
     *
     * @param x point 1
     * @param y point 1
     * @param targetX point 2
     * @param targetY point 2
     * @return Return the angle
     */
    public static float angleFromTwoPoint(int x, int y, int targetX, int targetY) {
        float angle;
        double dx = targetX - x;
        double dy = targetY - y;

        angle = (float) Math.toDegrees(Math.atan2(dy, dx));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

}
