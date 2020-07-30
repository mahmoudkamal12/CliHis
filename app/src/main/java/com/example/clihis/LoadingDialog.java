package com.example.clihis;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
   private Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        LayoutInflater inflater =activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog1,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }

}
