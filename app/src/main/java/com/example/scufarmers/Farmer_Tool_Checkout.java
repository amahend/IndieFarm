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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Farmer_Tool_Checkout extends AppCompatActivity {

    private Button Checkout;

    private Spinner spinnerTool;

    String sourceTool;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_checkout);

        Checkout = (Button)findViewById(R.id.btnCheckout);

        spinnerTool = (Spinner) findViewById(R.id.spTool);

        mQueue = Volley.newRequestQueue(this);
        List<String> tools = jsonParseTools();

        sourceTool = tools.get(0);

        // adapters for the spinners
        ArrayAdapter<String> adapterSpinnerSourceTool = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tools);

        // filling data
        spinnerTool.setAdapter(adapterSpinnerSourceTool);

        // selecting values on spinner
        spinnerTool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceTool = parent.getItemAtPosition(position).toString();
                Toast.makeText(Farmer_Tool_Checkout.this, sourceTool + " was selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openCheckoutDates(); }
        });
    }

    private List<String> jsonParseTools() {
        String inventoryURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/inventory";
        List<String> inventoryList = new ArrayList<String>();
        inventoryList.add("no selection");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, inventoryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("inventory");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject inventory = jsonArray.getJSONObject(i);

                        String id = inventory.getString("id");
                        String villageID = inventory.getString("Villageid");
                        String itemName = inventory.getString("Itemname");

                        System.out.println(id);
                        System.out.println(villageID);
                        System.out.println(itemName);

                        inventoryList.add(itemName);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(Farmer_Tool_Checkout.this, "Error getting inventory from db", Toast.LENGTH_LONG).show();
            }
        });

        mQueue.add(request);
        return inventoryList;
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