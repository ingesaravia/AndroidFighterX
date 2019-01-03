package com.ils.androidfighterx.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ils.androidfighterx.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ActivityCompartirRedSocial extends AppCompatActivity {

    private EditText mensaje;
    private String msjShare;
    private Intent miIntent;
    private ImageView miImageView;
    private Bitmap miIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir_red_social);

        //mensaje es para compartir algun mensaje o texto
        mensaje = (EditText) findViewById(R.id.msjShareWith);
        msjShare = mensaje.getText().toString();
        //configshareImagen es para compartir una imagen desde el celular
        Button configshareImagen = (Button) findViewById(R.id.btnShareImagen);

        //shareQuestion es para preguntar con que aplicacion compartir
        Button btnshareQuestion = (Button) findViewById(R.id.btnShareQuestion);
        //shareFacebook y shareWhatsapp ya vienen predefinidos
        Button btnshareFacebook = (Button) findViewById(R.id.btnShareFacebook);
        Button btnshareFacebookLite = (Button) findViewById(R.id.btnShareFacebookLite);
        Button btnshareWhatsapp = (Button) findViewById(R.id.btnShareWhatsapp);

        //CONFIGURACION DEL INTENT GLOBAL A USAR
        miIntent = new Intent(Intent.ACTION_SEND);
        miIntent.setType("text/plain");
        miIntent.putExtra(Intent.EXTRA_TEXT, msjShare);

        configshareImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logoprueba);
                //Se guarda la imagen en la SDCARD
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                miIcon.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                File f = new File(Environment.getExternalStorageDirectory() + "logoprueba.png");
                try {
                    FileOutputStream fout = new FileOutputStream(f);
                    fout.write(bytes.toByteArray());
                    fout.flush();
                    fout.close();
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage() );
                }

                miIntent.setType("image/png");
                miIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                //miIntent.setData(Uri.fromFile(f)); SIRVE PARA DATOS ESPECIFICOS

            }
        });

        btnshareQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msjShare = mensaje.getText().toString();
                miIntent.putExtra(Intent.EXTRA_TEXT, msjShare);
                startActivity(Intent.createChooser(miIntent, "Share with"));
            }
        });

        btnshareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msjShare = mensaje.getText().toString();
                miIntent.putExtra(Intent.EXTRA_TEXT, msjShare);
                miIntent.setPackage("com.facebook.katana");
                startActivity(miIntent);
            }
        });

        btnshareFacebookLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msjShare = mensaje.getText().toString();
                miIntent.putExtra(Intent.EXTRA_TEXT, msjShare);
                miIntent.setPackage("com.facebook.lite");
                startActivity(miIntent);
            }
        });

        btnshareWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msjShare = mensaje.getText().toString();
                miIntent.putExtra(Intent.EXTRA_TEXT, msjShare);
                miIntent.setPackage("com.whatsapp");
                startActivity(miIntent);
            }
        });
    }
}
