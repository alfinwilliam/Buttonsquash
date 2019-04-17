package com.example.warlock.buttonsquash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    Button startg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        startg=(Button)findViewById(R.id.stgame);
        startg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newgame=new Intent(menu.this,MainActivity.class);
                startActivity(newgame);
                finish();
            }
        });
    }
}
