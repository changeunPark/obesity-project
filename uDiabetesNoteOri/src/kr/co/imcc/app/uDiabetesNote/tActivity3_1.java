//운동요법
package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class tActivity3_1 extends Activity 
{
	/** Called when the activity is first created. */
	
	private BroadcastReceiver receiver;
	
	public String[] sWeek = {"일","월","화","수","목","금","토"};
   
	static Button button_hosp;
	
   	Calendar calendar;
   	int dayCount;
   	static int[] iDate;	//넘겨줄 일자 intent date
   	static int[] sltDate  = new int[3];  
   	// 상수    
   	public static final int YEAR=0;
	public static final int MONTH=1;	
	public static final int I_SDAY=0;
	public static final int I_CDAY=1;
	public static final int I_TODAY=2;
	
	public static String SelectedDate="";
	public static String SelectedDate2="";
   
//	private LinearLayout layout, layout2;
//	private TextView CalendarView;
//	private TextView buttonPre, buttonNext;
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	public static cal_CalView_3_1 calendarView;
	
	private int deviceHeight;
	private int calBackColor;
	private int calTextColor;
	private int calGridLineColor1;
	private int calGridLineColor2;
	public static int calSelectColor;
	
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		actionBar = getActionBar();
 		actionBar.setTitle("운동량 기록");
 		getActionBar().setDisplayHomeAsUpEnabled(true); 
 		
 		
		DisplayMetrics displayMetrics = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int deviceWidth = displayMetrics.widthPixels;
		deviceHeight = displayMetrics.heightPixels;

		// 꼭 넣어 주어야 한다. 이렇게 해야 displayMetrics가 세팅이 된다.

		//getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
		//int dipWidth  = (int) (120  * displayMetrics.density);
		//int dipHeight = (int) (90 * displayMetrics.density);

		
		if(deviceHeight > 1200)
		{
			setContentView(R.layout.tactivity3_1);
		}
		else
		{
			setContentView(R.layout.layer5);
		}
		
		/** 네트워크 상태 표시 */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
		
		
		calendar = Calendar.getInstance();
		   
		// 변수에 값 셋팅
		if (sltDate[YEAR] == 0){
			sltDate[YEAR] = calendar.get(Calendar.YEAR);
			sltDate[MONTH] = calendar.get(Calendar.MONTH);
			sltDate[I_TODAY] = calendar.get(Calendar.DATE);
		}
		
		setDate();
			   
		final TextView tvCalendar = (TextView) findViewById(R.id.tv1_layer5);		
		tvCalendar.setText(sltDate[YEAR]+"년"+"  "+ (sltDate[MONTH]+1)+"월");
		
		ImageButton btPrev = (ImageButton) findViewById(R.id.button_layer5_1);		
		ImageButton btNext = (ImageButton) findViewById(R.id.button_layer5_2);
		
		btPrev.setOnClickListener(new OnClickListener() //
		{		
			@Override
			public void onClick(View arg0) 
			{		
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
				
				calenminus();
        		setDate();
        		tvCalendar.setText(""+sltDate[YEAR]+"년"+"  "+(sltDate[MONTH]+1)+"월");//텍스트 지정
//      		calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
        		calendarView.invalidate();
        		
        		arrayListExcercise.clear();
//        		arrayAdapterExcercise.notifyDataSetChanged();
//        		dataCheck();
        		cal_CalView_3_1.SELECT_FLAG = false;		
        		cal_CalView_3_1.start = 0;
			}
		});
		
		
		btNext.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{				
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
        		
				calenplus();
				setDate();
				tvCalendar.setText(""+sltDate[YEAR]+"년"+"  "+(sltDate[MONTH]+1)+"월");//텍스트 지정
//				calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
				calendarView.invalidate();
//				dataCheck();
				arrayListExcercise.clear();
        		//arrayAdapterExcercise.notifyDataSetChanged();
        		
        		cal_CalView_3_1.SELECT_FLAG = false;
        		cal_CalView_3_1.start = 0;
			}
		});
		
		
		LinearLayout linearCalendar = (LinearLayout) findViewById(R.id.linear_layer5_2222);
		
		calendarView = new cal_CalView_3_1(this);
		
		// 변수 set
		calBackColor = getResources().getColor(R.color.cal_background);
		calTextColor = getResources().getColor(R.color.cal_foreground);
		calGridLineColor1 = getResources().getColor(R.color.cal_hilite);
		calGridLineColor2 = getResources().getColor(R.color.cal_light);
		calSelectColor = getResources().getColor(R.color.cal_selected);
		
		if(deviceHeight > 1800)
		{
			calendarView.setheight(150);
			calendarView.setpenYValue(15);
		}
		else if(deviceHeight > 1200)
		{
			calendarView.setheight(100);
			calendarView.setpenYValue(15);
		}
		else
		{
			calendarView.setheight(50);
			calendarView.setpenYValue(0);
		}
	
		calendarView.setTextSize(8);
		calendarView.setBackColor(calBackColor);
		calendarView.settextColor(calTextColor);
		calendarView.setGridLineColor1(calGridLineColor1);
		calendarView.setGridLineColor2(calGridLineColor2);
		calendarView.setSelectColor(calSelectColor);	
