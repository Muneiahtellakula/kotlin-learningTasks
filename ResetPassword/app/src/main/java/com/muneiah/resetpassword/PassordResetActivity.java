package com.muneiah.resetpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PassordResetActivity extends AppCompatActivity {
    EditText mail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passord_reset);
        mail=findViewById(R.id.resetemail);
        mAuth = FirebaseAuth.getInstance();
    }

    public void seandMail(View view) {
        String email = mail.getText().toString();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PassordResetActivity.this, "email sent", Toast.LENGTH_SHORT).show();
                            Log.i("TAG", "Email sent.");
                        }
                    }
                });
    }
}