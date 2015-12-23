package com.camille.camille_castillo_projet_android_final;
//Mon programme se trouve dans le package com.camille.camille_castillo_projet_android_final

//Des classes qui se trouvent dans des packages différents sont importés
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Un élément viewpager est crée, associé à l'élément viewpager du layout d'activity_main
        //Un viewpager sert à afficher les vues associés aux fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        // L'adaptateur de tabs (ou onglets) est assigné au viewpager
        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));

        //Un tablayout est crée, associé au layout d'activity_main
        //Un tablayout crée une interface permettant de gérer plusieurs onglets sur une vue
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        //La méthode installe les méthodes requises pour utiliser tablaout avec viewpager
        tabLayout.setupWithViewPager(viewPager);

    }

    //La méthode permet d'adapter les pages aux onglets
    //Elle dérive de FragmentPagerAdapter
    public static class TabsPagerAdapter extends FragmentPagerAdapter {

        // Un entier représentant le nombre de fragments existants et appeléss
        private static final int nbr_fragments = 2;

        //Le constructeur de la classe
        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // Chaque onglet retourne un fragment
        // La position de l'onglet détermine le fragment retourné
        // Une instance du fragment sera donc crée, et injecté dans la vue de l'onglet sélectionné
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new CameraFragment();
                case 1:
                    return new WebFragment();
                default:
                    return null;
            }
        }

        //La méthode qui provient de FragmentPageAdapter retourne le nombre d'éléments
        @Override
        public int getCount() {
            return nbr_fragments;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            // For simplicity of this tutorial this string is hardcoded
            // Otherwise it should be access using string resource
            return "Tab " + (position + 1);
        }

    }

}
