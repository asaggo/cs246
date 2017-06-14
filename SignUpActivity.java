package com.example.testingcenterv01;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "jessieMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Log.i(TAG, "SignupActivity started");
    }

    public void signupButtonClicked(View view) {
        Log.i(TAG, "signupButtonClicked function is called");

        EditText uname = (EditText)findViewById(R.id.usernameInput);
        EditText pass1 = (EditText)findViewById(R.id.passwordInput);
        EditText pass2 = (EditText)findViewById(R.id.confirmPasswordInput);
        EditText fname = (EditText)findViewById(R.id.firstnameInput);
        EditText lname = (EditText)findViewById(R.id.lastnameInput);
        //how to get radio button id here?


        String username = uname.getText().toString();
        String password1 = pass1.getText().toString();
        String password2 = pass2.getText().toString();
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();

        if(!password1.equals(password2)){
            //display popup message
            Toast passwordMatching = Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
            passwordMatching.show();
        }
        else {
            Log.i(TAG, "inside signupButtonClicked function, password match");


            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i(TAG, "inside try and catch block");
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        Log.i(TAG, "After receiveing json response");
                        
                        if (success) {
                            Log.i(TAG, "success");
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            SignUpActivity.this.startActivity(intent);
                        } else {
                            Log.i(TAG, "fail");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage("Registar failed").setNegativeButton("Retry", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            Log.i(TAG, "after responseListener");

            RegistarRequest registarRequest = new RegistarRequest(username,password1,firstname,lastname,responseListener);
            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            requestQueue.add(registarRequest);
        }


    }
}
