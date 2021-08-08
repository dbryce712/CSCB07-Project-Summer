package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePatient extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepatient);

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
