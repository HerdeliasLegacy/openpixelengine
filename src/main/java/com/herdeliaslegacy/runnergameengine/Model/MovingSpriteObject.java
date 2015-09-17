package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
public class MovingSpriteObject extends SpriteObject{
    public MovingSpriteObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public MovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
    }

    public MovingSpriteObject(SpriteObject object) {
        super(object);
    }
}
