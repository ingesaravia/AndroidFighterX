package com.ils.androidfighterx.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ils.androidfighterx.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.currentTimeMillis;

public class ActivityWebview extends AppCompatActivity{

    private String miUrl;
    private WebView miWebView;

    //CLIENTE WEB PARA GESTIONAR EL FUNCIONAMIENTO DEL WEBVIEW
    private class miClienteWeb extends WebViewClient{

        private long tiempodeCarga;

        @Override
        public boolean shouldOverrideUrlLoading(WebView miWebView, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(miWebView, request);
        }

        @Override
        public void onPageStarted(WebView miWebView, String url, Bitmap favicon) {
            super.onPageStarted(miWebView, url, favicon);

            this.tiempodeCarga = currentTimeMillis();
            Toast.makeText(getApplicationContext(),
                    "Una pagina se esta cargando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView miWebView, String url) {
            super.onPageFinished(miWebView, url);

            this.tiempodeCarga = currentTimeMillis() -  this.tiempodeCarga;
            String time = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault())
                    .format(new Date(this.tiempodeCarga));
            Toast.makeText(getApplicationContext(),
                    "La página se cargó en " + time, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        miWebView = (WebView) findViewById(R.id.webView);
        final EditText miUrlNavegar = (EditText) findViewById(R.id.urlNavegar);
        final Button mibtnNavegar = (Button) findViewById(R.id.btnNavegar);
        //pagina de ejemplo
        String customHtml = "<html><body><h1>Hello, WebView</h1>" +
                "<h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3>" +
                "<p>Sample paragraph.</p>" +
                "</body></html>";
        miWebView.loadData(customHtml, "text/html", "UTF-8");

        mibtnNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HABILITAR JAVASCRIPT EN WEBSETTINGS
                miUrl = miUrlNavegar.getText().toString();
                WebSettings miWebSettings = miWebView.getSettings();
                //miWebSettings.setJavaScriptEnabled(true);
                //HABILITAR CLIENTE WEB EN EL WEBVIEW
                miWebView.setWebViewClient(new miClienteWeb());
                miWebView.loadUrl(miUrl);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //CHEQUEAR SI HAY HISTORIAL
        if (this.miWebView.canGoBack())
            this.miWebView.goBack();
        else
            super.onBackPressed();
    }
}
