package com.tryus.myquote;

import android.app.Application;
import android.content.Intent;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class offlinefirebase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled( false );
}}
