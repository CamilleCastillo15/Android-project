package com.camille.camille_castillo_projet_android_final;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {

    private WebView webview;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("SwA", "WVF onCreateView");
        View _vue = inflater.inflate(R.layout.fragment_web, container, false);

        //Une webview est crée à partir de l'élément webview du layout de WebFragment
        webview = (WebView)_vue.findViewById(R.id.webview);

        //Le JavaScript est autorisé sur la page web affichée
        webview.getSettings().setJavaScriptEnabled(true);

        //l'URL chargée est celle de Google.com
        webview.loadUrl("http://www.google.com");

        //Une instance de webviewclient est crée
        webview.setWebViewClient(new WebViewClient());

        return _vue;
    }

}
