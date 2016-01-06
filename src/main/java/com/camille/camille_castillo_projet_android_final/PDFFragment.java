package com.camille.camille_castillo_projet_android_final;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */

public class PDFFragment extends Fragment {

    private View _vue;
    private String titre;

    public PDFFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _vue = inflater.inflate(R.layout.fragment_pdf, container, false); // Création de la vue par expansion

        //Un FileOutputStream est un fichier qui peut être modifié par la suite
        //Il est tout indiqué pour y insérer des images ou des documents PDF
        FileOutputStream fos = null;

        //Un fichier "final" (qui ne peut être changé par la suite) est crée
        //L'endroit où il sera crée correspond au dossier qui permet de stocker des éléments extérieurs
        //La méthode getabsolutefile permet de le situer par rapport à un chemin absolu (pas relatif par rapport à l'emplacement du fichier qui crée le file)
        //Le deuxième argument de la méthode correspond au nom du fichier (ici temp.pdf)
        final File f = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "temp.pdf");
        try {
            //fos sera nom du fichier qui va être réecrit, crée à partir de temp.pdf
            fos = new FileOutputStream(f);

            //Le fichier "Front-LD.pdf" va être copié dans le fichier temp.pdf grâce à la méthode copieFic définie plus bas
            //Ce fichier doit exister dans le dossier Assets de l'activité qui lance le fragment (ici MainActivity)
            //Ce dossier est crée manuellement, suivant les besoins, le fichier .pdf est collé à l'intérieur
            copieFic( this.getActivity().getAssets().open("Front-LD.pdf"), fos);

        } catch (Exception e) {

        }

        Log.w("ppd", f.getAbsolutePath());

        //Un bouton est crée à partir du layout de PDFFragment qui contient un bouton nommé buttonpdf
        Button btn=(Button) _vue.findViewById(R.id.buttonpdf);//lien entre bouton et code

        //Un écouteur lui est apposé
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Le code à l'intérieur du onClick ne se déclenchera que quand l'écouteur aura capté un clic sur le bouton
            public void onClick(View v) {//action sur la click du bouton

                //Un intent est crée, il envoie un message au système, qui va appeler le composant permettant de prendre des photos
                //Chaque intent a besoin d'une action
                Intent t = new Intent();

                //L'Intent t reçoit le type d'action à affectuer (ouvrir un fichier pdf)
                //Et le fichier à utiliser (f)
                t.setDataAndType(Uri.fromFile(f), "application/pdf");//selection type d'intent action sur pdf

                startActivityForResult(t, 0x12);//lancer l'intent

            }
        });
        Log.w("Ex14:FRG1", "*** onCreateView **************************************************");
        return _vue; // Retourne la vue (obligatoire)
    }

    private   void copieFic(InputStream src, OutputStream dest) throws Exception {

        //Un tableau de "buffer" est crée
        //Un buffer est un objet pouvant contenir des données
        //Ici es bits
        byte[] buffer = new byte[1024];//creer un buffer
        int length;

        //Tant que la taille du buffer de la source (sa taille) est supérieure à 0
        //Ses données sont écrites dans la destination
        //Quand le buffer de la source est vide, ses données sont donc complètement copiées dans la destination
        while ((length = src.read(buffer)) > 0) dest.write(buffer, 0, length);//copier les donnees de src vers dest

        //La destination voit son sache se vider
        dest.flush();

        //Puis, la source et la destination se ferment
        dest.close();
        src.close();
    }
}


