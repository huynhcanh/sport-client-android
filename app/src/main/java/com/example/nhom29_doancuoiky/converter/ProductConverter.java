package com.example.nhom29_doancuoiky.converter;

import com.example.nhom29_doancuoiky.response.ProductApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {

    public ProductApiResponse toApiResponse(JSONObject jsonObject)
    {
        try {
            ProductApiResponse productApiResponse = new ProductApiResponse();
            productApiResponse.setId(jsonObject.getLong("id"));
            productApiResponse.setName(jsonObject.getString("name"));
            productApiResponse.setDiscount((float) jsonObject.getLong("discount"));
            productApiResponse.setUnitPrice((float) jsonObject.getLong("unitPrice"));
            productApiResponse.setCategoryName(jsonObject.getJSONObject("category").getString("value"));
            productApiResponse.setDescription(jsonObject.getString("description"));
            List<String> images = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            for(int i =0; i< jsonArray.length(); i++)
            {
                images.add(jsonArray.getJSONObject(i).toString());
            }
            productApiResponse.setImages(images);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
