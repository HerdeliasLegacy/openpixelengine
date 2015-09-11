package com.herdeliaslegacy.runnergameengine.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.herdeliaslegacy.runnergameengine.R;

public class MainActivity extends Activity implements Button.OnClickListener {
    protected static final String TAG = "Main Activity Runner Engine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "test");

        //bind button
        Button startLevelActivitybtn = (Button) findViewById(R.id.startlevelactivity_btn);
        startLevelActivitybtn.setOnClickListener(this);

    }

    /**
     * Start level for default but neet to be overiden into the final game
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent gameIntent = null;
        int i = view.getId();
        if (i == R.id.startlevelactivity_btn) {
            gameIntent = new Intent(this, Level_Activity.class);
        }
        if (gameIntent != null) {
            startActivity(gameIntent);
        }
    }
}
