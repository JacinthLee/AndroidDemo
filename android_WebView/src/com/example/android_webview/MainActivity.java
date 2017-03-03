package com.example.android_webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends Activity {

	private String url="http://2014.qq.com/";
	private WebView webView;
	private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

//        Uri uri=Uri.parse(url);//url为你要链接的地址
//        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);

        init();
    }

    private void init() {
		webView=(WebView) findViewById(R.id.webView);//在AndroidManifest.xml文件中AddPermission——android.permission.INTERNET
		//WebView加载本地资源
		//webView.loadUrl("file:///android_asset/example.html");
		//WebView加载web资源
		webView.loadUrl(url);
		//覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//返回值是true的时候控制网页在WebView中打开，是false的时候调用系统浏览器或第三方浏览器打开
				view.loadUrl(url);
				return true;//强行写为true
			}
			//WebViewClient帮助WebView去处理一些页面控制和请求通知
			
		});
		//启用支持Javascript
		WebSettings settings=webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		/*
		 * WebView缓存的运用.令WebView加载页面优先使用缓存加载（默认是不使用）
		 * 在eclipse——Window——show view——other——FileExplorer选择对应应用中cache文件，里面是缓存；dataBase中cookies目录也储存了记录*/
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		/*
		 * 判断页面加载过程*/
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {//可以监测当前网站进度变化的方法，通过它可以判断当前页面的加载过程
				// TODO Auto-generated method stub
				//newProgress是1-100之间的整数
				if (newProgress==100) {
					//页面加载完毕，关闭progressDialog进度条
					closeDialog();
				}else {
					//网页正在加载
					openDialog(newProgress);
				}
			}

			//页面加载完毕，关闭progressDialog进度条
			private void closeDialog() {
				// TODO Auto-generated method stub
				if (progressDialog!=null&&progressDialog.isShowing()) {
					progressDialog.dismiss();//取消显示
					progressDialog=null;
				}
			}

			//网页正在加载
			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (progressDialog==null) {//进度条为空的时候
					progressDialog=new ProgressDialog(MainActivity.this);
					progressDialog.setTitle("正在加载");//设置标题
					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条样式。横向进度条
					progressDialog.setProgress(newProgress);//显示当前进度条进度
					progressDialog.show();
				}else {
					progressDialog.setProgress(newProgress);//进度条不为空的时候，显示进度条进度
				}
			}
		});
	}

   //改写手机物理按键返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	if (keyCode==KeyEvent.KEYCODE_BACK) {
    		Toast.makeText(this, webView.getUrl(), Toast.LENGTH_LONG).show();
			if (webView.canGoBack()) {
				webView.goBack();//返回上一页面
				return true;
			}
			else {
				System.exit(0);//退出程序
			}
		}
    	return super.onKeyDown(keyCode, event);
    }

}
