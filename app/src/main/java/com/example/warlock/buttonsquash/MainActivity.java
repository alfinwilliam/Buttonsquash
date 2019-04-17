package com.example.warlock.buttonsquash;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.util.DisplayMetrics;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn; int score=0; String scr; TextView scoredisp; boolean click;
    public static Handler myHandler = new Handler();
    private static final int TIME_TO_WAIT = 700;
    Runnable myRunnable; MediaPlayer btsound; LinearLayout scrlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.imgbtn);
        scrlayout=(LinearLayout)findViewById(R.id.scorelayout);
        scrlayout.setBackgroundColor(Color.parseColor("#d3d3d3"));
        btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btn.setX(380);
        btn.setY(650);
        scoredisp=(TextView)findViewById(R.id.score);
        String initscr=String.valueOf(score);
        scoredisp.setText(initscr);
        click=false;



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("HERE!");
                playmusic();
                stop();
                score+=5;
                scr=String.valueOf(score);
                scoredisp.setText(scr);

                Random random = new Random();
               int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
               btn.setBackgroundColor(color);

               int xval=xvalue();
                int yval=yvalue();
                //int butang=butangle();
               // btn.setRotation(butang);
                btn.setX(xval);
                btn.setY(yval);
                click=false;
                checkvalue();
                restart();
            }

        });



    }

     int xvalue() {
        Random randomGenerator = new Random();
        int xval = randomGenerator.nextInt(850) + 1;
        return xval;
    }

    int yvalue() {
        Random randomGenerator = new Random();
        int yval = randomGenerator.nextInt(1500) + 1;
        return yval;
    }

    int butangle() {
        Random randomGenerator = new Random();
        int butang = randomGenerator.nextInt(360) + 1;
        return butang;
    }


    public void checkvalue() {
        myRunnable = new Runnable() {
            @Override
            public void run() {
                if(!click) {
                    Intent result = new Intent(MainActivity.this, com.example.warlock.buttonsquash.result.class);
                    result.putExtra("score",scr);
                    startActivity(result);
                    finish();
                }
            }
        };

    }

    public void start() {
        myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
    }

    public void stop() {
        myHandler.removeCallbacks(myRunnable);
    }

    public void restart() {
        myHandler.removeCallbacks(myRunnable);
        myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
    }

    public void playmusic(){
        btsound = MediaPlayer.create(this,R.raw.btnclick);
        btsound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                btsound = null;
            }
        });
        btsound.start();
    }

}


