package com.example.cscb07summer.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.databinding.ActivityLoginBinding;
import com.example.cscb07summer.profile.DoctorMain;
import com.example.cscb07summer.profile.ProfilePatient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String UID = "";
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginInfo";
    private static final String AAA = "aaa";
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mAuth = FirebaseAuth.getInstance();

        Button forgotPassword = (Button) findViewById(R.id.forgot_password_button);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_password_button:
                startActivity(new Intent(this, ForgotPassword.class));
        }

    }

    public void signIn(View view) {

        EditText emailEdit = (EditText) findViewById(R.id.email);
        EditText passwordEdit = (EditText) findViewById(R.id.password);

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        CheckBox cb = (CheckBox) findViewById(R.id.is_Doc_check_login);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            FirebaseUser user = mAuth.getCurrentUser();
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.child("Doctors").hasChild(user.getUid()) && cb.isChecked()) {
                                        Log.d(TAG, "DoctorSignInWithEmail:success");
                                        updateUI(user);
                                    }
                                    else if(snapshot.child("Patients").hasChild(user.getUid()) && !cb.isChecked()) {
                                        Log.d(TAG, "PatientSignInWithEmail:success");
                                        updateUI(user);
                                    }
                                    else {
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Make sure this is a patient or doctor account",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                    updateUI(null);
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {


        CheckBox cb = (CheckBox) findViewById(R.id.is_Doc_check_login);
        if(user == null){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else {
            if(cb.isChecked()){
                Intent intent = new Intent(this, DoctorMain.class);
                intent.putExtra(UID, user.getUid());
                startActivity(intent);
            }else{

                Intent intent = new Intent(this, ProfilePatient.class);
                intent.putExtra(UID, user.getUid());
                startActivity(intent);
            }
        }

    }


    public void goToRegister(View view){
        //navigates to registration page
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }


}