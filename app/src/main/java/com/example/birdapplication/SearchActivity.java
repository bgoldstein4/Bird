package com.example.birdapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextZip2;
    TextView textViewBirdName2, textViewPersonName2;
    Button buttonSearch2, buttonToReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZip2 = findViewById(R.id.editTextZip2);
        textViewBirdName2 = findViewById(R.id.textViewBirdName2);
        textViewPersonName2 = findViewById(R.id.textViewPersonName2);
        buttonSearch2 = findViewById(R.id.buttonSearch2);
        buttonToReport = findViewById(R.id.buttonToReport);

        buttonToReport.setOnClickListener(this);
        buttonSearch2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonToReport) {
            Intent mainIntent = new Intent(SearchActivity.this,MainActivity.class);
            startActivity(mainIntent);
        }
        if (view == buttonSearch2) {
            String zipcode = editTextZip2.getText().toString();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference birdRef = database.getReference("bird");

            // Read from the database
            birdRef.orderByChild("zipcode").equalTo(zipcode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    Bird bird = dataSnapshot.getValue(Bird.class);
                    textViewBirdName2.setText(bird.birdname);
                    textViewPersonName2.setText(bird.personname);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


}
    }}