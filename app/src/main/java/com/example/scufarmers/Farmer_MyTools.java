package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
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

public class Farmer_MyTools extends AppCompatActivity {

    String userID = "";
    String email = "";
    String fullName = "";

    private RequestQueue mQueue;

    TextView tvTool1;
    TextView tvCheckoutDate1;
    TextView tvReturnDate1;

    TextView tvTool2;
    TextView tvCheckoutDate2;
    TextView tvReturnDate2;

    TextView tvTool3;
    TextView tvCheckoutDate3;
    TextView tvReturnDate3;

    // List<List<List<String>>> result = new ArrayList<>();
//    List<String> inventoryList = new ArrayList<String>();
//    List<String> checkoutDateList = new ArrayList<String>();
//    List<String> returnDateList = new ArrayList<String>();
    List<String> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_my_tools);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");

        tvTool1 = findViewById(R.id.tvTool);
        tvCheckoutDate1 = findViewById(R.id.tvCheckout);
        tvReturnDate1 = findViewById(R.id.tvReturn);

        tvTool2 = findViewById(R.id.tvTool2);
        tvCheckoutDate2 = findViewById(R.id.tvCheckout2);
        tvReturnDate2 = findViewById(R.id.tvReturn2);

        tvTool3 = findViewById(R.id.tvTool3);
        tvCheckoutDate3 = findViewById(R.id.tvCheckout3);
        tvReturnDate3 = findViewById(R.id.tvReturn3);

        mQueue = Volley.newRequestQueue(this);

        // result = getMyTools();
        System.out.println("********* FARMER MY TOOLS *********");
        // System.out.println(result);
        List<String> result2 = new ArrayList<String>();
        result = getMyTools();
        System.out.println(result2);
//        for (int i = 0; i < inventoryList.size(); i++) {
//            System.out.println(inventoryList.get(i));
//            System.out.println(checkoutDateList.get(i));
//            System.out.println(returnDateList.get(i));
//        }
//        System.out.println(inventoryList);
//        System.out.println(checkoutDateList);
//        System.out.println(returnDateList);
    }

    private List<String> getMyTools() {
        String inventoryURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/inventory";

        // List<List<List<String>>> result = new ArrayList<>();

        List<String> inventoryList = new ArrayList<String>();
        List<String> checkoutDateList = new ArrayList<String>();
        List<String> returnDateList = new ArrayList<String>();


        // List<String> result = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, inventoryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("inventory");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject inventory = jsonArray.getJSONObject(i);

                        if (inventory.getString("userId").equals(userID)) {
                            String itemNameString = inventory.getString("Itemname");
                            String checkoutDateString = inventory.getString("checkoutDate");
                            String returnDateString = inventory.getString("returnDate");

                            inventoryList.add(itemNameString);
                            checkoutDateList.add(checkoutDateString);
                            returnDateList.add(returnDateString);

                            result.add(itemNameString);
                            result.add(checkoutDateString);
                            result.add(returnDateString);

                            tvTool1.setText(itemNameString);
                            tvCheckoutDate1.setText(checkoutDateString);
                            tvReturnDate1.setText(returnDateString);

                            System.out.println(itemNameString);
                            System.out.println(checkoutDateString);
                            System.out.println(returnDateString);
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
                Toast.makeText(Farmer_MyTools.this, "Error getting inventory from db", Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);

        System.out.println("BOBBY HERE");
        System.out.println(inventoryList.size());
        System.out.println(checkoutDateList.size());
        System.out.println(returnDateList.size());

        return result;
    }

}