package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class Farmer_Tool_Return extends AppCompatActivity {

    private Button Return;

    private Spinner spinnerTool;

    String sourceTool;

    private RequestQueue mQueue;

    String userID = "";
    String email = "";
    String fullName = "";
    String returnItemID = "";
    String returnItemName = "";

    List<List<String>> result = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_return);

        Return = (Button) findViewById(R.id.btnReturn);

        spinnerTool = (Spinner) findViewById(R.id.spToolReturn);

        mQueue = Volley.newRequestQueue(this);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");

        List<List<String>> tools = jsonParseToolsReturn();
        List<String> toolsNameReturn = tools.get(0);

        sourceTool = toolsNameReturn.get(0);

        // adapters for the spinners
        ArrayAdapter<String> adapterSpinnerSourceTool = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, toolsNameReturn);

        // filling data
        spinnerTool.setAdapter(adapterSpinnerSourceTool);

        // selecting values on spinner
        spinnerTool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceTool = parent.getItemAtPosition(position).toString();
                // this should probably be another function but I don't have time
                for (int i = 0; i < result.get(0).size(); i++) {
                    if (result.get(0).get(i).equals(sourceTool)) {
                        System.out.println(result.get(0).get(i));
                        System.out.println(result.get(1).get(i));
                        returnItemName = sourceTool;
                        returnItemID = result.get(1).get(i);
                    }
                }
                Toast.makeText(Farmer_Tool_Return.this, sourceTool + " was selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelgit ected(AdapterView<?> parent) {

            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openReturn(); }
        });
    }

    private List<List<String>> jsonParseToolsReturn() {
    }

    private void openReturn(){
        Intent intent = new Intent(Farmer_Tool_Return.this, Farmer_Tool_Return_Success.class);
        intent.putExtra("USERID", userID);
        intent.putExtra("EMAIL", email);
        intent.putExtra("FULLNAME", fullName);
        intent.putExtra("RETURNITEMID", returnItemID);
        intent.putExtra("RETURNITEMNAME", returnItemName);
        startActivity(intent);
    }
}