package com.baoxiao.service.group;

import java.util.List;
import com.baoxiao.model.Groupuser;
import com.baoxiao.provider.GroupDAO;
import android.content.Context;

public class GroupService
{
	/**
	 * 查询所有群信息
	 */
	public static List<Groupuser> getGroupList(Context context){
		List<Groupuser> groupusers = new GroupDAO(context).getUserList();
		return groupusers;
	}
	
	/**
	 * 根据搜索的名字查询群信息
	 */
	public static List<Groupuser> getSearchGroup(Context context,String name){
		List<Groupuser> groupusers = new GroupDAO(context).getSearchList(name);
		return groupusers;
	}
	
	/**
	 * 增加 用于从后台恢复群组信息
	 */
	public static Long saveGroup(Groupuser user,Context context){
		Long i = new GroupDAO(context).SaveGroup(user);
		return i;
	}
	
	/**
	 * 群修改
	 */
	public static int updateGroup(final Groupuser user,Context context){
		int i = new GroupDAO(context).UpdateGroup(user);
		//同步修改
		if(i>0){
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					SyncService.syncUpdGroup(user);
				}
			}).start();
		}
		return i;
	}
	
	/**
	 * 群删除
	 */
	public static int deleteGroup(final String Phoneid,
			final String grouptype,Context context,final String uesrId){
		//本地数据库删除
		int i = new GroupDAO(context).DelPrice(Phoneid);
		//同步删除
		if(i>0){
			new Thread(new Runnable()
			{
				
				@Override
				public void run()
				{
					SyncService.syncDelGroup(Phoneid,grouptype,uesrId);
				}
			}).start();
		}
		return i;
	}

}
