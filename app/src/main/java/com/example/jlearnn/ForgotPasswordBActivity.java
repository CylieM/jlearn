package com.example.jlearnn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class ForgotPasswordBActivity extends AppCompatActivity {

    public EditText editPassword;

    EditText enterPassword;
     Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_b);

        editPassword = findViewById(R.id.editPassword);
        enterPassword = findViewById(R.id.enterPassword);
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
                Toast.makeText(ForgotPasswordBActivity.this, "Please re - enter your new password.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}