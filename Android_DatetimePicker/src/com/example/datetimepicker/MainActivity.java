package com.example.datetimepicker;


import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {

	private TimePicker timePicker;
	private DatePicker datePicker;
	private Calendar calendar;//显示当前日期时间情况
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //获取日历的一个对象
        calendar=Calendar.getInstance();
        //获取年月日 时分秒的信息
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;//Calendar()方法中month从0开始
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        setTitle(year+"-"+month+"-"+day+" "+hour+":"+minute);
        datePicker=(DatePicker) findViewById(R.id.datePicker);
        timePicker=(TimePicker) findViewById(R.id.timePicker);
        
        //datePicker初始化
        datePicker.init(year, calendar.get(Calendar.MONTH), day, new OnDateChangedListener() {
			
			@Override//事件监听器的三个参数返回的是当前日期的实际结果
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//monthOfYear从0开始计数
			}
		});
        
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				setTitle((hourOfDay-1)+":"+(minute-1));
			}
		});
        
//        /*DatePickerDialog实现日期选择对话框
//         *上下文
//         *事件监听器 
//         *year, monthOfYear, dayOfMonth相当于init方法做一个初始化的操作*/
//        new DatePickerDialog(this, new OnDateSetListener() {
//			
//			@Override
//			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//				// TODO Auto-generated method stub
//				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//monthOfYear从0开始计数
//			}
//		}, year, calendar.get(Calendar.MONTH), day).show();
        
        /*TimePickerDialog实现日期选择对话框
         *上下文
         *事件监听器 
         *hourOfDay, minute:相当于init方法做一个初始化的操作
         *is24HourView:是否以24小时为计数单位*/
        new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				setTitle((hourOfDay)+":"+(minute));
			}
		}, hour, minute, true).show();
    }


}
