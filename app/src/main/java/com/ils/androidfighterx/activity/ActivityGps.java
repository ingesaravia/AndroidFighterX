package com.ils.androidfighterx.activity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ils.androidfighterx.R;
import com.ils.androidfighterx.clases.ClaseGps;
import com.ils.androidfighterx.clases.ClaseNetGps;

import java.util.List;

import static com.ils.androidfighterx.clasesComunes.Constantes.GPS_SIN_CONEXION;
import static com.ils.androidfighterx.clasesComunes.Constantes.GPS_SIN_PERMISO;

public class ActivityGps extends AppCompatActivity {
    public Location loc;
    public Location loc2;

    public LocationManager locManager;
    public LocationListener locListener;
    public ClaseGps gt;
    public ClaseNetGps netGps;

    //por defecto
    public long tiempoMinimo = 1000;
    public float distanciaMinima = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        final EditText infoGps = (EditText) findViewById(R.id.infoGps);
        infoGps.setText("");
        //OPCIONES DE LOS SPINNERS
        final String[] arrayOpcTiempoMin = new String[] {"500","1000","2000","3000","4000","5000"};
        final String[] arrayOpcDistMin = new String[] {"1", "2", "3", "4", "5", "10", "20", "50", "100"};

        final ArrayAdapter<String> adaptadorOpcTiempoMin = new ArrayAdapter<String>(this, R.layout.spinner_item, arrayOpcTiempoMin);
        ArrayAdapter<String> adaptadorOpcDistMin = new ArrayAdapter<String>(this, R.layout.spinner_item, arrayOpcDistMin);

        final Spinner cmbOpcTiempoMin = (Spinner) findViewById(R.id.cmbtiempoMin);
        final Spinner cmbOpcDistMin = (Spinner) findViewById(R.id.cmbdistMin);

        adaptadorOpcTiempoMin.setDropDownViewResource(R.layout.spinner_item);
        adaptadorOpcDistMin.setDropDownViewResource(R.layout.spinner_item);

        cmbOpcTiempoMin.setAdapter(adaptadorOpcTiempoMin);
        cmbOpcDistMin.setAdapter(adaptadorOpcDistMin);

        cmbOpcTiempoMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tiempoMinimo = Integer.valueOf(arrayOpcTiempoMin[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cmbOpcDistMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                distanciaMinima = Integer.valueOf(arrayOpcDistMin[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* GPS PROVIDER */
        gt = new ClaseGps(ActivityGps.this);
        loc = gt.getGPSLocation(tiempoMinimo, distanciaMinima);

        final Button mibtnGpsActivo = (Button) findViewById(R.id.btnGpsActivo);
        mibtnGpsActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gt.gpsActivo())
                {
                    Toast.makeText(getApplicationContext(),"GPS ACTIVO",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"GPS INACTIVO",Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button btnLoc = (Button) findViewById(R.id.btnGpsPosicion);
        btnLoc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if (loc == null){
                    if (gt.getGps_sin_conexion() == GPS_SIN_CONEXION)
                        Toast.makeText(getApplicationContext(),"GPS No se puede conectar", Toast.LENGTH_SHORT).show();
                    if (gt.getGps_sin_permiso() == GPS_SIN_PERMISO)
                        Toast.makeText(getApplicationContext(),"GPS no tiene permiso", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"GPS error desconocido", Toast.LENGTH_SHORT).show();
                }else {
                    double lat = Math.round(loc.getLatitude() * 100d)/100d;
                    double lon = Math.round(loc.getLongitude() * 100d)/100d;
                    double alt = Math.round(loc.getAltitude() * 100d)/100d;
                    Toast.makeText(getApplicationContext(),"Latitud= "+lat+"\n longitud= "+lon + "\n altitud=" + alt,Toast.LENGTH_SHORT).show();
                    String datos = "Lat: " + lat + "\nLong: " + lon + "\nAlt: " + alt + "\n";
                    infoGps.append("[ GPS ]\n");
                    infoGps.append(datos);
                }
            }
        });

        final Button mibtnGpsProvider = (Button) findViewById(R.id.btnGpsProvider);
        mibtnGpsProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombregpsProvider = gt.gpsProvider();
                String nombrenetProvider = netGps.gpsProvider();

                Toast.makeText(getApplicationContext(),
                        "GPS PROVIDER: " + nombregpsProvider
                                + "\nNET PROVIDER: " + nombrenetProvider,
                        Toast.LENGTH_SHORT).show();

                infoGps.append("TODOS LOS PROVEEDORES \n");
                List<String> datos = gt.listagpsProviders();
                for (String dato : datos)
                {
                    infoGps.append(dato + "\n");
                }

            }
        });

        final Button mibtnCriteria = (Button) findViewById(R.id.btnCriteria);
        mibtnCriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Criteria miCriteria = new Criteria();

                try {
                    miCriteria.setAccuracy(Criteria.ACCURACY_HIGH);
                    miCriteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);

                    String mejorProvider = netGps.locManager.getBestProvider(miCriteria, true);
                    infoGps.append("MEJOR PROVIDER SEGUN CRITERIA \n");
                    infoGps.append(mejorProvider + "\n");

                    loc = netGps.getNetworkGPSLocation(miCriteria.getSpeedAccuracy(), miCriteria.getAccuracy());
                }

                catch (Exception e)
                {
                    Log.d("ERROR", "No se pudo obtener informacion de Criteria" + e.getMessage());
                }
            }
        });

        /* GPS PROVIDER POR NETWORK */
        netGps = new ClaseNetGps(ActivityGps.this);
        loc2 = netGps.getNetworkGPSLocation(tiempoMinimo, distanciaMinima);

        final Button mibtnNetLoc = (Button) findViewById(R.id.btnNetGpsProvider);
        mibtnNetLoc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if (loc2 == null){
                    if (netGps.getGps_sin_conexion() == GPS_SIN_CONEXION)
                        Toast.makeText(getApplicationContext(),"GPS No se puede conectar", Toast.LENGTH_SHORT).show();
                    if (netGps.getGps_sin_permiso() == GPS_SIN_PERMISO)
                        Toast.makeText(getApplicationContext(),"GPS no tiene permiso", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"GPS error desconocido", Toast.LENGTH_SHORT).show();
                }else {
                    double lat2 = Math.round(loc2.getLatitude() * 100d)/100d;
                    double lon2 = Math.round(loc2.getLongitude() * 100d)/100d;
                    double alt2 = Math.round(loc2.getAltitude() * 100d)/100d;
                    Toast.makeText(getApplicationContext(),"Latitud= "+lat2+"\n longitud= "+lon2 + "\n altitud=" + alt2,Toast.LENGTH_SHORT).show();
                    String datos = "Lat: " + lat2 + "\nLong: " + lon2 + "\nAlt: " + alt2 + "\n";
                    infoGps.append("[ GPS NETWORK ]\n");
                    infoGps.append(datos);
                }
            }
        });
    }
}

