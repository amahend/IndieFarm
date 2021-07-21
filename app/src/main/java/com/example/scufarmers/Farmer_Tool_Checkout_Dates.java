package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Farmer_Tool_Checkout_Dates extends AppCompatActivity {

    String userID = "";
    String email = "";
    String fullName = "";
    String checkoutItemID = "";
    String checkoutItemName = "";
    String checkoutDate = "";
    String returnDate = "";

    TextView tvUserID;
    TextView tvEmail;
    TextView tvFullName;
    TextView tvCheckoutItemID;
    TextView tvCheckoutItemName;
    TextView tvCheckoutDate;
    TextView tvReturnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_checkout_dates);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");
        checkoutItemID = getIntent().getStringExtra("CHECKOUTITEMID");
        checkoutItemName = getIntent().getStringExtra("CHECKOUTITEMNAME");
        checkoutDate = getIntent().getStringExtra("CHECKOUTDATE");
        returnDate = getIntent().getStringExtra("RETURNDATE");

        System.out.println("%%%%%%% IN FARMER TOOL CHECKOUT DATES %%%%%%%%");
        System.out.println(checkoutItemName);
        System.out.println(checkoutItemID);
        System.out.println(checkoutDate);
        System.out.println(returnDate);
        System.out.println(userID);
        System.out.println(email);
        System.out.println("%%%%%%% IN FARMER TOOL CHECKOUT DATES %%%%%%%%");

        tvCheckoutDate = findViewById(R.id.tvCheckout);
        tvReturnDate = findViewById(R.id.tvReturn);
        tvUserID = findViewById(R.id.tvUserId);
        tvEmail = findViewById(R.id.tvEmail);
        tvFullName = findViewById(R.id.tvFullName);
        tvCheckoutItemID = findViewById(R.id.tvCheckoutItemId);
        tvCheckoutItemName = findViewById(R.id.tvCheckoutItemName);

        tvCheckoutDate.setText(checkoutDate);
        tvReturnDate.setText(returnDate);
        tvUserID.setText(userID);
        tvEmail.setText(email);
        tvFullName.setText(fullName);
        tvCheckoutItemID.setText(checkoutItemID);
        tvCheckoutItemName.setText(checkoutItemName);

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