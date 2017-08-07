package kr.co.imcc.app.uDiabetesNote;


import java.util.*;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

public class Tab4 extends ListFragment {

    String[] values = new String[] {"비만식습관에 의한 식이추천", "스트레스에 의한  식이추천", "우울증에 의한  식이추천","Application 정보"};	    
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
					Intent menu1 = new Intent(getActivity(),tActivity4_1.class);
					startActivity(menu1);
					break;
					
				case 1:
					Intent menu2 = new Intent(getActivity(),tActivity4_2.class);
					startActivity(menu2);
					break;
				
				case 2:
					Intent menu3 = new Intent(getActivity(),tActivity4_3.class);
					startActivity(menu3);
					break;
				case 3:
					Intent menu4 = new Intent(getActivity(),tActivity4_4.class);
					startActivity(menu4);
					break;
				}
			}
	}

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

public class Tab4 extends ListFragment{

	private String myandroidversions[];
	public Tab4() {
		myandroidversions= new String[] {
				"�� �Ľ��� ����", "��Ʈ���� ����", "����� ����"
		};
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myandroidversions);
		setListAdapter(listAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}
	
public void onListItemClick(ListView list, View v, int position, long id) {
		
		switch(position)
		{
		case 0:
			Intent menu1 = new Intent(getActivity(),tActivity4_1.class);
			startActivity(menu1);
			break;
			
		case 1:
			Intent menu2 = new Intent(getActivity(),tActivity4_2.class);
			startActivity(menu2);
			break;
		
		case 2:
			Intent menu3 = new Intent(getActivity(),tActivity4_3.class);
			startActivity(menu3);
			break;
		}
	}
}
*/