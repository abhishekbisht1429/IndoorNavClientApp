package com.example.hp.indoornavclientapp.activity;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.indoornavclientapp.R;
import com.example.hp.indoornavclientapp.model.Employee;
import com.example.hp.indoornavclientapp.model.WapModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GuardsActivity extends AppCompatActivity {
    private static final String TAG = "GuardsActivity";
    private List<Employee> guards = new ArrayList<>();
    ViewGroup parent;
    int idcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guards);
        parent = findViewById(R.id.parent);
        FirebaseDatabase.getInstance().getReference()
                .child("employee_db").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> guardIds = new ArrayList<>();
                        for(DataSnapshot ds:dataSnapshot.getChildren()) {
                            Employee employee = ds.getValue(Employee.class);
                            if(employee.getType().equals("guard")) {
                                guards.add(employee);
                                guardIds.add(employee.getEmployeeId());
                            }
                        }
                        Collections.sort(guardIds);
                        FirebaseDatabase.getInstance().getReference()
                                .child("location_table")
                                .startAt(guardIds.get(0))
                                .endAt(guardIds.get(guardIds.size()-1))
                                .orderByKey()
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        HashMap<String, WapModel> wapMap = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, WapModel>>() {});
                                        if(wapMap!=null) {
                                            List<String> macAddrs = new ArrayList<>();
                                            for (String key : wapMap.keySet()) {
                                                String macAddr = wapMap.get(key).getMacAddr();
                                                macAddrs.add(macAddr);
                                            }
                                            Collections.sort(macAddrs);
                                            Toast.makeText(GuardsActivity.this,macAddrs.size()+"",Toast.LENGTH_SHORT).show();
                                            if(macAddrs.size()>0) {
                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("address_table")
                                                        .startAt(macAddrs.get(0))
                                                        .endAt(macAddrs.get(macAddrs.size() - 1))
                                                        .orderByKey()
                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                HashMap<String, WapModel> wapMap = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, WapModel>>() {});
                                                                System.out.println(wapMap);
                                                                if (wapMap != null) {
                                                                    for (String key : wapMap.keySet()) {
                                                                        Coordinates coordinates = calculateCoordinates(wapMap.get(key).getId());
                                                                        plotMarker(coordinates.x, coordinates.y);
                                                                    }
                                                                }

                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                            }
                                        } else {

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        databaseError.toException().printStackTrace();
                    }
                });
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

    void plotMarker(int x,int y) {
        ImageView marker = new ImageView(this);
        marker.setImageResource(R.drawable.radar2);
        marker.setId(idcount++);
        parent.addView(marker);
        ConstraintSet conSet = new ConstraintSet();
        conSet.constrainHeight(marker.getId(),50);
        conSet.constrainWidth(marker.getId(),50);
        conSet.connect(marker.getId(),ConstraintSet.LEFT,parent.getId(), ConstraintSet.LEFT,x);
        conSet.connect(marker.getId(),ConstraintSet.TOP,parent.getId(), ConstraintSet.TOP,y);
        conSet.applyTo((ConstraintLayout)parent);
    }
}
