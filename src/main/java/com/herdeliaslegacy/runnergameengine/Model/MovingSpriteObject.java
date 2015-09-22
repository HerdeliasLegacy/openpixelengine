package com.herdeliaslegacy.runnergameengine.Model;

/**
 * Created by skad on 14/09/15.
 */
public abstract class MovingSpriteObject extends SpriteObject{

    private Vector2D mMovingDirection;
    private double mVelocity;
    private Vector2D mGravity; //gravity applying during the game
    private int mTime; //time for applying the force
    private Vector2D mV0; //force initial
    private Vector2D mPos0; // posinitial for appling the force

    public MovingSpriteObject(int x, int y, int width, int height) {
        this(new Vector2D(x,y),width,height);
    }

    public MovingSpriteObject(Vector2D pos, int width, int height) {
        super(pos, width, height);
        mMovingDirection = new Vector2D(0,0);
        mVelocity = 0;
        setNewForce(new Vector2D(0,0));
    }

    public MovingSpriteObject(MovingSpriteObject object) {
        super(object);
        mVelocity = object.getVelocity();
    }

    public Vector2D getMovingDirection() {
        return mMovingDirection;
    }

    public void setMovingDirection(Vector2D mMovingDirection) {
        this.mMovingDirection = mMovingDirection;
    }

    public double getVelocity() {
        return mVelocity;
    }

    public void setVelocity(double mVelocity) {
        this.mVelocity = mVelocity;
    }

    /**
     * Move forward into the direction
     */
    public void forward()
    {
        mPosition = mPosition.add(mMovingDirection.multBynumber(mVelocity));
    }

    /**
     * Move backward into the direction
     */
    public void backward(){
        mPosition = mPosition.sub(mMovingDirection.multBynumber(mVelocity));
    }


    /**
     * Applying the pass gravity to the object
     */
    public void setGravity(Vector2D grav){

        mGravity = grav;
    }

    public void setNewForce(Vector2D mvt){
        mTime = 0;
        mPos0 = mPosition;
        mV0 = mvt;
    }

    public void update(){
        mPosition = computingPos();
    }
    /**
     * Compute the pos of the object with gravity and force
     * @return
     */
    private Vector2D computingPos(){
        Vector2D retour = new Vector2D(0,0);
        retour.setY(-0.5*mGravity.getY()*Math.pow(mTime,2)+mV0.getY()*Math.sin(90)*mTime+mPos0.getY());
        mTime+=1;
        return retour;
    }
}
