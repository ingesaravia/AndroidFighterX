package com.ils.androidfighterx.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ils.androidfighterx.R;

public class ActivityStreamingFacil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming_facil);
        // REQUIERE INTERNET

        //DIRECCION DEL VIDEO
        //videos soportados 3GP, MP4, WEBM y MKV
        //audio soportados  MP3, MID, OGG y WAV. Puede transmitir multimedia
        //en Android sobre RTSP, HTTP y HTTPS (de Android 3.1).
        String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        //Archive.org pagina de dominio publico
        Uri vidUri = Uri.parse(vidAddress);

        //CLASE PARA EL VIDEO
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);

        //CONTROLES PARA EL VIDEO - Requiere API 26
        MediaController vidControl = new MediaController(this);

        //CONFIGURACION DE LOS CONTROLES DEL VIDEO
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);

        //CONFIGURACION DEL VIDEO
        vidView.setVideoURI(vidUri);
        vidView.start();
    }
}
