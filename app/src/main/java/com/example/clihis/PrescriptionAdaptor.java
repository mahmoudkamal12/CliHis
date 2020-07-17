package com.example.clihis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clihis.Model.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrescriptionAdaptor extends RecyclerView.Adapter<PrescriptionAdaptor.PrescriptionViewHolder> {

ArrayList<Prescription> prescriptions;
Context mContext;

    public PrescriptionAdaptor(ArrayList<Prescription> prescriptions, Context mContext) {
        this.prescriptions = prescriptions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position) {
holder.tv_doctor_name.setText("Doctor : "+prescriptions.get(position).getDoctorName());
holder.tv_prescription.setText("Prescription : "+prescriptions.get(position).getPrescription());
    }

    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_doctor_name,tv_prescription;
        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_doctor_name=itemView.findViewById(R.id.tv_doctor_name);
            tv_prescription=itemView.findViewById(R.id.tv_prescription);
        }
    }
}
