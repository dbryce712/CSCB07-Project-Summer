package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePatientEdit extends AppCompatActivity {

    TextInputEditText patName, patEmail, patGender, patBirth;
    EditText patMed;
    Button patDone;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepatientedit);

        patName = findViewById(R.id.PatienteditName);
        patEmail = findViewById(R.id.PatienteditEmail);
        patGender = findViewById(R.id.PatientEditGender);
        patBirth = findViewById(R.id.PatientEditBirth);
        patMed = findViewById(R.id.PatientconditionEdit);
        patDone = findViewById(R.id.PatientEditDone);

        //save data in firebase on button click
        patDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Patients");

                //get all the values
                String name = patName.getText().toString();
                String email = patEmail.getText().toString();
                String gender = patGender.getText().toString();
                String birth = patBirth.getText().toString();
                String condition = patMed.getText().toString();

                Intent intent = getIntent();
                String username = intent.getStringExtra("Username");

                reference.child(username).child("Name").setValue(name);
                reference.child(username).child("Email").setValue(email);
                reference.child(username).child("Gender").setValue(gender);
                reference.child(username).child("Birth date").setValue(birth);

                openProfile();
            }
        });
    }
    public void openProfile() {
        Intent intent = new Intent(this, ProfilePatient.class);
        startActivity(intent);
    }
}
