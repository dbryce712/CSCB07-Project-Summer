package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DoctorMain extends AppCompatActivity{

    private Button profile;
    private Button patientList;
    private Button appointments;
    private Button logout;

    private Button patientLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_main_interface);

        profile = (Button)findViewById(R.id.ProfileButton);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        patientList = (Button)findViewById(R.id.PatientListButton);
        patientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientList();
            }
        });

        appointments = (Button)findViewById(R.id.AppointmentsButton);
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointments();
            }
        });

        patientLink = (Button)findViewById(R.id.PatientLink);
        patientLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientProfile();
            }
        });

        /**
        logout = (Button)findViewById(R.id.ProfileButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogout();
            }
        });*/
    }
    public void openProfile() {
        Intent intent = new Intent(this, DoctorProfile.class);
        startActivity(intent);
    }
    public void openPatientList() {
        Intent intent = new Intent(this, DoctorPatientList.class);
        startActivity(intent);
    }
    public void openAppointments() {
        Intent intent = new Intent(this, DoctorAppointment.class);
        startActivity(intent);
    }
    public void openPatientProfile(){
        Intent intent = new Intent(this, ProfilePatient.class);
        startActivity(intent);
    }
    /**
    public void openLogout() {
        Intent intent = new Intent(this, DoctorProfile.class);
        startActivity(intent);
    }*/
}
