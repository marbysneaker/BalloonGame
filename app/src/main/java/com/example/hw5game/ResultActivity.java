package com.example.hw5game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class ResultActivity extends AppCompatActivity {
    private TextView textViewInfo, textViewMyScore, textViewHighestScore;
    private Button buttonPlayAgain, buttonQuitGame;

    SharedPreferences sharedPreferences;

    private String sharePreFile = "com.example.hw5game";

    int score;

    int highscore;

    String highestScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getData();

        textViewInfo =findViewById(R.id.textViewInfo);
        textViewMyScore =findViewById(R.id.textViewMyScore);
        textViewHighestScore =findViewById(R.id.textViewHighestScore);
        buttonPlayAgain =findViewById(R.id.buttonPlayAgain);
        buttonQuitGame =findViewById(R.id.buttonQuitGame);

        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("Score");
        textViewMyScore.setText("Score: " + score);
        if(score>highscore){
            highscore = score;
        }
        textViewHighestScore.setText("Highest Score: " + highscore);

        savedData();

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void getData(){
        sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
        highscore = sharedPreferences.getInt("highestScore",0);
    }
    protected void onPause(){
        savedData();
        super.onPause();
    }

    public void savedData(){
        sharedPreferences =getSharedPreferences("saveData",MODE_PRIVATE);
//        highestScore = textViewHighestScore.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highestScore",highscore);
        editor.commit();
    }




}