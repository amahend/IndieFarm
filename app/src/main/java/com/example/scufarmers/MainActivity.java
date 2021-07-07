package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button FarmerLogin;
    private Button VHLogin;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FarmerLogin = (Button)findViewById(R.id.btnFarmerLogin);
        VHLogin = (Button)findViewById(R.id.btnVHLogin);
        Register = (Button)findViewById(R.id.btnRegister);

        FarmerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFarmerLogin(); }
        });

        VHLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openVHLogin(); }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegister(); }
        });
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

    public void openFarmerLogin(){
        Intent intent2 = new Intent(MainActivity.this, Farmer_Login.class);
        startActivity(intent2);
    }

    public void openVHLogin(){
        Intent intent2 = new Intent(MainActivity.this, VillageHead_Login.class);
        startActivity(intent2);
    }

    public void openRegister(){
        Intent intent3 = new Intent(MainActivity.this, Register_Account.class);
        startActivity(intent3);
    }
}