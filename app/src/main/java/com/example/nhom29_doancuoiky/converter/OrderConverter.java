package com.example.nhom29_doancuoiky.converter;

import com.example.nhom29_doancuoiky.response.OrderApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderConverter {
    public OrderApiResponse toApiResponse(JSONObject jsonObject) {
        try {
            OrderApiResponse orderApiResponse = new OrderApiResponse();
            orderApiResponse.setId(jsonObject.getLong("id"));
            orderApiResponse.setTotalMoney((float) jsonObject.getDouble("totalMoney"));
            orderApiResponse.setAdddress(jsonObject.getString("adddress"));
            orderApiResponse.setDateCreate(jsonObject.getString("createdDate").substring(0, 10));
            orderApiResponse.setUserName(jsonObject.getJSONObject("user").getString("name"));

            return orderApiResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
