package com.example.jlearnn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    public EditText editEmail;
     Button btnResetPassword;
    TextView registerRedirectText;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.editEmail);
        btnResetPassword = findViewById(R.id.btn_reset_password);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser email = firebaseAuth.getCurrentUser();
                if (email != null) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email is incorrect", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(ForgotPasswordActivity.this, UserActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter your current email to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        registerRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(I);
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call API or perform password reset logic here
                Toast.makeText(ForgotPasswordActivity.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }
}