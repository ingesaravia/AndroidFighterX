package com.ils.androidfighterx.clases;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 20/07/2017.
 */
public class CleanPhone {
    //si la Clase es Non-Activity requiere que la Activity le envie el contexto
    //LIMPIA LAS COPIAS DE SEGURIDAD REPETIDAS DE WHATSAPP
    //LIMPIA LA CACHE DEL CELU
    //LIMPIA ARCHIVOS INNECESARIOS
    private Context miContexto;
    private int cleanThumbs;
    private int cleanWhatsapp;
    private int cleanCache;

    public CleanPhone (Context miContexto){
        super();
        this.setMiContexto(miContexto);
    }

    public void borrarDirectorio(String directorio){
        File dir = new File(directorio);
        File[] listaArchivos = dir.listFiles();
        for (File archivo: listaArchivos)
            archivo.delete();
    }

    public void limpiarThumbs(){
        String path = "/storage/emulated/0/DCIM/.thumbnails";
        File directorioThumbs = new File(path);

        if (directorioThumbs.exists())
        {
            File[] listaArchivos = directorioThumbs.listFiles();
            for (File archivo : listaArchivos)
            {
                archivo.delete();
            }
            setCleanThumbs(1);
        }
        else
        {
            Toast.makeText(getMiContexto(), "No existe el directorio",Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarCache(){

        List<String> cachePaths = new ArrayList<String>();

        cachePaths.add("/storage/emulated/0/.face");
        cachePaths.add("/storage/emulated/0/Android/data/com.sand.airdroid/cache");
        cachePaths.add("/storage/emulated/0/Android/data/com.google.android.youtube/cache/exo");
        cachePaths.add("/storage/emulated/0/Android/data/com.facebook.lite/cache/video");
        cachePaths.add("/storage/emulated/0/Android/data/com.google.android.youtube/cache");

        File[] listaArchivos;
        for (String carpeta : cachePaths)
        {
            File dirCache = new File(carpeta);
            if (dirCache.exists()){
                listaArchivos = dirCache.listFiles();
                for (File archivo : listaArchivos){
                    if (archivo.isDirectory())
                        borrarDirectorio(archivo.getPath());
                    else
                        archivo.delete();
                }
            }
        }
        cachePaths.clear();
        setCleanCache(1);
    }

    public void limpiarWhatsapp(){
        //SE INDICAN LAS CARPETAS ESPECIFICAS PARA DENTRO DE WHATSAPP;
        //VN databases
        String pathDB = "/storage/emulated/0/WhatsApp/Databases";
        File dirWappDB = new File(pathDB);

        File[] listaArchivos;
        //SE ELIMINAN LAS DATABASES REDUNDANTES DE WAPP
        if (dirWappDB.exists())	{
            listaArchivos = dirWappDB.listFiles();
            if (listaArchivos.length > 1) {
                File maxFile = new File(listaArchivos[0].getPath());
                long maxTamanio = listaArchivos[0].length();

                for (File archivo : listaArchivos) 	{
                    if (archivo.length() >= maxTamanio) {
                        maxFile.delete();
                        maxFile = archivo;
                        maxTamanio = archivo.length();
                    } else	archivo.delete();
                }
            }
        } else 	{
            Toast.makeText(getMiContexto(), "No existen DataBases",Toast.LENGTH_SHORT).show();
        }

        List<String> cachePaths = new ArrayList<String>();
        cachePaths.add("/storage/emulated/0/WhatsApp/Media/WhatsApp Voice Notes");
        cachePaths.add("/storage/emulated/0/WhatsApp/Media/WhatsApp Audio");
        cachePaths.add("/storage/emulated/0/WhatsApp/.Shared");
        File[] listaArch;
        for (String carpeta : cachePaths)
        {
            File dirCache = new File(carpeta);
            if (dirCache.exists()){
                listaArch = dirCache.listFiles();
                for (File archivo : listaArch){
                    if (archivo.isDirectory())
                        borrarDirectorio(archivo.getPath());
                    else
                        archivo.delete();
                }
            }
        }
        cachePaths.clear();
        setCleanWhatsapp(1);
    }

    public void limpiarTodo ()
    {
        this.limpiarCache();
        this.limpiarThumbs();
        this.limpiarWhatsapp();

        if ((cleanCache == cleanWhatsapp) && (cleanWhatsapp == cleanThumbs))
            Toast.makeText(getMiContexto(), "Limpieza Completa",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getMiContexto(), "Limpieza InCompleta",Toast.LENGTH_SHORT).show();
    }

    public Context getMiContexto() {
        return miContexto;
    }

    public void setMiContexto(Context miContexto) {
        this.miContexto = miContexto;
    }

    public int getCleanThumbs() {
        return cleanThumbs;
    }

    public void setCleanThumbs(int cleanThumbs) {
        this.cleanThumbs = cleanThumbs;
    }

    public int getCleanWhatsapp() {
        return cleanWhatsapp;
    }

    public void setCleanWhatsapp(int cleanWhatsapp) {
        this.cleanWhatsapp = cleanWhatsapp;
    }

    public int getCleanCache() {
        return cleanCache;
    }

    public void setCleanCache(int cleanCache) {
        this.cleanCache = cleanCache;
    }
}

