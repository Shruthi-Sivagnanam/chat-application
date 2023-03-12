package com.example.talkonline;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingClass {
    private Activity activity;
    private AlertDialog alertDialog;

    LoadingClass(Activity activity){
        this.activity= activity;
    }

    void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading,null));
        builder.setCancelable(false);

        alertDialog= builder.create();
        alertDialog.show();
    }

    void dismissLoading(){
        alertDialog.dismiss();
    }
}
