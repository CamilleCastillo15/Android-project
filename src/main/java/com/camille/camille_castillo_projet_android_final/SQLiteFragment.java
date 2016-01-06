package com.camille.camille_castillo_projet_android_final;


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
    EditText editName, editSurname, editMarks;
    Button btnAddData;

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

        btnAddData = (Button)_vue.findViewById(R.id.button_add);

        AddData();

        return _vue;
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

}
