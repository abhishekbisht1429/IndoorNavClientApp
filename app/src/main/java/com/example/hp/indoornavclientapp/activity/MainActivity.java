package com.example.hp.indoornavclientapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hp.indoornavclientapp.R;

public class MainActivity extends AppCompatActivity {
    Button facultyBtn;
    Button guardsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facultyBtn = findViewById(R.id.button_faculty);
        guardsBtn = findViewById(R.id.button_find_guards);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button_faculty) {
                    Intent intent= new Intent(MainActivity.this, FacultyActivity.class);
                    startActivity(intent);
                } else if(v.getId() == R.id.button_find_guards) {
                    Intent intent = new Intent(MainActivity.this, GuardsActivity.class);
                    startActivity(intent);
                }

            }
        };
        facultyBtn.setOnClickListener(listener);
        guardsBtn.setOnClickListener(listener);
    }
}
