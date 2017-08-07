package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class tActivity2_3 extends Activity{
	
    private ActionBar actionBar;
	ArrayList<MyItem1> arItem;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		 actionBar = getActionBar();
			actionBar.setTitle("고지혈증 추천 식단");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		arItem = new ArrayList<MyItem1>();
		MyItem1 mi;
		mi = new MyItem1(R.drawable.listnext_page,"고지혈증 추천/피해야 할 음식");arItem.add(mi);
		mi = new MyItem1(R.drawable.listnext_page,"고지혈증 추천식단");arItem.add(mi);
		mi = new MyItem1(R.drawable.listnext_page,"고지혈증 식습관 가이드");arItem.add(mi);
		mi = new MyItem1(R.drawable.listnext_page,"고지혈증 주의사항");arItem.add(mi);

		MyListAdapter1 MyAdapter = new MyListAdapter1(this, R.layout.list_content,arItem);
		
		ListView MyList;
		MyList=(ListView)findViewById(R.id.list_ac);
		MyList.setAdapter(MyAdapter);
		
		MyList.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	
	        	switch(position){
	        	case 0:
	        		Intent page1 = new Intent(getApplicationContext(), tActivity2_3_1.class);
					page1.putExtra("diabtete", 1);
					startActivity(page1);
		            break;
	        	case 1:
	        		Intent page2 = new Intent(getApplicationContext(), tActivity2_3_2.class);
					startActivity(page2);
		            break;
	        	case 2:
	        		Intent page3 = new Intent(getApplicationContext(), tActivity2_3_3.class);
					page3.putExtra("diabtete", 3);
					startActivity(page3);
		            break;
	        	case 3:
	        		Intent page4 = new Intent(getApplicationContext(), tActivity2_3_4.class);
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

class MyItem1{
	MyItem1(int aIcon,String aName){
		Icon =aIcon;
		Name = aName;
	}
	
	int Icon;
	String Name;
}
class MyListAdapter1 extends BaseAdapter{
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem1> arSrc;
	int layout;
	
	public MyListAdapter1(Context context, int alayout, ArrayList<MyItem1> aarSrc){
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

/*package kr.co.imcc.app.uDiabetesNote;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class tActivity2_3 extends Activity{
	
    private ActionBar actionBar;
	ArrayList<MyItem1> arItem1;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		 actionBar = getActionBar();
			actionBar.setTitle("�������� ��õ �Ĵ�");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		arItem1 = new ArrayList<MyItem1>();
		MyItem1 mi;
		mi = new MyItem1(R.drawable.next_page,"��õ /���ؾ� �� ����");arItem1.add(mi);
		mi = new MyItem1(R.drawable.next_page,"�Ľ��� ���̵�");arItem1.add(mi);
		mi = new MyItem1(R.drawable.next_page,"��õ �Ĵ� ���α׷�");arItem1.add(mi);
		mi = new MyItem1(R.drawable.next_page,"�������� ��ó���");arItem1.add(mi);
		mi = new MyItem1(R.drawable.next_page,"�������� Q&A");arItem1.add(mi);

		MyListAdapter1 MyAdapter1 = new MyListAdapter1(this, R.layout.list_content,arItem1);
		
		ListView MyList;
		MyList=(ListView)findViewById(R.id.list_ac);
		MyList.setAdapter(MyAdapter1);
		
		MyList.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	
	        	switch(position){
	        	case 0:
	        		Intent page1 = new Intent(getApplicationContext(), tActivity2_3_1.class);
		            startActivity(page1);
		            break;
	        	case 1:
	        		Intent page2 = new Intent(getApplicationContext(), tActivity2_3_2.class);
		            startActivity(page2);
		            break;
	        	case 2:
	        		Intent page3 = new Intent(getApplicationContext(), tActivity2_3_3.class);
		            startActivity(page3);
		            break;
	        	case 3:
	        		Intent page4 = new Intent(getApplicationContext(), tActivity2_3_4.class);
		            startActivity(page4);
		            break;
	        	case 4:
	        		Intent page5 = new Intent(getApplicationContext(), tActivity2_3_5.class);
		            startActivity(page5);
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

class MyItem1{
	MyItem1(int aIcon,String aName){
		Icon =aIcon;
		Name = aName;
	}
	
	int Icon;
	String Name;
}
class MyListAdapter1 extends BaseAdapter{
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem1> arSrc;
	int layout;
	
	public MyListAdapter1(Context context, int alayout, ArrayList<MyItem1> aarSrc){
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


public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	switch(position)
	{
	case 0:
		Intent page1 = new Intent(this, tActivity2_3_1.class);
		startActivity(page1);
		break;
	} // TODO Auto-generated method stub
}
	AdapterView.OnItemClickListener mItemClickListener =
		new AdapterView.OnItemClickListener() {
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		switch(position)
		{
		case 0:
			Intent page1 = new Intent(this, tActivity2_3_1.class);
			startActivity(page1);
			break;
		}
	}
}
package kr.co.imcc.app.uDiabetesNote;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class tActivity2_3 extends Activity{
	
    private ActionBar actionBar;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		 actionBar = getActionBar();
			actionBar.setTitle("�������� ��õ �Ĵ�");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		
			ArrayList<String> arGeneral = new ArrayList<String>();
			arGeneral.add("�񸸵� ����");
			arGeneral.add("ü¡�� ���� ����");
			arGeneral.add("���� �񸸵� ����");
			arGeneral.add("�������");
			arGeneral.add("���� ���� ��ǥġ");
			
			ArrayAdapter<String> Adapter;
			Adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,arGeneral);
			
			
			ListView list = (ListView)findViewById(R.id.list_ac);
			list.setAdapter(Adapter);
			
			
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
	
}

package kr.co.imcc.app.uDiabetesNote;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class tActivity2_3 extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
			setContentView(R.layout.list_fragment);
			
			ArrayList<String> arGeneral = new ArrayList<String>();
			arGeneral.add("������");

			
			ArrayAdapter<String> Adapter;
			Adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,arGeneral);
			ListView list = (ListView)findViewById(R.id.list);
			list.setAdapter(Adapter);
			
		}
	}

package kr.co.imcc.app.uDiabetesNote;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Tab1 extends ListFragment {

		  @Override
		  public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    String[] values = new String[] { "�񸸵� ����","ü���� ���� ����","���� �񸸵� ����", "�������", "���� ���� ��ǥġ"};
		    
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		  }
		  
		  
		  @Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				return inflater.inflate(R.layout.list_fragment, container, false);
			}
		  
		  @Override
		  public void onListItemClick(ListView l, View v, int position, long id) {
		    // do something with the data
			  switch(position)
				{
				case 0:
					Intent menu1 = new Intent(getActivity(),tActivity1_1.class);
					startActivity(menu1);
					break;
					
				case 1:
					Intent menu2 = new Intent(getActivity(),tActivity1_2.class);
					startActivity(menu2);
					break;
					
				case 2:
					Intent menu3 = new Intent(getActivity(),tActivity1_3.class);
					startActivity(menu3);
					break;
					
				case 3:
					Intent menu4 = new Intent(getActivity(),tActivity1_4.class);
					startActivity(menu4);
					break;
					
				case 4:
					Intent menu5 = new Intent(getActivity(),tActivity1_5.class);
					startActivity(menu5);
					break;
						
				
				}
		  }
		} 


	

	public void onActivityCreate(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		String[] values = new String[]{"�񸸵� ����","ü���� ���� ����","���� �񸸵� ����", "�������", "���� ���� ��ǥġ"};
				
		
		
		ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myfriends);
//		ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.list_content, myfriends);

		setListAdapter(listAdapter);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		
	}


	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
		
		switch(position)
		{
		case 0:
			Intent menu1 = new Intent(getActivity(),tActivity1_1.class);
			startActivity(menu1);
			break;
			
		case 1:
			Intent menu2 = new Intent(getActivity(),tActivity1_2.class);
			startActivity(menu2);
			break;
			
		case 2:
			Intent menu3 = new Intent(getActivity(),tActivity1_3.class);
			startActivity(menu3);
			break;
			
		case 3:
			Intent menu4 = new Intent(getActivity(),tActivity1_4.class);
			startActivity(menu4);
			break;
			
		case 4:
			Intent menu5 = new Intent(getActivity(),tActivity1_5.class);
			startActivity(menu5);
			break;
				
		
		}
	}
}



package kr.co.imcc.app.uDiabetesNote;

import kr.co.imcc.app.uDiabetesNote.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class tActivity2_3 extends Activity {
    *//** Called when the activity is first created. *//*
	
    private ActionBar actionBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layer6_bloodpress);
        
        
        actionBar = getActionBar();
		actionBar.setTitle("�������� ��õ �Ĵ�");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		
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
		Button button3 = (Button) findViewById(R.id.button_layer6_5);
		Button button4 = (Button) findViewById(R.id.button_layer6_1);
		Button button5 = (Button) findViewById(R.id.button_layer6_2);
		//Button button6 = (Button) findViewById(R.id.button_layer6_6);

		button1.setOnClickListener(new OnClickListener() { // �索���̶�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_1.class);
				startActivity(intent);

			}
		});

		button2.setOnClickListener(new OnClickListener() { // �ǰ�������

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_2.class);
				startActivity(intent);

			}
		});
		
		button3.setOnClickListener(new OnClickListener() { // ü�߰����� ���� ���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_3.class);
				startActivity(intent);

			}
		});

		button4.setOnClickListener(new OnClickListener() { // �Ծ���Ұ�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_4.class);
				startActivity(intent);

			}
		});
		
		button5.setOnClickListener(new OnClickListener() { // �������ƾ��Ұ�

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_5.class);
				startActivity(intent);

			}
		});
	
		
		button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(tActivity2_3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity2_3_6.class);
				startActivity(intent);
				
			}			
		});
				
    }
}







*/