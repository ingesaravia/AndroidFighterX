package com.ils.androidfighterx.clases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class GPSBReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        /*
        if (intent.getAction().matches("android.intent.action.PROVIDER_CHANGED")) {
            Toast.makeText(context, "Produjo un PROVIDER CHANGED",
                    Toast.LENGTH_SHORT).show();
            Intent pushIntent = new Intent(context, ActivityGps.class);
            context.startService(pushIntent);
        }
        */
    }
}
