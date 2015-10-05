package com.herdeliaslegacy.runnergameengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.herdeliaslegacy.runnergameengine.Model.DecorsElement;
import com.herdeliaslegacy.runnergameengine.Model.Level;
import com.herdeliaslegacy.runnergameengine.Model.Player;
import com.herdeliaslegacy.runnergameengine.Model.Vector2D;
import com.herdeliaslegacy.runnergameengine.R;
import com.herdeliaslegacy.runnergameengine.Utils.FileUtils;
import com.herdeliaslegacy.runnergameengine.Utils.JsonParser;
import com.herdeliaslegacy.runnergameengine.View.LevelView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Level_Activity extends Activity implements Observer {
    private static final String TAG = "LevelActivity";
    private LevelView mLevelView;
    private boolean mObserving;
    private Level mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mLevelView = (LevelView) findViewById(R.id.level_view);
        mLevelView.setOnTouchListener(mLevelView);
        mLevel = Level.getInstance();
        loadLevel();
    }

    /**
     * Starts the level
     */
    private void loadLevel() {
        loadResource();
        registerObserver();
    }

    /**
     * Load Resource
     */
    public void loadResource(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mLevel.setmMaxXDraw(metrics.widthPixels);
        mLevel.setmMaxYDraw(metrics.heightPixels);
        if(loadLevelFromFile("0"))
        {
            mLevel.generateLevelStart();
        }
    }

    private boolean loadLevelFromFile(String number){
        JSONObject jsonLevel;
        Player player;
        DecorsElement decorsElement;

        boolean returnvalue = false;
        try {
            //Todo need definir what is default and what is not into the level file
            //loading json file
            jsonLevel = JsonParser.getFileContent(this, "main.json");
            jsonLevel = JsonParser.getFileContent(this, jsonLevel.getJSONArray("levels").getString(0));
            //setting the velocity
            mLevel.setmVelocity(jsonLevel.getDouble("default_velocity"));
            //setting the player
            JSONObject jsonplayer = jsonLevel.getJSONObject("player");
            JSONArray playerpos = jsonplayer.getJSONArray("start_pos");
            JSONArray playersize = jsonplayer.getJSONArray("size");

            player = new Player();
            player.setPosition(new Vector2D(playerpos.getInt(0),playerpos.getInt(1)));
            player.setSize(playersize.getInt(0),playersize.getInt(1));
            player.setSprite(FileUtils.getFile(this, jsonplayer.getString("sprite")));


            //setting the animations list
            JSONArray animationArray = jsonplayer.getJSONArray("animations");
            for (int i = 0 ; i < animationArray.length(); i++) {
                JSONObject animation = animationArray.getJSONObject(i);
                player.addAnimation(animation.getString("name"),FileUtils.getFile(this,animation.getString("sprite")));
            }

            mLevel.add(player);

            //setting the decors elements list
            JSONArray decorsArray = jsonLevel.getJSONArray("decorelements");
            for (int i = 0 ; i < decorsArray.length(); i++) {
                JSONObject decor = decorsArray.getJSONObject(i);
                decorsElement = new DecorsElement();
                decorsElement.setSize(decor.getJSONArray("size").getInt(0),decor.getJSONArray("size").getInt(1));
                decorsElement.setSprite(FileUtils.getFile(this,decor.getString("sprite")));

                mLevel.add(decorsElement);
            }

            returnvalue = true;
        } catch (IOException e) {
            Log.e(TAG, getString(R.string.error_load_level_file)+" > "+getString(R.string.file_missing));
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(TAG, getString(R.string.error_file_corupted)+" > "+getString(R.string.file_corrupted));
            e.printStackTrace();
        }

        return returnvalue;
    }

    /**
     * Pauses the game
     */
    private void pauseGame() {
        unregisterObserver();
    }

    /**
     * Register LevelActivity to observe both the Level
     */
    private void registerObserver() {
        if (!mObserving) {
            mLevel.addObserver(this);
            mObserving = true;
        }
    }

    /**
     * Unregisters LevelActivity as an observer of Level
     */
    private void unregisterObserver() {
        if (mObserving) {
            mLevel.deleteObserver(this);
            mObserving = false;
        }
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
