package com.example.rahul.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class adminDashboard extends AppCompatActivity {

    private FirebaseAuth auth;
     public Button inform;


    public void signOut()
    {
        auth.signOut();
        startActivity(new Intent(this,LoginActivity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        auth=FirebaseAuth.getInstance();

        inform=(Button) findViewById(R.id.btFoodInfo);
        inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminDashboard.this,viewFoodDetailActivity.class));
            }
        });




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;


    }




    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logoutMenu:
                signOut();

            case R.id.Profile:
                startActivity(new Intent(adminDashboard.this,profileActivity.class));

            case android.R.id.home:
                onBackPressed();

        }
        return true;
    }



}
