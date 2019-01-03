package com.ils.androidfighterx.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.ClaseCamara;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityCamara extends AppCompatActivity {

    private static final String TAG = "CameraActivity";

    private Camera mCamera = null;
    private ClaseCamara mCameraView = null;
    private Context miContexto = null;

    public String getFechaFoto()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        return date;
    }

    public void tomarFoto()
    {
        //Creamos una carpeta en la memoria del terminal
        String directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/AndroidFighter/";
        File imagesFolder = new File(directorio);
        if (!imagesFolder.exists())
            imagesFolder.mkdirs();

        //añadimos el nombre de la imagen
        String dirArchivo = directorio + "foto" + getFechaFoto() + ".jpg";
        File image = new File(dirArchivo);
        try {
            image.createNewFile();
        } catch (IOException e) {
            Log.e("ERROR", "Error en crear archivo:" + e);
        }
        //File image = new File(imagesFolder, NameOfFile + CurrentDateAndTime + ".jpg");
        Uri uriSavedImage = Uri.fromFile(image);

        //Creamos el Intent para llamar a la Camara
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Le decimos al Intent que queremos grabar la imagen
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(cameraIntent, 0);
    }

    public void tomarFotoFrame()
    {
        //Creamos el Intent para llamar a la Camara
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Bitmap frame = Bitmap.createBitmap(null);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, frame);
        int densidad = frame.getDensity();
        Log.d("MENSAJE", "densidad es" + String.valueOf(densidad));
    }

    public Bitmap mostrarFoto(String nombreArchivo)
    {
        //Creamos un bitmap con la imagen recientemente
        //almacenada en la memoria
        Bitmap bMap = BitmapFactory.decodeFile(
                Environment.getExternalStorageDirectory()+
                        "/AndroidFighter/" + nombreArchivo);
        //El bitmap se devuelve a través de un ImageView
        return(bMap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        miContexto = this;
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

        //boton para tomar la foto
        ImageButton imgFoto = (ImageButton)findViewById(R.id.imgFoto);
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCameraView.getHolder();
                //tomar foto
                tomarFoto();
                Toast.makeText(miContexto, "FOTO CAPTURADA",Toast.LENGTH_SHORT).show();

            }
        });

        ImageButton imgFotoFrame = (ImageButton)findViewById(R.id.imgFotoFrame);
        imgFotoFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFotoFrame();
                Toast.makeText(miContexto, "FRAME CAPTURADO",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
