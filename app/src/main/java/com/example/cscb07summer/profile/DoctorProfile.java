package com.example.cscb07summer.profile;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.example.cscb07summer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorProfile extends DoctorMain{

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledoctor);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Doctors");

        edit = (Button)findViewById(R.id.editDoctor);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileEdit();
            }
        });
    }

    public void openProfileEdit() {
        Intent intent = new Intent(this, DoctorProfileEdit.class);
        startActivity(intent);
    }

}
