package com.camille.camille_castillo_projet_android_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Camille on 06/01/2016.
 */

//DatabaseHelper est une classe qui va nous aider à exécuter des opérations sur notre Base de Données : créer, mettre à jour, voir, supprimmer des données
//Elle doit hériter de la classe SQLiteOpenHelper qui permet de gérer SQLite avec Androïd

public class DatabaseHelper extends SQLiteOpenHelper {

    //Pour créer une Base de Données avec Androïd, nous avons besoin tout d'abord d'un nom pour la BDD
    //Ce nom sera un attribut de notre classe, tout comme le nom de la table et des colonnes
    public static final String DATABASE_NAME = "etudiants.db";
    //Ensuite un nom de table
    public static final String TABLE_NAME = "etudiants_table";
    //Et un nom pour chaque colonne
    //ID représente la clé primaire de la table
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOM";
    public static final String COL_3 = "PRENOM";
    public static final String COL_4 = "NOTES";

    public DatabaseHelper(Context contex){

        //La Base de Données est crée quand le constructeur est appelé
        //La super classe a besoin du context
        // du nom de la BDD,
        // du comportement de base du cursorFactory soit celui de base car null,
        // et de la version de la BDD ici, donc 1
        super(contex, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //execSQL indique une requête que la BDD va exécuter dès qu'elle est crée
        //Ici une table portant le nom de notre va être crée,
        //Un + qui est un opérateur de concaténation marque la différence entre TABLE_NAME qui est une variable Java, et le reste, de type String
        // prenant comme colonne ID de type entier, qui s'autoincrémente et est clé primaire,
        //Name de type text,
        //Surname de type text,
        //Et marks de type entier
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT, PRENOM TEXT, NOTES INTEGER) ");

    }

    @Override
    //Méthode permettant de passer à la version supérieure d'une BDD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //La table se détruit si elle existe puis est remplacé
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        //La méthode onCreate avec la nouvelle BDD comme argument est appelée et exécutée
        onCreate(db);

    }

    //Méthode pour insérer des données dans la BDD
    //Elle prend en argument toutes les colonnes sauf ID qui va être crée automatiquement
    public boolean insertData(String nom, String prenom, String notes){

        //Une instance de SQLiteDatabase est crée, nommée db
        //Cette BDD pourra être modifiée (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        //Une instance de classe ContentValeus est crée
        //Elle perment de ranger des données à l'intérieur
        //Comme des strings, des variables...
        ContentValues contentValues = new ContentValues();

        //Ici on range donc dans contentvalues chaque donnée de colonne,
        // avec le nom de la colonne
        contentValues.put(COL_2, nom);
        contentValues.put(COL_3, prenom);
        contentValues.put(COL_4, notes);

        //insert prend 3 paramètres,
        // le nom de table,
        //En deuxième un paramètre qui doit être égal à null,
        // Sauf si on désire passer une valeur nulle à une colonne,
        //Car SQLite ne permet pas d'insérer que des colonnes vides
        // les données à insérer avec les nom des colonnes et leurs valeurs
        //La méthode est sauvegardée dans une long variable
        //Car elle retourne -1 si erreur
        //0 sinon
        long result = db.insert(TABLE_NAME, null, contentValues);

        //La méhtode retournera false si elle n'a pas marché
        if(result == -1) return false;
        //Sinon true
        else return true;

    }

    public Cursor getAllData(){

        //Une instance de SQLiteDatabase est crée, nommée db
        //Cette BDD pourra être modifiée (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        //rawQuery permet de créer une requête pour notre base
        //Cursor représente une collections de données relatives à une BDD (les lignes)
        //Ici toutes les lignes de la table sont retournée (par exemple 1 Camille Castillo 80)
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);

        //La fonction attend un objet de type Cursor en retour
        //Il est ici retourné
        return res;

    }

    public boolean updateData(String id, String nom, String prenom, String notes) {

        //Une instance de SQLiteDatabase est crée, nommée db
        //Cette BDD pourra être modifiée (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        //Une instance de classe ContentValeus est crée
        //Elle perment de ranger des données à l'intérieur
        //Comme des strings, des variables...
        ContentValues contentValues = new ContentValues();

        //Ici on range donc dans contentvalues chaque donnée de colonne,
        // avec le nom de la colonne
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nom);
        contentValues.put(COL_3, prenom);
        contentValues.put(COL_4, notes);

        //4 arguments pour updater une table de la BDD
        //nom de la table, la colonne à modifier et son contenu, la condition
        //"ID == ?" indique que la colonne doit se modifier suivant
        db.update(TABLE_NAME, contentValues, "ID == ?", new String[] {id});

        //La fonction doit retourner un booléen (ici true)
        return true;

    }

    public Integer deleteData(String id){

        //Une instance de SQLiteDatabase est crée, nommée db
        //Cette BDD pourra être modifiée (writable)
        SQLiteDatabase db = this.getWritableDatabase();

        //La fonction delete prend 3 arguments
        //Elle supprimme les valeurs
        //Le nom de la table, le nom du début des colonnes à supprimer (ici depuis l'id)
        return db.delete(TABLE_NAME, "ID == ?", new String[] {id});

    }

}
