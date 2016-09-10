package com.baoxiao.service.pay;

import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;

public class PayService
{

	/**
	 * 支付传给后台
	 * 
	 * @param 
	 * @return
	 */
	//1.用于勾选金元宝后传值
	public static MessageHelper Pay(String userid, String type,Integer gold)
	{	// 测试的时候服务器地址改为203
		String url = Define.Server + "paySAll.action?userid="
				+ userid + "&type=" + type
			    + "&gold=" + gold;
		return NetService.getDataFromServer(url);
	}
	//2.当金元宝为0时，就不传gold值给罗毅
	public static MessageHelper Pay(String userid, String type)
	{	
		String url = Define.Server + "paySAll.action?userid="
				+ userid + "&type=" + type;
		return NetService.getDataFromServer(url);
	}
}