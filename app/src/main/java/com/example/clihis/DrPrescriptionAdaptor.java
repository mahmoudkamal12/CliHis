package com.example.clihis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clihis.Model.DrPrescription;
import com.example.clihis.Model.Prescription;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DrPrescriptionAdaptor extends RecyclerView.Adapter<DrPrescriptionAdaptor.PrescriptionViewHolder> {

    ArrayList<DrPrescription> drPrescriptions;
    Context mContext;

    public DrPrescriptionAdaptor(ArrayList<DrPrescription> drPrescriptions, Context mContext) {
        this.drPrescriptions = drPrescriptions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DrPrescriptionAdaptor.PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrPrescriptionAdaptor.PrescriptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dr_prescription_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DrPrescriptionAdaptor.PrescriptionViewHolder holder, int position) {
        holder.patient_name.setText(" "+drPrescriptions.get(position).getPatientName());
        holder.diagnosis.setText(" "+drPrescriptions.get(position).getDiagnosis());
        holder.timer.setText(" "+drPrescriptions.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return drPrescriptions.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView timer,patient_name,diagnosis;
        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);

            timer=itemView.findViewById(R.id.timer);
            patient_name=itemView.findViewById(R.id.patient_name);
            diagnosis=itemView.findViewById(R.id.diagnosis);
        }
    }
}
