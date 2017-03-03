package com.example.android_listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,OnScrollListener{

	private ListView listView;
	private ArrayAdapter<String>arr_adapter;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>>dataList;//1.SimpleAdapter-data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        listView=(ListView) findViewById(R.id.listView);        
        String[]arr_data={"数据源内容1","数据源内容2","数据源内容3","数据源内容4","数据源内容5"};//新建一个数据源
        //1.新建一个数据适配器
        //ArrayAdapter(上下文，当前ListView加载的每一个列表项所对应的布局文件，数据源)
        //2.适配器加载数据源
        arr_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr_data);
        /*SimpleAdapter()
         *context:上下文
         *data:数据源，特定的泛性集合,最后写。 List<? extends Map<String, ?>> data 
         *     一个Map所组成的List集合，每一个Map都会去对应ListView列表中的一行
         *     每一个Map（键-值对）中的键必须包含所有在from中所指定
         *resource:列表项的布局文件ID
         *from:Map中的键名
         *to:绑定数据视图中的ID，与from呈对应关系*/   
        dataList=new ArrayList<Map<String,Object>>();//2.创建dataList，用ArrayList数组集合
        simp_adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","text"}, new int[]{R.id.pic,R.id.text});
        //3.视图（ListView）加载适配器
        //listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        
    }
    
    private List<Map<String, Object>> getData(){
    	for(int i=0;i<20;i++)
    	{
    		Map<String, Object>map=new HashMap<String, Object>();
    		map.put("pic", R.drawable.ic_launcher);
    		map.put("text", "自定义的数据源内容"+i);
    		dataList.add(map);
    	}
    	return dataList;
    }

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		switch (scrollState) {
		case SCROLL_STATE_FLING:
			Log.i("Main","用户在离开手指屏幕之前，由于用力滑了一下，视图仍依靠惯性继续滑动");
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("pic", R.drawable.ic_launcher);
			map.put("text", "增加项");
			dataList.add(map);
			simp_adapter.notifyDataSetChanged();//通知UI刷新界面
			break;
		case SCROLL_STATE_IDLE:
			Log.i("Main","视图已经停止滑动");
			break;
		case SCROLL_STATE_TOUCH_SCROLL:
			Log.i("Main","手指没有离开屏幕，视图正在滑动");
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		String text=listView.getItemAtPosition(position)+"";
		Toast.makeText(this, "position="+position+" text="+text, Toast.LENGTH_SHORT).show();
	}


}
