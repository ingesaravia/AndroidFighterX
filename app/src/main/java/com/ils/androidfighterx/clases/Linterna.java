package com.ils.androidfighterx.clases;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

/**
 * Created by Laura on 20/07/2017.
 */

public class Linterna {

    private Camera miCamara;

    public void activarLinterna(){

        //ENCENDER LINTERNA

        miCamara = Camera.open();

        Parameters miParameters = miCamara.getParameters();
        miParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
        miCamara.setParameters(miParameters);
        miCamara.startPreview();
    }

}
