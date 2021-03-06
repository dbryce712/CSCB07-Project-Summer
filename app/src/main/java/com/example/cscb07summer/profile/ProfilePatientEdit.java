package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePatientEdit extends AppCompatActivity {

    TextInputEditText patName, patEmail, patGender, patBirth;
    EditText patMed;
    Button patDone;

    String userkey;

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


                Intent intent = getIntent();
                String username = intent.getStringExtra("Username");

                System.out.println("setting values for: "+ username);

                reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                    System.out.println("Patient at: " + ds.getKey());
                                    System.out.println("email comparision " + username + " === " + ds.child("Email").getValue());
                                    if (ds.child("Email").getValue().equals(username)) {
                                        System.out.println("connection found");
                                        userkey = ds.getKey();
                                        System.out.println("getting: " + userkey);
                                        reference.child(userkey).child("Name").setValue(name);
                                        reference.child(userkey).child("Email").setValue(email);
                                        reference.child(userkey).child("Gender").setValue(gender);
                                        reference.child(userkey).child("Birth date").setValue(birth);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                System.out.println("userkey: " + userkey);


                openProfile();
            }
        });
    }
    public void openProfile() {
        Intent intent = new Intent(this, ProfilePatient.class);
        intent.putExtra("Username", getIntent().getStringExtra("Username"));
        startActivity(intent);
    }
}
