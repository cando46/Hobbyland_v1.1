package com.hobbyland.version1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// implements View.OnClickListener

public class LoginActivity extends AppCompatActivity {

    Button signUp, forgotPassword, signIn;
    ImageView logo;
    TextView welcome,signInTo;
    TextInputLayout username,password;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        signUp = findViewById(R.id.btn_login_sign_up);
        forgotPassword = findViewById(R.id.btn_login_forget_password);
        signIn = findViewById(R.id.btn_login_sign_in);
        logo = findViewById(R.id.imgv_login_logo);
        welcome = findViewById(R.id.tv_login_welcome);
        signInTo = findViewById(R.id.tv_login_sign_in);
        username = findViewById(R.id.etlayout_login_username);
        password = findViewById(R.id.etlayout_login_password);

        //signIn.setOnClickListener(this);
        //forgotPassword.setOnClickListener(this);
        //signUp.setOnClickListener(this);

        //firebase set up and if user logged in directly opens home
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("logging in");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        //if(firebaseAuth.getCurrentUser() !=null){
        //   startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        //   finish();
        // }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting all user values

                String uName = username.getEditText().getText().toString().trim();
                String pass = password.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(uName)) {
                    username.getEditText().setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.getEditText().setError("Password is Required");
                    return;
                }


                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(uName, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Logged in successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                      }
                });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }
}

