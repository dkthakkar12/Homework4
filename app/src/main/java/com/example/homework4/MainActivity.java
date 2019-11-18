package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editBirdName, editZipCode, editPersonName;
    Button buttonSubmit, buttonGotoSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editBirdName = findViewById(R.id.editBirdName);
        editZipCode = findViewById(R.id.editZipCode);
        editPersonName = findViewById(R.id.editPersonName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGotoSearch = findViewById(R.id.buttonGotoSearch);

        buttonSubmit.setOnClickListener(this);
        buttonGotoSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {



        if (view == buttonSubmit) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Bird Sighting");

            String birdname = editBirdName.getText().toString();
            int zipcode = Integer.parseInt(editZipCode.getText().toString());
            String personname = editPersonName.getText().toString();

            Bird myBird = new Bird(birdname, zipcode, personname);

            myRef.push().setValue(myBird);


        } else if (view == buttonGotoSearch) {
            Intent mainIntent = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(mainIntent);

        }

    }
}


