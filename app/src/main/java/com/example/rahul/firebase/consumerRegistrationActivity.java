package com.example.rahul.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class consumerRegistrationActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputName,inputAge,inputType,inputAddress,inputMobile;     //hit option + enter if you on mac , for windows hit ctrl + enter
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private String name,age,email,password,type,address,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_registration);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        inputName=(EditText)findViewById(R.id.etConsumerName);
        inputAge=findViewById((R.id.etConsumerAge));
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.etConsumeremail);
        inputMobile=findViewById(R.id.etConsumerMobile);
        inputPassword = (EditText) findViewById(R.id.etproducerpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        inputAddress=findViewById(R.id.etConsumerAddress2);
        // inputType=(EditText)findViewById(R.id.etType);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(consumerRegistrationActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=inputName.getText().toString().trim();
                age=inputAge.getText().toString().trim();

                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                address=inputAddress.getText().toString().trim();
                mobile=inputMobile.getText().toString().trim();

                // type=inputType.getText().toString().trim();

                //   userProfile profile=new userProfile(name,email,age,type);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(age)) {
                    Toast.makeText(getApplicationContext(), "Enter age!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Enter Address!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(consumerRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //   Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(consumerRegistrationActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
//
                                    sendEmailVarification();

                                    Log.d("In","yo yo");
                                    //sendData();
                                    //auth.signOut();
                                    // finish();
                                    // startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    protected void sendEmailVarification()
    {

        FirebaseUser user=auth.getCurrentUser();
        if(user!=null)
        {


            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(consumerRegistrationActivity.this,"Successfully Registerd and mail sent",Toast.LENGTH_SHORT).show();

                        sendData();
                        auth.signOut();



                        //   startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(consumerRegistrationActivity.this,"Varification mail not sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    public void sendData()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference myref=db.getReference("consumers").child(auth.getUid());
     /*db=myref.getDatabase();
     myref=db.getReference(auth.getUid());
*/

        consumerProfile profile=new consumerProfile(name,email,age,"consumer",address,mobile);
        myref.setValue(profile);
        myref=db.getReference("mangers").child(auth.getUid());
        myref.setValue(profile);
    }


}

