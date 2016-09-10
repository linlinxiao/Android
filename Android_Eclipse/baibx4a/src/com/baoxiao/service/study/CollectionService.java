package com.baoxiao.service.study;

import org.json.JSONObject;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetworkTool;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CollectionService {

	// ����MessageHelper�ж��Ƿ��ղسɹ�
	public void getJsonList(String url,Context context,MessageHelper messageHelper)
	{
//		MessageHelper messageHelper = getDataFromServer(url);
		

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).setCancelable(true)
		;
		if (messageHelper.getResult().equals(Define.S))
		{
			builder.setMessage("�ղسɹ�").create().show();
		}else{
			builder.setMessage("�ղ�ʧ��").create().show();
		}
	}
	
//  entity ��list û�н����������� json ���ݱ�����resouce����
	public MessageHelper  getDataFromServer(String url){
		
		StringBuilder sb=new StringBuilder();
		MessageHelper rst=new MessageHelper();
		try{
			sb.append(NetworkTool.getContent(url));
			JSONObject root= new JSONObject(sb.toString());
			JSONObject messageHelper=root.getJSONObject("messageHelper");
			rst.setResult(messageHelper.getString("result"));
		}catch(Exception e){
			e.printStackTrace();
		    System.out.println("---"+e.getMessage());
		}
		return rst;
	}
//	http://www.rabbitpre.com/m/i2qQfeR?sui=4aKSnW9b&lc=2#from=share&id=120&title_image=http://localhost:8080/baibx/images/news/XGCBZ@EY@LXWQ6SCV%WFI]R.png
	//��ȡurl�е�id��ֵ
	public String idSplit(String url){
		String[] strarray=url.split("\\?");
		String url2=strarray[1];
		String[] strarray2=url2.split("\\=");
		String url3=strarray2[1];
		String[] strarray3=url3.split("\\&");
		String id=strarray3[0];
		return id;
	}
	public String split(String who,String url){
		String result = null;
		String[] strarray = url.split(who + "=");
		if (strarray.length > 1) {
			result = strarray[1].split("&")[0];
		}
		return result;
	}

}
