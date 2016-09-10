package com.baoxiao.activity.invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baoxiao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InvitationListA extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation_list);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		int image_id[] = new int[]{
				R.drawable.invitation_mould1,
				R.drawable.invitation_mould2,
				R.drawable.invitation_mould3,
				R.drawable.invitation_mould4,
				R.drawable.invitation_mould5
				};
		String title[] = new String[]{
				"优才计划邀请函",
				"平安创业说明会邀请函",
				"综合金融创业说明会邀请函",
				"个人酒会邀请函",
				"拜访函"
				};
		String content[] = new String[]{
				"可以对内容进行编辑，然后向优才发出参加创业说明会的邀请。",
				"编辑邀请函，然后向被邀请人发出参加创业说明会的邀请。",
				"编辑邀请函，然后向被邀请人发出参加金融创业说明会的邀请。",
				"编辑邀请函，然后向被邀请人发出参加酒会的邀请。",
				"可以编辑拜访时间和内容，然后向客户、亲友发出拜访函，约定拜访时间地点。"
				};
		for (int i = 0; i < image_id.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image_id", image_id[i]);
			map.put("title", title[i]);
			map.put("content", content[i]);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list, 
				R.layout.invitation_list_item, 
				new String[]{"image_id","title","content"}, 
				new int[]{
						R.id.iv_in_wi_title,
						R.id.tv_in_wi_title,
						R.id.tv_in_wi_content,
				});
		ListView invitation_wine_list = (ListView)findViewById(R.id.invitation_wine_list);
		invitation_wine_list.setAdapter(adapter);
		invitation_wine_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(InvitationListA.this,Invitation2A.class));
					break;
				case 1:
					startActivity(new Intent(InvitationListA.this,InvitationWinePreViewA.class)
							.putExtra("type", 2));
					break;
				case 2:
					startActivity(new Intent(InvitationListA.this,InvitationFinanCialA.class));
					break;
				case 3:
					startActivity(new Intent(InvitationListA.this,InvitationWinePartyA.class));
					break;
				case 4:
					startActivity(new Intent(InvitationListA.this,InvitationVisitLetteA.class));
					break;

				default:
					break;
				}
			}
		});
	}
	public void invitation_list_back(View v){
		finish();
	}
}
