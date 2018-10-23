package com.example.rahul.firebase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class InformToNgoActivity extends AppCompatActivity {
EditText newNoOfPeople,newAddress,newTime,newFoodType;
Button submitToNGO;
FirebaseAuth auth;
FirebaseDatabase db;
String people,address,time,type1;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_to_ngo);
        newNoOfPeople=findViewById(R.id.etNoOfPeople);
        newAddress=findViewById(R.id.etManagerAddress);
        newTime=findViewById(R.id.timeLast);
      newFoodType=findViewById(R.id.etFoodType);
      submitToNGO=findViewById((R.id.btMangerSubmit));
        submitToNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                people=newNoOfPeople.getText().toString().trim();
                address=newAddress.getText().toString().trim();
                time=newTime.getText().toString().trim();
                type1=newFoodType.getText().toString().trim();
        if (TextUtils.isEmpty(people)) {
            Toast.makeText(getApplicationContext(), "Enter newNoOfPeople!", Toast.LENGTH_SHORT).show();
            return;
        }if (TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
            return;
        }if (TextUtils.isEmpty(time)) {
            Toast.makeText(getApplicationContext(), "Enter Time", Toast.LENGTH_SHORT).show();
            return;
        }if (TextUtils.isEmpty(type1)) {
            Toast.makeText(getApplicationContext(), "Type1", Toast.LENGTH_SHORT).show();
            return;
        }
                auth=FirebaseAuth.getInstance();

                String email=auth.getCurrentUser().getEmail();
        foodDetail Detail=new foodDetail(people,address,time,type1,email);

        db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference ("Food Detail").child(auth.getUid());
        ref.setValue(Detail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(InformToNgoActivity.this, "Thanks For Helping Us", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(InformToNgoActivity.this, "Some problem occur try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
            }
        });
    }
}
