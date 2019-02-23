package com.example.hp.indoornavclientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class FacultyActivity extends AppCompatActivity {

        ListView search_faculty;
        ArrayAdapter<String> adapter ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_faculty);
            Spinner spinner=findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.departments,android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            search_faculty=(ListView) findViewById(R.id.Faculty);
            ArrayList<String> arrayFaculty = new ArrayList<>();
            for(int i=0;i<6;++i)
                arrayFaculty.add("Faculty "+i);
            adapter = new ArrayAdapter<String>(
                    FacultyActivity.this,
                    android.R.layout.simple_list_item_1,
                    arrayFaculty
            );
            search_faculty.setAdapter(adapter);
        }



        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater= getMenuInflater();

            inflater.inflate(R.menu.search_faculty, menu);
            MenuItem item1 = menu.findItem(R.id.search_faculty);
            /*SearchView searchview=(SearchView)item1.getActionView();

            searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });*/
            return super.onCreateOptionsMenu(menu);
        }
}

