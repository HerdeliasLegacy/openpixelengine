package com.herdeliaslegacy.openpixelengine.Listener;

import android.view.MotionEvent;
import android.view.View;

import com.herdeliaslegacy.openpixelengine.Model.Level;
import com.herdeliaslegacy.openpixelengine.Model.Vector2D;
import com.herdeliaslegacy.openpixelengine.Utils.MathUtils;

/**
 * Created by skad on 21/10/15.
 * COPYRIGHT
 */
public class OnTouch implements View.OnTouchListener {
    private float downX;
    private float downY;
    private Level mLevel;

    public OnTouch(Level level){
        mLevel = level;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                this.downX = event.getX();
                this.downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP:
                Vector2D swipe = MathUtils.vectorFromPoint(downX, downY, event.getX(), event.getY());
                mLevel.setForceToPlayer(swipe);
                return true;
        }
        return false;
    }
}
