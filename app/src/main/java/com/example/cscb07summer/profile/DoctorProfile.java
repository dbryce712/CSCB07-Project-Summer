package com.example.cscb07summer.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cscb07summer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class DoctorProfile extends DoctorMain{

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private Button edit;
    private TextView nameText, emailText, genderText, birthdayText, specializationText;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledoctor);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        nameText = findViewById(R.id.DoctorName);
        emailText = findViewById(R.id.DoctorEmail);
        genderText = findViewById(R.id.DoctorGender);
        birthdayText = findViewById(R.id.DoctorBirthday);
        specializationText = findViewById(R.id.DoctorSpecialization);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    if(ds.child("Email").getValue().equals(username)) {
                        nameText.setText(ds.child("Name").getValue(String.class));
                        emailText.setText(ds.child("Email").getValue(String.class));
                        genderText.setText(ds.child("Gender").getValue(String.class));
                        birthdayText.setText(ds.child("Birth date").getValue(String.class));
                        specializationText.setText(ds.child("Specialization").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DoctorProfileClass", error.toException());
            }
        });

        edit = (Button)findViewById(R.id.editDoctor);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileEdit();
            }
        });
    }

    public void openProfileEdit() {
        Intent intent = new Intent(this, DoctorProfileEdit.class);
        startActivity(intent);
    }

}
