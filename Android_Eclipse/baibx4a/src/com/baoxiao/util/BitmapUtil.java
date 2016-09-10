package com.baoxiao.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {
	public static Bitmap scaleImage(Bitmap bitMap) {
		//ͼƬ�������ռ�   ��λ��KB
		double maxSize =20.00;
		//��bitmap���������У�����bitmap�Ĵ�С����ʵ�ʶ�ȡ��ԭ�ļ�Ҫ��
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		//���ֽڻ���KB  
		double mid = b.length/1024;  
		//�ж�bitmapռ�ÿռ��Ƿ�����������ռ�  ���������ѹ�� С����ѹ��
		if (mid > maxSize) {
			//��ȡbitmap��С ����������С�Ķ��ٱ�
			double i = mid / maxSize;
			//��ʼѹ��  �˴��õ�ƽ���� ������͸߶�ѹ������Ӧ��ƽ������ ��1.���̶ֿȺ͸߶Ⱥ�ԭbitmap����һ�£�ѹ����Ҳ�ﵽ������Сռ�ÿռ�Ĵ�С) 
			bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
			bitMap.getHeight() / Math.sqrt(i));
		}
		return bitMap;
	}
	private static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// ��ȡ���ͼƬ�Ŀ�͸�
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ������������
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ����ͼƬ����  
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}

}
