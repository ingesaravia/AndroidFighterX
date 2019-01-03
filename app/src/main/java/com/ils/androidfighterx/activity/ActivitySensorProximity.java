package com.ils.androidfighterx.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ils.androidfighterx.R;

public class ActivitySensorProximity extends AppCompatActivity {

    public TextView ProximitySensorExiste, ProximitySensor, ProximityMax, ProximityReading;
    public SensorManager mySensorManager;
    public Sensor myProximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        ProximitySensorExiste = (TextView) findViewById(R.id.proximitySensorExiste);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        ProximityMax = (TextView) findViewById(R.id.proximityMax);
        ProximityReading = (TextView) findViewById(R.id.proximityReading);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null) {
            ProximitySensorExiste.setText("ERROR!");
            ProximitySensor.setText("No hay Proximity Sensor!");
        } else {
            ProximitySensorExiste.setText("OK!");
            ProximitySensor.setText("Nombre Sensor: " + myProximitySensor.getName());
            ProximityMax.setText("Rango Maximo (cm): " + String.valueOf(myProximitySensor.getMaximumRange()));
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
                ProximityReading.setText("Leyendo Sensor de Proximidad (cm): "
                        + String.valueOf(sensorEvent.values[0]));

                if (sensorEvent.values[0] < myProximitySensor.getMaximumRange()){
                    ProximityReading.append("CERCA... \n");
                }
                else{
                    ProximityReading.append("LEJOS...\n");
                }

            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
