package com.camille.camille_castillo_projet_android_final;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A simple {@link Fragment} subclass.
 */

public class PDFragmentOld2 extends Fragment {

    private View _vue;
    private String titre;

    public PDFragmentOld2() {}

    /*public PDFFragment(String s) {
        Log.w("Ex14:FRG1", "*** constructeur **************************************************");
        titre = s;
    }*/

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        Log.w("Ex14:FRG1", "*** onAttach ******************************************************");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _vue = inflater.inflate(R.layout.fragment_pdf, container, false); // Création de la vue par expansion

        FileOutputStream fos = null;
        final File f = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "temp.pdf");//preparer un fichier temporaire
        try {
            fos = new FileOutputStream(f);//fichier en outputStream
            copieFic( this.getActivity().getAssets().open("Front-LD.pdf"), fos);
            //copier le contenue de test.pdf existant dans l'asset vers fichier temporaire
        } catch (Exception e) {

        }

        Log.w("ppd", f.getAbsolutePath());


        Button btn=(Button) _vue.findViewById(R.id.buttonpdf);//lien entre bouton et code

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//action sur la click du bouton
                Intent t = new Intent();

                t.setDataAndType(Uri.fromFile(f), "application/pdf");//selection type d'intent action sur pdf

                startActivityForResult(t, 0x12);//lancer l'intent

            }
        });
        Log.w("Ex14:FRG1", "*** onCreateView **************************************************");
        return _vue; // Retourne la vue (obligatoire)
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Ex14:FRG1", "*** onCreate ******************************************************");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w("Ex14:FRG1", "*** onActivityCreated *********************************************");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.w("Ex14:FRG1", "*** onStart *******************************************************");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("Ex14:FRG1", "*** onResume ******************************************************");
    }

    @Override
    public void onPause() {
        Log.w("Ex14:FRG1", "*** onPause *******************************************************");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.w("Ex14:FRG1", "*** onStop ********************************************************");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.w("Ex14:FRG1", "*** onDestroyView *************************************************");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.w("Ex14:FRG1", "*** onDestroy *****************************************************");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.w("Ex14:FRG1", "*** onDetach ******************************************************");
        super.onDetach();
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
    }

    private   void copieFic(InputStream src, OutputStream dest) throws Exception {
        byte[] buffer = new byte[1024];//creer un buffer
        int length;
        while ((length = src.read(buffer)) > 0) dest.write(buffer, 0, length);//copier les donnees de src vers dest
        dest.flush();//confermation de copier et forcer le tempan de disque dur de vider son cache dans le disque dur
        dest.close();//fermer le flux
        src.close();//fermer le flux
    }
}


