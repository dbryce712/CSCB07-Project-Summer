package com.example.cscb07summer.profile;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cscb07summer.R;
import com.example.cscb07summer.ui.login.register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DateFormat;

public class DoctorProfileEdit extends DoctorMain{

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private Button save;
    private String currentName, currentEmail, currentSpec;
    private EditText nameText, emailText, specializationText;
    private String uid;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledoctoredit);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Doctors");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Intent intent = getIntent();
        //String username = intent.getStringExtra("Username");

        nameText = (EditText) findViewById(R.id.DoctorChangeName);
        emailText = (EditText) findViewById(R.id.DoctorChangeEmail);
        //genderText = findViewById(R.id.DoctorGender);
        //birthdayText = findViewById(R.id.DoctorChangeBirth);
        specializationText = (EditText) findViewById(R.id.DoctorChangeSpec);
        currentName = nameText.getText().toString();
        currentEmail = emailText.getText().toString();
        currentSpec = specializationText.getText().toString();

        save = (Button)findViewById(R.id.DoctorSaveProfile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               save(user);
            }
        });
    }
    public void save(FirebaseUser user) {
        boolean change = false;
        if(!reference.child(user.getUid()).child("Name").equals(currentName) && !currentName.isEmpty()) {
            reference.child(user.getUid()).child("Name").setValue(currentName);
            change = true;
        }
        if(!reference.child(user.getUid()).child("Email").equals(currentEmail) && !currentEmail.isEmpty()) {
            reference.child(user.getUid()).child("Email").setValue(currentEmail);
            change = true;
        }
        if(!reference.child(user.getUid()).child("Specialization").equals(currentSpec) && !currentSpec.isEmpty()) {
            reference.child(user.getUid()).child("Specialization").setValue(currentSpec);
            change = true;
        }
        if(change) {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /**
    public void save(String username) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    if(ds.child("Email").getValue().equals(username)) {
                        if(!ds.child("Name").getValue().equals(nameText.getEditText().getText().toString())) {
                            reference.child(user.getUid()).child("Name").setValue(nameText.getEditText().getText().toString());
                        }
                        if(!ds.child("Email").getValue().equals(emailText.getEditText().getText().toString())) {
                            reference.child(user.getUid()).child("Email").setValue(emailText.getEditText().getText().toString());
                        }
                        if(!ds.child("Specialization").getValue().equals(specializationText.getEditText().getText().toString())) {
                            reference.child(user.getUid()).child("Specialization").setValue(specializationText.getEditText().getText().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DoctorProfileEditClass", error.toException());
            }
        });
    }*/
    /**
    public void updateProfile() {
        // [START update_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nameText.getEditText().getText().toString())
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        // [END update_profile]
    }*/

}
