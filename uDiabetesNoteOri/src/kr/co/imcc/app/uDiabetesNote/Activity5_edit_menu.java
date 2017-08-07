package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Activity5_edit_menu extends Activity implements OnItemClickListener{
	/** Called when the activity is first created. */

	String[] arGeneral = {"Ȱ���� ���","���� Ȱ���� ��ǥ"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_menu);
		
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
	    			Intent page1 = new Intent(this,tActivity3_1.class);
	    			startActivity(page1);
	    		}
	    		else if(position==1)
	    		{
	    			Intent page2 = new Intent(this,tActivity3_2.class);
	    			startActivity(page2);
	    		}
	    }
	    	
}
		/*
		Button button1 = (Button) findViewById(R.id.button_1); // ��� ���
		Button button2 = (Button) findViewById(R.id.button_2); // � ��ǥ
		Button button_back = (Button) findViewById(R.id.button_back);
		
		//Button button3 = (Button) findViewById(R.id.button3); // ��� �Է�

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
		
		button1.setOnClickListener(new OnClickListener() { //��� ���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity5_edit_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity3_1.class);
				startActivity(intent);

			}
		});
		
		button2.setOnClickListener(new OnClickListener() { //���ǥ

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity5_edit_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity3_2.class);
				startActivity(intent);

			}
		});
		/*
		button3.setOnClickListener(new OnClickListener() { //�񸸰���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity5_edit_menu.this,
						kr.co.imcc.app.uDiabetesNote.Activity2.class);
				startActivity(intent);

			}
		});*/
	