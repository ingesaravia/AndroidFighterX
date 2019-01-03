package com.ils.androidfighterx.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.CleanPhone;

import java.io.File;
import java.io.FilenameFilter;

public class ActivityGaleria extends AppCompatActivity {

    private String[] listaImg;
    private int posListaImg;
    private Bitmap miBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        final ImageView miImgView = (ImageView) findViewById(R.id.imgGaleria);
        final Button miBtnImgSig = (Button) findViewById(R.id.btnImgSig);
        final Button miBtnImgAnt = (Button) findViewById(R.id.btnImgAnt);

        String path = "/storage/emulated/0/Pictures";
        File directorioImg = new File(path);

        if (directorioImg.exists())
        {
            FilenameFilter miFilter = new FilenameFilter() {
                public boolean accept(File directory, String fileName) {
                    return fileName.endsWith(".jpg");
                }
            };
            listaImg = directorioImg.list(miFilter);
            if (listaImg != null) {
                miBitmap = BitmapFactory.decodeFile(listaImg[0]);
                posListaImg = 0;
                miImgView.setImageBitmap(miBitmap);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No existe el directorio",Toast.LENGTH_SHORT).show();
        }

        miBtnImgAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaImg != null)
                {
                    if (posListaImg > 0)
                    {
                        posListaImg = posListaImg - 1;
                        miBitmap = BitmapFactory.decodeFile(listaImg[posListaImg]);
                        miImgView.setImageBitmap(miBitmap);
                    }
                }
            }
        });

        miBtnImgSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaImg != null)
                {
                    if (posListaImg < listaImg.length)
                    {
                        posListaImg = posListaImg + 1;
                        miBitmap = BitmapFactory.decodeFile(listaImg[posListaImg]);
                        miImgView.setImageBitmap(miBitmap);
                    }
                }
            }
        });
    }
}
