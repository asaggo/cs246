package com.example.testingcenterv01;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "jessieMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClicked(View view) {
        //connect to database and see if there is matching result.
        //if it matches, start display activity
        Log.i(TAG, "loginClicked function is called");

        EditText etUsername = (EditText)findViewById(R.id.usernameLogin);
        EditText etPassword = (EditText)findViewById(R.id.passwordLogin);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success){
                        Log.i(TAG, "login success");
                        String firstname = jsonObject.getString("firstname");
                        String lastname = jsonObject.getString("lastname");
                        String username = jsonObject.getString("username");
                        /*boolean isAdmin = jsonObject.getBoolean("isAdmin");*/

                        Intent intent = new Intent(MainActivity.this, Display.class);
                        intent.putExtra("username", username);
                        intent.putExtra("firstname", firstname);
                        intent.putExtra("lastname", lastname);
                        /*intent.putExtra("isAdmin", isAdmin);*/

                        MainActivity.this.startActivity(intent);
                    }else {
                        Log.i(TAG, "login fail");
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Login failed").setNegativeButton("Retry", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(username,password,listener);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(loginRequest);



    }

    public void moveToSignupPage(View view) {
        Log.i(TAG, "moveToSignupPage function is called");

        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
