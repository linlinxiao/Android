package com.baoxiao.service.productshow;

import com.baoxiao.R;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimationService {
	
	public Animation startAnim(Context context,int animType){
		Animation anim = null;
		switch (animType) {
	    //
		case 1:
			anim = AnimationUtils.loadAnimation(context,
					R.anim.ps_shouhuxshow_anim_translateleftin);
			break;
		case 2:
			anim = AnimationUtils.loadAnimation(context,
					R.anim.ps_shouhuxshow_anim_translateleftin);
			break;
		case 3:
			anim = AnimationUtils.loadAnimation(context,
					R.anim.ps_shouhuxshow_anim_translateleftin);
			break;

		default:
			break;
		}
		return anim;
	}
}
