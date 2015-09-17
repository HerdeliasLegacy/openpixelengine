package com.herdeliaslegacy.runnergameengine.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

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
        loadLevelFromFile("0");

        mLevel.generateLevelStart();

    }

    private void loadLevelFromFile(String number){
        JSONObject jsonLevel = null;
        try {
            jsonLevel = JsonParser.getFileContent(this, "main.json");
            jsonLevel = JsonParser.getFileContent(this, jsonLevel.getJSONArray("levels").getString(0));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //defining all the elements the player
        Player player = null;
        DecorsElement decorsElement = null;
        try {
            //setting the player
            JSONArray playerpos = jsonLevel.getJSONObject("player").getJSONArray("start_pos");
            player = new Player(new Vector2D(playerpos.getInt(0),playerpos.getInt(1)),256,256);
            player.setSprite(FileUtils.getFile(this, jsonLevel.getJSONObject("player").getString("sprite")));
            mLevel.add(player);

            //setting the decors elements list
            JSONArray decorsArray = jsonLevel.getJSONArray("decorelements");
            for (int i = 0 ; i < decorsArray.length(); i++) {
                JSONObject decor = decorsArray.getJSONObject(i);
                decorsElement = new DecorsElement(new Vector2D(0,0),decor.getJSONArray("size").getInt(0),decor.getJSONArray("size").getInt(1));
                decorsElement.setSprite(FileUtils.getFile(this,decor.getString("sprite")));

                mLevel.add(decorsElement);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




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
