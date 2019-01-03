package com.ils.androidfighterx.activity;

import android.hardware.HardwareBuffer;
import android.hardware.Sensor;
import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.CpuUsageInfo;
import android.os.HardwarePropertiesManager;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.ils.androidfighterx.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityInfoHardware extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hardware);

        List<String> info = new ArrayList<String>();
        info.add("Informacion de SDK \n");

        String apiLevel="";
        String codeName="Unsupported";//below Jelly bean OR above nougat
        double release = 0;
        try{
            release=Double.parseDouble(Build.VERSION.RELEASE);
            if (release>=4.1 && release<4.4) codeName="Jelly Bean";
            else if(release<5) codeName="Kit Kat";
            else if(release<6) codeName="Lollipop";
            else if(release<7) codeName="Marshmallow";
            else if(release<8) codeName="Nougat";
            apiLevel = String.valueOf(Build.VERSION.SDK_INT);
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "No se puede obtener datos del SDK",Toast.LENGTH_SHORT).show();
            //en lugar de MainActivity se puede usar getApplicationContext()
        }
        //verificar el SDK y luego usar el Deprecated o el nuevo
        info.add(codeName+" v"+release+", API Level: "+ apiLevel + "\n");

        info.add("Informacion de Hardware \n");
        String brand = String.valueOf(Build.BRAND);
        info.add("Marca: " + brand + "\n");

        String hardware = String.valueOf(Build.HARDWARE);
        info.add("Hardware: " + hardware + "\n");
        String cpuAbi = String.valueOf(Build.CPU_ABI);
        info.add("CpuAbi: " + cpuAbi + "\n");
        info.add("Información de la Temperatura");

        /*if(apiLevel >= 23) {
            //AQUI ESTA EL ERRROR!!!!!!!!!!!!!!
            int tempBateria = HardwarePropertiesManager.DEVICE_TEMPERATURE_BATTERY;
            int tempCpu = HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU;
            int tempGpu = HardwarePropertiesManager.DEVICE_TEMPERATURE_GPU;

            info.add("Temperatura Bateria: " + tempBateria + "\n");
            info.add("Temperatura CPU: " + tempCpu + "\n");
            info.add("Temperatura GPU: " + tempGpu + "\n");
        }*/

        /*info.add("Medidas de Temperatura");
        if (apiLevel >= 23) {
            int tempActual = HardwarePropertiesManager.TEMPERATURE_CURRENT;
            int tempApagado = HardwarePropertiesManager.TEMPERATURE_SHUTDOWN;
            info.add("Temperatura actual: " + tempActual + "\n");
            info.add("Temperatura maxima: " + tempApagado + "\n");
        }*/

        final EditText miTxtInfo = (EditText)findViewById(R.id.txtInfo);
        miTxtInfo.setText("INFORMACIÓN DEL SMARTPHONE \n");
        for(String inf : info)
        {
            miTxtInfo.append(inf + "\n");
        }
        miTxtInfo.setVisibility(View.VISIBLE);

    }
}
