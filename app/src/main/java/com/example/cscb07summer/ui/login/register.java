package com.example.cscb07summer.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.cscb07summer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import java.text.BreakIterator;

public class register extends Activity {
    private FirebaseAuth auth;
    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    private void reload() { }

    public void createAccount(View view) {
        // [START create_user_with_email]
        EditText emailEdit = (EditText) findViewById(R.id.register_email);
        EditText passwordEdit = (EditText) findViewById(R.id.register_password);
        EditText password2Edit = (EditText) findViewById(R.id.register_password2);
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String pass2 = password2Edit.getText().toString();
        if(pass2.equals(password)){
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(register.this, "Email Already registered",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }else{
            Toast.makeText(register.this, "Two password does not match.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private void updateUI(FirebaseUser user) {
        EditText emailEdit = (EditText) findViewById(R.id.register_email);
        EditText passwordEdit = (EditText) findViewById(R.id.register_password);
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if(user == null){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else{
            DatabaseReference  ref = FirebaseDatabase.getInstance().getReference();
            String uid = user.getUid();
            CheckBox cb = (CheckBox) findViewById(R.id.RegisterCheckBox);
            if(cb.isChecked()){
                ref.child("Doctors").setValue(uid);
                ref.child("Doctors").child(uid).child("Birth date").setValue("1, jan, 1970");
                ref.child("Doctors").child(uid).child("Email").setValue(email);
                ref.child("Doctors").child(uid).child("Gender").setValue("please enter gender");
                ref.child("Doctors").child(uid).child("Password").setValue(password);
                ref.child("Doctors").child(uid).child("Name").setValue("enter your name here");
            }else {
                ref.child("Patients").child(uid).setValue(uid);
                ref.child("Patients").child(uid).child("Birth date").setValue("1, jan, 1970");
                ref.child("Patients").child(uid).child("Email").setValue(email);
                ref.child("Patients").child(uid).child("Gender").setValue("please enter gender");
                ref.child("Patients").child(uid).child("Password").setValue(password);
                ref.child("Patients").child(uid).child("Name").setValue("enter your name here");
            }

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }



}
