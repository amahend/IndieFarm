package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Farmer_Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button Login;
    private Button VHLogin;
    private Button SignUp;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        email = (EditText)findViewById(R.id.etFarmerLoginEmail);
        password = (EditText)findViewById(R.id.etFarmerLoginPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        VHLogin = (Button)findViewById(R.id.btnVHLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);

        mQueue = Volley.newRequestQueue(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StringEmail = email.getText().toString().trim();
                String StringPassword = password.getText().toString().trim();
                System.out.println("+++++++++++");
                System.out.println(StringEmail);
                System.out.println("+++++++++++");
                returnUserCredentials(StringEmail, StringPassword);
            }
        });

        VHLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVHLogin();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openRegister(); }
        });
    }

    private void returnUserCredentials(String emailParameter, String passwordParameter) {
        String validateUserURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user/users";
        // Map<String, List<String>> map = new HashMap<String, List<String>>();
        final Boolean[] flag = {false};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, validateUserURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject users = jsonArray.getJSONObject(i);

                        String id = users.getString("id");
                        String fullName = users.getString("fullName");
                        String tempEmail = users.getString("email");
                        String tempPassword = users.getString("password");
                        String age = users.getString("age");
                        String role = users.getString("role");
                        String gender = users.getString("gender");
                        String village = users.getString("village");
                        String loggedIn = users.getString("loggedIn");

                        System.out.println("OUTSIDE");
                        System.out.println("**********");
                        System.out.println(emailParameter);
                        System.out.println(tempEmail);
                        System.out.println(emailParameter.getClass().getName());
                        System.out.println(tempEmail.getClass().getName());

                        System.out.println("**********");
                        System.out.println(passwordParameter);
                        System.out.println(tempPassword);
                        System.out.println(passwordParameter.getClass().getName());
                        System.out.println(tempPassword.getClass().getName());

                        System.out.println("**********");
                        System.out.println(role);
                        System.out.println(role.getClass().getName());
                        System.out.println("Farmer");

                        if (emailParameter.equals(tempEmail) && passwordParameter.equals(tempPassword) && role.equals("Farmer")) {
                            System.out.println("INSIDE");
                            // flag[0] = true;
                            flag[0] = true;
                        }

                        System.out.println(flag[0]);

                        if (!flag[0]) {
                            Toast.makeText(Farmer_Login.this, "Invalid credentials or user does not exist", Toast.LENGTH_LONG).show();
                        } else {
                            // @TODO - need to actually send over id and name through intent
                            Toast.makeText(Farmer_Login.this, "Welcome " + emailParameter + "!", Toast.LENGTH_LONG).show();
                            Intent intent1 = new Intent(Farmer_Login.this, Farmer_Menu.class);
                            startActivity(intent1);
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
                Toast.makeText(Farmer_Login.this, "Error getting villages from db", Toast.LENGTH_LONG).show();
            }
        });

        mQueue.add(request);
    }

    public void openVHLogin() {
        Intent intent2 = new Intent(Farmer_Login.this, VillageHead_Login.class);
        startActivity(intent2);
    }

    public void openRegister() {
        Intent intent3 = new Intent(Farmer_Login.this, Register_Account.class);
        startActivity(intent3);
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

