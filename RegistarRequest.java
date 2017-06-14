package com.example.testingcenterv01;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by macbookpro on 6/8/17.
 */

public class RegistarRequest extends StringRequest {
    private static final String TAG = "jessieMessage";
    private static final String REGISTER_REQUEST_URL = "http://jessieji90.hostei.com/registar.php";
    private Map<String, String> params;

    public RegistarRequest(String username, String password, String firstname, String lastname, /*boolean isAdmin,*/ Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("firstname",firstname);
        params.put("lastname",lastname);
        //params.put("isAdmin",isAdmin + "");
        Log.i(TAG, "password" + password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
