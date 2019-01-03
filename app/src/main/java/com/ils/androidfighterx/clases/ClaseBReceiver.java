package com.ils.androidfighterx.clases;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clasesComunes.Constantes;

public class ClaseBReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Aviso de Broadcast: Poca memoria", Toast.LENGTH_SHORT).show();

        //realizar LIMPIEZA DE CELULAR
        CleanPhone miLimpiarCelu = new CleanPhone(context);
        miLimpiarCelu.limpiarTodo();

        if ((miLimpiarCelu.getCleanCache() == Constantes.CLEAN_CACHE) &&
        (miLimpiarCelu.getCleanThumbs() == Constantes.CLEAN_THUMBS) &&
                (miLimpiarCelu.getCleanWhatsapp() == Constantes.CLEAN_WHATSAPP)) {
            NotificationCompat.Builder miNotif = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MEMORIA LIBRE")
                    .setContentText("Se ha liberado espacio en el Celular");
            NotificationManager miNotifMgr = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            miNotifMgr.notify(1, miNotif.build());
        }
    }
}
