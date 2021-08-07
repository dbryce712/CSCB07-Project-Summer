package com.example.cscb07summer.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.cscb07summer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorProfileEdit extends DoctorMain{

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledoctoredit);

        save = (Button)findViewById(R.id.DoctorSaveProfile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void save() {
        // Save method
    }

}
