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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_menu);

        MyTools = (Button)findViewById(R.id.btnMyTools);
        FarmerToolCheckout = (Button)findViewById(R.id.btnFarmerToolCheckout);
        ToolReturn = (Button)findViewById(R.id.btnToolReturn);

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
        startActivity(intent1);
    }

    public void openFarmerToolCheckout(){
        Intent intent2 = new Intent(Farmer_Tool_Menu.this, Farmer_Tool_Checkout.class);
        startActivity(intent2);
    }

    private void openToolReturn(){
        Intent intent1 = new Intent(Farmer_Tool_Menu.this, Farmer_Tool_Return.class);
        startActivity(intent1);
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