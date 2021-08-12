package com.example.cscb07summer.profile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cscb07summer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientViewAppointments extends AppCompatActivity {

    TextView patPast, patUp;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_appointments);

        patPast = findViewById(R.id.patientPast);
        patUp = findViewById(R.id.patientUpcoming);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Patients");

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("Email").getValue().equals(username)) {
                        patPast.setText(ds.child("Previous Doctors").getValue(String.class));
                        patUp.setText(ds.child("Upcoming Doctors").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}