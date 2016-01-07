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

    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnUpdate;

    public SQLiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _vue =  inflater.inflate(R.layout.fragment_sqlite, container, false);
        _vue.clearFocus();

        myDb = new DatabaseHelper(this.getActivity());

        editName = (EditText)_vue.findViewById(R.id.editText_name);
        editName.clearFocus();
        editSurname = (EditText)_vue.findViewById(R.id.editText_surname);
        editSurname.clearFocus();
        editMarks = (EditText)_vue.findViewById(R.id.editText_marks);
        editMarks.clearFocus();
        editMarks = (EditText)_vue.findViewById(R.id.editText_id);

        btnAddData = (Button)_vue.findViewById(R.id.button_add);
        btnviewAll = (Button)_vue.findViewById(R.id.button_viewall);
        btnUpdate = (Button)_vue.findViewById(R.id.button_update);

        AddData();
        viewAll();

        return _vue;
    }

    public void UpdateData(){

        btnUpdate.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isUpdated = myDb.updateData();

                    }
                }

        );

    }

    public void AddData(){

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted =  myDb.inserData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());

                if(isInserted) Toast.makeText(getActivity(), "Les données sont insérées", Toast.LENGTH_LONG).show();
                else Toast.makeText(getActivity(), "Les données ne sont pas insérées", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void viewAll(){

        btnviewAll.setOnClickListener(

                new View.OnClickListener(){

                    @Override
                    public void onClick(View v){

                       Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){

                            //afficher les message
                            showMessage("Error", "Nothing found");
                            return;

                        }

                        StringBuffer buffer = new StringBuffer();

                        while(res.moveToNext()){

                            buffer.append("Id : " + res.getString(0) + "\n");
                            buffer.append("Name : "+ res.getString(1)+ "\n");
                            buffer.append("Surname : "+ res.getString(2)+ "\n");
                            buffer.append("Marks : "+ res.getString(3)+ "\n\n");

                        }

                        //Show all datas
                        showMessage("Data", buffer.toString());

                    }


                }

        );

    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);

        builder.show();

    }

}


