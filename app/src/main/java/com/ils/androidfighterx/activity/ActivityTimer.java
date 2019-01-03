package com.ils.androidfighterx.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ils.androidfighterx.R;

import org.w3c.dom.Text;

import java.util.Random;

public class ActivityTimer extends AppCompatActivity {

    private TextView miNumAzar;
    private Random miRand = new Random();
    private Handler miHandler = new Handler();
    private Runnable miRunnable = new Runnable(){
        public void run(){
            ejecutarTimer();
        }
    };

    public void ejecutarTimer(){
        int num = miRand.nextInt(10);
        miNumAzar.setText(String.valueOf(num));

        miHandler.postDelayed(miRunnable, 500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        miNumAzar = (TextView) findViewById(R.id.numAzar);
        miRunnable.run();
    }
}
