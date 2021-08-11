package com.example.cscb07summer.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        EditText birthDateEdit = (EditText) findViewById(R.id.register_birth_date);
        EditText nameEdit = (EditText) findViewById(R.id.register_name);

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String pass2 = password2Edit.getText().toString();
        String birthDate = birthDateEdit.getText().toString();
        String name = nameEdit.getText().toString();

        Spinner sp = (Spinner) findViewById(R.id.register_gender);
        String gender = String.valueOf(sp.getSelectedItem());

        if(pass2.equals(password) && !gender.equals("Please select your gender") && !name.isEmpty() && !birthDate.isEmpty()){
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Register success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If register in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(register.this, "Email Already registered Or is not a valid email",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
        else {
            Toast.makeText(register.this, "Please check again the info that you have entered",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUI(FirebaseUser user) {

        EditText emailEdit = (EditText) findViewById(R.id.register_email);
        EditText passwordEdit = (EditText) findViewById(R.id.register_password);
        EditText birthDateEdit = (EditText) findViewById(R.id.register_birth_date);
        EditText nameEdit = (EditText) findViewById(R.id.register_name);

        String birthDate = birthDateEdit.getText().toString();
        String name = nameEdit.getText().toString();
        Spinner sp = (Spinner) findViewById(R.id.register_gender);
        String gender = String.valueOf(sp.getSelectedItem());
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if(user == null){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        else {
            DatabaseReference  ref = FirebaseDatabase.getInstance().getReference();
            String uid = user.getUid();
            CheckBox cb = (CheckBox) findViewById(R.id.RegisterCheckBox);

            /*Pattern password_pattern = Pattern.compile(".{8,}");
            Pattern birthDate_pattern = Pattern.compile("(0[1-9]|1[0-2])//(0[1-9]|1[0-9]|2[0-9]|3[0-1])//[12][09][0-9][0-9]]");

            Matcher birthDate_matcher = birthDate_pattern.matcher(birthDate);
            Matcher password_matcher = password_pattern.matcher(password);
            if(!(birthDate_matcher.matches())){
                Toast.makeText(register.this, "birth Date entered is not valid(MM/DD/YYYY)",
                        Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                user.delete();
                finish();
                startActivity(intent);
            }
            if(!(password_matcher.matches())){
                Toast.makeText(register.this, "password is less then 8 characters, please enter a longer password",
                        Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                user.delete();
                finish();
                startActivity(intent);
            }*/
            if(cb.isChecked() == true){
                Intent intent = new Intent(this, SpecializationActivity.class);
                intent.putExtra("birthDate", birthDate);
                intent.putExtra("email", email);
                intent.putExtra("gender", gender);
                intent.putExtra("password", password);
                intent.putExtra("name", name);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
            else {
                List<String> emptyList = new ArrayList<String>();

                ref.child("Patients").child(uid).setValue(uid);
                ref.child("Patients").child(uid).child("Birth date").setValue(birthDate);
                ref.child("Patients").child(uid).child("Email").setValue(email);
                ref.child("Patients").child(uid).child("Gender").setValue(gender);
                ref.child("Patients").child(uid).child("Password").setValue(password);
                ref.child("Patients").child(uid).child("Name").setValue(name);
                emptyList.add("Previous Doctors");
                ref.child("Patients").child(uid).child("Previous Doctors").setValue(emptyList);
                emptyList.clear();
                emptyList.add("Upcoming Appointments");
                ref.child("Patients").child(uid).child("Upcoming Appointments").setValue(emptyList);
                emptyList.clear();
                emptyList.add("Past Appointments");
                ref.child("Patients").child(uid).child("Past Appointments").setValue(emptyList);
                Toast.makeText(register.this, "register success",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }


        }
    }



}
