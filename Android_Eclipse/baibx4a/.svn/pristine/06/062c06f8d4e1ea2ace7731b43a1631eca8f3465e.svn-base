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
	 * 登录
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
	 * 忘记密码
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
	 * 获取用户信息
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
	 * 注册
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
	 * 修改密码
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
	 * 验证码
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
	 * 激活
	 */
	public static MessageHelper jiHuoState(String userId, String acticode)
	{
		String url = Define.Server + "jihuo.action?userid=" + userId
				+ "&Acticode=" + acticode;
		return NetService.getDataFromServer(url);
	}

	/**
	 * 激活成功选择险种
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
	 * 得到本机Mac地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalMac(Context context)
	{
		String mac = "";
		// 获取wifi管理器
		WifiManager wifiMng = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		return mac;
	}

	/**
	 * 得到本机的imei
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
	 * 获取订单
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
	 * 精英招聘
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
	 * 走进平安
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
//		{"messageHelper":{"entity":{"address":"平安大学会堂","backMusic":null,"company":"中国平安人寿保险股份有限公司","companyName":"中国平安保险","guestImage1":"\/images\/invitation\/guestImage1.png","guestImage2":"\/images\/invitation\/guestImage2.png","guestName1":"张三","guestName2":"李四","guestOther1":"北京大学美国法博士\r\n中国法硕士","guestOther2":"北京大学美国法博士\r\n中国法硕士","image0":"\/images\/invitation\/youcai.png","image1":"\/images\/invitation\/image1.png","image2":"\/images\/invitation\/image2.png","image3":"\/images\/invitation\/staticmap.png","image4":null,"introduction1":"我们是一群来自不同行业的精英\r\n具备专业的素质\r\n受过良好的教育\r\n有着不i同的工作经历和行业背景","introduction2":"中国平安人寿为吸引高素质的人才，隆重推出“优才计划”，提供高额的底薪和专门的训练，我司将在平安培训中心举行“优才”专属创业说明会，届时欢迎您莅临现场，畅聊创业梦想，启航美好未来","introduction3":"中国平安是中国第一家股份制保险企业，至今已发展成为融保险、银行、投资三大主营业务为一体、核心金融与互联网金融业务并行发展的个人金融生活服务集团之一。截止2015年集团总资产达4.77万亿元，在美国《财富》杂志“全球领先企业500强”名列第96位，非国有企业中排名第一。","invitaName":"尊敬的先生\/女士","myName":"业务员\r\n张三","myPhone":"业务员联系电话\r\n13800000000","process1":"13:00--14:00 嘉宾签到","process2":"13:00--14:00 嘉宾签到","process3":"13:00--14:00 嘉宾签到","process4":"13:00--14:00 嘉宾签到","process5":"13:00--14:00 嘉宾签到","process6":"13:00--14:00 嘉宾签到","process7":null,"subject":"平安优才创业说明会","time":"2016 \/ 5\/ 8 \/  下午6：30","title1":"平安优才","title2":"优才一起来","userid":"123"},"list":null,"message":"","result":"S"}}
		JSONObject helper = NetService.getDataFromServer2(url);
		return helper;
	}
//	提交邀请函信息
	public static JSONObject saveInvitationData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationSave.action";
		//?userid=" + userid + "&" + name + "=" + content;
//		{"messageHelper":{"entity":{"address":"平安大学会堂","backMusic":null,"company":"中国平安人寿保险股份有限公司","companyName":"中国平安保险","guestImage1":"\/images\/invitation\/guestImage1.png","guestImage2":"\/images\/invitation\/guestImage2.png","guestName1":"张三","guestName2":"李四","guestOther1":"北京大学美国法博士\r\n中国法硕士","guestOther2":"北京大学美国法博士\r\n中国法硕士","image0":"\/images\/invitation\/youcai.png","image1":"\/images\/invitation\/image1.png","image2":"\/images\/invitation\/image2.png","image3":"\/images\/invitation\/staticmap.png","image4":null,"introduction1":"我们是一群来自不同行业的精英\r\n具备专业的素质\r\n受过良好的教育\r\n有着不i同的工作经历和行业背景","introduction2":"中国平安人寿为吸引高素质的人才，隆重推出“优才计划”，提供高额的底薪和专门的训练，我司将在平安培训中心举行“优才”专属创业说明会，届时欢迎您莅临现场，畅聊创业梦想，启航美好未来","introduction3":"中国平安是中国第一家股份制保险企业，至今已发展成为融保险、银行、投资三大主营业务为一体、核心金融与互联网金融业务并行发展的个人金融生活服务集团之一。截止2015年集团总资产达4.77万亿元，在美国《财富》杂志“全球领先企业500强”名列第96位，非国有企业中排名第一。","invitaName":"尊敬的先生\/女士","myName":"业务员\r\n张三","myPhone":"业务员联系电话\r\n13800000000","process1":"13:00--14:00 嘉宾签到","process2":"13:00--14:00 嘉宾签到","process3":"13:00--14:00 嘉宾签到","process4":"13:00--14:00 嘉宾签到","process5":"13:00--14:00 嘉宾签到","process6":"13:00--14:00 嘉宾签到","process7":null,"subject":"平安优才创业说明会","time":"2016 \/ 5\/ 8 \/  下午6：30","title1":"平安优才","title2":"优才一起来","userid":"123"},"list":null,"message":"","result":"S"}}
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
//	提交个人名片信息
	public static JSONObject saveMycardData(String userid, String name, String content)
	{
		String url = Define.Server + "mycardSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	提交酒会邀请函信息
	public static JSONObject saveInvitationWinePartyData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationWinePartySave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	提交综合金融创业说明会邀请函信息
	public static JSONObject saveInvitationFinancialData(String userid, String name, String content)
	{
		String url = Define.Server + "invitationFinancialSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	获取拜访函信息
	public static JSONObject getVisitLetteData(String userid)
	{
		String url = Define.Server + "visitLetteInfo.action?userid=" + userid;
		JSONObject helper = NetService.getDataFromServer2(url);
		return helper;
	}

//	提交综合金融创业说明会邀请函信息
	public static JSONObject saveVisitLetteData(String userid, String name, String content)
	{
		String url = Define.Server + "visitLetteSave.action";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put(name, content);
		JSONObject helper = NetService.getDataFromServerByPost(url,map);
		return helper;
	}
//	获取激活状态
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