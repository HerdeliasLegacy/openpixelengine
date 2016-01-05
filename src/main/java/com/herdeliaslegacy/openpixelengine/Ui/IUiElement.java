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

package com.herdeliaslegacy.openpixelengine.Ui;

/**
 * interface for Uielement
 * @author skad
 * @date 04/12/2015
 */
public interface IUiElement {

    /**
     * Set the id of the element
     * @param id name of the element ie: mycooluielement
     */
    void setId(String id);

    /**
     * Set the position on the screen
     * @param x pos
     * @param y pos
     */
    void setPos(int x, int y);

    /**
     * Just set x position
     * @param x pos
     */
    void setX(int x);

    /**
     * Just set y position
     * @param y pos
     */
    void setY(int y);

    /**
     * Set the size
     * @param w width
     * @param h height
     */
    void setSize(int w, int h);

    /**
     * Set the texture from the file
     * @param file filename need and absolute path
     */
    void setTexture(String file);
}
