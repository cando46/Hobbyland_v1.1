package com.hobbyland.version1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hobbyland.version1.HelperClasses.UserHelperClass;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout fullName;
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout email;
    TextInputLayout phone;
    Button register;
    Button goLogin;
    ProgressDialog progressDialog;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        fullName = findViewById(R.id.et_signUp_fullName);
        username = findViewById(R.id.et_signUp_username);
        password = findViewById(R.id.et_signUp_password);
        phone = findViewById(R.id.et_signUp_phoneNo);
        email = findViewById(R.id.et_signUp_email);

        register = findViewById(R.id.btn_signUp_register);
        goLogin = findViewById(R.id.btn_signUp_already_have_account);

        firebaseAuth = FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Users");


        //progress dialog set up
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering User");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);




        if(firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }

        //REGISTER ON CLICK METHOD
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting all user values
                final String name = fullName.getEditText().getText().toString().trim();
                final String uName = username.getEditText().getText().toString().trim();
                final String pass = password.getEditText().getText().toString().trim();
                final String phoneNo = phone.getEditText().getText().toString().trim();
                final String mail = email.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    fullName.getEditText().setError("Full Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(uName)) {
                    username.getEditText().setError("Username is Required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.getEditText().setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(phoneNo)) {
                    phone.getEditText().setError("Phone Number is Required");
                    return;
                }
                if (TextUtils.isEmpty(mail)) {
                    email.getEditText().setError("E mail is Required");
                    return;
                }
                if (pass.length()<6){
                    password.getEditText().setError("Password must be larger than 6 characters");
                    return;
                }


                progressDialog.show();


                //REGISTER USER TO FIREBASE
                firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            UserHelperClass user = new UserHelperClass(name,uName,mail,phoneNo,pass);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"User Created Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                }
                            });


                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Error! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
