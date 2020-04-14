package com.example.myapplication;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class EggTimer extends Thread {
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
    public void run() {

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

    public void onButtonStartClicked(final View view) {

        EggTimer eggTimer = new EggTimer();
        eggTimer.start();


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

}

