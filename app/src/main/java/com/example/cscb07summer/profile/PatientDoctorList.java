package com.example.cscb07summer.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cscb07summer.R;

public class PatientDoctorList extends AppCompatActivity {

    private Button one, two, three, four, five, six, seven, eight, nine, ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor_list);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        one = (Button)findViewById(R.id.button2);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        two = (Button)findViewById(R.id.button3);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        three = (Button)findViewById(R.id.button4);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        four = (Button)findViewById(R.id.button5);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        five = (Button)findViewById(R.id.button6);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        six = (Button)findViewById(R.id.button7);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        seven = (Button)findViewById(R.id.button8);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        eight = (Button)findViewById(R.id.button9);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        nine = (Button)findViewById(R.id.button10);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

        ten = (Button)findViewById(R.id.button11);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBook();
            }
        });

    }
    public void openBook() {
        Intent intent = new Intent(this, BookAppointment.class);
        startActivity(intent);
    }
}