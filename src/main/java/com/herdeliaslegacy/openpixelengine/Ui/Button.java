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

import android.content.Context;

import com.herdeliaslegacy.openpixelengine.Utils.UiUtils;

/**
 * Represent a button which can be textured
 * @author skad
 * @date 07/12/15
 */
public class Button extends android.widget.Button implements IUiElement {

    /**
     * Constructor
     * @param context generally an activity
     */
    public Button(Context context) {
        super(context);
        UiUtils.setLayout(this);

    }

    /**
     * Set the id of the button
     * @param id name of the button ie: mycoolestbutton
     */
    @Override
    public void setId(String id) {
        UiUtils.setId(this,id);
    }

    /**
     * Set the position on the screen
     * @param x pos
     * @param y pos
     */
    @Override
    public void setPos(int x, int y) {
        UiUtils.setPos(this, x, y);
    }

    /**
     * Just set x position
     * @param x pos
     */
    @Override
    public void setX(int x) {
        UiUtils.setX(this, x);
    }

    /**
     * Just set y position
     * @param y pos
     */
    @Override
    public void setY(int y) {
        UiUtils.setY(this, y);
    }

    /**
     * Set the size
     * @param w width
     * @param h height
     */
    @Override
    public void setSize(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
    }

    /**
     * Set the texture from the file
     * @param file filename need and absolute path
     */
    @Override
    public void setTexture(String file) {
        UiUtils.setTexture(this,file);
    }
}
