package com.ils.androidfighterx.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.QRLector;

public class ActivityQRLector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrlector);

        Context miContexto = null;
        miContexto.getApplicationContext();
        QRLector miQrlector = new QRLector(miContexto);

        miQrlector.initQR();

    }
}
