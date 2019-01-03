package com.ils.androidfighterx.clasesComunes;

/**
 * Created by Laura on 07/09/2017.
 */

public class FuncionesArray {


    public FuncionesArray(){

    }

    public void CastArrayDoubleToFloat(float[] arrayDestino, double[] arrayOrigen){
        float[] floatArray = new float[arrayOrigen.length];
        for (int i = 0 ; i < arrayOrigen.length; i++)
        {
            floatArray[i] = (float) arrayOrigen[i];
        }
        arrayDestino = floatArray;
    }

    public void ArrayDoubleDividir (double[] arrayOrigen, double divisor){
        for (int i = 0 ; i < arrayOrigen.length; i++)
        {
            arrayOrigen[i] = arrayOrigen[i]/divisor;
        }
    }

    public void ArrayFloatDividir(float[] arrayOrigen, float divisor){
        for (int i = 0 ; i < arrayOrigen.length; i++)
        {
            arrayOrigen[i] = arrayOrigen[i]/divisor;
        }
    }
}
