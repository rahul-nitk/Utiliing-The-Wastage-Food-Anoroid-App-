package com.example.rahul.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewFoodDetailActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();

        DatabaseReference ref=db.getReference("Food Detail");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout root = findViewById(R.id.loFoodDetail);
                LinearLayout layout;
                //  final //=new LinearLayout(viewFoodDetailActivity.this);
                for (final  DataSnapshot p : dataSnapshot.getChildren()) {
                    layout = new LinearLayout(viewFoodDetailActivity.this);
                     String name=p.getValue(foodDetail.class).email;
                    //userProfile profile = dataSnapshot.getValue(userProfile.class);

                    //String name = profile.getName();
                    String d = p.getKey();
                    TextView tv = new TextView(viewFoodDetailActivity.this);
                  //  tv.setWidth(200);

                    tv.setText(name);
                    // tv.setText(d);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(viewFoodDetailActivity.this,foodQueryActivity.class);

                            intent.putExtra("key",p.getKey());
                            startActivity(intent);
                        }
                    });
                    layout.addView(tv);
                    root.addView(layout);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(viewFoodDetailActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();

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