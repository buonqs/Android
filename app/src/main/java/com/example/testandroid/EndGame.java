package com.example.testandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {
TextView scoreEnd,highscore;
Button TryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        addControls();
        addEvents();
    }

    private void addEvents() {
        int score =getIntent().getIntExtra("ĐIỂM",0);
        scoreEnd.setText("ĐIỂM : "+score);

        SharedPreferences shP=getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        int HighScore =shP.getInt("ĐIỂM CAO",0);
        if(score>HighScore)
        {
            SharedPreferences.Editor editor=shP.edit();
            editor.putInt("ĐIỂM CAO",score);
            editor.apply();
            highscore.setText("HIGH SCORE :"+score);
        }else
        {
            highscore.setText("ĐIỂM CAO :"+HighScore);
        }
    }

    private void addControls() {
        scoreEnd=findViewById(R.id.txtscoreend);
        TryAgain=findViewById(R.id.btnplayagain);
        highscore=findViewById(R.id.txthighscore);


    }

    public void xulychoilai(View view) {
        Intent intent = new Intent(getApplicationContext(),Playgame.class);
        startActivity(intent);
    }
}