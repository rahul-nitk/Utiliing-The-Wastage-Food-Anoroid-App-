package com.example.rahul.firebase;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class consumerInfoActivity extends AppCompatActivity {

    TextView newEmail,newName,newAddress,newAge;
    Button newCollected;

    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newEmail=findViewById(R.id.tvConsumerEmail);
        newName=findViewById(R.id.tvCOnsumerName);
        newAddress=findViewById(R.id.tvConsumerAddress);
        newAge=findViewById(R.id.tvconsumerAge);
        newCollected=findViewById(R.id.btCollected);
        Intent intent=getIntent();
        String key=intent.getStringExtra("key");
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference("consumers").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                consumerProfile detail=dataSnapshot.getValue(consumerProfile.class);
                newEmail.setText(newEmail.getText()+" "+detail.getConsumerEmail());
                newName.setText(newName.getText()+" "+detail.getConsumerName());
                newAddress.setText(newAddress.getText()+" "+detail.getConsumerAddress());
                newAge.setText(newAge.getText()+" "+detail.getConsumerAge());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //newlastTIme.setText(key);




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
