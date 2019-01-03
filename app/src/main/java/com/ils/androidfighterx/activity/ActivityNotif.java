package com.ils.androidfighterx.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ils.androidfighterx.R;

public class ActivityNotif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        final EditText miTxtNotif = (EditText) findViewById(R.id.txtNotif);
        final Button miBtnNotif = (Button) findViewById(R.id.btnNotif);

        miBtnNotif.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                String nombre = miTxtNotif.getText().toString();
                NotificationCompat.Builder miNotif = new NotificationCompat.Builder(ActivityNotif.this)
                        .setSound(defaultSound)
                        //.setLargeIcon(bitmap)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("BIENVENIDO")
                        .setContentText("Hola "+nombre);
                NotificationManager miNotifMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                Intent miintent=new Intent(ActivityNotif.this, ActivityDisplay.class);
                //Nueva Actividad
                PendingIntent pendingIntent = PendingIntent.getActivity(ActivityNotif.this, 0, miintent, 0);
                miNotif.setContentIntent(pendingIntent);
                miNotifMgr.notify(1,miNotif.build());
            }
        });


    }
}
