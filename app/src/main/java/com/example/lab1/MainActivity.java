package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new CountDownTimer(startTime, interval);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("Start");
        }
        else{
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText("Reset");
        }
    }

    public class CountDownTimer {
        long millisUntilFinished;
        long countDownInterval;
        public CountDownTimer(long millisInFuture, long countDownInterval) {
            this.millisUntilFinished = millisInFuture;
            this.countDownInterval = countDownInterval;
        }

        private void start(){
            if(millisUntilFinished <= 0){
                onFinish();
            }
            else{

            }
        }

        private void cancel(){

        }

        private void onFinish() {
            text.setText("Time's up!");
            timeElapsedView.setText("Time remain: " + millisUntilFinished);
        }

        private void onTick() {
            text.setText("Time remain: " + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(("Time Elapsed: " + String.valueOf(timeElapsed)));
        }
    }
}