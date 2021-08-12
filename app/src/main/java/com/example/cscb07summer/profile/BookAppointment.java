package com.example.cscb07summer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cscb07summer.R;
import com.example.cscb07summer.ui.login.LoginActivity;
import com.example.cscb07summer.ui.login.register;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookAppointment extends DoctorMain{
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        book = (Button)findViewById(R.id.BookButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });

    }

    public void book() {
        Toast.makeText(BookAppointment.this, "Appointment booked",
                Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        intent = new Intent(this, ProfilePatient.class);
        intent.putExtra("Username", username);
        finish();
        startActivity(intent);
    }

}
