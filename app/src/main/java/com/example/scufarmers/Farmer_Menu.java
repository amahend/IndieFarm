package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Farmer_Menu extends AppCompatActivity {

    private Button ToolCheckout;
    private Button SignOut;

    String userID = "";
    String email = "";
    String fullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_menu);

        ToolCheckout = (Button)findViewById(R.id.btnFarmerTool);
        SignOut = (Button)findViewById(R.id.btnSignOut);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");
        System.out.println("IN FARMER MENU");
        System.out.println(userID);
        System.out.println(email);
        System.out.println(fullName);

        ToolCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToolMenu();
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignOut();
            }
        });
    }

    public void openToolMenu(){
        Intent intent = new Intent(Farmer_Menu.this, Farmer_Tool_Menu.class);
        intent.putExtra("USERID", userID);
        intent.putExtra("EMAIL", email);
        intent.putExtra("FULLNAME", fullName);
        System.out.println("IN FARMER MENU ONCLICK");
        System.out.println(userID);
        System.out.println(email);
        System.out.println(fullName);
        startActivity(intent);
    }
    public void openSignOut(){
        Intent intent2 = new Intent(Farmer_Menu.this, MainActivity.class);
        startActivity(intent2);
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