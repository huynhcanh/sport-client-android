package com.example.nhom29_doancuoiky.converter;

import com.example.nhom29_doancuoiky.response.UserApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class UserConverter {

    public UserApiResponse toApiResponse(JSONObject jsonObjectUser) {
        UserApiResponse userApiResponse = new UserApiResponse();
        try {
            userApiResponse.setId(jsonObjectUser.getLong("id"));
            userApiResponse.setName(jsonObjectUser.getString("name"));
            userApiResponse.setEmail(jsonObjectUser.getString("email"));
            userApiResponse.setPhone(jsonObjectUser.getString("phone"));
            userApiResponse.setCreatedDate(jsonObjectUser.getString("createdDate"));
            userApiResponse.setRoleName(jsonObjectUser.getJSONObject("role").getString("value"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userApiResponse;
    }
}
