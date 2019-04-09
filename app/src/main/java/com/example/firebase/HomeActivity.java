package com.example.firebase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static final String user = "name";
    TextView textUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textUser = findViewById(R.id.textuser);
        String user = getIntent().getStringExtra("name");
        textUser.setText("Welcome " + user);

    }

}
