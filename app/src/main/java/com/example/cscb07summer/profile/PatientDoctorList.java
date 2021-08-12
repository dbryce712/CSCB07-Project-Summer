package com.example.cscb07summer.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cscb07summer.R;

public class PatientDoctorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor_list);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

    }
}