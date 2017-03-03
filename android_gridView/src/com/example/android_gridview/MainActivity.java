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
			R.drawable.weather,R.drawable.world,R.drawable.youtube};//图标显示图片封装在一个数组里
	private String[]iconName={"通讯录","日历","照相机","时钟","游戏","短信","铃声","设置","语音",
			"天气","浏览器","视频"};//图标显示名称也封装在一个数组里
	private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridView=(GridView) findViewById(R.id.gridView);
        //1.准备数据源
        //2.新建适配器（SimpleAdapter）
        //3.视图界面即GridView加载适配器
        //4.GridView配置事件监听器（OnItemClickListener）监听单个条目的点击
        dataList=new ArrayList<Map<String,Object>>();//新建数据源的集合
        //getData();下一行SimpleAdapter中有getData()，加载了两遍，屏幕重复显示共两次数据
        adapter=new SimpleAdapter(this, getData(), R.layout.item, 
        		new String[]{"image","text"}, new int[]{R.id.image,R.id.text});//2.新建适配器（SimpleAdapter）
        gridView.setAdapter(adapter);//3.视图界面即GridView加载适配器
        gridView.setOnItemClickListener(this);//加载监听器
    }

    private List<Map<String, Object>> getData() {//每一个数据都是由Map组成的
    	for (int i = 0; i < icon.length; i++) {
    		Map<String, Object>map=new HashMap<String, Object>();//新建HashMap
        	map.put("image", icon[i]);
        	map.put("text", iconName[i]);
        	dataList.add(map);
		}
    	
		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "我是"+iconName[position], Toast.LENGTH_SHORT).show();
	}

    

}
