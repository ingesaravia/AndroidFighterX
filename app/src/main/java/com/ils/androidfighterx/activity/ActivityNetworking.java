package com.ils.androidfighterx.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.ClaseNetworking;

public class ActivityNetworking extends AppCompatActivity {

    public Context miContexto;
    public TextView verificarConn, networkActiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        verificarConn = (TextView) findViewById(R.id.verificarConexion);
        networkActiva = (TextView) findViewById(R.id.networkActiva);

        miContexto = getApplicationContext();

        ClaseNetworking miClaseNetworking = new ClaseNetworking(miContexto);
        if (! miClaseNetworking.verificaConexion()) {
            verificarConn.setText("No hay conexiones existentes...\n");
        }
        else {
            verificarConn.setText("Comprobando Estado de Conexion...\n");
            String miNetworkActiva = miClaseNetworking.nombreNetworkActiva();
            networkActiva.setText(miNetworkActiva);
        }


    }
}
