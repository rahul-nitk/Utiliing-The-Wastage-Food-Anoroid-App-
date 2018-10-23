package com.example.rahul.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {


    TextView  profilename,profileEmail,profileAge,profileType;
    Button profileEdit;
    ImageView userprofilePic;
    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_profile);
        profilename=findViewById(R.id.tvprofilename);
        profileEmail=findViewById(R.id.tvprofileemail);
        profileAge=findViewById(R.id.tvprofileAge);
        userprofilePic=findViewById(R.id.profilePic);
        profileEdit=(Button) findViewById(R.id.btProfileEdit);
        profileType=findViewById(R.id.tvType);

        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference(auth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile profile=dataSnapshot.getValue(userProfile.class);
                profilename.setText(/*profilename.toString().trim()+*/"Name:"+profile.getName());
                profileEmail.setText(/*profileEmail.toString().trim()+*/"Email:"+profile.getEmail());
                profileAge.setText(/*profileAge.toString().trim()+*/"Age:"+profile.getAge());
                profileType.setText("type:"+profile.getType());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT);

            }




        }  );

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profileActivity.this,updateActivity.class));
            }
        });







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())

        {
            case android.R.id.home:
                onBackPressed();


        }

        return super.onOptionsItemSelected(item);
    }


}
