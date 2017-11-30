package com.murach.ch10_ex5;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{

    private TextView messageTextView;
    private TextView downloadTextView;
    private Button startBtn;
    private Button stopBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        messageTextView = (TextView) findViewById(R.id.messageTextView);
        downloadTextView = (TextView) findViewById(R.id.downloadTextView);

        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);

        ClickListener clickListener = new ClickListener();
        startBtn.setOnClickListener(clickListener);
        stopBtn.setOnClickListener(clickListener);
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startBtn:
                    startTimer();
                    break;
                case R.id.stopBtn:

                    break;
            }
        }
    }

    private void startTimer() {
        final long startMillis = System.currentTimeMillis();
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                long elapsedMillis = System.currentTimeMillis() - startMillis;
                updateView(elapsedMillis);
            }
        };
        timer.schedule(task, 0, 1000);
    }

    private void updateView(final long elapsedMillis) {
        messageTextView.post(new Runnable() {

            int elapsedSeconds = (int) elapsedMillis / 1000;
            int downloadCount = 0;

            @Override
            public void run() {
                messageTextView.setText("Seconds: " + elapsedSeconds);
                downloadTextView.setText("Downloads: " + downloadCount);
            }
        });
    }
}