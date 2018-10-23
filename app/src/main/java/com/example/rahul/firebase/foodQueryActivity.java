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
public class foodQueryActivity extends AppCompatActivity {

    TextView newlastTIme,newhowMuch,newAddress,newtype;
    Button newCollected;

    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_query);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newlastTIme=findViewById(R.id.tvLastTime);
        newhowMuch=findViewById(R.id.tvHowManyPeople);
        newAddress=findViewById(R.id.tvAddress);
        newtype=findViewById(R.id.tvType);
        newCollected=findViewById(R.id.btCollected);
        Intent intent=getIntent();
        String key=intent.getStringExtra("key");
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference("Food Detail").child(key);
        ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            foodDetail detail=dataSnapshot.getValue(foodDetail.class);
            newlastTIme.setText(newlastTIme.getText()+" "+detail.getfoodTime());
            newhowMuch.setText(newhowMuch.getText()+" "+detail.getNoOfPeople());
            newAddress.setText(newAddress.getText()+" "+detail.getfoodAddress());
            newtype.setText(newtype.getText()+" "+detail.getfoodType());


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

        //newlastTIme.setText(key);


newCollected.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent intent=getIntent();
        String key=intent.getStringExtra("key");

        DatabaseReference ref1=db.getReference("Food Detail").child(key);

        ref1.removeValue();

       /* ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodDetail detail=dataSnapshot.getValue(foodDetail.class);
                detail.


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



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
