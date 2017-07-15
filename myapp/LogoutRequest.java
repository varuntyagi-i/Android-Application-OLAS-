package com.example.nightmare.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LogoutRequest extends StringRequest {
    private static final String LOGOUT_REQUEST_URL = "https://varontyagi.000webhostapp.com/Logout_php.php";
    private Map<String, String> params;

    public LogoutRequest(String dis, Response.Listener<String> listener) {
        super(Method.POST, LOGOUT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("district", dis);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
