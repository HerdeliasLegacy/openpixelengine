package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
abstract public class MovingSpriteObject extends SpriteObject{
    protected MovingSpriteObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    protected MovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
    }
}
