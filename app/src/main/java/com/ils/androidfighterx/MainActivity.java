package com.ils.androidfighterx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ils.androidfighterx.activity.ActivityCamara;
import com.ils.androidfighterx.activity.ActivityCamaraGps;
import com.ils.androidfighterx.activity.ActivityCompartirRedSocial;
import com.ils.androidfighterx.activity.ActivityControles;
import com.ils.androidfighterx.activity.ActivityDisplay;
import com.ils.androidfighterx.activity.ActivityGaleria;
import com.ils.androidfighterx.activity.ActivityGps;
import com.ils.androidfighterx.activity.ActivityInfoHardware;
import com.ils.androidfighterx.activity.ActivityLinterna;
import com.ils.androidfighterx.activity.ActivityNetworking;
import com.ils.androidfighterx.activity.ActivityNotif;
import com.ils.androidfighterx.activity.ActivityOpenGL;
import com.ils.androidfighterx.activity.ActivityQRLector;
import com.ils.androidfighterx.activity.ActivitySensorAccelerometer;
import com.ils.androidfighterx.activity.ActivitySensorBrujula;
import com.ils.androidfighterx.activity.ActivitySensorGiroscopio;
import com.ils.androidfighterx.activity.ActivitySensorProximity;
import com.ils.androidfighterx.activity.ActivitySensores;
import com.ils.androidfighterx.activity.ActivityStreamingFacil;
import com.ils.androidfighterx.activity.ActivityStreamingMediaPlayer;
import com.ils.androidfighterx.activity.ActivityStreamingMp3;
import com.ils.androidfighterx.activity.ActivitySurface;
import com.ils.androidfighterx.activity.ActivityTimer;
import com.ils.androidfighterx.clases.CleanPhone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "MainActivity onCreate", Toast.LENGTH_SHORT).show();

        //DEFINO LA LISTA DE ITEMS
        //TRABAJO CON LAS VISTAS MainItem_List
        final ListView miListView = (ListView) findViewById(R.id.listView);
        items = new ArrayList();
        //TRABAJO CON LAS CLASES MainItem
        //DEFINO CADA OPCION DEL LIST VIEW
        items.add(new MainItem(0, "LIMPIAR CELU"));
        items.add(new MainItem(1, "LINTERNA"));
        items.add(new MainItem(2, "INFO SDK + HW"));
        items.add(new MainItem(3, "TIMER"));
        items.add(new MainItem(4, "GPS"));
        items.add(new MainItem(5, "GMAPS"));
        items.add(new MainItem(6, "WEBVIEW"));
        items.add(new MainItem(7, "OPENGL"));
        items.add(new MainItem(8, "SENSORES"));
        items.add(new MainItem(9, "SENSOR ONTOUCH"));
        items.add(new MainItem(10, "SENSOR PROXIMITY"));
        items.add(new MainItem(11, "SENSOR ACCELEROMETER"));
        items.add(new MainItem(12, "SENSOR BRUJULA"));
        items.add(new MainItem(13, "SENSOR GIROSCOPIO"));
        items.add(new MainItem(14, "SENSOR GIROSCOPIO SW"));
        items.add(new MainItem(15, "LECTOR CODIGO QR"));
        items.add(new MainItem(16, "GENERADOR CODIGO QR"));
        items.add(new MainItem(17, "INTENTS"));
        items.add(new MainItem(18, "NOTIFICACIONES"));
        items.add(new MainItem(19, "NETWORKING"));
        items.add(new MainItem(20, "CAMARA"));
        items.add(new MainItem(21, "CAMARA + GPS"));
        items.add(new MainItem(22, "BROADCAST RECEIVER"));
        items.add(new MainItem(23, "GALERIA"));
        items.add(new MainItem(24, "SURFACE"));
        items.add(new MainItem(25, "STREAMING FACIL"));
        items.add(new MainItem(26, "STREAMING MEDIAPLAYER"));
        items.add(new MainItem(27, "STREAMING MP3"));
        items.add(new MainItem(28, "COMPARTIR RED SOCIAL"));
        items.add(new MainItem(29, "JUEGO BEBE"));
        items.add(new MainItem(30, "MENUS"));
        items.add(new MainItem(31, "FORMATO DE CONTROLES"));
        items.add(new MainItem(32, "BASE DE DATOS"));

        //TRABAJO CON LAS CLASES MainItemAdapter
        miListView.setAdapter(new MainItemAdapter(MainActivity.this, items));
        //DEFINO EL CALLBACK
        miListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg) {
                MainItem miItem = (MainItem) miListView.getAdapter().getItem(position);
                //REALIZAR OPERACIONES SEGUN ITEM
                switch (miItem.getOpc()) {
                    case 0:
                        //LIMPIAR CELU
                        CleanPhone miLimpiarCelu = new CleanPhone(MainActivity.this);
                        miLimpiarCelu.limpiarTodo();
                        break;
                    case 1:
                        //LINTERNA
                        Intent intentLinterna = new Intent(MainActivity.this, ActivityLinterna.class);
                        startActivity(intentLinterna);
                        break;
                    case 2:
                        //INFO HARDWARE + SDK
                        Intent intentHW = new Intent(MainActivity.this, ActivityInfoHardware.class);
                        startActivity(intentHW);
                        break;
                    case 3:
                        //TIMER
                        Intent intentTimer = new Intent(MainActivity.this, ActivityTimer.class);
                        startActivity(intentTimer);
                        break;
                    case 4:
                        //GPS
                        Intent miIntent = new Intent(MainActivity.this, ActivityGps.class);
                        startActivity(miIntent);
                        break;
                    case 5:
                        //GMAPS
                        break;
                    case 6:
                        //WEBVIEW
                        Intent intentWebView = new Intent(MainActivity.this, ActivityQRLector.class);
                        startActivity(intentWebView);
                        break;
                    case 7:
                        //OPENGLMEJORADO
                        Intent intentOpenGL = new Intent(MainActivity.this, ActivityOpenGL.class);
                        startActivity(intentOpenGL);
                        break;
                    case 8:
                        //SENSORES
                        Intent intentSensores = new Intent(MainActivity.this, ActivitySensores.class);
                        startActivity(intentSensores);
                        break;
                    case 9:
                        //SENSOR ONTOUCH
                        break;
                    case 10:
                        //PROXIMITY SENSOR
                        Intent intentProximity = new Intent(MainActivity.this, ActivitySensorProximity.class);
                        startActivity(intentProximity);
                        break;
                    case 11:
                        //ACCELEROMETER
                        Intent intentAccelerometer = new Intent(MainActivity.this, ActivitySensorAccelerometer.class);
                        startActivity(intentAccelerometer);
                        break;
                    case 12:
                        //BRUJULA
                        Intent intentBrujula = new Intent(MainActivity.this, ActivitySensorBrujula.class);
                        startActivity(intentBrujula);
                        break;
                    case 13:
                        //GIROSCOPIO
                        Intent intentGiroscopio = new Intent(MainActivity.this, ActivitySensorGiroscopio.class);
                        startActivity(intentGiroscopio);
                        break;
                    case 14:
                        //GIROSCOPIO CON SOFTWARE
                        break;
                    case 15:
                        //LECTOR CODIGO QR
                        Intent intentQRLector = new Intent(MainActivity.this, ActivityQRLector.class);
                        startActivity(intentQRLector);
                        break;
                    case 16:
                        //GENERADOR CODIGO QR
                        break;
                    case 17:
                        //MANEJO DE INTENTS
                        Intent nuevoIntent = new Intent(MainActivity.this, ActivityDisplay.class);
                        startActivity(nuevoIntent);
                        break;
                    case 18:
                        //NOTIFICACIONES
                        Intent intentNotif = new Intent(MainActivity.this, ActivityNotif.class);
                        startActivity(intentNotif);
                        break;
                    case 19:
                        //NETWORKING
                        Intent intentNetworking = new Intent(MainActivity.this, ActivityNetworking.class);
                        startActivity(intentNetworking);
                        break;
                    case 20:
                        //CAMARA
                        Intent intentCamara1 = new Intent(MainActivity.this, ActivityCamara.class);
                        startActivity(intentCamara1);
                        break;
                    case 21:
                        //CAMARA + GPS
                        Intent intentCamaraGps = new Intent(MainActivity.this, ActivityCamaraGps.class);
                        startActivity(intentCamaraGps);
                        break;
                    case 22:
                        //BROADCAST RECEIVER
                        break;
                    case 23:
                        //GALERIA
                        Intent miGaleria = new Intent(MainActivity.this, ActivityGaleria.class);
                        startActivity(miGaleria);
                        break;
                    case 24:
                        //SURFACE
                        Intent miSurface = new Intent(MainActivity.this, ActivitySurface.class);
                        startActivity(miSurface);
                        break;
                    case 25:
                        //STREAMING FACIL
                        Intent miStreamingFacil = new Intent(MainActivity.this, ActivityStreamingFacil.class);
                        startActivity(miStreamingFacil);
                        break;
                    case 26:
                        //STREAMING MEDIA PLAYER
                        Intent miStreamingMediaPlayer = new Intent(MainActivity.this, ActivityStreamingMediaPlayer.class);
                        startActivity(miStreamingMediaPlayer);
                        break;
                    case 27:
                        //STREAMING MP3
                        Intent miStreamingMp3 = new Intent(MainActivity.this, ActivityStreamingMp3.class);
                        startActivity(miStreamingMp3);
                        break;
                    case 28:
                        //COMPARTIR REDES SOCIALES
                        Intent miShareRedSocial = new Intent(MainActivity.this, ActivityCompartirRedSocial.class);
                        startActivity(miShareRedSocial);
                        break;
                    case 29:
                        //JUEGO BEBE
                        break;
                    case 30:
                        //MENU
                        break;
                    case 31:
                        Intent misControles = new Intent(MainActivity.this, ActivityControles.class);
                        startActivity(misControles);
                        //FORMATOS
                        break;
                    case 32:
                        //BASE DE DATOS
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "MainActivity onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), "MainActivity onPause", Toast.LENGTH_SHORT).show();
    }


}
