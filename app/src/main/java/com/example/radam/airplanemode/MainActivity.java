package com.example.radam.airplanemode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button bouton1;
    TextView texte1;
    Boolean etatActif = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bouton1 = (Button) findViewById(R.id.bouton1);
        texte1 = (TextView) findViewById(R.id.texte1);


        bouton1.setOnClickListener(new View.OnClickListener() {

            @TargetApi(17)
            public void onClick(View vue) {

                boolean isEnabled = Settings.System.getInt(
                        vue.getContext().getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON, 0) == 1;

                  if (etatActif) { // On désactive
                      try {
                          Settings.Global.putInt(vue.getContext().getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0);
                          texte1.setText("Passage en mode Désactivé" + Settings.System.getInt(vue.getContext().getContentResolver(), Settings.Global.AIRPLANE_MODE_ON));
                          etatActif = false;

                          Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                          intent.putExtra("state", !isEnabled);
                          sendBroadcast(intent);

                      } catch (Settings.SettingNotFoundException e) {
                          e.printStackTrace();
                      }

                  } else { // On active
                      try {
                          Settings.Global.putInt(vue.getContext().getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,1);
                          texte1.setText("Passage en mode Activé" + Settings.System.getInt(vue.getContext().getContentResolver(), Settings.Global.AIRPLANE_MODE_ON));
                          etatActif = true;
                      } catch (Settings.SettingNotFoundException e) {
                          e.printStackTrace();
                      }

                  }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
