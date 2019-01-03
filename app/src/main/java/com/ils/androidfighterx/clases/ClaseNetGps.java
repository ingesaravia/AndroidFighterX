package com.ils.androidfighterx.clases;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Laura on 12/09/2017.
 */

public class ClaseNetGps implements LocationListener {
    //si la Clase es Non-Activity requiere que la Activity le envie el contexto
    Context miContexto;
    private int gps_sin_permiso = 0;
    private int gps_sin_conexion = 0;
    private int gps_error_desconocido = 0;
    private int gps_ok = 0;

    public LocationManager locManager;
    public LocationProvider locProvider;
    public Location loc;

    public ClaseNetGps(Context miContexto) {
        super();
        this.miContexto = miContexto;
        //Context.Location Service es un static field por lo que se debe acceder con class reference
        locManager = (LocationManager) miContexto.getSystemService(Context.LOCATION_SERVICE);
        locProvider = locManager.getProvider(LocationManager.NETWORK_PROVIDER);
    }

    public Location getNetworkGPSLocation(long tiempoMinimo, float distanciaMinima){
        //Context.Location Service es un static field por lo que se debe acceder con class reference
        LocationManager locManager = (LocationManager) miContexto.getSystemService(Context.LOCATION_SERVICE);

        try {
            if (gpsActivo()) {
                if (ActivityCompat.checkSelfPermission(miContexto, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(miContexto, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Log.e("fist", "error");
                    setGps_sin_permiso(1);
                    return null;
                }
                else
                {
                    setGps_ok(1);
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tiempoMinimo, distanciaMinima, this);
                    loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    return(loc);
                }
            }else {
                Log.e("sec", "errorrrr");
                setGps_sin_conexion(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        setGps_error_desconocido(1);
        return null;
    }

    public String gpsProvider(){
        String provider;
        provider = locProvider.getName();
        return provider;
    }

    public boolean gpsActivo (){
        boolean result;
        if (locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            result = true;
        else
            result = false;
        return result;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public int getGps_sin_permiso() {
        return gps_sin_permiso;
    }
    public void setGps_sin_permiso(int gps_sin_permiso) {
        this.gps_sin_permiso = gps_sin_permiso;
    }
    public int getGps_sin_conexion() {
        return gps_sin_conexion;
    }
    public void setGps_sin_conexion(int gps_sin_conexion) {
        this.gps_sin_conexion = gps_sin_conexion;
    }
    public int getGps_error_desconocido() {
        return gps_error_desconocido;
    }
    public void setGps_error_desconocido(int gps_error_desconocido) {
        this.gps_error_desconocido = gps_error_desconocido;
    }
    public int getGps_ok() {
        return gps_ok;
    }
    public void setGps_ok(int gps_ok) {
        this.gps_ok = gps_ok;
    }
}
