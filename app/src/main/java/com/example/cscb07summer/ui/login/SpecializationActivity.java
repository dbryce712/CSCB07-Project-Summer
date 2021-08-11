package com.example.cscb07summer.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cscb07summer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SpecializationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization);
    }

    public void submitButton(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        EditText spEdit = (EditText) findViewById(R.id.register_sp);
        String sp = spEdit.getText().toString();
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        if(sp.isEmpty()){
            Toast.makeText(SpecializationActivity.this, "please enter specialization",
                    Toast.LENGTH_SHORT).show();
        }else{
            String birthDate = intent.getStringExtra("birthDate");
            String email = intent.getStringExtra("email");
            String gender = intent.getStringExtra("gender");
            String password = intent.getStringExtra("password");
            String name = intent.getStringExtra("name");
            ref.child("Doctors").child(uid).setValue(uid);
            ref.child("Doctors").child(uid).child("Birth date").setValue(birthDate);
            ref.child("Doctors").child(uid).child("Email").setValue(email);
            ref.child("Doctors").child(uid).child("Gender").setValue(gender);
            ref.child("Doctors").child(uid).child("Password").setValue(password);
            ref.child("Doctors").child(uid).child("Name").setValue(name);
            ref.child("Doctors").child(uid).child("Specialization").setValue(sp);
            Toast.makeText(SpecializationActivity.this, "register success",
                    Toast.LENGTH_SHORT).show();
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}