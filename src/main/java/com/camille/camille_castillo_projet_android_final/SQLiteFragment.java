package com.camille.camille_castillo_projet_android_final;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SQLiteFragment extends Fragment {

    //Un instance de la classe DatabaseHelper est crée, nommée myBdd
    DatabaseHelper myBdd;
    //Les editText du layout sont placés dans des instances d'EditText
    EditText editNom, editPrenom, editNotes, editTextId;
    //Pareil pour les boutons du fragment
    Button btnAjouter;
    Button btnVoir;
    Button btnMaj;
    Button btnSupprimer;

    public SQLiteFragment() {
        // Un constructeur vide est requis
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _vue =  inflater.inflate(R.layout.fragment_sqlite, container, false);

        //Ici elle est crée, elle a besoin du contexte comme argument,
        // on lui renvoit celui de l'activité
        //Pour vérifier si la BDD a bien été crée avec les bonnes tables / colonnes
        //Il est possible de se rendre dans l'Androïd Device Monitor quand l'émulateur et l'application tournent
        //Sous le nom de notre application, on peut trouver les éléments crées, on retrouve alors notre BDD
        //Il est même possible de l'exporter et de la gérer avec un gestionnaire de BDD de type SQLite
        myBdd = new DatabaseHelper(this.getActivity());

        //Les instances d'editTexts sont reliés aux EditText du layout
        editNom = (EditText)_vue.findViewById(R.id.editText_nom);
        editPrenom = (EditText)_vue.findViewById(R.id.editText_prénom);
        editNotes = (EditText)_vue.findViewById(R.id.editText_notes);
        editTextId = (EditText)_vue.findViewById(R.id.editText_id);

        //Pareil pour les boutons
        btnAjouter = (Button)_vue.findViewById(R.id.button_ajouter);
        btnVoir = (Button)_vue.findViewById(R.id.button_voir);
        btnMaj = (Button)_vue.findViewById(R.id.button_maj);
        btnSupprimer = (Button)_vue.findViewById(R.id.button_supprimer);

        //Les méthodes d'ajouts, de vu, de mis à jour ou encore de suppression sont exécutées au lancement du fragment SQLITE TAB
        AjouterDonnees();
        VoirTout();
        MajDonnes();
        SupprimerDonnes();

        return _vue;
    }

    public void SupprimerDonnes(){

        btnSupprimer.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //La fonction SupprimerDonnes de DatabaseHelper est appelée,
                        //Les lignes à identifier seront identifiés par leur ID
                        //Le résultat est enregistré dans un entier nommé "deletedRows"
                        Integer deletedRows = myBdd.deleteData(editTextId.getText().toString());

                        //La fonction retournera 0 si cela fonctionne
                        //Ce qui créera un Toast avec un message positif
                        if(deletedRows > 0) Toast.makeText(getActivity(), "Les données ont été supprimées", Toast.LENGTH_LONG).show();

                        //Sinon le message sera différent
                        else Toast.makeText(getActivity(), "Les données n'ont pas été supprimées", Toast.LENGTH_LONG).show();

                    }
                }

        );

    }

    public void MajDonnes(){

        btnMaj.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //La fonction MajDonnes est appelée sur notre BDD
                        //Les contenus des editText du layout sont pris en arguments
                        //Ils ont besoin d'être convertis en String pour correspondre aux types d'arguments de la fonction
                        boolean isUpdated = myBdd.updateData(editTextId.getText().toString(),
                                                            editNom.getText().toString(),
                                                            editPrenom.getText().toString(),
                                                            editNotes.getText().toString());


                        if(isUpdated) Toast.makeText(getActivity(), "Les données sont mises à jour", Toast.LENGTH_LONG).show();
                        else Toast.makeText(getActivity(), "Les données ne sont pas mises à jour", Toast.LENGTH_LONG).show();

                    }
                }

        );

    }

    public void AjouterDonnees(){

        //Un écouteur de clic est placé sur le bouton d'ajout de données
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //Un booléen est crée suivant l'insertion des données
               // qui sont égales aux textes des instances d'editTexts (converties en String)
               boolean isInserted =  myBdd.insertData(editNom.getText().toString(), editPrenom.getText().toString(), editNotes.getText().toString());

                //Si le booléen est égale à true
                //Un toast est affiché qui indique que les données ont bien été insérées
                //Un toast prend en paramètre : un context, une string, un type de taille
                //Enfin la méthode show() est appelée sur le Toast pour l'afficher
                if(isInserted) Toast.makeText(getActivity(), "Les données sont insérées", Toast.LENGTH_LONG).show();

                //Sinon un autre toast renvoit le contraire
                else Toast.makeText(getActivity(), "Les données ne sont pas insérées", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void VoirTout(){

        //Un écouteur de clic est posé sur le btnVoir
        btnVoir.setOnClickListener(

                new View.OnClickListener(){

                    @Override
                    public void onClick(View v){

                        //La fonction de DatabaseHelper est appelée
                        //Elle permet de ranger toutes les lignes de notre database dans un cursor
                       Cursor res = myBdd.getAllData();

                        //Si la taille du cursor est égale à 0
                        //Cela signifie que l'opération n'a pas marché
                        if(res.getCount() == 0){

                            //Cela va créer un messaage d'erreur
                            //Renvoyé par la fonction showMessage
                            showMessage("Erreur", "Rien n'a été trouvé");
                            //La fonction s'arrête
                            return;

                        }

                        //S'il y a un résultat
                        //On crée une instance de StringBuffer nommée buffer
                        //StringBuffer est une string mise en tampon
                        //Cela veut dire qu'elle va être détruite plus tard
                        StringBuffer buffer = new StringBuffer();

                        //La fonction moveToNext permet de déplacer le cursor au résultat suivant
                        while(res.moveToNext()){

                            //La string buffer va ajouter progressivement les résultats de la requête
                            //Avec le nom de la bonne colonne au début
                            //+ \n qui permet un retour à la ligne,

                            buffer.append("Id : " + res.getString(0) + "\n");
                            buffer.append("Nom : "+ res.getString(1)+ "\n");
                            buffer.append("Prénom : "+ res.getString(2)+ "\n");

                            //Ou quand il est double, un saut de ligne
                            buffer.append("Notes : "+ res.getString(3)+ "\n\n");

                        }

                        //La fonction showMessage est appelée
                        showMessage("Data", buffer.toString());

                    }


                }

        );

    }

    public void showMessage(String title, String Message){

        //Une alerte dialogue est une alerte qui apparaît sur l'écran
        //Son thème est celui par défaut, défini par Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        //Elle peut disparaître si setCancelable est à ture (si on clique en dehors de l'alerte)
        builder.setCancelable(true);
        //Son titre peut être celui d'une variable
        builder.setTitle(title);
        //Son message aussi
        //Ici ce sera les données de notre BDD
        //Car ces infos sont passées en paramètres quand showMessage est appelée dans la fonction voirTout()
        builder.setMessage(Message);

        //Le builder va être affiché
        builder.show();

    }

}


