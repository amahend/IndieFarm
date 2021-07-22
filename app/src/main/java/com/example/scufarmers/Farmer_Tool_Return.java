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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
//                try {
//                    openReturn();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {
                    openReturn();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<List<String>> jsonParseToolsReturn() {
        String inventoryURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/inventory";

        List<String> inventoryList = new ArrayList<String>();
        inventoryList.add("no selection");

        List<String> inventoryID = new ArrayList<String>();
        inventoryID.add("no selection");

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
                        String userIdReturn = inventory.getString("userId");

//                        System.out.println("********* FARMER TOOL RETURN **********");
//                        System.out.println("userID");
//                        System.out.println(userID);
//                        System.out.println("userIdReturn");
//                        System.out.println(userIdReturn);
                        if (userIdReturn.equals(userID)) {
                            inventoryList.add(itemName);
                            inventoryID.add(id);
//                            System.out.println("********* INSIDE **********");
//                            System.out.println(itemName);
//                            System.out.println(id);
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
                Toast.makeText(Farmer_Tool_Return.this, "Error getting inventory from db", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
        // System.out.println(inventoryID);
        result.add(inventoryList);
        result.add(inventoryID);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openReturn() throws JSONException {
        System.out.println("********* FARMER TOOL RETURN **********");
        System.out.println("returnItemID");
        System.out.println(returnItemID);
        System.out.println("returnItemName");
        System.out.println(returnItemName);

        Intent intent = new Intent(Farmer_Tool_Return.this, Farmer_Tool_Return_Success.class);
        intent.putExtra("USERID", userID);
        intent.putExtra("EMAIL", email);
        intent.putExtra("FULLNAME", fullName);
        intent.putExtra("RETURNITEMID", returnItemID);
        intent.putExtra("RETURNITEMNAME", returnItemName);

        String checkoutURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/checkout/" + returnItemID;
        JSONObject jsonCheckout = new JSONObject();
        jsonCheckout.put("inUse", "false");
        jsonCheckout.put("checkoutDate", "null");
        jsonCheckout.put("returnDate", "null");
        jsonCheckout.put("email", "null");
        jsonCheckout.put("fullName", "null");
        jsonCheckout.put("userId", "null");
        final String requestBody = jsonCheckout.toString();

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

        startActivity(intent);
    }
}