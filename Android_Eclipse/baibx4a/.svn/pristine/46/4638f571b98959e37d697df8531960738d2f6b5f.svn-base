package com.baoxiao.service.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.baoxiao.model.Groupuser;
import com.baoxiao.model.Invitation;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;

public class SyncService
{
	/**
	 * 同步添加时需要拼接的参数
	 * 
	 * @param user
	 * @return
	 */
	public String getPara(Groupuser user)
	{
		String para = "id=" + user.getPhoneid() + ",name=" + user.getName()
				+ ",phoneid=" + user.getPhoneid() + ",sex=" + user.getSex()
				+ ",age=" + user.getAge() + ",birth=" + user.getBirth()
				+ ",address=" + user.getAddress() + ",insurer="
				+ user.getInsurer() + ",company=" + user.getCompany()
				+ ",type=" + user.getType() + ",occupation="
				+ user.getOccupation() + ",remarks=" + user.getRemarks()
				+ ",usertag=" + user.getUsertag() + ",grouptype="
				+ user.getGrouptype() + ",userid=" + user.getUserid()
				+ ",lastvisit=" + user.getLasttime() + ",nextappointment="
				+ user.getNexttime();
		return para;
	}

	/**
	 * Group增加同步
	 * 
	 * @param param
	 * @return
	 */
	public static MessageHelper syncSaveGroup(Map<String, String> param)
	{
		String url = Define.Server + "allGroup.action";
		return NetService.postDataFromServer(url, param);
	}

	/**
	 * Group删除同步
	 * 
	 * @param id
	 * @return
	 */
	public static MessageHelper syncDelGroup(String Phoneid,
			String grouptype,String userId)
	{
		String url = Define.Server + "deletegroupuser.action?id=" + Phoneid +
				"&userid=" + userId + "&grouptype=" + grouptype;
		return NetService.getDataFromServer(url);
	}

	/**
	 * Group修改同步
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper syncUpdGroup(Groupuser user)
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", user.getPhoneid());
		param.put("name", user.getName());
		param.put("phoneid", user.getPhoneid());
		param.put("sex", user.getSex());
		param.put("age", user.getAge());
		param.put("birth", user.getBirth());
		param.put("address", user.getAddress());
		param.put("insurer", user.getInsurer());
		param.put("company", user.getCompany());
		param.put("type", user.getType());
		param.put("occupation", user.getOccupation());
		param.put("remarks", user.getRemarks());
		param.put("usertag", user.getUsertag());
		param.put("grouptype", user.getGrouptype());
		param.put("userid", user.getUserid());
		param.put("lastvisit", user.getLasttime());
		param.put("nextappointment", user.getNexttime());
		String url = Define.Server + "UPDATE.action";
		return NetService.postDataFromServer(url, param);
	}

	/**
	 * 新建或修改话术同步
	 * 
	 * @param invita
	 * @return
	 */
	public static MessageHelper syncSaveOrUpInvita(Invitation invita)
	{
		String url = Define.Server + "addhuashu.action?" + "id="
				+ invita.getId() + "&content=" + invita.getContent() + "&type="
				+ invita.getType() + "&userid=" + invita.getUserid();
		return NetService.getDataFromServer(url);
	}

	/**
	 * 话术删除同步
	 * 
	 * @param id
	 * @return
	 */
	public static MessageHelper syncDelInvita(String id)
	{
		String url = Define.Server + "deletehuashu.action?id=" + id;
		return NetService.getDataFromServer(url);
	}

	/**
	 * 恢复群组话术数据
	 * 
	 * @param userId
	 * @return
	 */
	public static ArrayList<Object> recoverData(String userId)
	{
		String url = Define.Server + "beifen.action?userid="+userId;
		MessageHelper msgHelper = new MessageHelper();
		msgHelper = NetService.getDataFromServer(url);
		ArrayList<Object> list = new ArrayList<Object>();
		if (msgHelper.getResult().equals(Define.S))
		{
			ArrayList<Groupuser> groupUsers = new ArrayList<Groupuser>();
			ArrayList<Invitation> invitas = new ArrayList<Invitation>();
			try
			{
				JSONObject root = new JSONObject(msgHelper.getResouce());
				JSONArray arrayGroupusers = root.getJSONArray("listgroupusers");
				JSONObject messageHelper = root.getJSONObject("messageHelper");
				JSONArray arrayInvitas = messageHelper.getJSONArray("list");
				for (int i = 0; i < arrayGroupusers.length(); i++)
				{
					JSONObject obj = arrayGroupusers.getJSONObject(i);
					Groupuser groupuser = new Groupuser();
					groupuser.setName(obj.getString("name"));
					groupuser.setPhoneid(obj.getString("phoneid"));
					groupuser.setSex(obj.getString("sex"));
					groupuser.setAge(obj.getString("age"));
					groupuser.setBirth(obj.getString("birth"));
					groupuser.setAddress(obj.getString("address"));
					groupuser.setInsurer(obj.getString("insurer"));
					groupuser.setCompany(obj.getString("company"));
					groupuser.setType(obj.getString("type"));
					groupuser.setOccupation(obj.getString("occupation"));
					groupuser.setRemarks(obj.getString("remarks"));
					groupuser.setUsertag(obj.getString("usertag"));
					groupuser.setGrouptype(obj.getString("grouptype"));
					groupuser.setUserid(obj.getString("userid"));
					groupuser.setLasttime(obj.getString("lastvisit"));
					groupuser.setNexttime(obj.getString("nextappointment"));
					groupUsers.add(groupuser);
				}
				for (int i = 0; i < arrayInvitas.length(); i++)
				{
					JSONObject obj = arrayInvitas.getJSONObject(i);
					Invitation invita = new Invitation();
					invita.setId(Integer.parseInt(obj.getString("id")));
					invita.setContent(obj.getString("content"));
					invita.setType(obj.getString("type"));
					invita.setUserid(obj.getString("userid"));
					invitas.add(invita);
				}
				list.add(groupUsers);
				list.add(invitas);
			} catch (JSONException e)
			{
				Log.i("一键恢复：", e.toString());
				e.printStackTrace();
			}

		}
		return list;
	}
}
