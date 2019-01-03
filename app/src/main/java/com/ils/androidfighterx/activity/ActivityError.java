package com.ils.androidfighterx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ils.androidfighterx.R;

public class ActivityError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        final EditText miError = (EditText) findViewById(R.id.txtError);
        miError.setText("SI LLEGASTE HASTA AQUÍ... \n");
        miError.append("...ALGO ESTAS HACIENDO MAL  \n");

    }
}
