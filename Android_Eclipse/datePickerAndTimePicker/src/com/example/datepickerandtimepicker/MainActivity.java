package com.example.datepickerandtimepicker;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends ActionBarActivity {
	// 时间日期选择器
	private TimePicker timePicker;
	private DatePicker datePicker;
	private Calendar calendar = Calendar.getInstance();
	private int year = calendar.get(Calendar.YEAR);
	private int month = calendar.get(Calendar.MONTH) + 1;
	private int day = calendar.get(Calendar.DAY_OF_MONTH);
	private int hour = calendar.get(Calendar.HOUR);
	private int minute = calendar.get(Calendar.MINUTE);
	private int second = calendar.get(Calendar.SECOND);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle(year + "-" + month + "-" + day + "  " + hour + ":" + minute
				+ ":" + second);

		initViews();
	}

	private void initViews() {
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		datePicker = (DatePicker) findViewById(R.id.datePicker1);

		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker arg0, int hour, int minute) {
				// TODO Auto-generated method stub
				setTitle(hour + ":" + minute);
			}
		});

		datePicker.init(year, calendar.get(Calendar.MONTH), day,
				new OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker arg0, int year,
							int month, int day) {
						// TODO Auto-generated method stub
						setTitle(year + "-" + (month + 1) + "-" + day);
					}
				});
	}

	public void showTimePickerDailog(View btn) {
		TimePickerDialog picker = new TimePickerDialog(this, new OnTimeSetListener(
				) {
			
			@Override
			public void onTimeSet(TimePicker arg0, int hour, int minute) {
				// TODO Auto-generated method stub
				setTitle(hour + ":" + minute);
			}
		}, hour, minute, true);
		picker.show();
	}

	public void showDatePickerDailog(View btn) {
		DatePickerDialog picker = new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				// TODO Auto-generated method stub
				setTitle(year + "-" + (month + 1) + "-" + day);
			}
		}, year, calendar.get(Calendar.MONTH), day);
		picker.show();
	}

}
