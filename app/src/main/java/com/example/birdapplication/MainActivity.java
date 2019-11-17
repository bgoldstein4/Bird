package com.example.birdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZipCode1, editTextHumanName;
    Button buttonSubmit, buttonToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipCode1 = findViewById(R.id.editTextZipCode1);
        editTextHumanName = findViewById(R.id.editTextHumanName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonToSearch = findViewById(R.id.buttonToSearch);

        buttonSubmit.setOnClickListener(this);
        buttonToSearch.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String birdname = editTextBirdName.getText().toString();
        String zipcode = editTextZipCode1.getText().toString();
        String personname = editTextHumanName.getText().toString();

        if (view == buttonSubmit) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("bird");

            Bird bird = new Bird(birdname, zipcode, personname);
            myRef.push().setValue(bird);

        }
        if (view == buttonToSearch) {
            Intent mainIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(mainIntent);
        }



    }


}

