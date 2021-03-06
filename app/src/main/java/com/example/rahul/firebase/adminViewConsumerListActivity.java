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

public class adminViewConsumerListActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_consumer_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();

        DatabaseReference ref=db.getReference("consumers");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout root = findViewById(R.id.loConsumerList);
                LinearLayout layout;
                int  i=1;
                //  final //=new LinearLayout(viewFoodDetailActivity.this);
                for ( final DataSnapshot p : dataSnapshot.getChildren()) {
                    layout = new LinearLayout(adminViewConsumerListActivity.this);

                    //String name1= p.getValue(consumerProfile.class).consumerName;
                   // String name=p.getValue(foodDetail.class).email;
                    //userProfile profile = dataSnapshot.getValue(userProfile.class);

                    //String name = profile.getName();
                   String d = p.getKey();
                    TextView tv = new TextView(adminViewConsumerListActivity.this);
                    //  tv.setWidth(200);

                    tv.setText("Consumer"+" "+i);
                    i++;
                    // tv.setText(d);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(adminViewConsumerListActivity.this,consumerInfoActivity.class);

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
                Toast.makeText(adminViewConsumerListActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();

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