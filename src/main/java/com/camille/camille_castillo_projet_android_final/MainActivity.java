package com.camille.camille_castillo_projet_android_final;
//Mon programme se trouve dans le package com.camille.camille_castillo_projet_android_final

//Des classes qui se trouvent dans des packages différents sont importés
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

//C'est la classe "mère" de l'application, qui dérive de "AppCompatActivity"
//C'est une activité, qui possède un cycle de vie
public class MainActivity extends AppCompatActivity {

    //Cette dérivation nécessite d'écrire "par - dessus" la méthode onCreate d'Activity (identifié grâce à Override)
    @Override

    //onCreate est la première méthode à être appelée dans le cycle de vie d'une application
    //L'interface graphique de l'application doit donc être définie dans cette fonction
    protected void onCreate(Bundle savedInstanceState) {

        //On fait référence au onCreate de la classe Activity (ou AppCompatActivity)
        //Notre code va s'ajouter à celui de la superclasse Activity
        super.onCreate(savedInstanceState);

        //en récupérant le layout d'activity_main (fichier XML appelé par R), dont une partie est générée automatiquement par Android Studio
        setContentView(R.layout.activity_main);

        //La toolbar crée dans le layout, sert à introduire plusieurs vues dans une application
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }
}
