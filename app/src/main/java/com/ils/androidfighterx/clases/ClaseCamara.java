package com.ils.androidfighterx.clases;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Laura on 07/03/2018.
 */

public class ClaseCamara extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Camera mCamera;

    /* constructor */
    public ClaseCamara(Context miContexto, Camera camara){
        super(miContexto);

        mCamera = camara;
        mCamera.setDisplayOrientation(90);
        mHolder = this.getHolder();
        mHolder.addCallback(this); //callback es para saber cuando la surface esta libre
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        }
        catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceCreater" + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(mHolder.getSurface() == null)
            return;

        try{
            mCamera.stopPreview();
        } catch (Exception e){
            Log.e("ERROR",  e.getMessage());
        }

        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("ERROR", "Camera error on surfaceChanged " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
    }
}
