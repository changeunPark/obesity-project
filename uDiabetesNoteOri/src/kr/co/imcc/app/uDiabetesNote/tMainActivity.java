package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.view.*;
 
public class tMainActivity extends FragmentActivity implements
        ActionBar.TabListener {
		
	static boolean LOGIN_FLAG = false;
	private BroadcastReceiver receiver;

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
//    private String[] tabs = { "�񸸰���", "���̰���", "����", "�ڰ�����" };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);
 
        startActivity(new Intent(this,tIntro.class));
        getActionBar().setHomeButtonEnabled(false);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        
        actionBar = getActionBar();
		actionBar.setTitle("청소년 비만관리 서비스");
 
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        actionBar.setLogo(new ColorDrawable(Color.TRANSPARENT));

        viewPager.setAdapter(mAdapter);
        
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 

        // For each of the sections in the app, add a tab to the action bar.
        
        actionBar.addTab(actionBar.newTab().setText("비만상태").setTabListener(this));

//        actionBar.addTab(actionBar.newTab().setText("�񸸰���").setCustomView(R.layout.custom_tab1).setTabListener(this));
        //setCustomView
        actionBar.addTab(actionBar.newTab().setText("식이관리").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("비만예방").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("자가진단").setTabListener(this));
        
    /*    
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
 */
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
                switch(position)
                {
                case 0:
                    viewPager.setBackgroundResource(R.drawable.tab1_fb);
                    break;
                case 1:
                    viewPager.setBackgroundResource(R.drawable.tab2_fb);
                    break;
                case 2:
                    viewPager.setBackgroundResource(R.drawable.tab3_fb);
                    break;
                case 3:
                    viewPager.setBackgroundResource(R.drawable.tab4_fb);
                    break;
                
                }
                
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
        
    }
 
    private int getPosition(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    
        
        
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	
    	case R.id.info:
    		Intent page1 = new Intent(this,tActivityInfo.class);
    		startActivity(page1);
    		return true;
    /*	case R.id.explain:
    		Intent page2 = new Intent(this,tActivityexplain.class);
    		startActivity(page2);
    		return true;
    		*/
    	}
    	
		return false;
    	
    }
 
}


