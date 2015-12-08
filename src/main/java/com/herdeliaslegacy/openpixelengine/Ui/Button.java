package com.herdeliaslegacy.openpixelengine.Ui;

import android.content.Context;

import com.herdeliaslegacy.openpixelengine.Utils.UiUtils;

/**
 * Created by skad on 07/12/15.
 * COPYRIGHT
 */
public class Button extends android.widget.Button implements IUiElement {

    public Button(Context context) {
        super(context);
        UiUtils.setLayout(this);

    }

    @Override
    public void setId(String id) {
        UiUtils.setId(this,id);
    }

    @Override
    public void setPos(int x, int y) {
        UiUtils.setPos(this, x, y);
    }

    @Override
    public void setX(int x) {
        UiUtils.setX(this, x);
    }

    @Override
    public void setY(int y) {
        UiUtils.setY(this, y);
    }

    @Override
    public void setSize(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
    }

    @Override
    public void setTexture(String file) {
        UiUtils.setTexture(this,file);
    }
}
