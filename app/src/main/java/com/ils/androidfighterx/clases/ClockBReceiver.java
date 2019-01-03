package com.ils.androidfighterx.clases;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.ils.androidfighterx.R;

public class ClockBReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Sonido por defecto de notificaciones, podemos usar otro
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder miNotif = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("AVISO DE ALARMA")
                .setContentText("Una nueva alarma ha sonado")
                .setSound(defaultSound);
        NotificationManager miNotifMgr = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        miNotifMgr.notify(1, miNotif.build());
    }
}
