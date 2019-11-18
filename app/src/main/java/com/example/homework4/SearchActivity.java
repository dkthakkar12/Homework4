package com.example.homework4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{


    EditText editTextZipcode;
    Button buttonSearchZipcode, buttonReport;
    TextView textViewBirdName, textViewPersonName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        buttonSearchZipcode = findViewById(R.id.buttonSearchZipcode);
        buttonReport = findViewById(R.id.buttonReport);
        editTextZipcode = findViewById(R.id.editTextZipcode);

        buttonSearchZipcode.setOnClickListener(this);
        buttonReport.setOnClickListener(this);

        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);



    }

    @Override
    public void onClick(View v) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bird Sighting");

        if (v == buttonSearchZipcode) {

          //  Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();

            int ZipCode = Integer.parseInt(editTextZipcode.getText().toString());

            myRef.orderByChild("zipcode").equalTo(ZipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                  Bird foundBird = dataSnapshot.getValue(Bird.class);
                  String findBirdName = foundBird.birdname;
                  String findPersonName = foundBird.personname;

                 //   String testOne = "text 1";
                //    String testTwo = "text 2";

               //     textViewBirdName.setText(testOne);
                //    textViewPersonName.setText(testTwo);

                   textViewBirdName.setText(findBirdName);
                   textViewPersonName.setText(findPersonName);



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

        else {
            Intent intentReport = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intentReport);
        }

    }
}
