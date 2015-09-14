package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
public class Player extends MovingSpriteObject {
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Player(Vector2D pos, int width, int height) {
        super(pos, width, height);
    }
}
