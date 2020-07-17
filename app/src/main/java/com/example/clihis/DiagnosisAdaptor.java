package com.example.clihis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clihis.Model.Prescription;

import java.util.List;

public class DiagnosisAdaptor extends RecyclerView.Adapter<DiagnosisAdaptor.PrescriptionViewHolder>{
    List<Prescription> prescriptions;
    Context mContext;


    public DiagnosisAdaptor(List<Prescription> prescriptions, Context mContext) {
        this.prescriptions = prescriptions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrescriptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnosis_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position) {
        holder.tv_diagnosis.setText(prescriptions.get(position).getDiagnosis());
        holder.tv_date.setText(prescriptions.get(position).getDate());

    }


    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_diagnosis,tv_date;
        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_diagnosis=itemView.findViewById(R.id.tv_diagnosis);
        }
    }


}
