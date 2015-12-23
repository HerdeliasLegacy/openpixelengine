package com.herdeliaslegacy.openpixelengine.Utils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by skad on 04/12/15.
 * COPYRIGHT
 */
public class UiUtils {
    private UiUtils() {
    }


    public static void setId(View element,String name){
        int id = element.getResources().getIdentifier(name, "id", element.getContext().getPackageName());
        element.setId(id);
    }
    public static void setLayout(View element){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 0;
        layoutParams.leftMargin = 0;
        element.setLayoutParams(layoutParams);
    }
    public static void setPos(View element,int x, int y) {
        UiUtils.setX(element, x);
        UiUtils.setY(element, y);
    }

    public static void setX(View element,int x) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.leftMargin = x;
        element.setLayoutParams(params);
    }

    public static void setY(View element,int y) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.topMargin = y;
        element.setLayoutParams(params);
    }

    public static void setTexture(View element,String file) {
        Context context = element.getContext();
        int id = context.getResources().getIdentifier(file, "drawable", context.getPackageName());
        element.setBackgroundResource(id);
    }

    public static void setSize(View element,int width, int height){
        UiUtils.setWidth(element, width);
        UiUtils.setHeight(element, height);
    }

    public static void setWidth(View element,int width){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)element.getLayoutParams();
        params.width = width;
        element.setLayoutParams(params);
    }

    public static void setHeight(View element,int height){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) element.getLayoutParams();
        params.height = height;
        element.setLayoutParams(params);
    }
}