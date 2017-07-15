package com.example.nightmare.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProviderRequest extends StringRequest {
    private static final String PROVIDER_REQUEST_URL = "https://varontyagi.000webhostapp.com/Provider_php.php";
    private Map<String, String> params;

    public ProviderRequest(String com_nam, String owner_nam, String mob_n,String street,String district,String state,
                           Response.Listener<String> listener) {
        super(Method.POST, PROVIDER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("com_nam",com_nam);
        params.put("owner_nam", owner_nam);
        params.put("mob_n", mob_n);
        params.put("street", street);
        params.put("district", district);
        params.put("state", state);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
