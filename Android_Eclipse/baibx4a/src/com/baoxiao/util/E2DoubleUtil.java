package com.baoxiao.util;

public class E2DoubleUtil {

	/**
	 * ȥ��double�Ŀ�ѧ���㷨
	 * @param a
	 * @return
	 */
	public static String E2Double(Double a){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
		nf.setGroupingUsed(false);  
		return nf.format(a);
	}
}
