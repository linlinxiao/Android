package com.baoxiao.service.productshow;

import org.json.JSONException;
import org.json.JSONObject;

import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;

public class ProductService
{
	public static String getHttpProduct(String userid){
		String type = "";
		String message = "";
		String url = Define.Server+"baoxianproduct.action?userid="+userid;
		MessageHelper helper = NetService.getDataFromServer(url);

		if(helper.getResult().equals(Define.S)){
			try
			{
				JSONObject root = new JSONObject(helper.getResouce());
				JSONObject messageHelper = root.getJSONObject("messageHelper");
				JSONObject entity = messageHelper.getJSONObject("entity");
				message = entity.getString("message");
				type = entity.getString("type");
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return message;
	}
}
