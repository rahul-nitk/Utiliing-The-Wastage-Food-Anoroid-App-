package com.example.rahul.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class consumerAddressDetailActivity extends AppCompatActivity {
    EditText consumerAddress;
    FirebaseDatabase db;
    FirebaseAuth auth;
    Button newupdateConsumerAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_address_detail);

        consumerAddress=findViewById(R.id.etConsumerAddressShow);
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference("consumers").child(auth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                consumerProfile profile=dataSnapshot.getValue(consumerProfile.class);
                consumerAddress.setText(profile.consumerAddress);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(consumerAddressDetailActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT);

            }




        }  );




    }
}
