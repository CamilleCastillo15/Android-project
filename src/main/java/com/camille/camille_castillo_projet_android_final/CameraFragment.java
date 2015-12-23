package com.camille.camille_castillo_projet_android_final;

import java.io.File;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Environment;

/**
 * A simple {@link Fragment} subclass.
 */

//La classe CameraFragment dérive de la classe Fragment
    //De plus, elle implémente
public class CameraFragment extends Fragment implements OnClickListener {

    Button btn;
    File imageFile;
    int REQUEST_CODE = 1;

    //Le constructeur de notre classe, vide
    public CameraFragment() {
        // Required empty public constructor
    }


    //L'héritade la classe fragment permet de réecrire la méthode onCreateView
    //Cette méthode construit une vue qui va construire l'interface graphique du fragment et la renvoyer
    @Override
    //inflater permet de rajouter du code au fichier XML de layout du fragment
    //le container représente la vue parente du fragment (ici celle de MainActivity) (si non null)
    //savedInstanceState permet de sauvegarder l'état du fragment suivant qu'on change de vue ou non (si non null)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Ce qui est retourné va permettre d'enrichir le layout initial du fragment
        View _vue =  inflater.inflate(R.layout.fragment_camera, container, false);

        Button btn = (Button)_vue.findViewById(R.id.btnPicture);
        btn.setOnClickListener(this);

        Log.w("Ex14:FRG1", "*** onCreateView **************************************************");

        return _vue;
    }

    @Override
    public void onClick(View arg0) {

        Log.w("Ex14:FRG1", "*** onClick **************************************************");
        //Un intent est crée, il envoie un message au système, qui va appeler le composant permettant de prendre des photos
        //Chaque intent a besoin d'une action
        //MediaStore représente une type de base de données sur Androïd qui sauvegarde les images
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //L'image est sauvegardée temporairement sur la carte SD
        //L'objet File a besoin de deux arguments, le premier détermine l'emplacement du dossier où l'image est sauvegardée
        //Le second sera le nom de l'image
        imageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "photo.jpeg");

        //Une URI (étiquette de l'objet) à partir de l'objet imageFile
        Uri tempuri = Uri.fromFile(imageFile);

        //Là où est la photo est sauvegardée
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        //Cela détermine la qualité de l'image capturée (haute qualité)
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        //Une méthode est appelée quand l'application reçoit la réponse (image prise)
        startActivityForResult(intent, 0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Le requestcode indique quelle application envoie ses données
        //Ici c'est un intent
        if(requestCode==0)
        {
            //Le resultcode indique si la sauvegarde marché, échoué ou autre (à définir)
            switch (resultCode) {

                //Indique si l'opération a marché
                case Activity.RESULT_OK:

                    //Si l'image sauvegardée existe
                    if(imageFile.exists()){

                        //Un "message - bulle" ou Toast va s'afficher et indiquer le chemin du répertoire de sauvegarde de l'image crée
                        Toast.makeText(getActivity(), "L'image est sauvegardée à "+imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                    }else {

                        //Sinon il renvoie un message d'erreur
                        Toast.makeText(getActivity(), "Une erreure s'est produite", Toast.LENGTH_LONG).show();

                    }

                    break;

                    //Rien n'est fait si l'opération a échoué
                    case Activity.RESULT_CANCELED:

                    break;

            }
        }

    }

}
