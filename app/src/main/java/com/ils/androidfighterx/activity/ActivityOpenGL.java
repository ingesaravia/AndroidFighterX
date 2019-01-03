package com.ils.androidfighterx.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clasesOpengl.GestorOpenGLView;

public class ActivityOpenGL extends AppCompatActivity {

    //private GLSurfaceView miOpenGLView;
    private int opcionObjeto;
    private Spinner miSpinner;
    private SurfaceView miSurface;
    private GestorOpenGLView miOpenGLView;
    private Context miContext;
    private String[] objetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);

        miContext= getApplicationContext();
        miSurface = findViewById(R.id.surfaceV);
        miSpinner = findViewById(R.id.listaObjetos);

        //definimos la lista de elementos del Spinner
        objetos = new String[]{"","Humano", "Varios", "Cubo", "Banana", "Tetera", "Mix", "Animado", "Piramide", "Cuadrado"};
        //definimos el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(miContext, android.R.layout.simple_spinner_dropdown_item, objetos);
        miSpinner.setAdapter(adaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
                if (position != 0)
                {
                    opcionObjeto = position;
                    if (opcionObjeto > 0) {
                        miOpenGLView = new GestorOpenGLView(miContext, opcionObjeto);
                        setContentView(miOpenGLView);
                }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
