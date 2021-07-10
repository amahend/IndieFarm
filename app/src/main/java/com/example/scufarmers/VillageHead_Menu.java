package com.example.scufarmers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VillageHead_Menu extends AppCompatActivity {

    private Button ToolCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_head_menu);

        ToolCheckout = (Button)findViewById(R.id.btnVHTool);

        ToolCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToolCheckout();
            }
        });
    }

    public void openToolCheckout(){
        Intent intent2 = new Intent(VillageHead_Menu.this, VillageHead_Tool.class);
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
