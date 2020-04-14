package com.example.myapplication;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.myapplication.EggTimer;

import java.util.Timer;

import static java.lang.Thread.State.RUNNABLE;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView countdownText;
    ImageButton softButton;
    ImageButton mediumButton;
    ImageButton hardButton;

    CountDownTimer countdownTimer;
    int Timer;
    boolean timerRunning = false;
    long timeLeftInMilliSeconds;
    String selectEgg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.StartButton);
        countdownText = (TextView) findViewById(R.id.TimerText);
        softButton = findViewById(R.id.SoftBoiledButton);
        mediumButton = findViewById(R.id.MediumBoiledButton);
        hardButton = findViewById(R.id.HardBoiledButton);

    }



    public void startTimer() {
        countdownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                countdownTimer.cancel();
                timeLeftInMilliSeconds = 0;
                button.setText("Start");
                softButton.setEnabled(true);
                mediumButton.setEnabled(true);
                hardButton.setEnabled(true);
                countdownText.setText("0:00");
                timerRunning = false;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonStartClicked(v);
                    }
                });
            }
        }.start();
        button.setText("Stop");
        timerRunning = true;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop(v);
            }
        });
    }

    public void onStop(View v) {
        countdownTimer.cancel();
        timeLeftInMilliSeconds = 0;
        button.setText("Start");
        softButton.setEnabled(true);
        mediumButton.setEnabled(true);
        hardButton.setEnabled(true);
        countdownText.setText("0:00");
        timerRunning = false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonStartClicked(v);
            }
        });
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }

    public void onButtonStartClicked(final View view) {




        if (selectEgg == "softBoiled"){
            mediumButton.setEnabled(false);
            hardButton.setEnabled(false);
        } else if (selectEgg == "mediumBoiled") {
            softButton.setEnabled(false);
            hardButton.setEnabled(false);
        }
        else if (selectEgg == "hardBoiled") {
            softButton.setEnabled(false);
            mediumButton.setEnabled(false);
        }
        else {
            softButton.setEnabled(true);
            mediumButton.setEnabled(true);
            hardButton.setEnabled(true);
        }
        startTimer();

        if(timeLeftInMilliSeconds <= 0) {
            //Do stuff
            onStop(view);
        }
    }

    public void onButtonEggSelectedClicked(View view) {
        if (timerRunning == false){

            switch (view.getId()) {
                case R.id.SoftBoiledButton:
                    button.setEnabled(true);
                    countdownText.setText("5:00");
                    timeLeftInMilliSeconds = 5000;
    //                timeLeftInMilliSeconds = 300000;
                    selectEgg = "softBoiled";
                    break;
                case R.id.MediumBoiledButton:
                    button.setEnabled(true);
                    countdownText.setText("7:00");
                    timeLeftInMilliSeconds = 420000;
                    selectEgg = "mediumBoiled";
                    break;

                case R.id.HardBoiledButton:
                    button.setEnabled(true);
                    countdownText.setText("10:00");
                    timeLeftInMilliSeconds = 600000;
                    selectEgg = "hardBoiled";
                    break;

                default:
                    throw new RuntimeException("Unknow button ID");
            }
        }
    }
}
