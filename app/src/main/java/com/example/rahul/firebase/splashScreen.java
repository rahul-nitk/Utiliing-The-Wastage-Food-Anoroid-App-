package com.example.rahul.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class splashScreen extends Activity {
    ProgressBar bar;
    String type = "No";
    String now_playing, earned;
    FirebaseAuth auth;
TextView te;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
te=findViewById(R.id.tesxtlogo);
bar=findViewById(R.id.progressBar2);

        bar.setVisibility(View.VISIBLE);

        new PrefetchData().execute();

    }


    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

          te.setText("RAhul");
            auth=FirebaseAuth.getInstance();
            db=FirebaseDatabase.getInstance();
            if(auth.getCurrentUser()!=null){


                DatabaseReference ref=db.getReference("mangers").child(auth.getUid());

                te.setText("Singhania");
            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    userProfile profile = dataSnapshot.getValue(userProfile.class);

                    type = profile.getType();
                    te.setText(type);




                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Toast.makeText(,databaseError.getCode(),Toast.LENGTH_SHORT).show();

                }




            }  );}
        return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            bar.setVisibility(View.GONE);
            Intent i = new Intent(splashScreen.this, LoginActivity.class);
            i.putExtra("type", "Rahul");
           startActivity(i);
            //finish();
        }

    }

}