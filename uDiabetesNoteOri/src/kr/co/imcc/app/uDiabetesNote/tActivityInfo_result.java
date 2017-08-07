package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;

public class tActivityInfo_result extends Activity {
	/** Called when the activity is first created. */
	
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity_explain);
		
		 actionBar = getActionBar();
			actionBar.setTitle("����");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
	}
}

/*
		Button button1 = (Button) findViewById(R.id.button_config_1);
		Button button2 = (Button) findViewById(R.id.button_config_2);
		Button button3 = (Button) findViewById(R.id.button_config_3);
		Button button4 = (Button) findViewById(R.id.button_config_4);
		Button button5 = (Button) findViewById(R.id.button_config_5);
		Button button6 = (Button) findViewById(R.id.button_config_6);

		button1.setOnClickListener(new OnClickListener() { // ����� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.ActivityConfig_1.class);
				startActivity(intent);

			}
		});

		button2.setOnClickListener(new OnClickListener() { // ���� ���� ��ǥġ

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_5.class);
				startActivity(intent);

			}
		});
		
		button6.setOnClickListener(new OnClickListener() { // ���� ���� ��ǥġ

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.tActivity3_2.class);
				startActivity(intent);

			}
		});
		
		button3.setOnClickListener(new OnClickListener() { // ���� ���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.ActivityConfig_3.class);
				startActivity(intent);

			}
		});

		button4.setOnClickListener(new OnClickListener() { // ���� �ð�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.ActivityConfig_4.class);
				startActivity(intent);

			}
		});
		
		button5.setOnClickListener(new OnClickListener() { // �˻�˸� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivityInfo.this,
						kr.co.imcc.app.uDiabetesNote.ActivityConfig_5.class);
				startActivity(intent);

			}
		});
		
		Button button_back = (Button) findViewById(R.id.button_config_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
	}
}*/