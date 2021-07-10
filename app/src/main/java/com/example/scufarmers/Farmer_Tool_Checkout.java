package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Farmer_Tool_Checkout extends AppCompatActivity {

    private Button Checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_checkout);

        Checkout = (Button)findViewById(R.id.btnCheckout);

        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openCheckoutDates(); }
        });
    }

    private void openCheckoutDates(){
        Intent intent1 = new Intent(Farmer_Tool_Checkout.this, Farmer_Tool_Checkout_Dates.class);
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