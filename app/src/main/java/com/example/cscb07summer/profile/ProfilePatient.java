package com.example.cscb07summer.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePatient extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepatient);
    }
}
