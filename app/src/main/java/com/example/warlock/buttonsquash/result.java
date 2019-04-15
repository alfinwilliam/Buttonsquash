package com.example.warlock.buttonsquash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView fscore; Button pagain; String scor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        fscore=(TextView)findViewById(R.id.finalscore);
        pagain=(Button)findViewById(R.id.pagain);
        scor=getIntent().getStringExtra("score");
        fscore.setText(scor);
        pagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game=new Intent(result.this,MainActivity.class);
                startActivity(game);
            }
        });
    }
}
