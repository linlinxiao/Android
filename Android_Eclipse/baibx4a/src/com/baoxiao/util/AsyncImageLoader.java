package com.baoxiao.util;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader
{
	// �����ã�ʹ���ڴ�����ʱ���� �������˳������ڴ治������������ã�
	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader()
	{
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	/**
	 * ����ص��ӿ�
	 */
	public interface ImageCallback
	{
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

	/**
	 * �������̼߳���ͼƬ ���̼߳�����ͼƬ����handler�������̲߳��ܸ���ui����handler�������̣߳����Ը���ui��
	 * handler�ֽ���imageCallback��imageCallback��Ҫ�Լ���ʵ�֣���������ԶԻص��������д���
	 * 
	 * @param imageUrl
	 *            ����Ҫ���ص�ͼƬurl
	 * @param imageCallback
	 *            ��
	 * @return
	 */
	public Drawable loadDrawable(final String imageUrl,
			final ImageCallback imageCallback)
	{

		// ��������д���ͼƬ ��������ʹ�û���
		if (imageCache.containsKey(imageUrl))
		{
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null)
			{
				imageCallback.imageLoaded(drawable, imageUrl);// ִ�лص�
				return drawable;
			}
		}

		/**
		 * �����߳���ִ�лص���������ͼ
		 */
		final Handler handler = new Handler()
		{
			public void handleMessage(Message message)
			{
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
			}
		};

		/**
		 * �������̷߳������粢����ͼƬ ���ѽ������handler����
		 */
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				Drawable drawable = loadImageFromUrl(imageUrl);
				// �������ͼƬ�ŵ�������
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}).start();

		return null;
	}

	/**
	 * ����ͼƬ ��ע��HttpClient ��httpUrlConnection������
	 */

	public Drawable loadImageFromUrl(String url)
	{
		Drawable d = null;
		try
		{
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 1000 * 15);
			HttpGet get = new HttpGet(url);
			HttpResponse response;

			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entity = response.getEntity();

				d = Drawable.createFromStream(entity.getContent(), "src");
			} else
			{
				return null;
			}
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return d;
	}

	// �������
	public void clearCache()
	{

		if (this.imageCache.size() > 0)
		{

			this.imageCache.clear();
		}

	}
}
