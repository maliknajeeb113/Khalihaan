package com.maliknajeeb.khalihaan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.logintext);
        mPassword = findViewById(R.id.passwordtext);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.regbutton);
        mCreateBtn = findViewById(R.id.toRegPage);
        forgotTextLink = findViewById(R.id.forgotPassword);


        mLoginBtn.setOnClickListener(v -> {

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword.setError("Password Must be >= 6 Characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // authenticate the user

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            });

        });

        mCreateBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Register.class)));

        forgotTextLink.setOnClickListener(v -> {

            final EditText resetMail = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
            passwordResetDialog.setView(resetMail);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                // extract the email and send reset link
                String mail = resetMail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(aVoid -> Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show());

            });

            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                // close the dialog
            });

            passwordResetDialog.create().show();

        });


    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
