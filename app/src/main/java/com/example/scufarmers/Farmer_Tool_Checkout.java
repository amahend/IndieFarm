package com.example.scufarmers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Farmer_Tool_Checkout extends AppCompatActivity {

    private Button Checkout;

    private Spinner spinnerTool;

    String sourceTool;

    private RequestQueue mQueue;

    String userID = "";
    String email = "";
    String fullName = "";
    String checkoutItemID = "";
    String checkoutItemName = "";
    String checkoutDate = "";
    String returnDate = "";

    List<List<String>> result = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_tool_checkout);

        Checkout = (Button)findViewById(R.id.btnCheckout);

        spinnerTool = (Spinner) findViewById(R.id.spTool);

        mQueue = Volley.newRequestQueue(this);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");

        // List<String> tools = jsonParseTools();
//        HashMap<String, String> tools = jsonParseTools();
//
//        System.out.println("@@@@@@@@@@@@@@@@@@");
//        for (Map.Entry<String, String> entry : tools.entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.println(tools.keySet());

        List<List<String>> tools = jsonParseTools();
        List<String> toolsName = tools.get(0);

        sourceTool = toolsName.get(0);

        // adapters for the spinners
        ArrayAdapter<String> adapterSpinnerSourceTool = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, toolsName);

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
                        checkoutItemName = sourceTool;
                        checkoutItemID = result.get(1).get(i);
                    }
                }
                // checkoutItemID = tools.get(sourceTool);
                Toast.makeText(Farmer_Tool_Checkout.this, sourceTool + " was selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Checkout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    openCheckoutDates();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<List<String>> jsonParseTools() {
        String inventoryURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/inventory";

        List<String> inventoryList = new ArrayList<String>();
        inventoryList.add("no selection");
//        HashMap<String, String> inventoryDetails = new HashMap<String, String>();
//        inventoryDetails.put("no selection", "no selection");
        List<String> inventoryID = new ArrayList<String>();
        inventoryID.add("no selection");
        // List<List<String>> result = new ArrayList<List<String>>();


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

//                        System.out.println(id);
//                        System.out.println(villageID);
//                        System.out.println(itemName);

                        inventoryList.add(itemName);
                        inventoryID.add(id);
                        // inventoryDetails.put(itemName, id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(Farmer_Tool_Checkout.this, "Error getting inventory from db", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
        // System.out.println(inventoryID);
        result.add(inventoryList);
        result.add(inventoryID);
        return result;

//        System.out.println("$$$$$$$$$$$$$$$");
//        for (Map.Entry<String, String> entry : inventoryDetails.entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.println(inventoryDetails.keySet());
//        System.out.println(inventoryDetails);

//        return inventoryDetails;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openCheckoutDates() throws UnsupportedEncodingException, JSONException {
        // generate checkoutDate and returnDate
        LocalDateTime tempCheckoutDate = LocalDateTime.now();
        LocalDateTime tempReturnDate = tempCheckoutDate.plusDays(14);
        checkoutDate = tempCheckoutDate.toString().substring(0, 10);
        returnDate = tempReturnDate.toString().substring(0, 10);

//        System.out.println("%%%%%%%%%%%%%%%");
//        System.out.println(checkoutItemName);
//        System.out.println(checkoutItemID);
//        System.out.println(checkoutDate);
//        System.out.println(returnDate);
//        System.out.println(userID);
//        System.out.println(email);
//        System.out.println("%%%%%%%%%%%%%%%");

        Intent intent1 = new Intent(Farmer_Tool_Checkout.this, Farmer_Tool_Checkout_Dates.class);
        intent1.putExtra("USERID", userID);
        intent1.putExtra("EMAIL", email);
        intent1.putExtra("FULLNAME", fullName);
        intent1.putExtra("CHECKOUTITEMNAME", checkoutItemName);
        intent1.putExtra("CHECKOUTITEMID", checkoutItemID);
        intent1.putExtra("CHECKOUTDATE", checkoutDate);
        intent1.putExtra("RETURNDATE", returnDate);

        // do the put call here - should probably be another function but I do not have time
        String checkoutURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/checkout/" + checkoutItemID;
        JSONObject jsonCheckout = new JSONObject();
        jsonCheckout.put("inUse", true);
        jsonCheckout.put("checkoutDate", checkoutDate);
        jsonCheckout.put("returnDate", returnDate);
        jsonCheckout.put("email", email);
        jsonCheckout.put("fullName", fullName);
        jsonCheckout.put("userId", userID);
        final String requestBody = jsonCheckout.toString();

        // should implement some sort of loading screen as I make the put call
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, checkoutURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        mQueue.add(stringRequest);

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