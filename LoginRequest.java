package com.example.testingcenterv01;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by macbookpro on 6/9/17.
 */

public class LoginRequest extends StringRequest{
    private static final String TAG = "jessieMessage";
    private static final String LOGIN_REQUEST_URL = "http://jessieji90.hostei.com/login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        Log.i(TAG, "password" + password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
