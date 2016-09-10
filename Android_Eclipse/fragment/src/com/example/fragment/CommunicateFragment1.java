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
public class CommunicateFragment1 extends Fragment implements OnClickListener {

	private Button btn;
	private TextView textView;
	private String data;

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
		Bundle bundle = getArguments();
		Object obj = bundle.get("name");
		btn.setText((String)obj);
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
		
	}
}
