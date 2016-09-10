package com.baoxiao.activity.study;

import com.baoxiao.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * '学习乐园'布局界面
 *
 */
public class StudyA extends Activity implements OnClickListener{

	private ImageView studyBack;
	private RelativeLayout studyVideo,addMember,studyResource,
					     studySkill,studyZhugu,studyShare,
					     studyZige,studyJili,studyKnowledge;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 	super.onCreate(savedInstanceState);
	    setContentView(R.layout.study);
	    
	    studyVideo = (RelativeLayout) findViewById(R.id.studyVideo);
	    addMember = (RelativeLayout) findViewById(R.id.addMember);
	    studyResource = (RelativeLayout) findViewById(R.id.studyResource);
	    studySkill = (RelativeLayout) findViewById(R.id.studySkill);
	    studyZhugu = (RelativeLayout) findViewById(R.id.studyZhugu);
	    studyShare = (RelativeLayout) findViewById(R.id.studyShare);
	    studyZige = (RelativeLayout) findViewById(R.id.studyZige);
	    studyJili = (RelativeLayout) findViewById(R.id.studyJili);
	    studyKnowledge = (RelativeLayout) findViewById(R.id.studyKnowledge);
	    
	    studyBack = (ImageView) findViewById(R.id.studyBack);

	    studyBack.setOnClickListener(this);
	    studyVideo.setOnClickListener(this);
	    addMember.setOnClickListener(this);
	    studyResource.setOnClickListener(this);
	    studySkill.setOnClickListener(this);
	    studyZhugu.setOnClickListener(this);
	    studyShare.setOnClickListener(this);
	    studyZige.setOnClickListener(this);
	    studyJili.setOnClickListener(this);
	    studyKnowledge.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studyVideo:
			Intent intent = new Intent(this,StudyWebViewA.class);
			intent.putExtra("title", "保险视频");
			intent.putExtra("urlPath", "video");
			startActivity(intent);
			break;
		case R.id.studyResource:
			Intent intent1 = new Intent(this,StudyWebViewA.class);
			intent1.putExtra("title", "早会资源");
			intent1.putExtra("urlPath", "EarlyResources");
			startActivity(intent1);
			break;
		case R.id.studySkill:
			Intent intent2 = new Intent(this,StudyWebViewA.class);
			intent2.putExtra("title", "营销技能");
			intent2.putExtra("urlPath", "Marketing");
			startActivity(intent2);
			break;
		case R.id.studyZhugu:
			Intent intent3 = new Intent(this,StudyWebViewA.class);
			intent3.putExtra("title", "主顾开拓");
			intent3.putExtra("urlPath", "DevelopCustomer");
			startActivity(intent3);
			break;
		case R.id.studyShare:
			Intent intent4 = new Intent(this,StudyWebViewA.class);
			intent4.putExtra("title", "高手分享");
			intent4.putExtra("urlPath", "MasterShare");
			startActivity(intent4);
			break;
		case R.id.studyZige:
			Intent intent5 = new Intent(this,StudyWebViewA.class);
			intent5.putExtra("title", "投保规则");
			intent5.putExtra("urlPath", "InsuranceRules");
			startActivity(intent5);
			break;
		case R.id.studyJili:
			Intent intent6 = new Intent(this,StudyWebViewA.class);
			intent6.putExtra("title", "成功激励");
			intent6.putExtra("urlPath", "Success");
			startActivity(intent6);
			break;
		case R.id.studyKnowledge:
			Intent intent7 = new Intent(this,StudyWebViewA.class);
			intent7.putExtra("title", "行业知识");
			intent7.putExtra("urlPath", "Industry");
			startActivity(intent7);
			break;
		case R.id.studyBack:
			finish();
			break;
		case R.id.addMember:
			Intent intent9 = new Intent(this,StudyWebViewA.class);
			intent9.putExtra("title", "增员技巧");
			intent9.putExtra("urlPath", "Increase");
			startActivity(intent9);
			break;

		default:
			break;
		}
	}

	
}
