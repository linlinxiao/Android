package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FirstFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link FirstFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class FirstFragment extends Fragment {
	
	private TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_first, container, false);
		textView = (TextView) view.findViewById(R.id.textView);
		textView.setText("第一个fragment页面");
		return view;
	}
	
}
