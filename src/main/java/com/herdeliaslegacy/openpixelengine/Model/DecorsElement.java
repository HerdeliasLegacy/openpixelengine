package com.herdeliaslegacy.openpixelengine.Model;

/**
 * Created by skad on 16/09/15.
 */
public class DecorsElement extends MovingSpriteObject {
    public DecorsElement(){
        this(0,0,0,0);
    }
    public DecorsElement(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public DecorsElement(Vector2D pos, int width, int height) {
        super(pos, width, height);
    }

    public DecorsElement(DecorsElement object) {
        super(object);
    }
    
    @Override
    protected Vector2D computingPos() {
        this.forward();
        return this.mPosition;
    }
}
