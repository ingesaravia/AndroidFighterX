package com.ils.androidfighterx.activity;

import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.ClaseCamara;
import com.ils.androidfighterx.clases.ClaseGps;
import com.ils.androidfighterx.clases.ClaseNetGps;

import org.w3c.dom.Text;

public class ActivityCamaraGps extends AppCompatActivity {

    private Camera mCamera = null;
    private ClaseCamara mCameraView = null;
    private ClaseNetGps netgpsInfo = null;
    private ClaseGps gpsInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_gps);

        gpsInfo = new ClaseGps(this);
        netgpsInfo = new ClaseNetGps(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try{
            mCamera = Camera.open();
        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new ClaseCamara(this, mCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);//agrega la vista CameraView()
        }

        //boton para cerrar la aplicación.
        ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cierra la actividad.
                finish();
            }
        });

        //mensaje de GPS
        final TextView infoGps = (TextView) findViewById(R.id.infoGps);
        infoGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoGps.setText("GPS: " + String.valueOf(gpsInfo.getGPSLocation(2000,2)) + "\n");
                infoGps.append("NET GPS: " + String.valueOf(netgpsInfo.getNetworkGPSLocation(2000,2)));
            }
        });

    }


}
