package com.example.rahul.firebase;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class updateActivity extends AppCompatActivity {

    EditText updateName,updateAge,updateEmail,updateMobile;
    Button updateButton;

    FirebaseAuth auth;
    FirebaseDatabase db;
    String newName,newAge,newEmail,newType,newMobile;
    StorageReference sr;
    Uri imagepath;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateName=findViewById(R.id.etUpdateName);
        updateEmail=findViewById(R.id.etupdateEmail);
        updateAge=findViewById(R.id.etUpdateAge);
        updateMobile=findViewById(R.id.etUpdateMobile);
    //    userprofilePic=findViewById(R.id.profilePic);
        updateButton=(Button) findViewById(R.id.btProfileUpdate);

        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        sr=storage.getReference();
        final DatabaseReference ref=db.getReference("mangers").child(auth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile profile=dataSnapshot.getValue(userProfile.class);
                updateName.setText(/*profilename.toString().trim()+*/"Name:"+profile.getName());
                updateEmail.setText(/*profileEmail.toString().trim()+*/"Email:"+profile.getEmail());
                updateAge.setText(/*profileAge.toString().trim()+*/"Age:"+profile.getAge());
                updateMobile.setText("Mobile No:"+profile.getuserMobile());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(updateActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();

            }




        }  );


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newName=updateName.getText().toString().trim();
                newAge=updateAge.getText().toString().trim();
                newEmail=updateEmail.getText().toString().trim();
                newMobile=updateMobile.getText().toString().trim();

                userProfile profile=new userProfile(newName,newEmail,newAge,"Ram",newMobile);
                ref.setValue(profile);

                finish();
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
