package com.example.clihis.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clihis.DrPrescriptionAdaptor;
import com.example.clihis.Model.DrPrescription;
import com.example.clihis.Model.Prescription;
import com.example.clihis.Model.User;
import com.example.clihis.PrescriptionAdaptor;
import com.example.clihis.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class PrescriptionFragment extends Fragment {


RecyclerView recyclerView;
    PrescriptionAdaptor adaptor;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<DrPrescription>drPrescriptions;
    DrPrescriptionAdaptor drPrescriptionAdaptor;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_prescription, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);

       recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        prescriptions=new ArrayList<>();
        drPrescriptions=new ArrayList<>();

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user =snapshot.getValue(User.class);
                if (user.getDr().equals("NO")){
                    readPrescription();
                }else {
                    readDrPrescription();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


      
        return view;
    }



    private void readPrescription() {

        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Prescriptions").child(firebaseUser.getUid());
reference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        prescriptions.clear();
        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
            Prescription prescription=dataSnapshot.getValue(Prescription.class);

            prescriptions.add(prescription);



        }
        Collections.reverse(prescriptions);

        adaptor=new PrescriptionAdaptor(prescriptions,getContext());
        recyclerView.setAdapter(adaptor);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }


    private void readDrPrescription() {

        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("DrPrescriptions").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drPrescriptions.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    DrPrescription drPrescription=dataSnapshot.getValue(DrPrescription.class);

                    drPrescriptions.add(drPrescription);



                }
                Collections.reverse(drPrescriptions);

                drPrescriptionAdaptor=new DrPrescriptionAdaptor(drPrescriptions,getContext());
                recyclerView.setAdapter(drPrescriptionAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}