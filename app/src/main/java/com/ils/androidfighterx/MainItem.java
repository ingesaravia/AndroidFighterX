package com.ils.androidfighterx;

import android.os.Build;
import android.widget.Toast;

/**
 * Created by Laura on 29/07/2017.
 */

public class MainItem {
    private String opcion;
    private int opc;

    public MainItem(){
        super();
    }

    public MainItem(int opc, String miOpcion){
        super();
        this.setOpc(opc);
        this.setOpcion(miOpcion);
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getOpc() {
        return opc;
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

}
