package com.ils.androidfighterx.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ils.androidfighterx.R;

import java.util.List;

public class ActivitySensores extends AppCompatActivity implements SensorEventListener{

    public EditText lSensores;
    public EditText datosSensores;
    public SensorManager mySensorManager;
    public Sensor mySensor;
    public List<Sensor> misSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        lSensores = (EditText) findViewById(R.id.listaSensores);
        lSensores.setText("LISTA DE SENSORES DISPONIBLES \n");
        lSensores.setVisibility(View.VISIBLE);

        datosSensores = (EditText) findViewById(R.id.datosSensores);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        misSensores = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor miSensor : misSensores){
            lSensores.append(miSensor.getName() + "\n");
            mySensorManager.registerListener(this, miSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        for (Sensor miSensor : misSensores) {
            mySensorManager.registerListener((SensorEventListener) this, miSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        mySensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_MOTION_DETECT:
                datosSensores.append("VALUES MOTION DETECT \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_ACCELEROMETER:
                datosSensores.append("VALUES ACCELEROMETER \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_GYROSCOPE:
                datosSensores.append("VALUES GYROSCOPE \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                datosSensores.append("VALUES MAGNETOMETRO \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_PROXIMITY:
                datosSensores.append("VALUES PROXIMITY \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_ORIENTATION:
                datosSensores.append("VALUES ORIENTATION \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                datosSensores.append("VALUES AUTO ROTATION \n");
                for (int i=0 ; i<sensorEvent.values.length ; i++) {
                    double valor = Math.round(sensorEvent.values[i] * 100d) / 100d;
                    datosSensores.append("["+Double.toString(valor)+"]");
                }
                datosSensores.append("\n");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
