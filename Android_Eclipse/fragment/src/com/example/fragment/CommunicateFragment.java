package com.example.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class CommunicateFragment extends Fragment implements OnClickListener {

	private Button btn;
	private TextView textView;
	private String data;
	private CommunicateListener communicateListener;

	public CommunicateListener getCommunicateListener() {
		return communicateListener;
	}

	public void setCommunicateListener(CommunicateListener communicateListener) {
		this.communicateListener = communicateListener;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
//		textView.setText(data);
		Log.i("tag", data);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater
				.inflate(R.layout.fragment_communicate, container, false);
		btn = (Button) view.findViewById(R.id.button1);
		btn.setOnClickListener(this);
		textView = (TextView) view.findViewById(R.id.text);

		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		textView.setText(data);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (communicateListener != null) {
			communicateListener.onBack("从fragment传回来的数据");
		}
	}

	public interface CommunicateListener {
		public void onBack(String data);
	}
}
