package com.ils.androidfighterx.clases;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Laura on 20/08/2017.
 */

public class ClaseAlarma {
    private AlarmManager miAlarma;
    private Context miContexto;
    private static final int ALARM_REQUEST_CODE = 1;

    public ClaseAlarma(Context C){
        this.miContexto = C;
    }

    public void guardarAlarma(int hh, int mm){
        miAlarma = (AlarmManager) miContexto.getSystemService(Context.ALARM_SERVICE);

        Intent miIntent = new Intent(this.miContexto, ClockBReceiver.class);
        PendingIntent miPIntent = PendingIntent.getBroadcast(this.miContexto, ALARM_REQUEST_CODE, miIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //convertir la hora, los segundos y minutos para guardar la alarma
        int thora = hh * 3600;
        int tmin = mm * 60;
        miAlarma.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (thora + tmin) * 1000, miPIntent);
    }

    public void AlarmaPorHora(){
        miAlarma = (AlarmManager) miContexto.getSystemService(Context.ALARM_SERVICE);

        Intent miIntent = new Intent(this.miContexto, ClockBReceiver.class);
        PendingIntent miPIntent = PendingIntent.getBroadcast(this.miContexto, ALARM_REQUEST_CODE, miIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //calcular para que responda por cada hora que pase
        miAlarma.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() , miPIntent);
    }


}
