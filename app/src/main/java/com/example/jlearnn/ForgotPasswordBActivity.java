package com.example.jlearnn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordBActivity extends AppCompatActivity {

    public EditText editPassword;
     Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editPassword = findViewById(R.id.editPassword);
        btnResetPassword = findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editPassword.getText().toString();

                if (password.isEmpty()) {
                    Toast.makeText(ForgotPasswordBActivity.this, "Please enter your new password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call API or perform password reset logic here
                Toast.makeText(ForgotPasswordBActivity.this, "Password re - enter your new password.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}