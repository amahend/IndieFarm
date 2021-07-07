package com.example.scufarmers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Register_Account extends AppCompatActivity  implements View.OnClickListener{
    private FirebaseAuth mAuth;
    public final static String TAG = "RegisterAccount";

    private EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextAge;
    private Spinner spinnerRole, spinnerGender, spinnerVillage;
    private TextView registerUser;

    String sourceRole, sourceGender, sourceVillage;

    // @TODO think of a smart way for moving user data throuhg pages - create a class?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register_account);

        registerUser = (Button) findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.etName);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.etConfPassword);
        editTextAge = (EditText) findViewById(R.id.etAge);

        spinnerRole = (Spinner) findViewById(R.id.spRole);
        spinnerGender = (Spinner) findViewById(R.id.spGender);
        spinnerVillage = (Spinner) findViewById(R.id.spVillage);

        // values to fill spinner dropdowns
        List<String> roles = new ArrayList<String>();
        roles.add("Village head");
        roles.add("Farmer");

        List<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");

        // @TODO make this into dynamic list from backend
        List<String> villages = new ArrayList<String>();
        villages.add("1");
        villages.add("2");
        villages.add("3");
        villages.add("4");
        villages.add("5");

        // @TODO set the initial values to the first in the list - maybe create 0 as just an empty value and fix toast by checking if empty no toast
        sourceRole = roles.get(0);
        sourceGender = gender.get(0);
        sourceVillage = villages.get(0);

        // adapters for the spinners
        ArrayAdapter<String> adapterSpinnerSourceRole = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
        ArrayAdapter<String> adapterSpinnerSourceGender = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        ArrayAdapter<String> adapterSpinnerSourceVillage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, villages);

        // filling data
        spinnerRole.setAdapter(adapterSpinnerSourceRole);
        spinnerGender.setAdapter(adapterSpinnerSourceGender);
        spinnerVillage.setAdapter(adapterSpinnerSourceVillage);

        // selecting values on spinner
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceRole = parent.getItemAtPosition(position).toString();
                Toast.makeText(Register_Account.this, sourceRole + " was selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceGender = parent.getItemAtPosition(position).toString();
                Toast.makeText(Register_Account.this, sourceGender + " was selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceVillage = parent.getItemAtPosition(position).toString();
                Toast.makeText(Register_Account.this, sourceVillage + " was selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextFullName.setError("Last name is required");
            editTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid email format");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Password does not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }

        try {
            createUserCall(fullName, email, password, age);
        } catch(Exception ex) {
            Toast.makeText(Register_Account.this, "Error calling createUserPOST function", Toast.LENGTH_LONG).show();
            Log.e("EXCEPTION", ex.toString());
        }

    }

    private void createUserCall(String fullName, String email, String password, String age) throws UnsupportedEncodingException, JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String userURL = "https://us-central1-farmers-d71d5.cloudfunctions.net/user";
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("fullName", fullName);
        jsonUser.put("email", email);
        jsonUser.put("password", password);
        jsonUser.put("age", age);
        jsonUser.put("role", sourceRole);
        jsonUser.put("gender", sourceGender);
        jsonUser.put("village", sourceVillage);
        jsonUser.put("loggedIn", "true");
        final String requestBody = jsonUser.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, userURL, new Response.Listener<String>() {
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
        requestQueue.add(stringRequest);

        // rethink what to actually send through pages
        takeUserToNextPageAfterRegistration(fullName);
    }

    private void takeUserToNextPageAfterRegistration(String fullName) {
        if (sourceRole.equals("Farmer")) {
            Intent farmerIntent = new Intent(Register_Account.this, Farmer_Menu.class);
            startActivity(farmerIntent);
//            Toast.makeText(Register_Account.this, "Welcome " + sourceRole + " " + fullName, Toast.LENGTH_LONG).show();
        } else {
            Intent villageHeadIntent = new Intent(Register_Account.this, VillageHead_Menu.class);
            startActivity(villageHeadIntent);
            // Toast.makeText(Register_Account.this, "Welcome " + sourceRole + " " + fullName, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(Register_Account.this, "Welcome " + sourceRole + " " + fullName, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser!=null) {
//            // this user is already signed in
//            System.out.println(currentUser);
//        }
//        else {
//            System.out.println(currentUser);
//        }
//        Log.i(TAG, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    // @TODO on stop should log the user out --> change with the update
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