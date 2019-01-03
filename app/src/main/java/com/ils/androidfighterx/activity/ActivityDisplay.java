package com.ils.androidfighterx.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.ils.androidfighterx.R;

import java.util.List;

public class ActivityDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Context miContexto = getApplicationContext();
        Display miDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        String anchoDisplay = "Ancho de la Pantalla " + Integer.toString(miDisplay.getWidth());
        String altoDisplay = "Alto de la Pantalla " + Integer.toString(miDisplay.getHeight());
        String densidadPantalla = "Densidad de la pantalla (dpi) " + getResources().getDisplayMetrics().densityDpi;

        float escala = getApplicationContext().getResources().getDisplayMetrics().density;
        String escalaPantalla = "Escala " + Float.toString(getApplicationContext().getResources().getDisplayMetrics().density);

        final EditText txtInfoDisplay = (EditText) findViewById(R.id.infoDisplay);
        txtInfoDisplay.setVisibility(View.INVISIBLE);
        txtInfoDisplay.setText("Informacion Display" + "\n");
        txtInfoDisplay.append(anchoDisplay + "\n");
        txtInfoDisplay.append(altoDisplay + "\n");
        txtInfoDisplay.append(densidadPantalla + "\n");
        txtInfoDisplay.append(escalaPantalla + "\n");
        txtInfoDisplay.setVisibility(View.VISIBLE);

        // buscando los pixeles a partir de dips con la densidad
        int dips = 200;
        float pixelBoton = 0;
        float scaleDensity = 0;

        DisplayMetrics metricas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metricas);

        switch(metricas.densityDpi)
        {
            case DisplayMetrics.DENSITY_HIGH: //HDPI
                //"Alta Densidad"
                scaleDensity = escala * 240;
                pixelBoton = dips * (scaleDensity / 240);
                break;
            case DisplayMetrics.DENSITY_MEDIUM: //MDPI
                //"mediana Densidad");
                scaleDensity = escala * 160;
                pixelBoton = dips * (scaleDensity / 160);
                break;

            case DisplayMetrics.DENSITY_LOW:  //LDPI
                //"baja Densidad"
                scaleDensity = escala * 120;
                pixelBoton = dips * (scaleDensity / 120);
                break;
        }


        Log.d(getClass().getSimpleName(), "pixels:"+Float.toString(pixelBoton));


    }
}
