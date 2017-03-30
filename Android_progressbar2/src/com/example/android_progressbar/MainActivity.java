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
        //���ô������������ô����ȺͲ������ȵĽ�����
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        setContentView(R.layout.main);

        //��ʾ���ֽ�����
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(600);//�����ȵĽ�����������ʾ�̶ȣ����ֵΪ10000
        
        init();//��ʼ�������巽��д��������
    }
    
    private void init() {
		progressBar=(ProgressBar) findViewById(R.id.horiz);
		add=(Button) findViewById(R.id.add);
		reduce=(Button) findViewById(R.id.reduce);
		reset=(Button) findViewById(R.id.reset);
		text=(TextView) findViewById(R.id.text);
		show=(Button) findViewById(R.id.show);
		show.setOnClickListener(this);
		//��ʼ��ʱ��ʾ�������ķ���getProgress()����ȡ��һ�������Ľ��ȷ���
		int first=progressBar.getProgress();
		//getSecondaryProgress()����ȡ�ڶ��������Ľ��ȷ���
		int second=progressBar.getSecondaryProgress();
		//getMax()����ȡ������
		int max=progressBar.getMax();
		//�����԰ٷֱȵ���ʽչʾ��Ҫ���м���
		text.setText("��һ���Ȱٷֱ�"+(int)(first/(float)max*100)+"% �ڶ����Ȱٷֱ�"+(int)(second/(float)max*100)+"%");
		
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
			//���ӵ�һ���Ⱥ͵ڶ�����10���̶�
			progressBar.incrementProgressBy(10);
			progressBar.incrementSecondaryProgressBy(10);
			break;
		}
		case R.id.reduce:
		{
			//���ٵ�һ���Ⱥ͵ڶ�����10���̶�
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
			 * ҳ����ʾ���*/
			//�½�progressDialog����
			progressDialog=new ProgressDialog(MainActivity.this);
			//���Ի���������ʾ���
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			//���ñ���
			progressDialog.setTitle("�Ի���Demo");
			//���öԻ�����������ݣ�������Ϣ
			progressDialog.setMessage("Keep Calm and Carry On��");
			//����ͼ��
			progressDialog.setIcon(R.drawable.ic_launcher);
			
			/*
			 * �趨����ProgressBar��������һЩ����*/
			//�趨������
			progressDialog.setMax(100);
			//�趨��ʼ���Ѿ��������Ľ���
			progressDialog.incrementProgressBy(50);
			//����������ȷ��ʾ���ȵģ�falseֵΪ��ʾ��trueΪ����ʾ��
			progressDialog.setIndeterminate(false);
			
			/*
			 * �趨һ��ȷ����ť*/
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "Carry On!", Toast.LENGTH_SHORT).show();
					Log.i("tag", "�趨һ��ȷ����ť");
				}
			});
			
			//�Ƿ����ͨ�����ذ�ť�˳��Ի���
			progressDialog.setCancelable(true);
			
			//��ʾProgressDialog
			progressDialog.show();
			
			break;
		}
		}
		text.setText("��һ���Ȱٷֱ�"+(int)(progressBar.getProgress()/(float)progressBar.getMax()*100)+"% �ڶ����Ȱٷֱ�"+(int)(progressBar.getSecondaryProgress()/(float)progressBar.getMax()*100)+"%");
		
	}
   

}
