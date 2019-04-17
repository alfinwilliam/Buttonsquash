package com.example.warlock.buttonsquash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView fscore,highs; Button pagain; String scor; int hs,scrcheck; MediaPlayer gameover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        gomusic();
        fscore=(TextView)findViewById(R.id.finalscore);
        highs=(TextView)findViewById(R.id.his);
        pagain=(Button)findViewById(R.id.pagain);
        scor=getIntent().getStringExtra("score");
        fscore.setText(scor);

        LoadInt();

        scrcheck=Integer.parseInt(scor);
        if(scrcheck>hs)
        {
            hs=scrcheck;

        }
        SaveInt("highscore",hs);

        highs.setText(Integer.toString(hs));



        pagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game=new Intent(result.this,MainActivity.class);
                startActivity(game);
                finish();
            }
        });
    }

    public void gomusic(){
        gameover = MediaPlayer.create(this,R.raw.go);
        gameover.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                gameover = null;
            }
        });
        gameover.start();
    }


    public void SaveInt(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void LoadInt(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        hs = sharedPreferences.getInt("highscore", 0);
    }
}
