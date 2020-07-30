package com.example.clihis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clihis.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PrescriptionActivity extends AppCompatActivity {
    TextView et_doctor_name,tv_patient_name;
AutoCompleteTextView et_diagnosis;
MultiAutoCompleteTextView et_prescription;
  FirebaseUser firebaseUser;
    DatabaseReference reference;

    LoadingDialog loadingDialog;
    String EXTRA_ID;


String [] illnessNames=new String[]{"gastroenteritis","IHD","headache","acute asthma"};
    String [] drugs=new String[]{"antinal caps \n كبسوله 3 مرات يوميا \n","cipro 500 tab \n قرص كل 12 ساعه قبل الاكل لمده 5 ايام\n"
            ,"motilium tab \n قرص 3 مرات يوميا\n","visceralgine tab \nقرص 3 مرات يوميا \n" };




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Prescription");


        et_doctor_name=findViewById(R.id.et_doctor_name);
        et_prescription=findViewById(R.id.et_prescription);
        et_diagnosis=findViewById(R.id.et_diagnosis);
        tv_patient_name=findViewById(R.id.tv_patient_name);

        ArrayAdapter <String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,illnessNames);
        et_diagnosis.setAdapter(adapter);
        ArrayAdapter <String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drugs);
        et_prescription.setAdapter(adapter1);
        et_prescription.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


         loadingDialog=new LoadingDialog(PrescriptionActivity.this);




        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                et_doctor_name.setText(user.getUsername().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent intent=getIntent();
        EXTRA_ID=intent.getStringExtra("EXTRA_ID");
        reference=FirebaseDatabase.getInstance().getReference("Users").child(EXTRA_ID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                tv_patient_name.setText(user.getUsername());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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
                String patientName=tv_patient_name.getText().toString();

                loadingDialog.startLoadingDialog();

               addDrPrescription(doctorName,prescription,diagnosis,patientName);
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
        hashMap.put("patientId",EXTRA_ID);
        reference.child(EXTRA_ID).push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    loadingDialog.dismissDialog();
                    Intent intent=new Intent(PrescriptionActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void addDrPrescription(String doctorName,String prescription,String diagnosis,String patientName ){
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MMM-yyyy");
        String dateTime =simpleDateFormat.format(calendar.getTime());


        reference= FirebaseDatabase.getInstance().getReference("DrPrescriptions");
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("doctorName",doctorName);

        hashMap.put("prescription",prescription);
        hashMap.put("diagnosis",diagnosis);
        hashMap.put("date",dateTime);
        hashMap.put("patientName",patientName);
        reference.child(firebaseUser.getUid()).push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    loadingDialog.dismissDialog();
                    Intent intent=new Intent(PrescriptionActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}