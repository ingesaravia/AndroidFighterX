package com.ils.androidfighterx.activity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ils.androidfighterx.R;

import org.w3c.dom.Text;

import java.util.List;

public class ActivitySensorAccelerometer extends AppCompatActivity implements SensorEventListener{

    private TextView miMensaje;
    private EditText infoAccel;

    private SensorManager miSensorManager;
    private Sensor miAccelerometer;

    private float[] miGravity; //valores del sensor del acelerometro
    private TextView maximumRange;
    private float deltaX=0, deltaY=0, deltaZ=0;
    private float deltamaxX=0, deltamaxY=0, deltamaxZ=0;
    private TextView maxX, maxY, maxZ;
    private EditText actualX, actualY, actualZ;
    private float ultimoX, ultimoY, ultimoZ;
    private float alarmaAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_sensor);
        //ORIENTACION BLOQUEADA
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //APAGAR LA PANTALLA BLOQUEADA
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        miMensaje = (TextView) findViewById(R.id.txtMensajeAccelerometer);
        infoAccel = (EditText) findViewById(R.id.infoPosicionAccel);

        actualX = (EditText) findViewById(R.id.actualX);
        actualY = (EditText) findViewById(R.id.actualY);
        actualZ = (EditText) findViewById(R.id.actualZ);
        maxX = (TextView) findViewById(R.id.maxX);
        maxY = (TextView) findViewById(R.id.maxY);
        maxZ = (TextView) findViewById(R.id.maxZ);
        maximumRange = (TextView) findViewById(R.id.txtMaxRange);

        miSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        miAccelerometer = miSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (miAccelerometer != null)
        {
            miSensorManager.registerListener(this, miAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            alarmaAccelerometer = SensorManager.GRAVITY_EARTH;
            maximumRange.setText("Max Acel:" + Float.toString(miAccelerometer.getMaximumRange())+"m/s2 \n");
        }
        miGravity = null;
    }

    private void mostrarValoresActualesMaximos(){
        actualX.setText(Float.toString(deltaX) + "\n");
        actualY.setText(Float.toString(deltaY) + "\n");
        actualZ.setText(Float.toString(deltaZ) + "\n");

        if (deltaX > deltamaxX) {
            deltamaxX = deltaX;
            maxX.setText(Float.toString(deltaX)+ "\n");
        }
        if (deltaY > deltamaxY) {
            deltamaxY = deltaY;
            maxY.setText(Float.toString(deltaY)+ "\n");
        }
        if (deltaZ > deltamaxZ) {
            deltamaxZ = deltaZ;
            maxZ.setText(Float.toString(deltaZ)+ "\n");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        mostrarValoresActualesMaximos();

        miGravity = sensorEvent.values;

        deltaX = ultimoX - miGravity[0];
        deltaY = ultimoY - miGravity[1];
        deltaZ = ultimoZ - miGravity[2];

        if ((deltaX < 2) || (deltaX < -2)) deltaX = 0;
        if ((deltaY < 2) || (deltaY < -2)) deltaY = 0;
        if ((deltaZ < 2) || (deltaY < -2)) deltaZ = 0;

        if (deltaY > 0){
            double cociente = deltaX / deltaY;
            cociente = Math.round(cociente * 100d) / 100d;
            double angulo = Math.atan(cociente);
            if (deltaX > 0){
                infoAccel.append("DERECHA A GRADOS" + Double.toString(angulo) + "\n");
            }
            else{
                if (deltaX < 0)
                    infoAccel.append("IZQUIERDA A GRADOS" + Double.toString(angulo) + "\n");
            }
        }

        if ((deltaX > alarmaAccelerometer) || (deltaY > alarmaAccelerometer) || (deltaZ > alarmaAccelerometer))
            miMensaje.setText("MOVIMIENTO BRUSCO \n");

        ultimoX = miGravity[0];
        ultimoY = miGravity[1];
        ultimoZ = miGravity[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        miSensorManager.registerListener((SensorEventListener) this, miAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause(){
        super.onPause();
        miSensorManager.unregisterListener((SensorEventListener) this);
    }
}
