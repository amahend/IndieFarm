package com.example.scufarmers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class VillageHead_Menu extends AppCompatActivity {

    private Button ToolCheckout;
    private Button SignOut;
    private RequestQueue mQueue;

    String userID = "";
    String email = "";
    String fullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_head_menu);

        ToolCheckout = (Button)findViewById(R.id.btnVHTool);
        SignOut = (Button)findViewById(R.id.btnSignOut);
        mQueue = Volley.newRequestQueue(this);

        userID = getIntent().getStringExtra("USERID");
        email = getIntent().getStringExtra("EMAIL");
        fullName = getIntent().getStringExtra("FULLNAME");

        ToolCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToolCheckout();
            }
        });

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openSignOut();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void openToolCheckout(){
        Intent intent = new Intent(VillageHead_Menu.this, VillageHead_Tool.class);

        intent.putExtra("USERID", userID);
        intent.putExtra("EMAIL", email);
        intent.putExtra("FULLNAME", fullName);

        startActivity(intent);
    }

    public void openSignOut() throws UnsupportedEncodingException, JSONException {
        Intent intent2 = new Intent(VillageHead_Menu.this, MainActivity.class);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/" + userID;
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("loggedIn", "false");
        final String requestBody = jsonUser.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
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
