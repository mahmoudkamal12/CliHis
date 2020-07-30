package com.example.clihis.Model;

public class DrPrescription {

    private String doctorName;
    private String prescription;
    private String diagnosis;
    private String date;
    private String patientName;

    public DrPrescription(String doctorName, String prescription, String diagnosis, String date, String patientName) {
        this.doctorName = doctorName;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.date = date;
        this.patientName = patientName;
    }

    public DrPrescription() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
