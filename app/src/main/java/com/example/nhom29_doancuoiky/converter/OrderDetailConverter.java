package com.example.nhom29_doancuoiky.converter;

import com.example.nhom29_doancuoiky.response.OrderDetailApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailConverter {

    public OrderDetailApiResponse toApiResponse(JSONObject jsonObject) {
        try {
            OrderDetailApiResponse orderApiResponse = new OrderDetailApiResponse();
            orderApiResponse.setTotalMoney((float) jsonObject.getDouble("totalMoney"));
            orderApiResponse.setQuantity(jsonObject.getInt("quantity"));
            orderApiResponse.setNameProd(jsonObject.getJSONObject("productsize").getJSONObject("product").getString("name"));
            orderApiResponse.setLinkImgProd(jsonObject.getJSONObject("productsize")
                    .getJSONObject("product").getJSONArray("images").getJSONObject(0).getString("image")
                    .replaceAll("\\\\", "").trim());

            return orderApiResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
