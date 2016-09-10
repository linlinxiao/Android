package com.baoxiao.test.activity.webview;

import com.baoxiao.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity2 extends Activity{
	//实例化一个10个TextView的数组  
    private TextView fanHuanJinS[] = new TextView[72];

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ps_shousuxshow);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
	
	public TextView[] findFanHuanJing(){
		
		//实例化一个10个TextView的数组  
	    TextView fanHuanJinS[] = new TextView[72];
	    
		fanHuanJinS[0] = (TextView) findViewById(R.id.fan1);  
		fanHuanJinS[1] = (TextView) findViewById(R.id.fan2);  
		fanHuanJinS[2] = (TextView) findViewById(R.id.fan3);  
		fanHuanJinS[3] = (TextView) findViewById(R.id.fan4);  
		fanHuanJinS[4] = (TextView) findViewById(R.id.fan5);  
		fanHuanJinS[5] = (TextView) findViewById(R.id.fan6);  
		fanHuanJinS[6] = (TextView) findViewById(R.id.fan7);  
		fanHuanJinS[7] = (TextView) findViewById(R.id.fan8);  
		fanHuanJinS[8] = (TextView) findViewById(R.id.fan9);  
		fanHuanJinS[9] = (TextView) findViewById(R.id.fan10);  
		fanHuanJinS[10] = (TextView) findViewById(R.id.fan11);  
		fanHuanJinS[11] = (TextView) findViewById(R.id.fan12);  
		fanHuanJinS[12] = (TextView) findViewById(R.id.fan13);  
		fanHuanJinS[13] = (TextView) findViewById(R.id.fan14);  
		fanHuanJinS[14] = (TextView) findViewById(R.id.fan15);  
		fanHuanJinS[15] = (TextView) findViewById(R.id.fan16);  
		fanHuanJinS[16] = (TextView) findViewById(R.id.fan17);  
		fanHuanJinS[17] = (TextView) findViewById(R.id.fan18);  
		fanHuanJinS[18] = (TextView) findViewById(R.id.fan19);  
		fanHuanJinS[19] = (TextView) findViewById(R.id.fan20);  
		fanHuanJinS[20] = (TextView) findViewById(R.id.fan21);  
		fanHuanJinS[21] = (TextView) findViewById(R.id.fan22);  
		fanHuanJinS[22] = (TextView) findViewById(R.id.fan23);  
		fanHuanJinS[23] = (TextView) findViewById(R.id.fan24);  
		fanHuanJinS[24] = (TextView) findViewById(R.id.fan25);  
		fanHuanJinS[25] = (TextView) findViewById(R.id.fan26);  
		fanHuanJinS[26] = (TextView) findViewById(R.id.fan27);  
		fanHuanJinS[27] = (TextView) findViewById(R.id.fan28);  
		fanHuanJinS[28] = (TextView) findViewById(R.id.fan29);  
		fanHuanJinS[19] = (TextView) findViewById(R.id.fan30);  
		fanHuanJinS[30] = (TextView) findViewById(R.id.fan31);  
		fanHuanJinS[31] = (TextView) findViewById(R.id.fan32);  
		fanHuanJinS[32] = (TextView) findViewById(R.id.fan33);  
		fanHuanJinS[33] = (TextView) findViewById(R.id.fan34);  
		fanHuanJinS[34] = (TextView) findViewById(R.id.fan35);  
		fanHuanJinS[35] = (TextView) findViewById(R.id.fan36);  
		fanHuanJinS[36] = (TextView) findViewById(R.id.fan37);  
		fanHuanJinS[37] = (TextView) findViewById(R.id.fan38);  
		fanHuanJinS[38] = (TextView) findViewById(R.id.fan39);  
		fanHuanJinS[39] = (TextView) findViewById(R.id.fan40);  
		fanHuanJinS[40] = (TextView) findViewById(R.id.fan41);  
		fanHuanJinS[41] = (TextView) findViewById(R.id.fan42);  
		fanHuanJinS[42] = (TextView) findViewById(R.id.fan43);  
		fanHuanJinS[43] = (TextView) findViewById(R.id.fan44);  
		fanHuanJinS[44] = (TextView) findViewById(R.id.fan45);  
		fanHuanJinS[45] = (TextView) findViewById(R.id.fan46);  
		fanHuanJinS[46] = (TextView) findViewById(R.id.fan47);  
		fanHuanJinS[47] = (TextView) findViewById(R.id.fan48);  
		fanHuanJinS[48] = (TextView) findViewById(R.id.fan49);  
		fanHuanJinS[49] = (TextView) findViewById(R.id.fan50);  
		fanHuanJinS[50] = (TextView) findViewById(R.id.fan51);  
		fanHuanJinS[51] = (TextView) findViewById(R.id.fan52);  
		fanHuanJinS[52] = (TextView) findViewById(R.id.fan53);  
		fanHuanJinS[53] = (TextView) findViewById(R.id.fan54);  
		fanHuanJinS[54] = (TextView) findViewById(R.id.fan55);  
		fanHuanJinS[55] = (TextView) findViewById(R.id.fan56);  
		fanHuanJinS[56] = (TextView) findViewById(R.id.fan57);  
		fanHuanJinS[57] = (TextView) findViewById(R.id.fan58);  
		fanHuanJinS[58] = (TextView) findViewById(R.id.fan59);  
		fanHuanJinS[59] = (TextView) findViewById(R.id.fan60);  
		fanHuanJinS[60] = (TextView) findViewById(R.id.fan61);  
		fanHuanJinS[61] = (TextView) findViewById(R.id.fan62);  
		fanHuanJinS[62] = (TextView) findViewById(R.id.fan63);  
		fanHuanJinS[63] = (TextView) findViewById(R.id.fan64);  
		fanHuanJinS[64] = (TextView) findViewById(R.id.fan65);  
		fanHuanJinS[65] = (TextView) findViewById(R.id.fan66);  
		fanHuanJinS[66] = (TextView) findViewById(R.id.fan67);  
		fanHuanJinS[67] = (TextView) findViewById(R.id.fan68);  
		fanHuanJinS[68] = (TextView) findViewById(R.id.fan69);  
		fanHuanJinS[69] = (TextView) findViewById(R.id.fan70);  
		fanHuanJinS[70] = (TextView) findViewById(R.id.fan71);  
		fanHuanJinS[71] = (TextView) findViewById(R.id.fan72);  

		for(int i = 0; i < 72 ; i++){
			//设置不可见
			fanHuanJinS[i].setVisibility(View.INVISIBLE);
		}
		return fanHuanJinS;
	}

}
