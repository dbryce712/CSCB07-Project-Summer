package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorProfilePatientView extends DoctorMain{
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Button bookAppointment;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_patientview);

        bookAppointment = (Button)findViewById(R.id.bookAppointmentButton);
        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookAppointment();
            }
        });

        exit = (Button)findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExit();
            }
        });
    }

    public void openExit() {
        Intent intent = new Intent(this, PatientDoctorList.class);
        startActivity(intent);
    }
    public void openBookAppointment() {
        Intent intent = new Intent(this, DoctorPatientList.class);
        startActivity(intent);
    }
}
