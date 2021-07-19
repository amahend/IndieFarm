package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Farmer_Menu extends AppCompatActivity {

    private Button ToolCheckout;
    private Button SignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_menu);

        ToolCheckout = (Button)findViewById(R.id.btnFarmerTool);
        SignOut = (Button)findViewById(R.id.btnSignOut);

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