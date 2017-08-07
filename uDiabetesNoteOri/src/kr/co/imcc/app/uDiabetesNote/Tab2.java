package kr.co.imcc.app.uDiabetesNote;


import java.util.*;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

public class Tab2 extends ListFragment {

    String[] values = new String[] {"음식중독 치료하기","건강 칼로리", "고지혈증 추천 식단", "당뇨병 추천 식단"
    		};	    
	    // Array of integers points to images stored in /res/drawable/
	    int[] image = new int[]{
	    		R.drawable.listnext_page,
	    		R.drawable.listnext_page,
	    		R.drawable.listnext_page,
	    		R.drawable.listnext_page
	    };
	    
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
			
			// Each row in the list stores country name, currency and flag
	        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
	        
	        for(int i=0;i<values.length;i++){
	        	HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put("text", values[i]);
	            hm.put("img", Integer.toString(image[i]) );            
	            aList.add(hm);        
	        }
	        
	        // Keys used in Hashmap
	        String[] text = {"text", "img"};
	        
	        // Ids of views in listview_layout
	        int[] img = {R.id.list_text, R.id.list_image};        
	        
	        // Instantiating an adapter to store each items
	        // R.layout.listview_layout defines the layout of each item
	        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.list_content, text, img);       
			
			setListAdapter(adapter);
			
//			inflater.inflate(R.layout.list_fragment, container, false);

			
			return super.onCreateView(inflater, container, savedInstanceState);		
		}
		
		 public void onListItemClick(ListView l, View v, int position, long id) {
			    // do something with the data
			 switch(position)
				{
				
				
				case 0:
					Intent menu0 = new Intent(getActivity(), tActivity2_1.class);
					startActivity(menu0);
					break;
					
				case 1:
					Intent menu1 = new Intent(getActivity(),tActivity2_2.class);
					startActivity(menu1);
					break;
					
				case 2:
					Intent menu2 = new Intent(getActivity(),tActivity2_3.class);
					startActivity(menu2);
					break;
					
				case 3:
					Intent menu3 = new Intent(getActivity(),tActivity2_4.class);
					startActivity(menu3);
					break;
				}
			}

	}

/*package kr.co.imcc.app.uDiabetesNote;


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
		} */


	/*

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
*/

/*package kr.co.imcc.app.uDiabetesNote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tab2 extends ListFragment {
	
	private String myfriends[];
	
	public Tab2() {
		
		myfriends = new String[] {
				"�����ߵ� ġ���ϱ�","���� �ǰ� Į�θ�", "�������� ��õ �Ĵ�", "�Ҿ� �索�� ��õ �Ĵ�"
		};
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myfriends);
		setListAdapter(listAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
		
		switch(position)
		{
		
		
		case 0:
			Intent menu0 = new Intent(getActivity(), tActivity2_1.class);
			startActivity(menu0);
			break;
			
		case 1:
			Intent menu1 = new Intent(getActivity(),tActivity2_2.class);
			startActivity(menu1);
			break;
			
		case 2:
			Intent menu2 = new Intent(getActivity(),tActivity2_3.class);
			startActivity(menu2);
			break;
			
		case 3:
			Intent menu3 = new Intent(getActivity(),tActivity2_4.class);
			startActivity(menu3);
			break;
			
				
		
		}
	}
}
*/