package com.ils.androidfighterx.clasesOpengl;

import com.ils.androidfighterx.clasesOpengl.objetos3D.Banana;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Cuadrado;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Cubo;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Humano;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Mix;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Piramide;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Tetera;
import com.ils.androidfighterx.clasesOpengl.objetos3D.Varios;

public class GestorObjeto3D {
    public static final int CharacterNumVerts = 1908;

    public float[] CharacterVerts;

    public GestorObjeto3D(int opcionObjeto) {
        switch (opcionObjeto) {
            case 1:
                Humano miObjeto3DHumano = new Humano();
                this.CharacterVerts = miObjeto3DHumano.puntos;
                break;
            case 2:
                Varios miObjeto3DVarios = new Varios();
                this.CharacterVerts = miObjeto3DVarios.puntos;
                break;
            case 3:
                Cubo miObjeto3DCubo = new Cubo();
                this.CharacterVerts = miObjeto3DCubo.puntos;
                break;
            case 4:
                Banana miObjeto3DBanana = new Banana();
                this.CharacterVerts = miObjeto3DBanana.puntos;
                break;
            case 5:
                Tetera miObjeto3DTetera = new Tetera();
                this.CharacterVerts = miObjeto3DTetera.puntos;
                break;
            case 6:
                Mix miObjeto3DMix = new Mix();
                this.CharacterVerts = miObjeto3DMix.puntos;
                break;
            case 7:
                //FIGURA ANIMADA
                break;
            case 8:
                Piramide miPiramide = new Piramide();
                this.CharacterVerts = miPiramide.puntos;
                break;
            case 9:
                Cuadrado miCuadrado = new Cuadrado();
                this.CharacterVerts = miCuadrado.puntos;
                break;
        }
    }
}
