package com.example.hp.indoornavclientapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.indoornavclientapp.model.Employee;
import com.example.hp.indoornavclientapp.model.WapModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class FacultyActivity extends AppCompatActivity {

    private static final String TAG = "FacultyActivity";
    RecyclerView search_faculty;
        FacultyAdapter facultyAdapter;
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
            search_faculty=findViewById(R.id.Faculty);
//            ArrayList<String> arrayFaculty = new ArrayList<>();
//            for(int i=0;i<6;++i)
//                arrayFaculty.add("Faculty "+i);
//            adapter = new ArrayAdapter<String>(
//                    FacultyActivity.this,
//                    android.R.layout.simple_list_item_1,
//                    arrayFaculty
//            );
            facultyAdapter = new FacultyAdapter();
            search_faculty.setLayoutManager(new LinearLayoutManager(this));
            search_faculty.setAdapter(facultyAdapter);
            FirebaseDatabase.getInstance().getReference()
                    .child("employee_db")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Employee> employees = new ArrayList<>();
                            for(DataSnapshot ds:dataSnapshot.getChildren()) {
                                employees.add(ds.getValue(Employee.class));
                            }
                            Log.i(TAG,employees.size()+"");
                            facultyAdapter.setEmployees(employees);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

        void onEmployeeSelect(Employee employee) {
            showToast(employee.getEmployeeId());
            FirebaseDatabase.getInstance().getReference()
                    .child("location_table")
                    .child(employee.getEmployeeId())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            WapModel wapModel = dataSnapshot.getValue(WapModel.class);
                            if(wapModel!=null) {
                                showToast(wapModel.getBuilding()+" "+wapModel.getFloor()+" "+wapModel.getMacAddr());
                            } else {
                                showToast("location not found");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            databaseError.toException().printStackTrace();
                        }
                    });
        }

    private void showToast(String msg) {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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

        private class FacultyAdapter extends RecyclerView.Adapter<ItemViewHolder> {
            List<Employee> employees = new ArrayList<>();

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_faculty,viewGroup,false);
                return new ItemViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
                itemViewHolder.bindData(employees.get(i));
            }

            @Override
            public int getItemCount() {
                return employees.size();
            }

            public void setEmployees(List<Employee> employees) {
                if(employees!=null) {
                    this.employees = employees;
                    notifyDataSetChanged();
                }
            }

        }

        private class ItemViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            Employee employee;
            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view_item_layout_faculty);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEmployeeSelect(employee);
                    }
                });
            }

            public void bindData(Employee employee) {
                textView.setText(employee.getEmployeeId());
                this.employee = employee;
            }
        }
}

