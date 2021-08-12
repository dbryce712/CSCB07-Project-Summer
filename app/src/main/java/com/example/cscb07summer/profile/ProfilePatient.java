package com.example.cscb07summer.profile;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String name,email,gender,birth;

    private Button edit;
    private Button list;
    private Button book;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepatient);

        patName = findViewById(R.id.PatientName);
        patEmail = findViewById(R.id.PatientEmail);
        patGender = findViewById(R.id.PatientGender);
        patBirth = findViewById(R.id.PatientBirth);

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
                        name = ds.child("Name").getValue(String.class);
                        email = ds.child("Email").getValue(String.class);
                        gender = ds.child("Gender").getValue(String.class);
                        birth = ds.child("Birth date").getValue(String.class);
                        setAllDataText();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });



        edit = (Button)findViewById(R.id.PatientEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileEdit(username);
            }
        });

        list = (Button)findViewById(R.id.PatientDoctorList);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctorList(username);
            }
        });

        book = (Button)findViewById(R.id.BookAppointmentButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookApp(username);
            }
        });

        logout = (Button)findViewById(R.id.patientLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

    }

    public void openProfileEdit(String username) {
        Intent intent = new Intent(this, ProfilePatientEdit.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openDoctorList(String username) {

        Intent intent = new Intent(this, PatientDoctorList.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void bookApp(String username) {

        Intent intent = new Intent(this, BookAppointment.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void Logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setAllDataText() {
        patName.setText(name);
        patEmail.setText(email);
        patGender.setText(gender);
        patBirth.setText(birth);
    }

}
