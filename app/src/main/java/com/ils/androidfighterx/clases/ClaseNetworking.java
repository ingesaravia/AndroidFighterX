package com.ils.androidfighterx.clases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Laura on 04/09/2017.
 */

public class ClaseNetworking {

    public Context miContexto;

    public ClaseNetworking(Context miContexto){
        this.miContexto = miContexto;
    }

    public boolean verificaConexion() {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) miContexto
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            // No sólo wifi, también GPRS
            NetworkInfo[] redes = connec.getAllNetworkInfo();

            for (int i = 0; i < 2; i++) {
                // ¿Tenemos conexión? ponemos a true
                if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                    bConectado = true;
                }
            }
        }
        catch(Exception e) {
            Log.d("ERROR", "Problema en obtener datos de las redes" + e.getMessage());
        }

        return bConectado;
    }

    public String nombreNetworkActiva (){
        ConnectivityManager connec = (ConnectivityManager) miContexto
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo mired = connec.getActiveNetworkInfo();
        String nombre = mired.getSubtypeName();

        return nombre;
    }


}
