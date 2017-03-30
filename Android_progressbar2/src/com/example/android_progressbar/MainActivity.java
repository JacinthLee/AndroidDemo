package com.example.android_progressbar;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private ProgressBar progressBar;
	private Button add;
	private Button reduce;
	private Button reset;
	private TextView text;
	private ProgressDialog progressDialog;
	private Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启用窗口特征，启用带进度和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        setContentView(R.layout.main);

        //显示两种进度条
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(600);//带进度的进度条可以显示刻度，最大值为10000
        
        init();//初始化，具体方法写在了外面
    }
    
    private void init() {
		progressBar=(ProgressBar) findViewById(R.id.horiz);
		add=(Button) findViewById(R.id.add);
		reduce=(Button) findViewById(R.id.reduce);
		reset=(Button) findViewById(R.id.reset);
		text=(TextView) findViewById(R.id.text);
		show=(Button) findViewById(R.id.show);
		show.setOnClickListener(this);
		//初始化时显示进度条的方法getProgress()，获取第一进度条的进度方法
		int first=progressBar.getProgress();
		//getSecondaryProgress()，获取第二进度条的进度方法
		int second=progressBar.getSecondaryProgress();
		//getMax()，获取最大进度
		int max=progressBar.getMax();
		//进度以百分比的形式展示需要进行计算
		text.setText("第一进度百分比"+(int)(first/(float)max*100)+"% 第二进度百分比"+(int)(second/(float)max*100)+"%");
		
		add.setOnClickListener(this);
		reduce.setOnClickListener(this);
		reset.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
		{
			//增加第一进度和第二进度10个刻度
			progressBar.incrementProgressBy(10);
			progressBar.incrementSecondaryProgressBy(10);
			break;
		}
		case R.id.reduce:
		{
			//减少第一进度和第二进度10个刻度
			progressBar.incrementProgressBy(-10);
			progressBar.incrementSecondaryProgressBy(-10);
			break;
		}		
		case R.id.reset:
		{
			progressBar.setProgress(50);
			progressBar.setSecondaryProgress(80);
			break;
		}
		case R.id.show:
		{
			/*
			 * 页面显示风格*/
			//新建progressDialog对象
			progressDialog=new ProgressDialog(MainActivity.this);
			//给对话框设置显示风格
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			//设置标题
			progressDialog.setTitle("对话框Demo");
			//设置对话框里面的内容，文字信息
			progressDialog.setMessage("Keep Calm and Carry On！");
			//设置图标
			progressDialog.setIcon(R.drawable.ic_launcher);
			
			/*
			 * 设定关于ProgressBar进度条的一些属性*/
			//设定最大进度
			progressDialog.setMax(100);
			//设定初始化已经增长到的进度
			progressDialog.incrementProgressBy(50);
			//进度条是明确显示进度的（false值为显示，true为不显示）
			progressDialog.setIndeterminate(false);
			
			/*
			 * 设定一个确定按钮*/
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "Carry On!", Toast.LENGTH_SHORT).show();
					Log.i("tag", "设定一个确定按钮");
				}
			});
			
			//是否可以通过返回按钮退出对话框
			progressDialog.setCancelable(true);
			
			//显示ProgressDialog
			progressDialog.show();
			
			break;
		}
		}
		text.setText("第一进度百分比"+(int)(progressBar.getProgress()/(float)progressBar.getMax()*100)+"% 第二进度百分比"+(int)(progressBar.getSecondaryProgress()/(float)progressBar.getMax()*100)+"%");
		
	}
   

}
