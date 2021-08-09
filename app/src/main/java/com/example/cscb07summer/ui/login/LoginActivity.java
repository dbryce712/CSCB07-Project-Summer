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
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.databinding.ActivityLoginBinding;
import com.example.cscb07summer.profile.DoctorMain;
import com.example.cscb07summer.profile.ProfilePatient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import java.text.BreakIterator;

public class LoginActivity extends AppCompatActivity {

    private static final String UID = "";
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginInfo";
    private static final String AAA = "aaa";
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

    }

    public void signIn(View view) {
        EditText emailEdit = (EditText) findViewById(R.id.email);
        EditText passwordEdit = (EditText) findViewById(R.id.password);
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

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
            Intent intent = new Intent(this, register.class);
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

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
    public void goToRegister(View view){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

}