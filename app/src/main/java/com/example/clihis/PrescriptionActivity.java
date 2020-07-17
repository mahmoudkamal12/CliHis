package com.example.clihis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PrescriptionActivity extends AppCompatActivity {
    EditText et_doctor_name,et_prescription,et_diagnosis;

  FirebaseUser firebaseUser;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Prescription");


        et_doctor_name=findViewById(R.id.et_doctor_name);
        et_prescription=findViewById(R.id.et_prescription);
        et_diagnosis=findViewById(R.id.et_diagnosis);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prescription_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_prescription:
                String doctorName=et_doctor_name.getText().toString();
                String prescription=et_prescription.getText().toString();
                String diagnosis=et_diagnosis.getText().toString();
               addPrescription(doctorName,prescription,diagnosis);
                return true;
        }
        return false;
    }

    private void addPrescription(String doctorName,String prescription,String diagnosis ){
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MMM-yyyy");
        String dateTime =simpleDateFormat.format(calendar.getTime());


        reference= FirebaseDatabase.getInstance().getReference("Prescriptions");
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("doctorName",doctorName);
        hashMap.put("prescription",prescription);
        hashMap.put("diagnosis",diagnosis);
        hashMap.put("date",dateTime);
        reference.child(firebaseUser.getUid()).push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(PrescriptionActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}