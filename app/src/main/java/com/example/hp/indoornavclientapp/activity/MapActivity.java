package com.example.hp.indoornavclientapp.activity;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.indoornavclientapp.R;
import com.example.hp.indoornavclientapp.model.WapModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity {
    public static final String EMPLOYEE_ID_PARCEL_KEY = "employee id parcel key";
    private static final int MARKER_ID = 0x000012;
    private String employeeId;
    ViewGroup parent;
    private ConstraintSet conSet;
    private ImageView marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        parent = findViewById(R.id.parent);
        Bundle args = getIntent().getExtras();
        if(args==null)
            args = savedInstanceState;
        employeeId = args.getString(EMPLOYEE_ID_PARCEL_KEY);

        marker = new ImageView(this);
        marker.setImageResource(R.drawable.radarone);
        marker.setId(MARKER_ID);
        parent.addView(marker);
        conSet = new ConstraintSet();
        conSet.constrainHeight(marker.getId(),50);
        conSet.constrainWidth(marker.getId(),50);

        FirebaseDatabase.getInstance().getReference()
                .child("location_table")
                .child(employeeId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        WapModel wapModel = dataSnapshot.getValue(WapModel.class);
                        Coordinates markerPos = calculateCoordinates(wapModel.getId());
                        showToast(markerPos.x+" : "+markerPos.y);
                        placeMarker(markerPos.x,markerPos.y);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        databaseError.toException().printStackTrace();
                    }
                });
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    Coordinates calculateCoordinates(int locationId) {
        Coordinates markerPos = null;
        switch (locationId) {
            case 0 : {
                markerPos = new Coordinates(210,500);
                break;
            }
            case 1 : {
                markerPos = new Coordinates(250,1300);
                break;
            }
            case 2 : {
                markerPos = new Coordinates(825,500);
                break;
            }
            case 3 : {
                markerPos = new Coordinates(825,700);
                break;
            }
            case 4 : {
                markerPos = new Coordinates(850,1200);
                break;
            }
            case 5 : {
                markerPos = new Coordinates(850,1450);
                break;
            }
            default : {
                break;
            }
        }
        return markerPos;
    }

    void placeMarker(int x,int y) {
        conSet.connect(marker.getId(),ConstraintSet.LEFT,parent.getId(), ConstraintSet.LEFT,x);
        conSet.connect(marker.getId(),ConstraintSet.TOP,parent.getId(), ConstraintSet.TOP,y);
        conSet.applyTo((ConstraintLayout)parent);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(EMPLOYEE_ID_PARCEL_KEY,employeeId);
    }
}
