package com.baoxiao.service.mine;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.baoxiao.model.Userb;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;

public class MineService
{
	/**
	 * 认证传递数据访问后台
	 * 
	 * @param userb
	 * @return
	 */
	public static MessageHelper mineAuth(Userb userb)
	{
		String url = Define.Server + "renz.action";
		Map<String, String> param = new HashMap<String, String>();
		param.put("userid", userb.getUserid());
		param.put("insurer", userb.getInsurer());
		param.put("name", userb.getName());
		param.put("phone", userb.getPhone());
		param.put("company", userb.getCompany());
		param.put("position", userb.getPosition());
		param.put("employeenumber", userb.getEmployeenumber());
		param.put("address", userb.getAddress());
		param.put("hearts", userb.getHeart());
		param.put("honorphotoss", userb.getHonorphotos());
		return NetService.postDataFromServer(url, param);
	}

	/**
	 * 获取名片信息
	 * 
	 * @param userId
	 * @return
	 */
	public static Userb getHttpCard(String userId)
	{
		String url = Define.Server + "renzhenbji.action?userid=" + userId;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		Userb userb = new Userb();
		if (msgHelper.getResult().equals(Define.S))
		{
			try
			{
				JSONObject root = new JSONObject(msgHelper.getResouce());
				JSONObject messageHelper = root.getJSONObject("messageHelper");
				JSONObject entity = messageHelper.getJSONObject("entity");
				userb.setName(entity.getString("name"));
				userb.setInsurer(entity.getString("insurer"));
				userb.setCompany(entity.getString("company"));
				userb.setPosition(entity.getString("position"));
				userb.setPhone(entity.getString("phone"));
				userb.setEmployeenumber(entity.getString("employeenumber"));
				userb.setAddress(entity.getString("address"));
				userb.setHeart(entity.getString("heart"));
				userb.setHonorphotos(entity.getString("honorphotos"));
				if (userb.getName().equals("null"))
				{
					userb.setName("");
				}
				if (userb.getInsurer().equals("null"))
				{
					userb.setInsurer("");
				}
				if (userb.getCompany().equals("null"))
				{
					userb.setCompany("");
				}
				if (userb.getPosition().equals("null"))
				{
					userb.setPosition("");
				}
				if (userb.getPhone().equals("null"))
				{
					userb.setPhone("");
				}
				if (userb.getEmployeenumber().equals("null"))
				{
					userb.setEmployeenumber("");
				}
				if (userb.getAddress().equals("null"))
				{
					userb.setAddress("");
				}
				if (userb.getHeart().equals("null"))
				{
					userb.setHeart("");
				}
				if (userb.getHonorphotos().equals("null"))
				{
					userb.setHonorphotos("");
				}

			} catch (JSONException e)
			{
				e.printStackTrace();
			}

		}
		return userb;
	}

	/**
	 * 获取网络图片资源
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url)
	{
		URL myFileURL;
		Bitmap bitmap = null;
		try
		{
			myFileURL = new URL(url);
			// 获得连接
			HttpURLConnection conn = (HttpURLConnection) myFileURL
					.openConnection();
			// 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
			conn.setConnectTimeout(6000);
			// 连接设置获得数据流
			conn.setDoInput(true);
			// 不使用缓存
			conn.setUseCaches(false);
			// 这句可有可无，没有影响
			// conn.connect();
			// 得到数据流
			InputStream is = conn.getInputStream();
			// 解析得到图片
			bitmap = BitmapFactory.decodeStream(is);
			// 关闭数据流
			is.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return bitmap;

	}

	public static Userb MyAccountNews(String userid)
	{
		String url = Define.Server + "myuser.action?userid=" + userid;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		Userb userb = new Userb();
		try
		{
			if (msgHelper.getResult().equals(Define.S))
			{

				JSONObject root = new JSONObject(msgHelper.getResouce());
				JSONObject messageHelper = root.getJSONObject("messageHelper");
				JSONObject entity = messageHelper.getJSONObject("entity");
				userb.setUserid(entity.getString("userid"));
				String status = "";
				if (entity.getString("status").equals("weijihuo"))
					status = "未激活";
				if (entity.getString("status").equals("jihuo"))
					status = "激活";
				if (entity.getString("status").equals("dongjie"))
					status = "冻结";
				if (entity.getString("status").equals("zhuxiao"))
					status = "注销";
				userb.setStatus(status);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss ");
				userb.setRenewaltime(sdf.parse(entity.getString("renewaltime")
						+ " "));
				userb.setRemainDay(entity.getString("day"));
				userb.setXianZhong(entity.getString("xianzhong"));
				userb.setXianzhonggs(entity.getString("xianzhonggs"));
				userb.setGold(Integer.parseInt(entity.getString("gold")));

			}
		} catch (Exception e)
		{
			Log.i("我的账户：", msgHelper.getResult() + "--" + e.toString());
			e.printStackTrace();
		}

		return userb;
	}

	/**
	 * 判断代理人是否存在 S 存在 F 不存在
	 */
	public static MessageHelper AgentResult(String insurerperson)
	{
		String url = Define.Server + "dailiren.action?insurerperson="
				+ insurerperson;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		return msgHelper;
	}

	/**
	 * 查询代理人
	 */
	public static Userb MyInsuranceAgent(String insurerperson)
	{
		String url = Define.Server + "dailiren.action?insurerperson="
				+ insurerperson;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		Userb userb = new Userb();
		JSONObject root;
		try
		{
			root = new JSONObject(msgHelper.getResouce());
			JSONObject messageHelper = root.getJSONObject("messageHelper");
			JSONObject entity = messageHelper.getJSONObject("entity");
			userb.setUserid(entity.getString("userid"));
			userb.setName(entity.getString("name"));
			userb.setPhone(entity.getString("phone"));
			userb.setInsurerperson(entity.getString("insurerperson"));
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return userb;
	}

	public static String setAgent(String userid, String insurerperson)
	{
		String url = Define.Server + "szdailiren.action?userid=" + userid
				+ "&insurerperson=" + insurerperson;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		return msgHelper.result;
	}
}
