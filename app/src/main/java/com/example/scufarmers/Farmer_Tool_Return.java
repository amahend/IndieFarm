package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Farmer_Tool_Return extends AppCompatActivity {

    private Button Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_return);

        Return = (Button)findViewById(R.id.btnReturn);

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openReturn(); }
        });
    }

    private void openReturn(){
        Intent intent = new Intent(Farmer_Tool_Return.this, Farmer_Tool_Return_Success.class);
        startActivity(intent);
    }
}