package com.example.clihis.Model;

public class Prescription {

    private String doctorName;
    private String prescription;
    private String diagnosis;
    private String date;

    public Prescription() {
    }

    public Prescription(String doctorName, String prescription, String diagnosis, String date) {
        this.doctorName = doctorName;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