/*package kr.co.imcc.app.uDiabetesNote;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class tMainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	static boolean LOGIN_FLAG = false;
	private BroadcastReceiver receiver;

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    public void onCreate(Bundle savedInstanceState) {
    	

		startActivity(new Intent(this, Intro.class));
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);

        getActionBar().setHomeButtonEnabled(false);
        final ActionBar actionBar = getActionBar();
        actionBar.setLogo(new ColorDrawable(Color.TRANSPARENT));
    
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // For each of the sections in the app, add a tab to the action bar.
        actionBar.addTab(actionBar.newTab().setText("�񸸰���").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("�񸸿���").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("����").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("�ڰ�����").setTabListener(this));
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	
    	case R.id.info:
    		Intent page = new Intent(this,tActivityInfo.class);
    		startActivity(page);
    		return true;
    	}
    	
		return false;
    	
    }
    
	@Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        
    	if (tab.getPosition() == 0) {
    		Tab1 tab1 = new Tab1();
    		getSupportFragmentManager().beginTransaction().replace(R.id.container, tab1).commit();
    	} 
    	else if (tab.getPosition() == 1) {
    		Tab2 tab2 = new Tab2();
    		getSupportFragmentManager().beginTransaction().replace(R.id.container, tab2).commit();
		}
    	
    	else if (tab.getPosition()==2){
    		
    		Tab3 tab3 = new Tab3();
    		getSupportFragmentManager().beginTransaction().replace(R.id.container, tab3).commit();
    	}
    	
    	else
    	{
    		Tab4 tab4 = new Tab4();
    		getSupportFragmentManager().beginTransaction().replace(R.id.container, tab4).commit();
    	}
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            Bundle args = getArguments();
            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
            return textView;
        }
    }
}








package kr.co.imcc.app.uDiabetesNote;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
public class tMainActivity extends Activity {
	*//** Called when the activity is first created. *//*
	
	static boolean LOGIN_FLAG = false;
	private BroadcastReceiver receiver;

	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			startActivity(new Intent(this, Intro.class));
	//Ȩ��ư�� ����Ҽ��ְ� �ϱ�		
//			getActionBar().setHomeButtonEnabled(true);
		}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
//���� ���
		//		return super.onCreateOptionsMenu(menu);
	}


}		

TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
tabHost.setup();


Intent page1 = new Intent(this, Activity2.class);
TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(page1)
		.setIndicator("�񸸰���");
tabHost.addTab(spec1);

Intent page2 = new Intent(this, Activity6_menu.class);
TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(page2)
		.setIndicator("�񸸿���");
tabHost.addTab(spec2);

Intent page3 = new Intent(this, Activity5_edit_menu.class);
TabSpec spec3 = tabHost.newTabSpec("Tab3").setContent(page3)
		.setIndicator("����");
tabHost.addTab(spec3);

Intent page4 = new Intent(this, Activity4_menu.class);
TabSpec spec4 = tabHost.newTabSpec("Tab4").setContent(page4)
		.setIndicator("�ڰ�����");
tabHost.addTab(spec4);

tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=80;
tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=80;
tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=80;
tabHost.getTabWidget().getChildAt(3).getLayoutParams().height=80;

RelativeLayout.LayoutParams tvParams =new RelativeLayout.LayoutParams(
		ViewGroup.LayoutParams.MATCH_PARENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);

RelativeLayout rl1 =(RelativeLayout) tabHost.getTabWidget().getChildAt(0);
rl1.setGravity(Gravity.CENTER_VERTICAL);
TextView tv1 =(TextView) rl1.getChildAt(1);
tv1.setLayoutParams(tvParams);
tv1.setPadding(10,0,0,0);
tv1.setGravity(Gravity.CENTER);

RelativeLayout rl2 =(RelativeLayout) tabHost.getTabWidget().getChildAt(1);
rl2.setGravity(Gravity.CENTER_VERTICAL);
TextView tv2 =(TextView) rl2.getChildAt(1);
tv2.setLayoutParams(tvParams);
tv2.setPadding(10,0,0,0);
tv2.setGravity(Gravity.CENTER);

RelativeLayout rl3 =(RelativeLayout) tabHost.getTabWidget().getChildAt(2);
rl3.setGravity(Gravity.CENTER_VERTICAL);
TextView tv3 =(TextView) rl3.getChildAt(1);
tv3.setLayoutParams(tvParams);
tv3.setPadding(10,0,0,0);
tv3.setGravity(Gravity.CENTER);

RelativeLayout rl4 =(RelativeLayout) tabHost.getTabWidget().getChildAt(3);
rl4.setGravity(Gravity.CENTER_VERTICAL);
TextView tv4 =(TextView) rl4.getChildAt(1);
tv4.setLayoutParams(tvParams);
tv4.setPadding(10,0,0,0);
tv4.setGravity(Gravity.CENTER);		






*//** ��Ʈ��ũ ���� ǥ�� *//*
		
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		DBHelpepr dbHelpepr = new DBHelpepr(this);
		Button button1 = (Button) findViewById(R.id.button1); // �񸸰���
		Button button2 = (Button) findViewById(R.id.button2); // �� ����
		Button button3 = (Button) findViewById(R.id.button3); // � ���
		Button button4 = (Button) findViewById(R.id.button4); // �ڰ�����
		Button button5 = (Button) findViewById(R.id.button5); // ����� ����


		button1.setOnClickListener(new OnClickListener() { //�񸸰���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity2.class);
				startActivity(intent);

			}
		});
		
		button2.setOnClickListener(new OnClickListener() { //�� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity6_menu.class);
				startActivity(intent);

			}
		});
		
		button3.setOnClickListener(new OnClickListener() { //� ���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity5_edit_menu.class);
				startActivity(intent);

			}
		});
		
		button4.setOnClickListener(new OnClickListener() { //�ڰ�����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity4_menu.class);
				startActivity(intent);

			}
		});
		button5.setOnClickListener(new OnClickListener() { //����� ����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.tActivityInfo.class);
				startActivity(intent);

			}
		});

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();	
		unregisterReceiver(receiver);
		LOGIN_FLAG = false;
		System.exit(0);
	}
}
		
	/*	button1.setOnClickListener(new OnClickListener() { //�������

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity1_menu.class);
				startActivity(intent);
				
			}
		});
		
		
		
		button2.setOnClickListener(new OnClickListener() { //���а���

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.ActivityBloodPressure.class);
				startActivity(intent);

			}
		});
		
		
		
		button_child.setOnClickListener(new OnClickListener() { //��������, ��ȭ�索

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity_child.class);
				startActivity(intent);

			}
		});
		

		
		button4.setOnClickListener(new OnClickListener() { //�ɳ����� ���赵

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity3.class);
				startActivity(intent);

			}
		});
		
		
		
		button7.setOnClickListener(new OnClickListener() { //����

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.tActivityInfo.class);
				startActivity(intent);

			}
		});
		
		
		button8.setOnClickListener(new OnClickListener() { //about

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.ActivityInfo.class);
				startActivity(intent);
				
			}
		});

	}
	
		button8.setOnClickListener(new OnClickListener() { //about

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.ActivityInfo.class);
				startActivity(intent);
				
			}
		});
		
		
		button_add1.setOnClickListener(new OnClickListener() { //activity1

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity_add1.class);
				startActivity(intent);
				
			}
		});
		
		button_add2.setOnClickListener(new OnClickListener() { //activity2

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(tMainActivity.this,
						kr.co.imcc.app.uDiabetesNote.Activity_add2.class);
				startActivity(intent);
				
			}
		});
	*/