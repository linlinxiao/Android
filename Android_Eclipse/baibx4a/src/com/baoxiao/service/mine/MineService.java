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
	 * ��֤�������ݷ��ʺ�̨
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
	 * ��ȡ��Ƭ��Ϣ
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
	 * ��ȡ����ͼƬ��Դ
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
			// �������
			HttpURLConnection conn = (HttpURLConnection) myFileURL
					.openConnection();
			// ���ó�ʱʱ��Ϊ6000���룬conn.setConnectionTiem(0);��ʾû��ʱ������
			conn.setConnectTimeout(6000);
			// �������û��������
			conn.setDoInput(true);
			// ��ʹ�û���
			conn.setUseCaches(false);
			// �����п��ޣ�û��Ӱ��
			// conn.connect();
			// �õ�������
			InputStream is = conn.getInputStream();
			// �����õ�ͼƬ
			bitmap = BitmapFactory.decodeStream(is);
			// �ر�������
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
					status = "δ����";
				if (entity.getString("status").equals("jihuo"))
					status = "����";
				if (entity.getString("status").equals("dongjie"))
					status = "����";
				if (entity.getString("status").equals("zhuxiao"))
					status = "ע��";
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
			Log.i("�ҵ��˻���", msgHelper.getResult() + "--" + e.toString());
			e.printStackTrace();
		}

		return userb;
	}

	/**
	 * �жϴ������Ƿ���� S ���� F ������
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
	 * ��ѯ������
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