//		calendarView.invalidate();
		
		linearCalendar.addView(calendarView);
		
		/*Button button_back = (Button) findViewById(R.id.button_layer5_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});*/
				
		//입력버튼
		ImageButton button_input = (ImageButton) findViewById(R.id.graph_input);
		button_input.setOnClickListener(new OnClickListener() 
		{ 
			@Override
			public void onClick(View v) 
			{		
				if(SelectedDate.equals("") || cal_CalView_3_1.SELECT_FLAG==false)
				{
					Toast.makeText(getApplicationContext(), "날짜를 선택해 주세요.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
//					showInputDialog();						
					Intent i = new Intent(tActivity3_1.this, tActivity3_1_input.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

					i.putExtra("SelectedDate2", SelectedDate2);
					// i.putExtra("MESSAGE", message);
				
					startActivity(i);
				}				
			}
		});
		
		
		
		ImageButton button_list = (ImageButton) findViewById(R.id.graph_list);

		button_list.setOnClickListener(new OnClickListener() 
		{ // list
			@Override
			public void onClick(View v) 
			{				
				showListDialog();
			}
		});	
		showExcerciseList("");	
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
	
	public void onBackPressed() 
	{
		super.onBackPressed();
		SelectedDate="";
		SelectedDate2="";
		//termflag = 0;
		cal_CalView_3_1.start = 0;
		sltDate[YEAR] = 0;	//엑티비티 재시작시 스태틱 날짜값 초기화를 위해
	};
		
	public String makeDate(int year, int month, int day)
	{	
		String strMonth="";
		String strDay="";
		
		if(month + 1 <10) 
		{
			strMonth = "0"+(month + 1);
		}
		else
		{
			strMonth = ""+(month + 1);
		}
				
		if(day<10) 
		{
			strDay = "0"+day;
		}
		else
		{
			strDay = ""+day;
		}
		
		return year+"/"+strMonth+"/"+strDay;
	}
	
	
	Dialog listDialog;
	public void showListDialog() {
		listDialog = new Dialog(this);
		listDialog.setTitle("전체 리스트");
		listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		listDialog.setContentView(R.layout.tactivity3_1_list);
		/*if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.tactivity3_1_list);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog_5);
		}*/
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showExcerciseListTotal("total");
	
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_5);
		
		btClose.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{				
				listDialog.dismiss();
			}
		});	
		listDialog.show();
	}
	
	public void showDayListDialog() {
		listDialog = new Dialog(this);
//		listDialog.setTitle("일일 리스트");
		listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.tactivity3_1_list);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog_5);
		}
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showExcerciseListTotal(SelectedDate2);
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_5);
		
		btClose.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{				
				listDialog.dismiss();
			}
		});	
		listDialog.show();
	}
		
	ListView lvExcercise;
	static ArrayAdapterExcercise arrayAdapterExcercise;
	static ArrayList<HashMap<String, Object>> arrayListExcercise = new ArrayList<HashMap<String, Object>>();
	String sqlExcerciseList = "";
	Cursor cursorExcerciseList;
	Cursor cursorExcerciseBase;
	String[] itemValue = new String[10];
	
	//달력에서 넘겨준 선택한 날짜정보를 파라미터 받아서 출력한다
	public void showExcerciseList(String selectedDate)
	{			
		for(int i = 0; i < itemValue.length; i++)
		{
			itemValue[i] = "";
		}
		
		//lvExcercise = (ListView) findViewById(R.id.listview_layer5_1);		 
		arrayListExcercise.clear();
		//날짜를 key로 조회
		sqlExcerciseList = "select * from EXCERCISE where date='"+selectedDate+"' order by date, time asc";
		cursorExcerciseList = sqLiteDatabase.rawQuery(sqlExcerciseList, null);
				
		if (cursorExcerciseList.getCount() != 0) 
		{				
			cursorExcerciseList.moveToFirst();			
			do //조회된 데이터 각 행별로 어레이리스트에 저장 
			{
				//데이터가 조회된 숫자만큼 루프를 돌며 배열에 저장 
				for (int i = 0; i < cursorExcerciseList.getColumnCount(); i++) 
				{				
					if(cursorExcerciseList.getString(i) == null)
					{
						itemValue[i] = "";
					}
					else
					{
						itemValue[i] = cursorExcerciseList.getString(i);	
					}					
				//Log.d("tttttFFtt", itemValue[i]);
				}
				
				HashMap<String, Object> item = new HashMap<String, Object>();
				
				item.put("_id", itemValue[0].toString().trim());
				item.put("date", itemValue[1].toString().trim());
				item.put("time", itemValue[2].toString().trim());
				item.put("kind", itemValue[3].toString().trim());
				item.put("extime", itemValue[4].toString().trim());
				item.put("weight", itemValue[5].toString().trim());
				item.put("memo", itemValue[6].toString().trim());
				item.put("calorie", itemValue[7].toString().trim());

     			arrayListExcercise.add(item);
			} 
			while (cursorExcerciseList.moveToNext());				
			
			String sqlStandardValue = "select * from STANDARDVALUE";
			cursorExcerciseBase = sqLiteDatabase.rawQuery(sqlStandardValue, null);
			
			cursorExcerciseBase.moveToFirst();
			String standardCalorie = cursorExcerciseBase.getString(3);
			String standardTime = cursorExcerciseBase.getString(4);
			
			int caloriesum = 0, timesum = 0, calorie = 0, time = 0;
			
			for(int i = 0; i < arrayListExcercise.size();  i++)
			{
				HashMap<String, Object> item3 = arrayListExcercise.get(i);
				String id = item3.get("_id").toString();
				
				if(id.equals("-1"))
				{
					break;
				}
				else
				{
					calorie = Integer.parseInt(item3.get("calorie").toString());
					caloriesum += calorie;				
					time = Integer.parseInt(item3.get("extime").toString());
					timesum += time;			
				}					
			}
			
			TextView tvcalorie = (TextView) findViewById(R.id.ex_calorie);
			TextView tvstandardcalorie = (TextView) findViewById(R.id.ex_standardcalorie);
			TextView tvtime = (TextView) findViewById(R.id.ex_time);
			TextView tvsatandardtime = (TextView) findViewById(R.id.ex_satandardtime);
			//TextView tvExCal = (TextView) findViewById(R.id.ex_cal);
			
			HashMap<String, Object> item = arrayListExcercise.get(0);
			
			//String text = item.get("time").toString().substring(0, 2)+"시 "+item.get("time").toString().substring(3, 5)+"분";
						

			tvcalorie.setText(Integer.toString(caloriesum));		
	
			tvstandardcalorie.setText(" / " + standardCalorie + "Kcal");
	
			tvtime.setText(Integer.toString(timesum));		

			tvsatandardtime.setText(" / " + standardTime + "분");			
			
//			ImageView imgViewHand = (ImageView)findViewById(R.id.imageView1);
			
			BitmapDrawable imgGood = (BitmapDrawable)getResources().getDrawable(R.drawable.good_mark);
			BitmapDrawable imgBad = (BitmapDrawable)getResources().getDrawable(R.drawable.bad_mark);
			
			/*if (caloriesum >= Integer.parseInt(standardCalorie) && timesum >= Integer.parseInt(standardTime)  ) 
			{
				imgViewHand.setImageDrawable(imgGood);
			}
			else
			{
				imgViewHand.setImageDrawable(imgBad);
			}*/
			
			TableLayout dayTotalArea = (TableLayout) findViewById(R.id.tableLayout99911_ex);
			
			dayTotalArea.setVisibility(dayTotalArea.VISIBLE);
			
			dayTotalArea.setOnClickListener(new OnClickListener() //
			{		
				@Override
				public void onClick(View arg0) 
				{		
					showDayListDialog();
				}
			});
		}
		else
		{
			TableLayout dayTotalArea = (TableLayout) findViewById(R.id.tableLayout99911_ex);
			dayTotalArea.setVisibility(dayTotalArea.GONE);
		}
		
		cursorExcerciseList.close();
				
		//어레이아답터 클래스 생성
		/*arrayAdapterExcercise = new ArrayAdapterExcercise(tActivity3_1.this, R.layout.excerciselist, arrayListExcercise);
		lvExcercise.setAdapter(arrayAdapterExcercise);
		
		//리스트아이템 선택
		lvExcercise.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
			{			
//				showInputDialog1(position);
//				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(tActivity3_1.this, Activity5_2.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

				i.putExtra("SelectedDate2", SelectedDate2);
				i.putExtra("position", position+"");
			
				startActivity(i);				
			}
		});	*/	
	}
	
	//운동기록
	public class ArrayAdapterExcercise extends ArrayAdapter
	{
		ArrayList<HashMap<String, Object>> arraylist;
		
		public ArrayAdapterExcercise(Context context, int textViewResourceId, List objects) 
		{
			super(context, textViewResourceId, objects);
			arraylist = (ArrayList) objects;
		}
		
		@Override
		public void notifyDataSetChanged() 
		{
			super.notifyDataSetChanged();
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) 
		{
			View view = convertView;
//			View view = null;
			
			if (view == null)
			{
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.excerciselist, null);	
			}
						
//			TextView tvBSDate = (TextView) view.findViewById(R.id.bloodsugar_date);
			TextView tvTime = (TextView) view.findViewById(R.id.ex_time);
			TextView tvExKind = (TextView) view.findViewById(R.id.ex_kind);
			TextView tvExTime = (TextView) view.findViewById(R.id.excercise_time);
			TextView tvExMemo = (TextView) view.findViewById(R.id.ex_memo);
			TextView tvExCal = (TextView) view.findViewById(R.id.ex_cal);
					
			HashMap<String, Object> item = arraylist.get(position);
				
			String text = item.get("time").toString().substring(0, 2)+"시 "+item.get("time").toString().substring(3, 5)+"분";
			tvTime.setText(text);
					
			text = item.get("kind").toString();
			tvExKind.setText(text);		
	
			text = item.get("extime").toString();
			tvExTime.setText("운동시간: "+text+"분");
	
			text = item.get("memo").toString();
			tvExMemo.setText("메모: "+text);
			
			text = item.get("calorie").toString();
			tvExCal.setText("칼로리: "+text+"kcal");			
				
			return view;
		}
	}
	
	ListView lvExcerciseTotal;
	static ArrayAdapterExcerciseTotal arrayAdapterExcerciseTotal;
	static ArrayList<HashMap<String, Object>> arrayListExcerciseTotal = new ArrayList<HashMap<String, Object>>();
	String sqlExcerciseListTotal = "";
	Cursor cursorExcerciseListTotal;
	String[] itemValueTotal = new String[10];
	
	
	//토탈운동기록
	public void showExcerciseListTotal(String selectedDate)
	{
		String tempDate="";
		
		for(int i=0; i<itemValueTotal.length;i++)
		{
			itemValueTotal[i] = "";
		}
		
		lvExcerciseTotal = (ListView) listDialog.findViewById(R.id.listview_listdialog_5);		 
		arrayListExcerciseTotal.clear();
		
		if(selectedDate == "total")
		{
			sqlExcerciseListTotal = "select * from EXCERCISE order by date desc, time asc";	
		}
		else
		{
			sqlExcerciseListTotal = "select * from EXCERCISE where date='"+selectedDate+"' order by date, time asc";
		}

		cursorExcerciseListTotal = sqLiteDatabase.rawQuery(sqlExcerciseListTotal, null);
		
		if (cursorExcerciseListTotal.getCount() != 0) {
			
			cursorExcerciseListTotal.moveToFirst();
			
			do 
			{
				for (int i = 0; i < cursorExcerciseListTotal.getColumnCount(); i++) 
				{				
					if(cursorExcerciseListTotal.getString(i) == null)
					{
						itemValueTotal[i] = "";
					}
					else
					{
						itemValueTotal[i] = cursorExcerciseListTotal.getString(i);	
					}					
//					Log.d("tttttFFtt", itemValue[i]);
				}

				HashMap<String, Object> item = new HashMap<String, Object>();
				String date = itemValueTotal[1].toString().trim();
				
				if(!date.equals(tempDate))
				{	
					tempDate = date;
					
					HashMap<String, Object> item1 = new HashMap<String, Object>();
					
					item1.put("_id", "-1");
					item1.put("date", "");
					item1.put("time", "");
					item1.put("kind", "");
					item1.put("extime", "");
					item1.put("weight", "");
					item1.put("memo", "");
					item1.put("calorie", "");
					
					arrayListExcerciseTotal.add(item1);					
				}

				item.put("_id", itemValueTotal[0].toString().trim());
				item.put("date", itemValueTotal[1].toString().trim());
				item.put("time", itemValueTotal[2].toString().trim());
				item.put("kind", itemValueTotal[3].toString().trim());
				item.put("extime", itemValueTotal[4].toString().trim());
				item.put("weight", itemValueTotal[5].toString().trim());
				item.put("memo", itemValueTotal[6].toString().trim());
				item.put("calorie", itemValueTotal[7].toString().trim());

				arrayListExcerciseTotal.add(item);
			}
			while (cursorExcerciseListTotal.moveToNext());			
		}
		
		cursorExcerciseListTotal.close();
		
		arrayAdapterExcerciseTotal = new ArrayAdapterExcerciseTotal(tActivity3_1.this, R.layout.excerciselisttotal, arrayListExcerciseTotal);
		
		//arrayAdapterExcercise = new ArrayAdapterExcercise(tActivity3_1.this, R.layout.excerciselisttotal, arrayListExcercise);
		
		lvExcerciseTotal.setAdapter(arrayAdapterExcerciseTotal);				
			
		//리스트아이템 선택
		lvExcerciseTotal.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
			{			
//				showInputDialog1(position);
//				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
				HashMap<String, Object> item = arrayListExcerciseTotal.get(position);
				
				String calorie = item.get("calorie").toString();
				
				if(!calorie.equals(""))
				{		
					Intent i = new Intent(tActivity3_1.this, tActivity3_1_item.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);토탈혈당기록ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴ
					
					i.putExtra("SelectedDate2", SelectedDate2);
					i.putExtra("position", position+"");
				
					startActivity(i);	
				}
			}
		}); 		
	}
		
	//토탈운동기록
	public class ArrayAdapterExcerciseTotal extends ArrayAdapter
	{
		ArrayList<HashMap<String, Object>> arraylist;
		public ArrayAdapterExcerciseTotal(Context context, int textViewResourceId, List objects) 
		{
			super(context, textViewResourceId, objects);
			arraylist = (ArrayList) objects;
		}
		
		@Override
		public void notifyDataSetChanged() 
		{
			super.notifyDataSetChanged();
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
//			View view= convertView;
			View view = null;
			
			HashMap<String, Object> item = arraylist.get(position);
			String id = item.get("_id").toString();
			
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						
			if(!id.equals("-1"))
			{		
				if (view == null) 
				{
					view = layoutInflater.inflate(R.layout.excerciselisttotal, parent, false);
				}
				
				TextView tvTime = (TextView) view.findViewById(R.id.ex_time_total);
				TextView tvExKind = (TextView) view.findViewById(R.id.ex_kind_total);
				TextView tvExTime = (TextView) view.findViewById(R.id.excercise_time_total);
				TextView tvExMemo = (TextView) view.findViewById(R.id.ex_memo_total);
				TextView tvExCal = (TextView) view.findViewById(R.id.ex_cal_total);

//				HashMap<String, Object> item = arraylist.get(position);

//				if (tvBSDate != null) {
//					String text = item.get("date").toString().substring(0, 4)+"년 "+item.get("date").toString().substring(5, 7)+"월 "+item.get("date").toString().substring(8, 10)+"일";
//					tvBSDate.setText(text);
//				}

				if (tvTime != null) {
					String text = item.get("time").toString().substring(0, 2)+"시 "+item.get("time").toString().substring(3, 5)+"분";
					tvTime.setText(text);
				}

				if (tvExKind != null) {
					String text = item.get("kind").toString();
					tvExKind.setText(text);
				}
				
				if (tvExTime != null) {
					String text = item.get("extime").toString();
					tvExTime.setText("운동시간 : "+text+"분");
				}
				
				if (tvExMemo != null) {
					String text = item.get("memo").toString();
					tvExMemo.setText("메모 : "+text);
				}

				if (tvExCal != null) {
					String text = item.get("calorie").toString();
					tvExCal.setText("칼로리 : "+text+"kcal");
				}
			}
			else
			{			
				int sum = 0, value = 0;
				
				for(int i = position+1; i<arraylist.size(); i++)
				{
					HashMap<String, Object> item3 = arraylist.get(i);
					String id3 = item3.get("_id").toString();
					
					if(id3.equals("-1"))
					{
						break;
					}
					else
					{
						value = Integer.parseInt(item3.get("calorie").toString());
						sum += value;									
					}					
				}
				
				if (view == null) 
				{
					if(sum != 0)
					{					
	//					LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						view = layoutInflater.inflate(R.layout.excerciselisttotal_title, parent, false);
						
						HashMap<String, Object> item1 = arraylist.get(position+1);
						
						TextView tvExDateTitle = (TextView) view.findViewById(R.id.total_excercise_date_title);
						
						if (tvExDateTitle != null) 
						{
							String text = item1.get("date").toString().substring(0, 4)+"년 "+item1.get("date").toString().substring(5, 7)+"월 "+item1.get("date").toString().substring(8, 10)+"일";
							tvExDateTitle.setText(text);
						}
						
						TextView tvExSumValue = (TextView) view.findViewById(R.id.total_excercise_sum_value);
						
						if (tvExSumValue != null) 
						{
							tvExSumValue.setText("합계: "+ sum+"kcal");
						}
					}
					else
					{
						view = layoutInflater.inflate(R.layout.empty, parent, false);
					}
				}
			}		
			return view;
		}
		
		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arraylist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arraylist.get(position);
		}

		@Override
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
	}
	
	public void calenplus() {
		sltDate[MONTH]++;
		if ( sltDate[MONTH] == 12) {
			sltDate[MONTH] = 0;
			sltDate[YEAR]++;
		}
	}
	
	public void calenminus() {
		sltDate[MONTH]--;
		if ( sltDate[MONTH] == -1) {
			sltDate[MONTH] = 11;
			sltDate[YEAR]--;
		}
	}
   
	public void setDate(){
		/*
		if( sltDate[MONTH] == 12)
			sltDate[MONTH] = 0;
		if ( sltDate[MONTH] == -1)
			sltDate[MONTH] = 11;
		 */
		calendar.set(sltDate[YEAR],sltDate[MONTH],1);
		//calendar.set(sltDate[YEAR],sltDate[MONTH],1);		
		iDate = new int[3]; // 0:시작데이, 1:표시되어야 할 일수 , 2 현재 데이, 3,총 토탈 데이
	   
		dayCount=0;
		for( int i =1; i <= calendar.getActualMaximum(Calendar.DATE); i++)
			dayCount++;
		
		
		iDate[I_CDAY] = dayCount;
	   
		iDate[I_SDAY] = calendar.get( Calendar.DAY_OF_WEEK ) -1; // 시작되는 요일을 가져온다.
    	iDate[I_TODAY] = sltDate[I_TODAY];
    	//showToast(iDate[I_TODAY] ,""+ calendar.get(Calendar.DATE));
    	//showToast(sltDate[YEAR],""+iDate[I_SDAY]);
	}
		
	protected void showToast(int x, String  y) {
		Toast toast = Toast.makeText(this,x +"    "+ y, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private void setLayout(LinearLayout layout , int a , LinearLayout.LayoutParams params) {
		layout.setBackgroundColor(getResources().getColor(R.color.cal_background));
		layout.setOrientation(a);//정렬방향 지정
		layout.setGravity(Gravity.CENTER);
	}
	
	private void setTextView( TextView view, String text, int color, int gravity, LinearLayout.LayoutParams params) {
		view.setText(""+text);//텍스트 지정
		view.setTextColor(color);//텍스트 색상 지정
		view.setBackgroundColor(getResources().getColor(R.color.cal_background));
		view.setGravity(gravity);
		view.setLayoutParams(params);	
	}	
	
	public int getDateYear() {
		return sltDate[YEAR];
	}
	
	public int getDateMonth() {
		return sltDate[MONTH]+1;
	}
	
	public int getDateWeek( int y, int m, int d){
		int temp = 0;
		Calendar calTemp = Calendar.getInstance();
		calTemp.set(y,m,d);
		// 일 월 화 수 목 금 토
		temp=calTemp.get( Calendar.DAY_OF_WEEK )-1;
		
		return temp;
	}
	
	public void startIntent( int year, int mon, int day, int week ) {
		Intent temp = null;		   
		   
	}
	
	public String spaceFull(int a) {
		String temp =""+a;
		if(temp.length() < 2)
			temp = "0" + a;
		return temp; 
	}
		
	public int getDaysInMonth(int year, int month) { 
	  Calendar calendar = Calendar.getInstance();	  
	  int date = 1 ; 
	  calendar.set(year, month, date);
	   
	  return calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
	} 
	
	@Override
	protected void onResume() {
		super.onResume();
		showExcerciseList(SelectedDate2);
//		SelectedDate="";
//		SelectedDate2="";
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}	
}