package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfilePatient extends AppCompatActivity {

    TextView patName, patEmail, patGender, patBirth;
    TextView patMed;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepatient);

        patName = findViewById(R.id.PatientName);
        patEmail = findViewById(R.id.PatientEmail);
        patGender = findViewById(R.id.PatientGender);
        patBirth = findViewById(R.id.PatientBirth);
        patMed = findViewById(R.id.ConditionPara);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Patients");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String name = dataSnapshot.child("Name").getValue(String.class);
                String email = dataSnapshot.child("Email").getValue(String.class);
                String Gender = dataSnapshot.child("Gender").getValue(String.class);
                String Birth = dataSnapshot.child("Birth date").getValue(String.class);

                // ..
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        if(username != null) {
            reference.child(username).addValueEventListener(postListener);
        }
        else {
            Toast.makeText(ProfilePatient.this, "extras Empty",
                    Toast.LENGTH_SHORT).show();
        }


        edit = (Button)findViewById(R.id.PatientEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileEdit();
            }
        });
    }

    public void openProfileEdit() {
        Intent intent = new Intent(this, ProfilePatientEdit.class);
        startActivity(intent);
    }
}
