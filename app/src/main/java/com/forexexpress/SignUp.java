package com.forexexpress;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText nFirstNameField;
    private EditText nLastNameField;
    private EditText nEmailField;
    private EditText nPasswordField;
    private TextView nLoginLink;

    private Button nRegisterButton;
    private ProgressDialog nProgress;
    private DatabaseReference nDatabase;


    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nFirstNameField = (EditText) findViewById(R.id.firstName);
        nLastNameField = (EditText) findViewById(R.id.lastName);
        nEmailField = (EditText) findViewById(R.id.emailRegister);
        nPasswordField = (EditText) findViewById(R.id.passwordRegister);
        nLoginLink = (TextView) findViewById(R.id.loginLink);
        nRegisterButton = (Button) findViewById(R.id.registerButton);

        nDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        nProgress = new ProgressDialog(this);

        mauth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    startActivity(new Intent(SignUp.this, MainActivity.class));
                }
            }
        };


        nRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

        nLoginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        });



    }

    private void startRegister() {
        final String firstname = nFirstNameField.getText().toString().trim();
        final String lastname = nLastNameField.getText().toString().trim();
        String email = nEmailField.getText().toString().trim();
        String password = nPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Check that all fields are filled in", Toast.LENGTH_SHORT).show();

        }

        else{

            nProgress.setMessage("Registering User...");
            nProgress.show();

            mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String user_id = mauth.getCurrentUser().getUid();

                        DatabaseReference current_user_db = nDatabase.child(user_id);
                        current_user_db.child("First Name").setValue(firstname);
                        current_user_db.child("Last Name").setValue(lastname);

                        nProgress.dismiss();
                        finish();
                        Intent mainIntent = new Intent(SignUp.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    }
                    else{
                        Toast.makeText(SignUp.this, "Could not Register. Please Try Again", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mauth.removeAuthStateListener(mAuthListener);
        }
    }
}
