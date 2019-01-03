package com.ils.androidfighterx.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.ils.androidfighterx.R;

import java.util.List;

public class ActivitySensorGiroscopio extends AppCompatActivity implements SensorEventListener{

    public SensorManager miSensorManager;
    public Sensor miGiroscopio;
    public float maximumRotation;

    public long miTiempoRotation;
    public long minimunDelayTime;

    public float[] miGiros;

    public EditText infoGiroscopio;
    public EditText valoresGiroscopio;
    public List<String> infoG;

    public boolean sensorExiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_giroscopio);
        boolean sensorExiste=false;

        infoGiroscopio = (EditText) findViewById(R.id.infoGiroscopio);
        valoresGiroscopio = (EditText) findViewById(R.id.valoresGiroscopio);

        try {
            miGiroscopio = miSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensorExiste = true;
        }
        catch (Exception e) {
            infoGiroscopio.setText("NO EXISTE EL SENSOR DE GIROSCOPIO\n");
        }

        if (sensorExiste) {
            maximumRotation = miGiroscopio.getMaximumRange();
            minimunDelayTime = miGiroscopio.getMinDelay();

            infoG.add("INFORMACION DEL GIROSCOPIO \n");
            infoG.add("Valor maximo de Rotación: " + Float.toString(maximumRotation) + "\n");
            infoG.add("Tiempo de Espera de Giroscopio: " + Float.toString(minimunDelayTime) + "\n");

            for (String info : infoG)
            {
               infoGiroscopio.append(info);
            }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorExiste) {
            miTiempoRotation = sensorEvent.timestamp;
            miGiros = sensorEvent.values;

            if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                long now = System.currentTimeMillis();

                if ((now - miTiempoRotation) > minimunDelayTime) {
                    miTiempoRotation = now;

                    valoresGiroscopio.setText("");
                    if (Math.abs(sensorEvent.values[0]) > maximumRotation ||
                            Math.abs(sensorEvent.values[1]) > maximumRotation ||
                            Math.abs(sensorEvent.values[2]) > maximumRotation) {
                        valoresGiroscopio.append("X: " + Float.toString(miGiros[0]) + "\n");
                        valoresGiroscopio.append("Y: " + Float.toString(miGiros[1]) + "\n");
                        valoresGiroscopio.append("Z: " + Float.toString(miGiros[2]) + "\n");
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();

        //REGISTRAR EL LISTENER PARA LOS SENSORES
        if (sensorExiste)
        miSensorManager.registerListener((SensorEventListener) this, miGiroscopio, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause(){
        super.onPause();

        //DETENER SENSOR PARA AHORRAR ENERGIA
        if (sensorExiste)
        miSensorManager.unregisterListener((SensorEventListener) this);
    }
}
