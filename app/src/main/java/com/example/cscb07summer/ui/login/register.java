package com.example.cscb07summer.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cscb07summer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class register extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view){
        EditText email = (EditText) findViewById(R.id.register_email);
        EditText password = (EditText) findViewById(R.id.register_password);
        EditText password2 = (EditText) findViewById(R.id.register_password2);
        String pass = password.getText().toString();
        String pass2 = password2.getText().toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DataSnapshot ds = ref.child("patient").child("email").get().getResult();

        if(ds.hasChild(email.getText().toString())){
            LinearLayout layout = (LinearLayout)findViewById(R.id.register_container);
            TextView myText = new TextView(this);
            myText.setText("email is already registered");
            layout.addView(myText);
        }
        else if(pass.equals(pass2)){
            LinearLayout layout = (LinearLayout)findViewById(R.id.register_container);
            TextView myText = new TextView(this);
            myText.setText("password does not match");
            layout.addView(myText);
        }else{
            ref.child("patient").child("email").setValue(email.getText().toString());
            ref.child("patient").child("password").setValue(pass);
        }
    }


}
