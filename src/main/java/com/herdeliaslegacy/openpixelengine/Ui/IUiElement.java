package com.herdeliaslegacy.openpixelengine.Ui;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by skad on 04/12/15.
 * COPYRIGHT
 */
public interface IUiElement {

    public void setPos(int x, int y);

    public void setX(int x);

    public void setY(int y);

    public void setSize(int x, int y);

    public void setTexture(String file);
}
