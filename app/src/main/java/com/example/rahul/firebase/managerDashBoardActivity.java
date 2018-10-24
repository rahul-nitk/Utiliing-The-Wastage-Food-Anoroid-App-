package com.example.rahul.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class managerDashBoardActivity extends AppCompatActivity {
    Button newInform;
    FirebaseAuth auth;

    public void signOut()
    {
        auth.signOut();
        startActivity(new Intent(this,LoginActivity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dash_board);
      auth=FirebaseAuth.getInstance();
    newInform=findViewById(R.id.btInform);
       // Intent i=getIntent();
      //  newInform.setText(i.getStringExtra("type"));

    newInform.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(managerDashBoardActivity.this,InformToNgoActivity.class));
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
                startActivity(new Intent(managerDashBoardActivity.this,profileActivity.class));

            case android.R.id.home:
                onBackPressed();

        }
        return true;
    }




}
