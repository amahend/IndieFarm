package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Farmer_Tool_Return_Success extends AppCompatActivity {

    String userID = "";
    String email = "";
    String fullName = "";
    String returnItemID = "";
    String returnItemName = "";

    TextView tvUserID;
    TextView tvEmail;
    TextView tvFullName;
    TextView tvReturnItemID;
    TextView tvReturnItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_return_success);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");
        returnItemID = getIntent().getStringExtra("RETURNITEMID");
        returnItemName = getIntent().getStringExtra("RETURNITEMNAME");

        System.out.println("%%%%%%% IN FARMER TOOL RETURN SUCCESS %%%%%%%%");
        System.out.println(returnItemName);
        System.out.println(returnItemID);
        System.out.println(userID);
        System.out.println(email);
        System.out.println(fullName);
        System.out.println("%%%%%%% IN FARMER TOOL RETURN SUCCESS %%%%%%%%");

        tvUserID = findViewById(R.id.tvUserId);
        tvEmail = findViewById(R.id.tvEmail);
        tvFullName = findViewById(R.id.tvFullName);
        tvReturnItemID = findViewById(R.id.tvReturnItemId);
        tvReturnItemName = findViewById(R.id.tvReturnItemName);

        tvUserID.setText(userID);
        tvEmail.setText(email);
        tvFullName.setText(fullName);
        tvReturnItemID.setText(returnItemID);
        tvReturnItemName.setText(returnItemName);
    }
}