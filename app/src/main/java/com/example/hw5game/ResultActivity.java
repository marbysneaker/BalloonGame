package com.example.hw5game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewInfo, textViewMyScore, textViewHighestScore;
    private Button buttonPlayAgain, buttonQuitGame;

    SharedPreferences sharedPreferences;

    int score;

    int highscore;

    String highestScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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


    }

    public void savedData(){
        sharedPreferences =getSharedPreferences("saveData",MODE_PRIVATE);
        highestScore = textViewHighestScore.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key score", highestScore);
    }



}