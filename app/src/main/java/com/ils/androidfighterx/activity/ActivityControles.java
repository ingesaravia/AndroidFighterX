package com.ils.androidfighterx.activity;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.ils.androidfighterx.R;


public class ActivityControles extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /*DATOS DE CHECKBOX*/

    /*DATOS DE RADIOGROUP*/

    /*DATOS DE SPINNER */
    private Context miContext;
    private Spinner miSpinner;
    private TabHost miTabs;
    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles);

        //gestión del TabHost

        Resources miRes = getResources();
        miContext = getApplicationContext();
        miTabs = (TabHost)findViewById(R.id.tabhost);
        miTabs.setup();

        //definicion de cada especificacion de Tabs
        //tab1
        TabHost.TabSpec miSpec;
        miSpec = miTabs.newTabSpec("tab 1");
        miSpec.setContent(R.id.tab1);
        miSpec.setIndicator("TAB1", miRes.getDrawable(R.drawable.ic_media_play_light));
        miTabs.addTab(miSpec);

        //tab2
        miSpec = miTabs.newTabSpec("tab 2");
        miSpec.setContent(R.id.tab2);
        miSpec.setIndicator("TAB2", miRes.getDrawable(R.drawable.ic_media_play_light));
        miTabs.addTab(miSpec);

        //tab3
        miSpec = miTabs.newTabSpec("tab 3");
        miSpec.setContent(R.id.tab3);
        miSpec.setIndicator("TAB3", miRes.getDrawable(R.drawable.ic_media_play_light));
        miTabs.addTab(miSpec);

        //tab4
        miSpec = miTabs.newTabSpec("tab 4");
        miSpec.setContent(R.id.tab1);
        miSpec.setIndicator("TAB4", miRes.getDrawable(R.drawable.ic_media_play_light));
        miTabs.addTab(miSpec);

        miTabs.setCurrentTab(0);

        /* SPINNER */
        miSpinner = (Spinner) findViewById(R.id.spinner);
        miSpinner.setOnItemSelectedListener(this);

        //definimos la lista de elementos del Spinner
        datos = new String[]{"elemento 1", "elemento 2", "elemento 3"};
        //definimos el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(miContext, android.R.layout.simple_spinner_dropdown_item, datos);
        miSpinner.setAdapter(adaptador);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(miContext, "ELEGISTE: " + datos[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

/*
    private void cambiarSpanned()
    {
        //objeto Editable para gestionar la interfaz Spanned
        Editable str = Editable.Factory.getInstance().newEditable("Cadena con interfaz Spanned");
        //modificacion de propiedades de la cadena
        str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 11, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //posteriormente, estos efectos se agregan a los controles usados
    }
    */
}
