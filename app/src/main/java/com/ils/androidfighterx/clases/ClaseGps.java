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
 * Created by Laura on 20/07/2017.
 */

public class ClaseGps implements LocationListener {
    //si la Clase es Non-Activity requiere que la Activity le envie el contexto
    Context miContexto;
    private int gps_sin_permiso = 0;
    private int gps_sin_conexion = 0;
    private int gps_error_desconocido = 0;
    private int gps_ok = 0;

    public LocationManager locManager;
    public LocationProvider locProvider;
    public Location loc;

    public ClaseGps(Context miContexto) {
        super();
        this.miContexto = miContexto;
        //Context.Location Service es un static field por lo que se debe acceder con class reference
        locManager = (LocationManager) miContexto.getSystemService(Context.LOCATION_SERVICE);
        locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);
    }

    /* GPS */
    public Location getGPSLocation(long tiempoMinimo, float distanciaMinima) {
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
                    //float minTime: minimo tiempo de retardo en ms (1000 ms es un 1 s)
                    //float minDistance: minima distancia de diferencia en m
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempoMinimo, distanciaMinima, this);
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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

    public boolean gpsActivo (){
        boolean result;
        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            result = true;
        else
            result = false;
        return result;
    }

    public String gpsProvider(){
        String provider;
        provider = locProvider.getName();
        return provider;
    }

    public List<String> listagpsProviders(){
        List<String> miLista = null;
        miLista = locManager.getAllProviders();
        return miLista;
    }

    @Override
    public void onLocationChanged(Location location) {
        double distX;
        double distY;
        double distZ;
        String mensaje = null;
        long tiempo;

        if (loc != null){
            distX = loc.getLatitude() - location.getLatitude();
            distY = loc.getLongitude() - location.getLongitude();
            distZ = loc.getAltitude() - location.getAltitude();
            tiempo = location.getTime() - loc.getTime();

            mensaje = "USTED SE MOVIO A: ";
            if (distX > 0) {
                if (distY > 0){
                    mensaje = mensaje + "NORESTE ";
                }
                else
                    if (distY < 0){
                        mensaje = mensaje + "SURESTE ";
                    }
                    else
                        mensaje = mensaje + "ESTE ";
            }
            else {
                if (distX < 0) {
                    if (distY > 0) {
                        mensaje = mensaje + "NOROESTE ";
                    } else if (distY < 0)
                        mensaje = mensaje + "SUROESTE ";
                    else
                        mensaje = mensaje + "OESTE ";
                } else {
                    if (distY > 0) {
                        mensaje = mensaje + "NORTE ";
                    } else if (distY < 0)
                        mensaje = mensaje + "SUR ";
                    else
                        mensaje = mensaje + "MISMO LUGAR ";
                }
            }

            if (distZ > 0){
                mensaje = mensaje + "Y ARRIBA ";
            }
            else
            if (distZ < 0){
                mensaje = mensaje + "Y ABAJO ";
            }
            else
                mensaje = mensaje + "MISMA ALTURA ";

            Toast.makeText(miContexto,mensaje + "EN " + Long.toString(tiempo) + "SEGS", Toast.LENGTH_SHORT).show();
            loc = location;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(miContexto,"Proveedor de GPS está habilitado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(miContexto,"Proveedor de GPS está deshabilitado", Toast.LENGTH_SHORT).show();
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
