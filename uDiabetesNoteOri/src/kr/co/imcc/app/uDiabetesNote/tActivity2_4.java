package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class tActivity2_4 extends Activity{
	
    private ActionBar actionBar;
	ArrayList<MyItem> arItem;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		 actionBar = getActionBar();
			actionBar.setTitle("당뇨병 추천 식단");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		arItem = new ArrayList<MyItem>();
		MyItem mi;
		mi = new MyItem(R.drawable.listnext_page,"당뇨병 추천/피해야 할 음식");arItem.add(mi);
		mi = new MyItem(R.drawable.listnext_page,"당뇨병 추천식단");arItem.add(mi);
		mi = new MyItem(R.drawable.listnext_page,"당뇨병 식습관 가이드");arItem.add(mi);
		mi = new MyItem(R.drawable.listnext_page,"당뇨병 대처방법");arItem.add(mi);

		MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.list_content,arItem);
		
		ListView MyList;
		MyList=(ListView)findViewById(R.id.list_ac);
		MyList.setAdapter(MyAdapter);
		
		MyList.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	
	        	switch(position){
	        	case 0:
	        		Intent page1 = new Intent(getApplicationContext(), tActivity2_4_1.class);
					page1.putExtra("diabtete", 1);
					startActivity(page1);
		            break;
	        	case 1:
	        		Intent page2 = new Intent(getApplicationContext(), tActivity2_4_2.class);
					page2.putExtra("diabtete", 2);
					startActivity(page2);
		            break;
	        	case 2:
	        		Intent page3 = new Intent(getApplicationContext(), tActivity2_4_3.class);
					page3.putExtra("diabtete", 3);
					startActivity(page3);
		            break;
	        	case 3:
	        		Intent page4 = new Intent(getApplicationContext(), tActivity2_4_4.class);
					page4.putExtra("diabtete", 4);
					startActivity(page4);
		            break;
	        	}
	        	
	       }
	});
		
		
	}
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		      case android.R.id.home:
		        onBackPressed();
		    }
		    return true;
		}
}

class MyItem{
	MyItem(int aIcon,String aName){
		Icon =aIcon;
		Name = aName;
	}
	
	int Icon;
	String Name;
}
class MyListAdapter extends BaseAdapter{
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem> arSrc;
	int layout;
	
	public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc){
		maincon = context;
		Inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		arSrc=aarSrc;
		layout=alayout;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arSrc.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return arSrc.get(position).Name;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final int pos = position;
		if(convertView == null){
			convertView = Inflater.inflate(layout, parent, false);
		}
		
		ImageView img = (ImageView)convertView.findViewById(R.id.list_image);
		img.setImageResource(arSrc.get(position).Icon);
		
		TextView txt = (TextView)convertView.findViewById(R.id.list_text);
		txt.setText(arSrc.get(position).Name);
		
		// TODO Auto-generated method stub
		return convertView;
	}	
	

}

/*package kr.co.imcc.app.uDiabetesNote;

import kr.co.imcc.app.uDiabetesNote.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class tActivity2_4 extends Activity {
    *//** Called when the activity is first created. *//*
	
	
    private ActionBar actionBar;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layer6);
        
        actionBar = getActionBar();
		actionBar.setTitle("�Ҿ� �索�� ��õ �Ĵ�");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
        
        
        Button button_back = (Button) findViewById(R.id.button_layer6_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});
			
		Button button1 = (Button) findViewById(R.id.button_layer6_1);
		Button button2 = (Button) findViewById(R.id.button_layer6_2);
		Button button3 = (Button) findViewById(R.id.button_layer6_3);
		Button button4 = (Button) findViewById(R.id.button_layer6_4);
		Button button5 = (Button) findViewById(R.id.button_layer6_5);
		//Button button6 = (Button) findViewById(R.id.button_layer6_6);

		button1.setOnClickListener(new OnClickListener() { // 

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(tActivity2_4.this, kr.co.imcc.app.uDiabetesNote.tActivity2_4_1.class);
				intent.putExtra("diabtete", 1);
				startActivity(intent);
			}
		});

		button2.setOnClickListener(new OnClickListener() { // 

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_4.this, kr.co.imcc.app.uDiabetesNote.tActivity2_4_1.class);
				intent.putExtra("diabtete", 2);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() { //

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_4.this, kr.co.imcc.app.uDiabetesNote.tActivity2_4_1.class);
				intent.putExtra("diabtete", 3);
				startActivity(intent);

			}
		});

		button4.setOnClickListener(new OnClickListener() { // 

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_4.this, kr.co.imcc.app.uDiabetesNote.tActivity2_4_1.class);
				intent.putExtra("diabtete", 4);
				startActivity(intent);

			}
		});
		
		button5.setOnClickListener(new OnClickListener() { //

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_4.this, kr.co.imcc.app.uDiabetesNote.tActivity2_4_1.class);
				intent.putExtra("diabtete", 5);
				startActivity(intent);

			}
		});
		
		
		button6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(tActivity2_4.this,
						kr.co.imcc.app.uDiabetesNote.Activity6_6.class);
				startActivity(intent);
				
			}
						
		});		
		
    }
}







*/