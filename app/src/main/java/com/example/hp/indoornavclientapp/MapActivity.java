package com.example.hp.indoornavclientapp;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {
    public static final String EMPLOYEE_ID_PARCEL_KEY = "employee id parcel key";
    private static final int MARKER_ID = 0x000012;
    private String employeeId;
    ViewGroup parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        parent = findViewById(R.id.parent);
        Bundle args = getIntent().getExtras();
        if(args==null)
            args = savedInstanceState;
        employeeId = args.getString(EMPLOYEE_ID_PARCEL_KEY);

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.logo1);
        //iv.setId()
        ConstraintSet conSet = new ConstraintSet();
        //conSet.connect()
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(EMPLOYEE_ID_PARCEL_KEY,employeeId);
    }
}
