# Adding sprite to your first level

## Sprite class
First create a class for our sprites. This class need extending SpriteObject model

```
    public class MySprite extends SpriteObject{
        public MySprite() {}
    
        @Override
        protected void updateChild() {
            //do nothing because our sprite is not doing anything, it's a test sprite :p
        }
    }
```

After you need to include it into your scene model.   

## Updating base exemple
### MyScene

For that you need:

- a method into the scene class for adding a sprite
- update the getAllSprites method for returning all of your sprites.

myscene.java :   

```
    ...
    /**
     * Method for adding a sprite to the sprite list for the decors
     * @param sprite to add
     */
    public void addSprite(MySprite sprite){
        myspritelist.add(sprite);
    }

    public List<SpriteObject> getAllSprites(){
        List<SpriteObject> spriteList = new ArrayList<SpriteObject>();

        spriteList.addAll(myspritelist);
        
        return spriteList;
    }
```

### LevelActivity
You need to update the loadResource() method into levelActivity.java. This is this method wich load all the stuff.   

levelActivity.java
```
    ...
    @Override
    public MyLevel loadResource(){

        MyLevel myLevel = new MyLevel();

        MySprite sprite  = new MySprite();
        sprite.setSize(size.getInt(0), size.getInt(1));
        sprite.setPosition(pos.getInt(0), pos.getInt(1));
        sprite.setSprite("full path of the png/jpeg file");
        
        myLevel.addSprite(sprite)

        return myLevel;
    }
```

After all of this, just run and you will see your sprite on the screen