package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Activity2 extends Activity implements OnItemClickListener{
	/** Called when the activity is first created. */
	
	String[] arGeneral = {"�񸸵�", "BMI(ü���� ����)","���� �񸸵�","�������","���� ���� ��ǥġ"};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layer2);
		
		ArrayAdapter<String> Adapter;
		Adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,arGeneral);
		
		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);
		list.setOnItemClickListener(this);
		}
	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	{
		if(position==0)
		{
			Intent page1 = new Intent(this,tActivity1_1.class);
			startActivity(page1);
		}
		else if(position==1)
		{
			Intent page2 = new Intent(this,tActivity1_2.class);
			startActivity(page2);
		}
		else if(position==2)
		{
			Intent page3 = new Intent(this,tActivity1_3.class);
			startActivity(page3);
		}
		else if(position==3)
		{
			Intent page4 = new Intent(this,tActivity3_3.class);
			startActivity(page4);
		}
		else if(position==4)
		{
			Intent page5 = new Intent(this,tActivity3_4.class);
			startActivity(page5);
		}
		
}}
/*
		Button button1 = (Button) findViewById(R.id.button_layer2_1);
		Button button2 = (Button) findViewById(R.id.button_layer2_2);
		Button button3 = (Button) findViewById(R.id.button_layer2_3);
		Button button4 = (Button) findViewById(R.id.button_layer2_4);
		Button button5 = (Button) findViewById(R.id.button_layer2_5);
		
		button1.setOnClickListener(new OnClickListener() { // �񸸵� �˻�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_1.class);
				startActivity(intent);

			}
		});

		button2.setOnClickListener(new OnClickListener() { // BMI �˻�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_2.class);
				startActivity(intent);

			}
		});

		button3.setOnClickListener(new OnClickListener() { // ���� �񸸵� �˻�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_3.class);
				startActivity(intent);

			}
		});
		
		button4.setOnClickListener(new OnClickListener() { // ���� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_4.class);
				startActivity(intent);

			}
		});
		
		button5.setOnClickListener(new OnClickListener() { // ���� ��ǥġ

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_5.class);
				startActivity(intent);

			}
		});
		Button button_back = (Button) findViewById(R.id.button_layer2_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
	}
}*/