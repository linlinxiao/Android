package com.baoxiao.service.userproduct;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.baoxiao.model.Userb;
import com.baoxiao.util.Define;
import com.baoxiao.util.Encrypt;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class UserService
{
	public static String userType = "";

	/**
	 * ��¼
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper login(Userb user, String equipmentId,
			String macaddress)
	{
		String key = getKey(user.getUserid(),user.getPassword());
		String url = Define.Server + "userlogin.action?userid="
				+ user.getUserid() + "&password=" + user.getPassword()
				+ "&equipmentId=" + equipmentId + "&macaddress=" + macaddress
				+ "&key=" + key;
		return NetService.getDataFromServer(url);
	}

	private static String getKey(String userid, String password) {
		String date = getdate();
		return Encrypt.string2MD5(userid + password + "baoxiao.com" + date);
	}

	private static String getdate() {
		return new SimpleDateFormat("MM-dd",Locale.getDefault()).format(new Date());
	}

	/**
	 * ��������
	 * @param userId
	 * @return
	 */
	public static MessageHelper seekPwd(String userId){
		String key = getKey(userId,null);
		String url = Define.Server + "wjimima.action?userid=" + userId
				+ "&key=" + key;
		return NetService.getDataFromServer(url);
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @param msgHelper
	 * @return
	 */
	public static Userb getUserb(MessageHelper msgHelper)
	{
		Userb userb = new Userb();
		if (msgHelper.getResult().equals(Define.S))
		{
			try
			{
				JSONObject root = new JSONObject(msgHelper.getResouce());
				JSONObject messageHelper = root.getJSONObject("messageHelper");
				JSONObject entity = messageHelper.getJSONObject("entity");
				userb.setUserid(entity.getString("userid"));
				userb.setPassword(entity.getString("password"));
				userb.setType(entity.getString("type"));
				userb.setName(entity.getString("name"));
				userb.setPhone(entity.getString("phone"));
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

		}
		return userb;
	}

	/**
	 * ע��
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper register(Userb user)
	{
		String key = getKey(user.getUserid(),user.getPassword());
//		String url = Define.Server + "register.action?userid="
//				+ user.getUserid() + "&password=" + user.getPassword()
//				+ "&type=" + user.getType() + "&verifycode="
//				+ user.getVerifycode() + "&name=" + user.getName()
//				+ "&key=" + key;
		String url = Define.Server + "register.action";
		Map<String, String> map = new HashMap<String ,String>();
		map.put("userid", user.getUserid());
		map.put("password", user.getPassword());
		map.put("type", user.getType());
		map.put("verifycode", user.getVerifycode());
		map.put("name", user.getName());
		map.put("key", key);
//		return NetService.getDataFromServer(url);
		return NetService.postDataFromServer(url,map);
	}
	/**
	 * �޸�����
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper modifyPassword(String userid,String password,String xpassword)
	{
		String key = getKey(userid,password);
		String url = Define.Server + "updateMiMa.action?userid="
				+ userid + "&password=" + password
				+ "&xpassword=" + xpassword + "&key=" + key;
		return NetService.getDataFromServer(url);
	}
//	http://localhost:8080/baibx/updateMiMa.action?userid=123456&password=123456&xpassword=67879
	/**
	 * ��֤��
	 * 
	 * @param userId
	 * @return
	 */
	public static MessageHelper yanZhengMa(String userId)
	{
		String key = getKey(userId,null);
		String url = Define.Server + "yzm.action?userid=" + userId
				+ "&key=" + key;
		return NetService.getDataFromServer(url);
	}

	/**
	 * ����
	 */
	public static MessageHelper jiHuoState(String userId, String acticode)
	{
		String url = Define.Server + "jihuo.action?userid=" + userId
				+ "&Acticode=" + acticode;
		return NetService.getDataFromServer(url);
	}

	/**
	 * ����ɹ�ѡ������
	 */
	public static MessageHelper selectXianZhong(String userId, String type,
			String id, String productcode)
	{
		String url = "";
		if (type.equals("user"))
		{
			url = Define.Server + "customselect.action?userid=" + userId
					+ "&type=" + type + "&id=" + id;
		} else if (type.equals("custom"))
		{
			url = Define.Server + "customselect.action?userid=" + userId
					+ "&type=" + type + "&id=" + id + "&productcode="
					+ productcode;
		}
		return NetService.getDataFromServer(url);
	}

	/**
	 * �õ�����Mac��ַ
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalMac(Context context)
	{
		String mac = "";
		// ��ȡwifi������
		WifiManager wifiMng = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		return mac;
	}

	/**
	 * �õ�������imei
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context)
	{
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		return imei;
	}

	/**
	 * ��ȡ����
	 */
	public static MessageHelper getDealData(String userId, String acticode,
			String equipmentId, String macaddress)
	{
		String url = Define.Server + "jihuo.action?userid=" + userId
				+ "&Acticode=" + acticode + "&equipmentId=" + equipmentId
				+ "&macaddress=" + macaddress;
		MessageHelper msgHelper = NetService.getDataFromServer(url);
		ArrayList<Map<String, String>> maps = null;
		if (msgHelper.getResult().equals(Define.S))
		{
			maps = new ArrayList<Map<String, String>>();
			try
			{
				JSONObject root = new JSONObject(msgHelper.getResouce());
				JSONObject message = root.getJSONObject("messageHelper");
				JSONArray array = message.getJSONArray("list");
				for (int i = 0; i < array.length(); i++)
				{
					JSONObject obj = array.getJSONObject(i);
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", obj.getString("id"));
					map.put("productgroup", obj.getString("productgroup"));
					maps.add(map);
				}
				msgHelper.setList(maps);
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return msgHelper;
	}
	/**
	 * ��Ӣ��Ƹ
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper getRecruitUrl()
	{
		String url = Define.Server + "recruit.action";
		return NetService.getDataFromServer(url);
	}
	/**
	 * �߽�ƽ��
	 * 
	 * @param user
	 * @return
	 */
	public static MessageHelper getIntoPeaceUrl()
	{
		String url = Define.Server + "intoPeace.action";
		return NetService.getDataFromServer(url);
	}
	public static JSONObject getInvitationData(String userid)
	{
		String url = Define.Server + "invitation2Info.action?userid=" + userid;
//		{"messageHelper":{"entity":{"address":"ƽ����ѧ����","backMusic":null,"company":"�й�ƽ�����ٱ��չɷ����޹�˾","companyName":"�й�ƽ������","guestImage1":"\/images\/invitation\/guestImage1.png","guestImage2":"\/images\/invitation\/guestImage2.png","guestName1":"����","guestName2":"����","guestOther1":"������ѧ��������ʿ\r\n�й���˶ʿ","guestOther2":"������ѧ��������ʿ\r\n�й���˶ʿ","image0":"\/images\/invitation\/youcai.png","image1":"\/images\/invitation\/image1.png","image2":"\/images\/invitation\/image2.png","image3":"\/images\/invitation\/staticmap.png","image4":null,"introduction1":"������һȺ���Բ�ͬ��ҵ�ľ�Ӣ\r\n�߱�רҵ������\r\n�ܹ����õĽ���\r\n���Ų�iͬ�Ĺ�����������ҵ����","introduction2":"�й�ƽ������Ϊ���������ʵ��˲ţ�¡���Ƴ����Ųżƻ������ṩ�߶�ĵ�н��ר�ŵ�ѵ������˾����ƽ����ѵ���ľ��С��Ųš�ר����ҵ˵���ᣬ��ʱ��ӭ��ݰ���ֳ������Ĵ�ҵ���룬��������δ��","introduction3":"�й�ƽ�����й���һ�ҹɷ��Ʊ�����ҵ�������ѷ�չ��Ϊ�ڱ��ա����С�Ͷ��������Ӫҵ��Ϊһ�塢���Ľ����뻥��������ҵ���з�չ�ĸ��˽������������֮һ����ֹ2015�꼯�����ʲ���4.77����Ԫ�����������Ƹ�����־��ȫ��������ҵ500ǿ�����е�96λ���ǹ�����ҵ��������һ��","invitaName":"�𾴵�����\/Ůʿ","myName":"ҵ��Ա\r\n����","myPhone":"ҵ��Ա��ϵ�绰\r\n13800000000","process1":"13:00--14:00 �α�ǩ��","process2":"13:00--14:00 �α�ǩ��","process3":"13:00--14:00 �α�ǩ��","process4":"13:00--14:00 �α�ǩ��","process5":"13:00--14:00 �α�ǩ��","process6":"13:00--14:00 �α�ǩ��","process7":null,"subject":"ƽ���ŲŴ�ҵ˵����","time":"2016 \/ 5\/ 8 \/  ����6��30","title1":"ƽ���Ų�","title2":"�Ų�һ����","userid":"123"},"list":null,"message":"","result":"S"}}
		JSONObject helper = NetService.getDataFromServer2(url);
		return helper;
	}
//	�ύ���뺯��Ϣ
	public static JSONObject saveInvitationData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationSave.action";
		//?userid=" + userid + "&" + name + "=" + content;
//		{"messageHelper":{"entity":{"address":"ƽ����ѧ����","backMusic":null,"company":"�й�ƽ�����ٱ��չɷ����޹�˾","companyName":"�й�ƽ������","guestImage1":"\/images\/invitation\/guestImage1.png","guestImage2":"\/images\/invitation\/guestImage2.png","guestName1":"����","guestName2":"����","guestOther1":"������ѧ��������ʿ\r\n�й���˶ʿ","guestOther2":"������ѧ��������ʿ\r\n�й���˶ʿ","image0":"\/images\/invitation\/youcai.png","image1":"\/images\/invitation\/image1.png","image2":"\/images\/invitation\/image2.png","image3":"\/images\/invitation\/staticmap.png","image4":null,"introduction1":"������һȺ���Բ�ͬ��ҵ�ľ�Ӣ\r\n�߱�רҵ������\r\n�ܹ����õĽ���\r\n���Ų�iͬ�Ĺ�����������ҵ����","introduction2":"�й�ƽ������Ϊ���������ʵ��˲ţ�¡���Ƴ����Ųżƻ������ṩ�߶�ĵ�н��ר�ŵ�ѵ������˾����ƽ����ѵ���ľ��С��Ųš�ר����ҵ˵���ᣬ��ʱ��ӭ��ݰ���ֳ������Ĵ�ҵ���룬��������δ��","introduction3":"�й�ƽ�����й���һ�ҹɷ��Ʊ�����ҵ�������ѷ�չ��Ϊ�ڱ��ա����С�Ͷ��������Ӫҵ��Ϊһ�塢���Ľ����뻥��������ҵ���з�չ�ĸ��˽������������֮һ����ֹ2015�꼯�����ʲ���4.77����Ԫ�����������Ƹ�����־��ȫ��������ҵ500ǿ�����е�96λ���ǹ�����ҵ��������һ��","invitaName":"�𾴵�����\/Ůʿ","myName":"ҵ��Ա\r\n����","myPhone":"ҵ��Ա��ϵ�绰\r\n13800000000","process1":"13:00--14:00 �α�ǩ��","process2":"13:00--14:00 �α�ǩ��","process3":"13:00--14:00 �α�ǩ��","process4":"13:00--14:00 �α�ǩ��","process5":"13:00--14:00 �α�ǩ��","process6":"13:00--14:00 �α�ǩ��","process7":null,"subject":"ƽ���ŲŴ�ҵ˵����","time":"2016 \/ 5\/ 8 \/  ����6��30","title1":"ƽ���Ų�","title2":"�Ų�һ����","userid":"123"},"list":null,"message":"","result":"S"}}
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
	public static JSONObject getMycardData(String userid)
	{
		String url = Define.Server + "mycardInfo.action?userid=" + userid;
		JSONObject helper = NetService.getDataFromServer2(url);
		return helper;
	}
//	�ύ������Ƭ��Ϣ
	public static JSONObject saveMycardData(String userid, String name, String content)
	{
		String url = Define.Server + "mycardSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	�ύ�ƻ����뺯��Ϣ
	public static JSONObject saveInvitationWinePartyData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationWinePartySave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	�ύ�ۺϽ��ڴ�ҵ˵�������뺯��Ϣ
	public static JSONObject saveInvitationFinancialData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationFinancialSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	��ȡ�ݷú���Ϣ
	public static JSONObject getVisitLetteData(String userid)
	{
		String url = Define.Server + "visitLetteInfo.action?userid=" + userid;
		JSONObject helper = NetService.getDataFromServer2(url);
		return helper;
	}

//	�ύ�ۺϽ��ڴ�ҵ˵�������뺯��Ϣ
	public static JSONObject saveVisitLetteData(String userid, String name, String content)
	{
		String url = Define.Server + "visitLetteSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	��ȡ����״̬
	public static JSONObject getIsActive(String userid, String equipmentId)
	{
		String url = Define.Server + "isActive.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("equipmentId", equipmentId);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
}