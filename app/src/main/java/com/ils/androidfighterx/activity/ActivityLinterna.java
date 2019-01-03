package com.ils.androidfighterx.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.ils.androidfighterx.R;

import java.util.Random;

public class ActivityLinterna extends AppCompatActivity {

    private Camera miCamara;

    private ImageButton miImagen;
    private Display miDisplay;
    private Random miRandX = new Random();
    private Random miRandY = new Random();
    private Handler miHandler = new Handler();
    private Runnable miRunnable = new Runnable(){
        public void run(){
            ejecutarTimer();
        }
    };

    public void ejecutarTimer(){
        Context miContexto = getApplicationContext();
        miDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int anchoDisplay = miDisplay.getWidth();
        int altoDisplay = miDisplay.getHeight();

        float posX = (float) miRandX.nextInt(anchoDisplay);
        float posY = (float) miRandY.nextInt(altoDisplay);
        miImagen.setX(posX);
        miImagen.setY(posY);

        miHandler.postDelayed(miRunnable, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linterna);

        //BLOQUEAR LA ORIENTACION DEL CELULAR
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ENCENDER LINTERNA
        miCamara = Camera.open();

        Camera.Parameters miParameters = miCamara.getParameters();
        miParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        miCamara.setParameters(miParameters);
        miCamara.startPreview();

        final Button linternaOn = (Button) findViewById(R.id.btnLinternaOn);
        linternaOn.setText("ENCENDIDO");

        //TIMER DE IMAGE
        miImagen = (ImageButton) findViewById(R.id.icono);
        miRunnable.run();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        // Apagar el flash.
        // No es necesario apagar el flash si vamos a cerrar la
        // cámara, se apaga automáticamente.
        //Parameters parameters = camera.getParameters();
        //parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
        //camera.setParameters(parameters);
        miCamara.stopPreview();
        miCamara.release();
    }
}
