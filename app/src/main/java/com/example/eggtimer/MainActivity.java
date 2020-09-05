package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerseekBar;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer (){
        timerTextView.setText("0:30");
        timerseekBar.setProgress(30);
        timerseekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Start");
        counterIsActive = false;

    }
    public void buttonClicked(View view){
        if(counterIsActive){
            resetTimer();

        }
        else {
            counterIsActive = true;
            timerseekBar.setEnabled(false);
            goButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerseekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();

                }
            }.start();


        }

    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);
        String secondString = Integer.toString(seconds);

        if (seconds<=9){
            secondString = "0"+secondString;
        }

        timerTextView.setText(Integer.toString(minutes) +":"+ secondString);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseekBar = findViewById(R.id.timerseekBar);
        timerTextView = findViewById(R.id.countDowntextView);
        goButton = findViewById(R.id.gobutton);
        timerseekBar.setMax(600);
        timerseekBar.setProgress(30);
        timerseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}