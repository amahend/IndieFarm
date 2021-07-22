package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VillageHead_Tool extends AppCompatActivity {
    private TextView Result;
    private RequestQueue mQueue;
    String url = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/inventory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_head_tool);

        Result = findViewById(R.id.tvResult);
        mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("inventory");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject inventory = jsonArray.getJSONObject(i);

                        String itemName = inventory.getString("Itemname");
                        //String village = inventory.getString("Villageid");
                        String fullName = inventory.getString("fullName");
                        String inUse = inventory.getString("inUse");
                        //String email = inventory.getString("email");
                        String checkoutDate = inventory.getString("checkoutDate");
                        String returnDate = inventory.getString("returnDate");
                        if (!checkoutDate.equals("null")){
                            Result.append("Item Name: " + itemName + " | Checked Out: " + inUse + " | Farmer Name: " + fullName  + " | Checkout Date: " + checkoutDate + " | Return Date: " + returnDate + "\n\n");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}