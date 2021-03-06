package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCountDownTimer countDownTimer;
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
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
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
            String startString = getString(R.string.start);
            startB.setText(startString);
        }
        else{
            countDownTimer.cancel();
            timerHasStarted = false;
            String resetString = getString(R.string.reset);
            startB.setText(resetString);
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished The amount of time until finished.
         */
        @Override
        public void onTick(long millisUntilFinished) {
            String remainStr = getString(R.string.timeRemain);
            String elapseStr = getString(R.string.timeElapsed);
            text.setText(remainStr + String.valueOf(millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(elapseStr + String.valueOf(timeElapsed));
        }

        /**
         * Callback fired when the time is up.
         */
        @Override
        public void onFinish() {
            String textStr = getString(R.string.timeElapsed);
            text.setText(R.string.timeOver);
            timeElapsedView.setText(textStr + String.valueOf(startTime));
        }


    }
}