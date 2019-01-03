package com.ils.androidfighterx.clasesOpengl.objetos3D;

/**
 * Created by IgnaS on 07/04/2018.
 */

public class Cuadrado {
    public float puntos[];

    public Cuadrado(){
        float auxP[] = {-1.0f, -1.0f,0.0f,
                1.0f, -1.0f, 0.0f,
                -1.0f, 1-0f, 0.0f,
                1.0f, 1.0f, 0.0f};
        puntos = auxP;
    }
}
