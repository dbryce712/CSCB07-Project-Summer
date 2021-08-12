package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DoctorMain extends AppCompatActivity{

    private Button profile;
    private Button patientList;
    private Button appointments;
    private Button logout;
    private Button delete;
    private Button patientLink;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_main_interface);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        profile = (Button)findViewById(R.id.ProfileButton);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile(username);
            }
        });

        patientList = (Button)findViewById(R.id.PatientListButton);
        patientList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientList(username);
            }
        });

        appointments = (Button)findViewById(R.id.AppointmentsButton);
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointments(username);
            }
        });

        logout = (Button)findViewById(R.id.LogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogout();
            }
        });
        delete = (Button) findViewById(R.id.deleteDoctorButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAcc();
            }
        });
    }

    public void openProfile(String username) {
        Intent intent = new Intent(this, DoctorProfile.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
    public void openPatientList(String username) {
        Intent intent = new Intent(this, DoctorPatientList.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }
    public void openAppointments(String username) {
        Intent intent = new Intent(this, DoctorAppointment.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void openLogout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void deleteAcc(){

    }

}
