package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Farmer_Tool_Menu extends AppCompatActivity {

    private Button MyTools;
    private Button FarmerToolCheckout;
    private Button ToolReturn;

    String userID = "";
    String email = "";
    String fullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_menu);

        MyTools = (Button)findViewById(R.id.btnMyTools);
        FarmerToolCheckout = (Button)findViewById(R.id.btnFarmerToolCheckout);
        ToolReturn = (Button)findViewById(R.id.btnToolReturn);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");
//        System.out.println("IN FARMER TOOL MENU ON CREATE");
//        System.out.println(userID);
//        System.out.println(email);
//        System.out.println(fullName);

        MyTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openMyTools(); }
        });

        FarmerToolCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFarmerToolCheckout(); }
        });

        ToolReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openToolReturn(); }
        });
    }

    private void openMyTools(){
        Intent intent1 = new Intent(Farmer_Tool_Menu.this, Farmer_MyTools.class);
        intent1.putExtra("USERID", userID);
        intent1.putExtra("EMAIL", email);
        intent1.putExtra("FULLNAME", fullName);
        startActivity(intent1);
    }

    public void openFarmerToolCheckout(){
        Intent intent2 = new Intent(Farmer_Tool_Menu.this, Farmer_Tool_Checkout.class);
        intent2.putExtra("USERID", userID);
        intent2.putExtra("EMAIL", email);
        intent2.putExtra("FULLNAME", fullName);
//        System.out.println("IN FARMER TOOL MENU");
//        System.out.println(userID);
//        System.out.println(email);
//        System.out.println(fullName);
        startActivity(intent2);
    }

    private void openToolReturn(){
        Intent intent3 = new Intent(Farmer_Tool_Menu.this, Farmer_Tool_Return.class);
        intent3.putExtra("USERID", userID);
        intent3.putExtra("EMAIL", email);
        intent3.putExtra("FULLNAME", fullName);
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