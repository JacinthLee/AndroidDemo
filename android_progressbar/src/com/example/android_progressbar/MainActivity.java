package com.example.android_progressbar;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private ProgressBar progressBar;
	private Button add;
	private Button reduce;
	private Button reset;
	private TextView text;
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
		}
		text.setText("��һ���Ȱٷֱ�"+(int)(progressBar.getProgress()/(float)progressBar.getMax()*100)+"% �ڶ����Ȱٷֱ�"+(int)(progressBar.getSecondaryProgress()/(float)progressBar.getMax()*100)+"%");
		
	}
   

}
