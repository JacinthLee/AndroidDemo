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

//        Uri uri=Uri.parse(url);//urlΪ��Ҫ���ӵĵ�ַ
//        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);

        init();
    }

    private void init() {
		webView=(WebView) findViewById(R.id.webView);//��AndroidManifest.xml�ļ���AddPermission����android.permission.INTERNET
		//WebView���ر�����Դ
		//webView.loadUrl("file:///android_asset/example.html");
		//WebView����web��Դ
		webView.loadUrl(url);
		//����WebViewĬ��ͨ��������������ϵͳ���������ҳ����Ϊ��ʹ����ҳ������WebView�д�
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//����ֵ��true��ʱ�������ҳ��WebView�д򿪣���false��ʱ�����ϵͳ�������������������
				view.loadUrl(url);
				return true;//ǿ��дΪtrue
			}
			//WebViewClient����WebViewȥ����һЩҳ����ƺ�����֪ͨ
			
		});
		//����֧��Javascript
		WebSettings settings=webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		/*
		 * WebView���������.��WebView����ҳ������ʹ�û�����أ�Ĭ���ǲ�ʹ�ã�
		 * ��eclipse����Window����show view����other����FileExplorerѡ���ӦӦ����cache�ļ��������ǻ��棻dataBase��cookiesĿ¼Ҳ�����˼�¼*/
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		/*
		 * �ж�ҳ����ع���*/
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {//���Լ�⵱ǰ��վ���ȱ仯�ķ�����ͨ���������жϵ�ǰҳ��ļ��ع���
				// TODO Auto-generated method stub
				//newProgress��1-100֮�������
				if (newProgress==100) {
					//ҳ�������ϣ��ر�progressDialog������
					closeDialog();
				}else {
					//��ҳ���ڼ���
					openDialog(newProgress);
				}
			}

			//ҳ�������ϣ��ر�progressDialog������
			private void closeDialog() {
				// TODO Auto-generated method stub
				if (progressDialog!=null&&progressDialog.isShowing()) {
					progressDialog.dismiss();//ȡ����ʾ
					progressDialog=null;
				}
			}

			//��ҳ���ڼ���
			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (progressDialog==null) {//������Ϊ�յ�ʱ��
					progressDialog=new ProgressDialog(MainActivity.this);
					progressDialog.setTitle("���ڼ���");//���ñ���
					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//���ý�������ʽ�����������
					progressDialog.setProgress(newProgress);//��ʾ��ǰ����������
					progressDialog.show();
				}else {
					progressDialog.setProgress(newProgress);//��������Ϊ�յ�ʱ����ʾ����������
				}
			}
		});
	}

   //��д�ֻ����������ص��߼�
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	if (keyCode==KeyEvent.KEYCODE_BACK) {
    		Toast.makeText(this, webView.getUrl(), Toast.LENGTH_LONG).show();
			if (webView.canGoBack()) {
				webView.goBack();//������һҳ��
				return true;
			}
			else {
				System.exit(0);//�˳�����
			}
		}
    	return super.onKeyDown(keyCode, event);
    }

}
