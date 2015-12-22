package com.camille.camille_castillo_projet_android_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */

//La classe CameraFragment dérive de la classe Fragment
public class CameraFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

}
