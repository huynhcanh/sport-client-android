package com.example.nhom29_doancuoiky.converter;

import com.example.nhom29_doancuoiky.response.CartApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CartConverter {

    public CartApiResponse toApiResponse(JSONObject jsonObject) {
        try {
            CartApiResponse cartApiResponse = new CartApiResponse();
            cartApiResponse.setId(jsonObject.getLong("id"));
            cartApiResponse.setQuantity(jsonObject.getInt("quantity"));
            cartApiResponse.setTotalMoney((float) jsonObject.getLong("totalMoney"));
            cartApiResponse.setProductSizeName(jsonObject.getJSONObject("productsize").getJSONObject("size").getString("value"));
            cartApiResponse.setUserName(jsonObject.getJSONObject("user").getString("name"));
            JSONArray jsonArray = jsonObject.getJSONObject("productsize").getJSONObject("product").getJSONArray("images");
            cartApiResponse.setLinkImgProd(jsonArray.getJSONObject(0).getString("image").replaceAll("\\\\", "").trim());
            cartApiResponse.setNameProd(jsonObject.getJSONObject("productsize").getJSONObject("product").getString("name"));
            return cartApiResponse;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
