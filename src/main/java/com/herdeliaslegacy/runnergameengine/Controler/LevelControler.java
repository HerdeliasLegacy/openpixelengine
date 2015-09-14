package com.herdeliaslegacy.runnergameengine.Controler;

import android.content.Context;
import android.util.Log;

import com.herdeliaslegacy.runnergameengine.Model.Level;
import com.herdeliaslegacy.runnergameengine.Model.Player;
import com.herdeliaslegacy.runnergameengine.Model.Vector2D;
import com.herdeliaslegacy.runnergameengine.Utils.FileUtils;
import com.herdeliaslegacy.runnergameengine.Utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class for controling the level model (like load/clean and other thing)
 * Created by skad on 14/09/15.
 */
public class LevelControler {
    private static final String TAG = "Level Controler";
    private LevelControler() {
    }

    public static Level loadLevel(Context context,String number){
        if (number == null) number = "0";
        Level level = Level.getInstance();

        JSONObject jsonLevel = null;
        try {
            jsonLevel = JsonParser.getFileContent(context, "main.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //setting the player
        Player player = null;
        try {
            JSONArray playerpos = jsonLevel.getJSONObject("player").getJSONArray("start_pos");
            player = new Player(new Vector2D(playerpos.getInt(0),playerpos.getInt(1)),256,256);
            player.setSprite(FileUtils.getFile(context,jsonLevel.getJSONObject("player").getString("sprite")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        level.add(player);
        return level;
    }
}
