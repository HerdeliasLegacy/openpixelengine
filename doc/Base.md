# Your first level

You need 2 thing for start having a basic level

    1 Level
    2 LevelActivity

## Level

This is the model of your level. It will contain the logic and all the graphics of your game.   


```
    public class MyLevel extends Scene {
        
        private List<SpriteObject> myspritelist
        public MyLevel() {
        }
        
        public List<SpriteObject> getAllSprites(){
            return myspritelist;
        }
        
        public void update(){
            //todo do your game logic here
        }
    }
```

Only those two methods are requred for implementing a level.   

* getAllSprite() must return a list of all sprites wich need to be draw (background/decors/player)
* update() this is where you implement the gamelogic (moving player, update background and other stuff)


## Level Activity

This is the Activity wich manage all of your element for the level (Level Model, view, sounds, input ).    
Simply extend the SceneActity like this


```
    public class LevelActivity extends SceneActivity {    
        @Override
        public MyLevel loadResource(){
        
            //load your stuf
            
            return myLevel;
        }
        
         @Override
         public void update(final Observable observable, Object o) {
         
         }
    }
```

That all for the activity. With that you have an activity where the graphics were be displayed, 
a sound manager included into the activity and the call to the update method from the level

## Finally

This is a quick shot for having the core off your game. In another pages you will learn how to add sprite to your level
