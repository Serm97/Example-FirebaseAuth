package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextMail, TextPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        firebaseAuth = FirebaseAuth.getInstance();

        TextMail = findViewById(R.id.field_email);
        TextPassword = findViewById(R.id.field_password);

        btnLogin = findViewById(R.id.email_sign_in_button);
        btnLogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);




    }

    private void logInEmail(){
        final String email = TextMail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please type a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please type a email", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Starting...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(MainActivity.this, "Welcome: " + TextMail.getText(), Toast.LENGTH_LONG).show();
                            goHome(user);

                        } else {

                            Toast.makeText(MainActivity.this, "Incorrect Data", Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void goHome(String user){
        Intent intent = new Intent(getApplication(), HomeActivity.class);
        intent.putExtra(HomeActivity.user, user);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.email_sign_in_button:
                logInEmail();
                break;
        }
    }
}
