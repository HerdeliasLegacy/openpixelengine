package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
public class Player extends MovingSpriteObject {
    public Player(){
        this(0,0,0,0);
    }
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

}
