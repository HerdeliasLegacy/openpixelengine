package com.herdeliaslegacy.openpixelengine.Ui;

import android.content.Context;
import android.widget.TextView;

import com.herdeliaslegacy.openpixelengine.Utils.UiUtils;

/**
 * Created by skad on 02/12/15.
 * COPYRIGHT
 */
public class TextBox extends TextView implements IUiElement {

    public TextBox(Context context) {
        super(context);
        UiUtils.setLayout(this);

    }

    @Override
    public void setPos(int x, int y) {
        UiUtils.setPos(this,x,y);
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
