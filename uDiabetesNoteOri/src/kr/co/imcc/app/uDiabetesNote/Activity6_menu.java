package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Activity6_menu extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */

	String[] arGeneral = {"�ʿ俭��","�Ҿƴ索��","��������"};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer6_menu);
             
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
    			Intent page1 = new Intent(this,tActivity2_2.class);
    			startActivity(page1);
    		}
    		else if(position==1)
    		{
    			Intent page2 = new Intent(this,tActivity2_4.class);
    			startActivity(page2);
    		}
    		else if(position==2)
    		{
    			Intent page3 = new Intent(this,tActivity2_3.class);
    			startActivity(page3);
    		}
    }}

        /*
        
        Button button_back = (Button) findViewById(R.id.button_layer6_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});
				
		Button button1 = (Button) findViewById(R.id.button_layer6_3);
		Button button2 = (Button) findViewById(R.id.button_layer6_4);
		Button button3 = (Button) findViewById(R.id.button_layer6_2);
		
		button3.setOnClickListener(new OnClickListener() { // �ʿ俭��

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity6_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_2.class);
				startActivity(intent);
			}
		});


		button1.setOnClickListener(new OnClickListener() { // �Ҿ� �索��

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity6_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_4.class);
				startActivity(intent);

			}
		});

		button2.setOnClickListener(new OnClickListener() { // ��������

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity6_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3.class);
				startActivity(intent);
			}
		});	
		
    }
}*/


