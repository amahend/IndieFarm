package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Farmer_Login extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button VHLogin;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        VHLogin = (Button)findViewById(R.id.btnVHLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { validate(); }
        });

        VHLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVHLogin();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegister(); }
        });
    }

    private void validate(){
        Intent intent1 = new Intent(Farmer_Login.this, Farmer_Menu.class);
        startActivity(intent1);
    }

    public void openVHLogin(){
        Intent intent2 = new Intent(Farmer_Login.this, VillageHead_Login.class);
        startActivity(intent2);
    }

    public void openRegister(){
        Intent intent3 = new Intent(Farmer_Login.this, Register_Account.class);
        startActivity(intent3);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

