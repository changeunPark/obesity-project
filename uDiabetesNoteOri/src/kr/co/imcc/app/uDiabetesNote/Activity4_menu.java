package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Activity4_menu extends Activity implements OnItemClickListener{
	/** Called when the activity is first created. */	
	
	String[] arGeneral = {"�񸸽Ľ��� ����", "��Ʈ���� ����","����� ����"};
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_list);
		
			
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
				Intent page1 = new Intent(this,tActivity4_1.class);
				startActivity(page1);
			}
			else if(position==1)
			{
				Intent page2 = new Intent(this,tActivity4_2.class);
				startActivity(page2);
			}
			else if(position==2)
			{
				Intent page3 = new Intent(this,tActivity4_3.class);
				startActivity(page3);
			}
	}}
		
/*
		
		Button button1 = (Button) findViewById(R.id.button_layer4_menu_1); // �񸸽Ľ��� ����
		Button button2 = (Button) findViewById(R.id.button_layer4_menu_2); // ��Ʈ����
		Button button3 = (Button) findViewById(R.id.button_layer4_menu_3); // ����� ����
		
		button1.setOnClickListener(new OnClickListener() { //�񸸽Ľ��� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity4_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity4_1.class);
				startActivity(intent);

			}
		});
		button2.setOnClickListener(new OnClickListener() { // ��Ʈ���� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity4_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity4_2.class);
				startActivity(intent);

			}
		});

		button3.setOnClickListener(new OnClickListener() { //����� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity4_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity4_3.class);
				startActivity(intent);

			}
		});
		
		
		
		Button button_back = (Button) findViewById(R.id.button_layer4_menu_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});	
	}
}*/