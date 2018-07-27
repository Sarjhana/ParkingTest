package com.example.sarjhu.parkingtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Login extends AppCompatActivity {


    DatabaseReference mref;
 //   DatabaseReference tmref;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    EditText email, pass;

    Button login;
    Button btn2;

    TextView tv;
    String eval,passval;
   // Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        tv=(TextView)findViewById(R.id.textview);
        auth = FirebaseAuth.getInstance();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        progressDialog = new ProgressDialog(this);
        mref = FirebaseDatabase.getInstance().getReference();
       // context=getApplicationContext();
        FirebaseApp.initializeApp(Login.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("please wait");
                progressDialog.setMessage("LoggingIn");
                progressDialog.show();
                loginfun();
            }
        });
    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, QRCode.class));
            finish();
        }


    }*/

    void loginfun() {
        progressDialog.setMessage("Please Wait");

        eval = email.getText().toString().trim();
        passval = pass.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(eval).matches()) {
            progressDialog.dismiss();
            email.setError("invalid email id");
            return;
        }
        if (passval.length() < 6) {
            progressDialog.dismiss();
            pass.setError("Password should contain atleast 6 character");
            return;
        }
        auth.signInWithEmailAndPassword(eval, passval).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(Login.this, QRCode.class);
                    progressDialog.dismiss();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });


    }
}





