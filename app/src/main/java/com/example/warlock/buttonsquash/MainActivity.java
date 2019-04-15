package com.example.warlock.buttonsquash;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn; int score; String scr; TextView scoredisp; boolean click;
    public static Handler myHandler = new Handler();
    private static final int TIME_TO_WAIT = 700;
    Runnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.imgbtn);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.btnclick);
        scoredisp=(TextView)findViewById(R.id.score);
        click=false;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("HERE!");
                mp.start();
                stop();
                score++;
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
}


