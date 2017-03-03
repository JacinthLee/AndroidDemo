package com.example.android_gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	private GridView gridView;
	private List<Map<String, Object>>dataList;
	private int[]icon={R.drawable.address_book,R.drawable.calendar,R.drawable.camera,
			R.drawable.clock,R.drawable.games_control,R.drawable.messenger,
			R.drawable.ringtone,R.drawable.settings,R.drawable.speech_balloon,
			R.drawable.weather,R.drawable.world,R.drawable.youtube};//ͼ����ʾͼƬ��װ��һ��������
	private String[]iconName={"ͨѶ¼","����","�����","ʱ��","��Ϸ","����","����","����","����",
			"����","�����","��Ƶ"};//ͼ����ʾ����Ҳ��װ��һ��������
	private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridView=(GridView) findViewById(R.id.gridView);
        //1.׼������Դ
        //2.�½���������SimpleAdapter��
        //3.��ͼ���漴GridView����������
        //4.GridView�����¼���������OnItemClickListener������������Ŀ�ĵ��
        dataList=new ArrayList<Map<String,Object>>();//�½�����Դ�ļ���
        //getData();��һ��SimpleAdapter����getData()�����������飬��Ļ�ظ���ʾ����������
        adapter=new SimpleAdapter(this, getData(), R.layout.item, 
        		new String[]{"image","text"}, new int[]{R.id.image,R.id.text});//2.�½���������SimpleAdapter��
        gridView.setAdapter(adapter);//3.��ͼ���漴GridView����������
        gridView.setOnItemClickListener(this);//���ؼ�����
    }

    private List<Map<String, Object>> getData() {//ÿһ�����ݶ�����Map��ɵ�
    	for (int i = 0; i < icon.length; i++) {
    		Map<String, Object>map=new HashMap<String, Object>();//�½�HashMap
        	map.put("image", icon[i]);
        	map.put("text", iconName[i]);
        	dataList.add(map);
		}
    	
		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "����"+iconName[position], Toast.LENGTH_SHORT).show();
	}

    

}
