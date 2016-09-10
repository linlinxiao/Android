package com.baoxiao.service.group;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.baoxiao.model.Collection;
import com.baoxiao.model.Invitation;
import com.baoxiao.provider.InvitationDAO;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import com.baoxiao.util.NetService;

import android.content.Context;

public class InvitaService
{
	// ��ѯ����
	public List<Invitation> getInvitaList(String userId, String type,
			Context context)
	{
		List<Invitation> Invitations = new InvitationDAO(context).GetUserList(
				userId, type);
		return Invitations;
	}

	// ���ӻ���
	public Long saveInvita(final Invitation invita, final Context context)
	{
		Long uid = new InvitationDAO(context).SaveInvitation(invita);
		if (uid > 0)
		{
			new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					Invitation invitation = getInvitaContent(
							invita.getContent(), context);
					SyncService.syncSaveOrUpInvita(invitation);
				}
			}).start();
		}
		return uid;
	}
	
	//���ӻ���  ���ڴӺ�̨�ָ�����
	public static Long insertInvita(Invitation invita, Context context)
	{
		Long uid = new InvitationDAO(context).SaveInvitation(invita);
		return uid;
	}

	// �޸Ļ���
	public int updateInvita(final Invitation invita, final Context context)
	{
		int uId = new InvitationDAO(context).UpdateInvitation(invita);
		if (uId > 0)
		{
			new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					Invitation invitation = getInvitaContent(
							invita.getContent(), context);
					SyncService.syncSaveOrUpInvita(invitation);
				}
			}).start();
		}
		return uId;
	}

	// ����ID��ѯ����
	public Invitation getInvitaId(String id, Context context)
	{
		Invitation invitation = new InvitationDAO(context).HaveInvitation(id);
		return invitation;
	}

	// �������ݲ�ѯ����
	public Invitation getInvitaContent(String content, Context context)
	{
		Invitation invitation = new InvitationDAO(context).HaveInvitaC(content);
		return invitation;
	}

	// ɾ������
	public int deleteInvita(final String invitaId, Context context)
	{
		int delid = new InvitationDAO(context).DelPrice(invitaId);
		if (delid > 0)
		{
			new Thread(new Runnable()
			{

				@Override
				public void run()
				{
					SyncService.syncDelInvita(invitaId);
				}
			}).start();
		}
		return delid;
	}

	// json���ص��ղ��б�
	public static MessageHelper getJsonList(String userId)
	{
		String url = Define.Server + "shouclj.action?userid=" + userId;
		MessageHelper messageHelper = new MessageHelper();
		messageHelper = NetService.getDataFromServer(url);

		if (messageHelper.getResult().equals(Define.S))
		{
			ArrayList<Collection> list = new ArrayList<Collection>();
			JSONObject root;
			try
			{
				root = new JSONObject(messageHelper.getResouce());
				JSONObject message = root.getJSONObject("messageHelper");
				JSONArray array = message.getJSONArray("list");
				for (int j = 0; j < array.length(); j++)
				{
					JSONObject obj = array.getJSONObject(j);
					Collection collection = new Collection();
					collection.setId(Integer.parseInt(obj.getString("id")));
					collection.setTitle(obj.getString("title"));
					list.add(collection);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			messageHelper.setList(list);
		}
		return messageHelper;
	}

	public static MessageHelper share(String userid, String invitaId,
			String shoucid)
	{
		String url = Define.Server + "huashu.action?userid=" + userid + "&id="
				+ invitaId + "&shoucid=" + shoucid;
		return NetService.getDataFromServer(url);
	}
}
