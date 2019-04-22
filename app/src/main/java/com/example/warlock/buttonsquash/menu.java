package com.example.warlock.buttonsquash;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menu extends AppCompatActivity {

    ImageButton startg; MediaPlayer bgm; ImageButton mutebtn; int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        music();
        startg=(ImageButton)findViewById(R.id.stgame);
        mutebtn=(ImageButton)findViewById(R.id.mbtn);
        startg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgm.stop();
                Intent newgame=new Intent(menu.this,MainActivity.class);
                startActivity(newgame);
                finish();
            }
        });

        mutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n==0){
                    bgm.stop();
                    bgm.reset();
                    n=1;
                }
                else{
                    music();
                    n=0;
                }
            }
        });
    }

    void music(){

        bgm=MediaPlayer.create(this,R.raw.menu);
        bgm.setLooping(true);
        bgm.start();

    }


    // to stop music as soon as app is closed.
    @Override
    protected void onStop(){
        super.onStop();
        bgm.stop();
    }
}
