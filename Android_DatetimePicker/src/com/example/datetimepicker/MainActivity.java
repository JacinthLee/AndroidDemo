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
	private Calendar calendar;//��ʾ��ǰ����ʱ�����
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //��ȡ������һ������
        calendar=Calendar.getInstance();
        //��ȡ������ ʱ�������Ϣ
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;//Calendar()������month��0��ʼ
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        setTitle(year+"-"+month+"-"+day+" "+hour+":"+minute);
        datePicker=(DatePicker) findViewById(R.id.datePicker);
        timePicker=(TimePicker) findViewById(R.id.timePicker);
        
        //datePicker��ʼ��
        datePicker.init(year, calendar.get(Calendar.MONTH), day, new OnDateChangedListener() {
			
			@Override//�¼��������������������ص��ǵ�ǰ���ڵ�ʵ�ʽ��
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//monthOfYear��0��ʼ����
			}
		});
        
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				setTitle((hourOfDay-1)+":"+(minute-1));
			}
		});
        
//        /*DatePickerDialogʵ������ѡ��Ի���
//         *������
//         *�¼������� 
//         *year, monthOfYear, dayOfMonth�൱��init������һ����ʼ���Ĳ���*/
//        new DatePickerDialog(this, new OnDateSetListener() {
//			
//			@Override
//			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//				// TODO Auto-generated method stub
//				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);//monthOfYear��0��ʼ����
//			}
//		}, year, calendar.get(Calendar.MONTH), day).show();
        
        /*TimePickerDialogʵ������ѡ��Ի���
         *������
         *�¼������� 
         *hourOfDay, minute:�൱��init������һ����ʼ���Ĳ���
         *is24HourView:�Ƿ���24СʱΪ������λ*/
        new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				setTitle((hourOfDay)+":"+(minute));
			}
		}, hour, minute, true).show();
    }


}
