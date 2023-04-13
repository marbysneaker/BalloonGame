package com.example.hw5game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTime, textViewCountDown, textViewScore;
    private ImageView balloon1,balloon2,balloon3,balloon4,balloon5,balloon6,balloon7,balloon8,balloon9;
    private GridLayout gridLayout;
    int score = 0;

    Runnable runnable;
    Handler handler;

    ImageView[] balloonsArray;

    MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        textViewCountDown = findViewById(R.id.textViewCountdown);
        textViewScore = findViewById(R.id.textViewScore);
        balloon1 = findViewById(R.id.balloon1);
        balloon2 = findViewById(R.id.balloon2);
        balloon3 = findViewById(R.id.balloon3);
        balloon4 = findViewById(R.id.balloon4);
        balloon5 = findViewById(R.id.balloon5);
        balloon6 = findViewById(R.id.balloon6);
        balloon7 = findViewById(R.id.balloon7);
        balloon8 = findViewById(R.id.balloon8);
        balloon9 = findViewById(R.id.balloon9);
        gridLayout = findViewById(R.id.gridLayout);

        mediaPlayer = MediaPlayer.create(this,R.raw.balloon_sound);
        balloonsArray = new ImageView[] {balloon1,balloon2,balloon3,balloon4,balloon5,balloon6,balloon7,balloon8,balloon9};

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                textViewCountDown.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                balloonsControl();

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {
                        textViewTime.setText("Remaining time:"+String.valueOf(l/1000));
                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                        startActivity(intent);
                    }
                }.start();
            }
        }.start();

    }

    public void increaseScoreByOne(View view){
        score++;
        textViewScore.setText(("Score: "+ score));
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
        mediaPlayer.start();

        if(view.getId() == balloon1.getId()){
            balloon1.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon2.getId()){
            balloon2.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon3.getId()){
            balloon3.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon4.getId()){
            balloon4.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon5.getId()){
            balloon5.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon6.getId()){
            balloon6.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon7.getId()){
            balloon7.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon8.getId()){
            balloon8.setImageResource(R.drawable.boom);
        }
        if(view.getId() == balloon9.getId()){
            balloon9.setImageResource(R.drawable.boom);
        }
    }

    public void balloonsControl(){
        textViewCountDown.setVisibility(View.INVISIBLE);
        textViewTime.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView balloon: balloonsArray){
                    balloon.setVisibility(View.INVISIBLE);
                    balloon.setImageResource(R.drawable.balloon);

                }
                gridLayout.setVisibility(View.VISIBLE);;
                Random random =new Random();
                int i = random.nextInt(balloonsArray.length);
                balloonsArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
    }
}