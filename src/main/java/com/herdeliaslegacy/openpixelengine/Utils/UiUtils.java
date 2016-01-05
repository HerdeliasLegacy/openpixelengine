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

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Static class with somme utilities methods for setting element for the ui
 * this is the implementation of the IUiElement
 *
 * @author skad
 * @date 04/12/2015
 */
public class UiUtils {
    private UiUtils() {
    }

    /**
     * Set the id of the element
     * @param element element to set
     * @param name id to set
     */
    public static void setId(View element,String name){
        int id = element.getResources().getIdentifier(name, "id", element.getContext().getPackageName());
        element.setId(id);
    }

    /**
     * Set the layout of the element
     * @param element element to set
     */
    public static void setLayout(View element){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 0;
        layoutParams.leftMargin = 0;
        element.setLayoutParams(layoutParams);
    }

    /**
     * Set the position of the element
     * @param element element to set
     * @param x pos
     * @param y pos
     */
    public static void setPos(View element,int x, int y) {
        UiUtils.setX(element, x);
        UiUtils.setY(element, y);
    }

    /**
     * Set the x pos of the element
     * @param element element to set
     * @param x pos
     */
    public static void setX(View element,int x) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.leftMargin = x;
        element.setLayoutParams(params);
    }

    /**
     * Set the y pos of the element
     * @param element element to set
     * @param y pos
     */
    public static void setY(View element,int y) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.topMargin = y;
        element.setLayoutParams(params);
    }

    /**
     * Set texture of the element
     * @param element element to set
     * @param file filename of the texture, need a full path
     */
    public static void setTexture(View element,String file) {
        Context context = element.getContext();
        int id = context.getResources().getIdentifier(file, "drawable", context.getPackageName());
        element.setBackgroundResource(id);
    }

    /**
     * Set the size of the element
     * @param element element to set
     * @param width of the element
     * @param height of the element
     */
    public static void setSize(View element,int width, int height){
        UiUtils.setWidth(element, width);
        UiUtils.setHeight(element, height);
    }

    /**
     * Set the with of the element
     * @param element element to set
     * @param width of the element
     */
    public static void setWidth(View element,int width){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)element.getLayoutParams();
        params.width = width;
        element.setLayoutParams(params);
    }

    /**
     * Set the height of the element
     * @param element to set
     * @param height of the element
     */
    public static void setHeight(View element,int height){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.height = height;
        element.setLayoutParams(params);
    }
}