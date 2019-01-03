package com.ils.androidfighterx.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ils.androidfighterx.R;

import java.text.DecimalFormat;

public class ActivitySensorBrujula extends AppCompatActivity implements SensorEventListener{
    //SE USA ACCELEROMETER Y MAGNETIC FIELD
    // EN LUGAR DE ORIENTATION
    //EL ACTIVITY USA implements SensorEventListener
    public TextView txtAngulo;
    public ImageView imgViewBrujula;
    public Context miContexto;

    //SENSORES REQUERIDOS
    private SensorManager miSensorManager;
    private Sensor miAccelerometer;
    private Sensor miMagnetometer;

    private float anguloActual = 0f;
    private float anguloFlecha;
    private float azimut;
    private float[] miGravity; //valores del sensor del acelerometro
    private float[] miGeomagnetico; //valores del sensor del magnetometro


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_brujula);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        miContexto = getApplicationContext();

        txtAngulo = (TextView) findViewById(R.id.txtAngulo);
        imgViewBrujula = (ImageView) findViewById(R.id.imgViewBrujula);

        miSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        miAccelerometer = miSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        miMagnetometer = miSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        miGravity = null;
        miGeomagnetico = null;

    }

    @Override
    protected void onResume(){
        super.onResume();

        //REGISTRAR EL LISTENER PARA LOS SENSORES
        miSensorManager.registerListener((SensorEventListener) this, miAccelerometer, SensorManager.SENSOR_DELAY_UI);
        miSensorManager.registerListener((SensorEventListener) this, miMagnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause(){
        super.onPause();

        //DETENER SENSOR PARA AHORRAR ENERGIA
        miSensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        // Se comprueba que tipo de sensor está activo en cada momento
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                miGravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                miGeomagnetico = event.values;
                break;
        }

        if ((miGravity != null) && (miGeomagnetico != null)) {
            float RotationMatrix[] = new float[16];
            boolean success = SensorManager.getRotationMatrix(RotationMatrix,
                    null, miGravity, miGeomagnetico);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(RotationMatrix, orientation);
                azimut = orientation[0] * (180 / (float) Math.PI);
            }
        }

        anguloFlecha = azimut;

        double numAngulo = Math.round(anguloFlecha * 100d) / 100d;
        txtAngulo.setText("Usted se encuentra a: " + Double.toString(numAngulo) + " grados del Norte");

        // se crea la animacion de la rottacion (se revierte el giro en grados, negativo)
        RotateAnimation ra = new RotateAnimation(
                anguloActual,
                anguloFlecha,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // el tiempo durante el cual la animación se llevará a cabo
        ra.setDuration(1000);
        // establecer la animación después del final de la estado de reserva
        ra.setFillAfter(true);
        // Inicio de la animacion

        imgViewBrujula.startAnimation(ra);
        anguloActual = -anguloFlecha;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }




}
