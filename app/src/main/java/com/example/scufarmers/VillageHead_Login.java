package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VillageHead_Login extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button FarmerLogin;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_head_login);

        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        FarmerLogin = (Button)findViewById(R.id.btnFarmerLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);


        /*Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });*/

        FarmerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFarmerLogin(); }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegister(); }
        });
    }

    /*private void validate(String userEmail, String userPassword){
        if((userEmail.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent1 = new Intent(VillageHead_Login.this, VillageHead_menu.class);
            startActivity(intent1);
        }
    }*/

    public void openFarmerLogin(){
        Intent intent2 = new Intent(VillageHead_Login.this, Farmer_Login.class);
        startActivity(intent2);
    }

    public void openRegister(){
        Intent intent3 = new Intent(VillageHead_Login.this, Register_Account.class);
        startActivity(intent3);
    }

}