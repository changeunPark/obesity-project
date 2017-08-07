//혈압관리

package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityBloodPressure extends Activity {
	/** Called when the activity is first created. */
	
	private BroadcastReceiver receiver;
	
	public String[] sWeek = {"일","월","화","수","목","금","토"};
   
	Button button_hosp;
	
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
	
	public static int termflag = 0;
	private Boolean startflag = true; 
	public static int graphCusorPosition = 0;
	
	public static String SelectedDate="";
	public static String SelectedDate2="";
   
//	private LinearLayout layout, layout2;
//	private TextView CalendarView;
//	private TextView buttonPre, buttonNext;
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	public static cal_CalView_BPD calendarView;
	
	private int calBackColor;
	private int calTextColor;
	private int calGridLineColor1;
	private int calGridLineColor2;
	private int deviceHeight;
	public static int calSelectColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
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
			setContentView(R.layout.layerbloodpressurebig);
		}
		else
		{
			setContentView(R.layout.layerbloodpressure);
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

		final TextView tvCalendar = (TextView) findViewById(R.id.tv1);		
		tvCalendar.setText(sltDate[YEAR]+"년"+"  "+ (sltDate[MONTH]+1)+"월");
		
		Button btPrev = (Button) findViewById(R.id.button_layer1_1);		
		Button btNext = (Button) findViewById(R.id.button_layer1_2);
		
		btPrev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
				
				calenminus();
        		setDate();
        		tvCalendar.setText(""+sltDate[YEAR]+"년"+"  "+(sltDate[MONTH]+1)+"월");//텍스트 지정
//        		calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
        		calendarView.invalidate();
        		
        		arrayListBloodSugar.clear();
        		//arrayAdapterBloodSugar.notifyDataSetChanged();
//        		dataCheck();
        		cal_CalView_BPD.SELECT_FLAG = false;
        		cal_CalView_BPD.start = 0;
        		
				showBloodSugarList(SelectedDate2);
			}
		});
		
		
		btNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
        		
				calenplus();
				setDate();
				tvCalendar.setText(""+sltDate[YEAR]+"년"+"  "+(sltDate[MONTH]+1)+"월");//텍스트 지정
//				calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
				calendarView.invalidate();
//				dataCheck();
				arrayListBloodSugar.clear();
        		//arrayAdapterBloodSugar.notifyDataSetChanged();
        		
        		cal_CalView_BPD.SELECT_FLAG = false;
        		cal_CalView_BPD.start = 0;
        		
				showBloodSugarList(SelectedDate2);
			}
		});
		
		
		LinearLayout linearCalendar = (LinearLayout) findViewById(R.id.linear_layer1_2222);
		
		calendarView = new cal_CalView_BPD(this);
		
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
		

		Button button_back = (Button) findViewById(R.id.button_layer1_back);

		button_back.setOnClickListener(new OnClickListener() { // 뒤로버튼

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		
		// 입력버튼
		Button button_input = (Button) findViewById(R.id.button_layer1_3);

		button_input.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				if(!ActivityLogin.LOGIN_FLAG)
				{
					Toast.makeText(ActivityBloodPressure.this, "PHR연동후 입력 할 수 있습니다." ,	Toast.LENGTH_SHORT).show();
				}
				else
				{			
					if(SelectedDate.equals("") || cal_CalView_BPD.SELECT_FLAG==false)
					{
						Toast.makeText(getApplicationContext(), "날짜를 선택해 주세요.",
								Toast.LENGTH_SHORT).show();
					}
					else
					{
	//					showInputDialog();	
						
						Intent i = new Intent(ActivityBloodPressure.this, ActivityBloodPressure_input.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	
						i.putExtra("SelectedDate2", SelectedDate2);
						// i.putExtra("MESSAGE", message);
					
						startActivity(i);
					}		
				}
			}
		});
		
		
		button_hosp = (Button) findViewById(R.id.button_layer1_hosp);//병원연동 버튼  할당

		button_hosp.setOnClickListener(new OnClickListener() { // 병원연동
			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true)
				{			
					if (ActivityLogin.LOGIN_FLAG) {
						
						//createThreadAndDialog();	
						hospitalConnectOn();						
					} 
					else 
					{
						Intent i = new Intent(
								ActivityBloodPressure.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);//로그인 엑티비티 호출
					}		
					calendarView.invalidate();//달력갱신
				}
				else
				{
					Toast.makeText(ActivityBloodPressure.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		
		Button button_graph = (Button) findViewById(R.id.button_layer1_4);

		button_graph.setOnClickListener(new OnClickListener() { // graph

			@Override
			public void onClick(View v) {
				
				showGraphDialog();					
				if(startflag)
				{		
					hScrollView.post(new Runnable()
					{
			            public void run() 
			            {
			            	hScrollView.scrollTo(graphCusorPosition-50, 0);	//스크롤뷰 커서 이동	
			            }
					});
				}
			}
		});
		
		Button button_list = (Button) findViewById(R.id.button_layer1_5);

		button_list.setOnClickListener(new OnClickListener() { // list
			@Override
			public void onClick(View v) {
				
				showListDialog();
			}
		});
		
		
		showBloodSugarList("");
		
		hospitalConnectOnLoad();
		
//		Toast.makeText(Activity1.this, "dd" + ULNetworkReceiver.NETWORK_LIVE ,	Toast.LENGTH_SHORT).show();
	}
	
	
	public void hospitalConnectOn(){		
		
		createThreadAndDialog();
		
	}
	
	public void hospitalConnectOnLoad(){
		if(ULNetworkReceiver.NETWORK_LIVE==true){
			
			if (ActivityLogin.LOGIN_FLAG) {
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
								
				hospitalConnectOn();
				calendarView.invalidate();
			} 
			
		}else{
			Toast.makeText(ActivityBloodPressure.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	
	public void onBackPressed() {
		super.onBackPressed();
		SelectedDate="";
		SelectedDate2="";
		cal_CalView_BPD.start = 0;
		sltDate[YEAR] = 0;	
		termflag =0;
	};
	
	
	Dialog graphDialog;
	String sqlBloodSugarListGraph = "";
	Cursor cursorBloodSugarListGraph;
	String[] itemValueGraph = new String[10];
	
	String sqlBloodSugarMaxMin = "";
	Cursor cursorBloodSugarMaxMin;
	
	static GraphViewBloodpress GraphView ;
	Button btnDateStart;
	Button btnDateEnd;
	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	HorizontalScrollView hScrollView;
	
	public void showGraphDialog() 
	{
		graphDialog = new Dialog(this);
		graphDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		//graphDialog.setTitle("혈압 그래프(3개월)");
		graphDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(deviceHeight > 1200)
		{
			graphDialog.setContentView(R.layout.tactivity3_3_graph);
		}
		else
		{
			graphDialog.setContentView(R.layout.graphdialog);
		}
		
		Button btClose = (Button) graphDialog.findViewById(R.id.button_3_2_graphdialog_result);
		Button btnDateSearch = (Button) graphDialog.findViewById(R.id.btn_search);
		//tvDateStart = (TextView) graphDialog.findViewById(R.id.textview_graph_2);
		btnDateStart = (Button) graphDialog.findViewById(R.id.button1);
		btnDateEnd = (Button) graphDialog.findViewById(R.id.button2);
		TextView title = (TextView) graphDialog.findViewById(R.id.textview_graph_1); 
		title.setText("혈압 그래프");
		
		hScrollView = (HorizontalScrollView) graphDialog.findViewById(R.id.horizontalScrollView1_graph);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(graphDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		graphDialog.getWindow().setAttributes(lp); 
		
				
		btClose.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{				
				graphDialog.dismiss();
			}
		});
		

		if(termflag == 0)
		{
			startCalendar.add(calendar.MONTH, -3);	//현재날짜-3개월					
			//sltDate[MONTH] = calendar.get(Calendar.MONTH);
		}
		
		endCalendar.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
		endCalendar.add(calendar.MONTH, 3);
			
		String startDate = makeDate(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
		String endDate = makeDate(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
		
		termflag = 1;
		
		//시작일 표시
		btnDateStart.setText(startDate);
		btnDateEnd.setText(endDate);
		
		drawGraph(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)
				,endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
		
		
		//시작날짜선택 다이얼로그
		btnDateStart.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {			
				new DatePickerDialog(ActivityBloodPressure.this, startDateSetListener, startCalendar.get(Calendar.YEAR), 
						startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)).show();		
			}
		});	
	
		
		//종료날짜선택 다이얼로그
		btnDateEnd.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {						
				new DatePickerDialog(ActivityBloodPressure.this, endDateSetListener, endCalendar.get(Calendar.YEAR), 
						endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH)).show();		
			}
		});			
		
		
		//검색버튼
		btnDateSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String strStart = makeDate(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
				String strEnd = makeDate(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
				
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				 
				 Date End = null;
				 Date Start = null;
				try {
					End = formatter.parse(strEnd.replace("/", ""));
					Start = formatter.parse(strStart.replace("/", ""));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								 				 
				long test = (End.getTime() - Start.getTime()) / (1000 * 60 * 60 * 24);
				
				if(test < 0)
				{
					new AlertDialog.Builder(ActivityBloodPressure.this)					
					.setTitle("알림")
					.setMessage("검색범위가 잘못 지정되었습니다")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}
				else if(test > 94)
				{
					new AlertDialog.Builder(ActivityBloodPressure.this)					
					.setTitle("알림")
					.setMessage("검색범위는 최대 3개월까지 지정 할 수 있습니다")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}
				else
				{
					drawGraph(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH),
						endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
					Activity1_1_hb.SelectedDate2 ="";		
					startflag = true;				
					hScrollView.scrollTo(graphCusorPosition - 50, 0);//첫데이터 위치로 커서이동
					Toast.makeText(ActivityBloodPressure.this, "검색 되었습니다", Toast.LENGTH_SHORT).show();
				}
			}
		});				
		graphDialog.show();
	}

	DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() 
	{	
		@Override	
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
		{		
			startCalendar.set(year, monthOfYear, dayOfMonth);
			String startDate = makeDate(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));				
			btnDateStart.setText(startDate);
		}			
	}; 
	
	DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() 
	{	
		@Override	
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) 
		{					
			endCalendar.set(year, monthOfYear, dayOfMonth);
			String endDate = makeDate(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));		
			btnDateEnd.setText(endDate);
		}			
	}; 
		
	public void drawGraph(int y, int m, int d, int y2, int m2, int d2){
		String strStartDate = makeDate(y, m, d);
		
		Calendar cal2 = Calendar.getInstance();//시작날
		cal2.set(y, m, d);
		
		Calendar cal1 = Calendar.getInstance();//마지막날
		cal1.set(y2, m2, d2);
			
		String strEndDate = makeDate(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
		
		ArrayList<String> xlabel = new ArrayList<String>();
		
		while(true){
			
			String date = makeDate(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH));
			xlabel.add(date);
			
			cal2.add(cal2.DAY_OF_MONTH, 1);
			
			if(date.equals(strEndDate)) break;
			
			Log.d("date: ", date);		
		}
		
		ArrayList<String> xvalue = new ArrayList<String>();
		ArrayList<String> yvalue = new ArrayList<String>();
		ArrayList<String> zvalue = new ArrayList<String>();
		
		sqlBloodSugarListGraph = "select date, time, bpdiastolic, bpsystolic from BlOODPRESSUREBPD where date between '"+ strStartDate +"' and '" + strEndDate + "' order by date asc,time asc";
		cursorBloodSugarListGraph = sqLiteDatabase.rawQuery(sqlBloodSugarListGraph, null);
		
		if (cursorBloodSugarListGraph.getCount() != 0) {
			
			cursorBloodSugarListGraph.moveToFirst();
			
			
			do {

				for (int i = 0; i < cursorBloodSugarListGraph.getColumnCount(); i++) 
				{			
					if(cursorBloodSugarListGraph.getString(i) == null){
						itemValueGraph[i] = "";
					}else{
						itemValueGraph[i] = cursorBloodSugarListGraph.getString(i);	
					}					
//					Log.d("tttttFFtt", itemValue[i]);
				}
					xvalue.add(itemValueGraph[0].toString().trim() + itemValueGraph[1].toString().trim());//수축기혈압		
					yvalue.add(itemValueGraph[2].toString().trim());//수축기혈압
					zvalue.add(itemValueGraph[3].toString().trim());//이완기혈압			
//				getDaysInMonth()

			} while (cursorBloodSugarListGraph.moveToNext());
						
		}
		
		cursorBloodSugarListGraph.close();

		
		float[] values = new float[yvalue.size()];
		float[] zvalues = new float[yvalue.size()];
		
		for(int i=0; i<yvalue.size();i++){
			values[i] = Float.parseFloat(yvalue.get(i).toString());
			zvalues[i] = Float.parseFloat(zvalue.get(i).toString());
		}
//		ArrayList<String> xlabel = new ArrayList<String>();
//		ArrayList<String> ylabel = new ArrayList<String>();
		
//		String[] verlabels = new String[] { "great", "ok", "bad" };
//		String[] horlabels = new String[] { "today", "tomorrow", "next week", "next month" };
		
		LinearLayout linearGraph = (LinearLayout) graphDialog.findViewById(R.id.linear_graphdialog_2222);	
		
		if(GraphView != null){
			linearGraph.removeAllViews();
		}
			
		float max = 120.0f;
		float min = 80.0f;
		
		TextView textStandard = (TextView)graphDialog.findViewById(R.id.textStandard);
		textStandard.setText("*정상 혈압범위 : 80~120mmhg");		
			
		GraphView = new GraphViewBloodpress(this, values, zvalues, xvalue, xlabel, xlabel, 500.0f , 10000.0f, max, min);
		if(values.length != 0)
		{
			graphCusorPosition = Math.round(GraphView.SearchPosition(1));//처음 값 가져오기
		}
		else
		{
			graphCusorPosition = 0;
		}
			
		linearGraph.addView(GraphView);
		
		//라인분리
		LineViewBloodpress lineView = new LineViewBloodpress(this, values, xvalue, xlabel, xlabel, 500.0f , 10000.0f, max, min);
		LinearLayout linearLine = (LinearLayout) graphDialog.findViewById(R.id.linear_line);
		linearLine.addView(lineView);
		//graphView.posX1 = 20;
		//graphView.posX2 = 20;
	}

	public String makeDate(int year, int month, int day){
		
		String strMonth="";
		String strDay="";
		
		if(month + 1 <10) {
			strMonth = "0"+(month + 1);
		}else{
			strMonth = ""+(month + 1);
		}
				
		if(day<10) {
			strDay = "0"+day;
		}else{
			strDay = ""+day;
		}		
		return year+"/"+strMonth+"/"+strDay;
	}
	
	
	Dialog listDialog;
	public void showListDialog() {
		listDialog = new Dialog(this);
		listDialog.setTitle("혈압 리스트");
		
		if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.tactivity3_3_list);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog);
		}
		
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showBloodSugarListTotal("total");
	
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_1);
		
		btClose.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				listDialog.dismiss();
			}
		});
		
		listDialog.show();
	}
	
	public void showDayListDialog() {
		listDialog = new Dialog(this);
		listDialog.setTitle("일일 리스트");
		
		if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.tactivity3_3_list);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog);
		}
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showBloodSugarListTotal(SelectedDate2);
	
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_1);
		
		btClose.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				listDialog.dismiss();
			}
		});
		
		listDialog.show();
	}
	
	
	ListView lvBloodSugarTotal;
	static ArrayAdapterBloodSugarTotal arrayAdapterBloodSugarTotal;
	static ArrayList<HashMap<String, Object>> arrayListBloodSugarTotal = new ArrayList<HashMap<String, Object>>();
	String sqlBloodSugarListTotal = "";
	Cursor cursorBloodSugarListTotal;
	String[] itemValueTotal = new String[10];
	
		
	public void showBloodSugarListTotal(String selectedDate){
		
		String tempDate="";
		
		for(int i=0; i<itemValueTotal.length;i++){
			itemValueTotal[i] = "";
		}
		
		lvBloodSugarTotal = (ListView) listDialog.findViewById(R.id.listview_listdialog_1);		 
		arrayListBloodSugarTotal.clear();
		
		if (selectedDate == "total") 
		{
			sqlBloodSugarListTotal = "select * from BlOODPRESSUREBPD order by date desc, time asc";
		} 
		else 
		{
			sqlBloodSugarListTotal = "select * from BlOODPRESSUREBPD where date='"+selectedDate+"' order by date, time asc";
		}
			
		cursorBloodSugarListTotal = sqLiteDatabase.rawQuery(sqlBloodSugarListTotal, null);
		
		if (cursorBloodSugarListTotal.getCount() != 0) {
			
			cursorBloodSugarListTotal.moveToFirst();
			
			do {

				for (int i = 0; i < cursorBloodSugarListTotal.getColumnCount(); i++) {
					
					if(cursorBloodSugarListTotal.getString(i) == null){
						itemValueTotal[i] = "";
					}else{
						itemValueTotal[i] = cursorBloodSugarListTotal.getString(i);	
					}					
//					Log.d("tttttFFtt", itemValue[i]);
				}

				HashMap<String, Object> item = new HashMap<String, Object>();
				String date = itemValueTotal[1].toString().trim();
				
				if(!date.equals(tempDate)){
					
					tempDate = date;
					
					HashMap<String, Object> item1 = new HashMap<String, Object>();
					
					item1.put("_id", "-1");
					item1.put("date", "");
					item1.put("time", "");
					item1.put("bpdiastolic", "");
					item1.put("bpsystolic", "");
					item1.put("memo", "");
					item1.put("hospital", "");
					
					arrayListBloodSugarTotal.add(item1);					
				}

				item.put("_id", itemValueTotal[0].toString().trim());
				item.put("date", itemValueTotal[1].toString().trim());
				item.put("time", itemValueTotal[2].toString().trim());
				item.put("bpdiastolic", itemValueTotal[3].toString().trim());
				item.put("bpsystolic", itemValueTotal[4].toString().trim());
				item.put("memo", itemValueTotal[5].toString().trim());
				item.put("hospital", itemValueTotal[6].toString().trim());

				arrayListBloodSugarTotal.add(item);

			} while (cursorBloodSugarListTotal.moveToNext());
			
		}
		
		cursorBloodSugarListTotal.close();
		
		arrayAdapterBloodSugarTotal = new ArrayAdapterBloodSugarTotal(ActivityBloodPressure.this, arrayListBloodSugarTotal);
		
		lvBloodSugarTotal.setAdapter(arrayAdapterBloodSugarTotal);	
		
		//리스트아이템 선택
		lvBloodSugarTotal.setOnItemClickListener(new OnItemClickListener() 
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
					{		
//						showInputDialog1(position);
//						Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
						HashMap<String, Object> item = arrayListBloodSugarTotal.get(position);
						
						String hospital = item.get("hospital").toString();
						
						if(!hospital.equals(""))
						{		
							if(!ActivityLogin.LOGIN_FLAG)
							{
								Toast.makeText(ActivityBloodPressure.this, "PHR연동후 수정, 삭제 할 수 있습니다." ,	Toast.LENGTH_SHORT).show();
							}
							else if(hospital.equals("Y"))
							{
								Toast.makeText(ActivityBloodPressure.this, "병원데이터는 수정, 삭제 할 수 없습니다." ,	Toast.LENGTH_SHORT).show();
							}
							else
							{													
								Intent i = new Intent(ActivityBloodPressure.this, ActivityBloodPressure_change.class);
								i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
								
								i.putExtra("SelectedDate2", SelectedDate2);
								i.putExtra("position", position+"");
							
								startActivity(i);		
							}
						}
					}
				}); 		
//		lvBloodSugarTotal.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//				
//				showInputDialog1(position);
////				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
//				
//				
//			}
//		});		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Toast.makeText(getApplicationContext(), "로그인에 실패",
		// Toast.LENGTH_SHORT).show();

		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				createThreadAndDialog();
			}
		}
	}
	
	
	private ProgressDialog loagindDialog; // Loading Dialog
	
	class AddTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			loagindDialog = ProgressDialog.show(ActivityBloodPressure.this, "", "Loading...", true, true);
			GetVitalsignBPDHandler.BlOODPRESSUREBPD_COUNT = 0;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getBloodSugar();
			return null;
		}
	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			loagindDialog.dismiss(); // 다이얼로그 삭제
			
			if(ActivityLogin.LOGIN_FLAG == true){
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
				
				button_hosp.setOnClickListener(new OnClickListener() { // 병원연동해제

					@Override
					public void onClick(View v) {
						
						AlertDialog alert = new AlertDialog.Builder(ActivityBloodPressure.this)					
						.setTitle("병원연동 해제")
						.setMessage("병원 연동을 해제 하시겠습니까?")
						.setPositiveButton( "확인", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								ActivityLogin.LOGIN_FLAG = false;
								button_hosp.setBackgroundResource(R.drawable.hosp_selector);
								
								button_hosp.setOnClickListener(new OnClickListener() { // 병원연동

									@Override
									public void onClick(View v) {
										
										if(ULNetworkReceiver.NETWORK_LIVE==true){
											
											if (ActivityLogin.LOGIN_FLAG) {
//												createThreadAndDialog();
												
												hospitalConnectOn();
											} else {

												Intent i = new Intent(
														ActivityBloodPressure.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
											calendarView.invalidate();
											
										}else{
											Toast.makeText(ActivityBloodPressure.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
										}

									}
								});
								dialog.dismiss();
							}
						})
						.setNegativeButton("취소", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}

						})
						.show();				
					}
				});
				
			}
			
			Toast.makeText(getApplicationContext(), "PHR데이터가 연동되었습니다.", Toast.LENGTH_SHORT).show();
			
			calendarView.invalidate();
		}	
	}
		
		
	Handler handler1 = new Handler();
	
	void createThreadAndDialog() 
	{	
		new AddTask().execute(); 
//		/* ProgressDialog */	
//		loagindDialog = ProgressDialog
//				.show(Activity1.this, "", "Loading...", true, true);
//	
//		
//		GetVitalsignBloodSugarHandler.BlOODPRESSUREBPD_COUNT = 0;
//
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// 시간걸리는 처리
//				getBloodSugar();
//				handler.sendEmptyMessage(0);
//			}
//		});
//		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // 다이얼로그 삭제
			Toast.makeText(getApplicationContext(), GetVitalsignBPDHandler.BlOODPRESSUREBPD_COUNT + " 개의 데이터가 추가되었습니다.", Toast.LENGTH_SHORT).show();
			// view 갱신
//			for (int i = 0; i < ArrayListVitalsignHeight.size(); i++) {
//
//				item1 = ArrayListVitalsignHeight
//						.get(i);
//
//				if (Double.parseDouble(item1.get("height").toString()) != 0.0
//						& Double.parseDouble(item1.get("weight").toString()) != 0) {
//					break;
//				}
//
//			}
//
//			if(item1.get("height").toString().equals("") & item1.get("weight").toString().equals("")){
//				Toast.makeText(getApplicationContext(), "데이터가 없습니다.",
//						Toast.LENGTH_SHORT).show();
//			}else{
//				etHeight.setText(item1.get("height").toString());
//				etWeight.setText(item1.get("weight").toString());
//			}
			
		}
	};
	
	HashMap<String, String> item1;

	public void getBloodSugar() {

		if (!ActivityLogin.item.get("PatientID").equals("")) 
		{	
			//		
			String startDate = "1999-01-01";//시작일
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			Date date = new Date();
			String endDate = dateFormat.format(date);//종료일
			
/*			String sqlBloodSugarListLast = "";
			Cursor cursorBloodSugarListLast;
			
			sqlBloodSugarListLast = "select date(date(replace(date, '/', '-')), '+1 days') from BlOODPRESSUREBPD where hospital = 'Y' order by date desc";
			cursorBloodSugarListLast = sqLiteDatabase.rawQuery(sqlBloodSugarListLast, null);
			
			if(cursorBloodSugarListLast.getCount()>0){ 
				
				cursorBloodSugarListLast.moveToFirst();	
				
				startDate = cursorBloodSugarListLast.getString(0);
//					max = Float.parseFloat(cursorBloodSugarMaxMin.getString(0));
//					min = Float.parseFloat(cursorBloodSugarMaxMin.getString(1));	
				
//				Toast.makeText(getApplicationContext(), cursorBloodSugarListLast.getString(0), Toast.LENGTH_SHORT).show();
				
			}
			//
			cursorBloodSugarListLast.close();*/
			
			String sqlBloodPressureTableDelete = "";
			sqlBloodPressureTableDelete = "delete from BlOODPRESSUREBPD";
			sqLiteDatabase.execSQL(sqlBloodPressureTableDelete);
			
			Log.d("date", "result1112: " + startDate + " "+ endDate);

			HospitalInterface hospitalInterface = new HospitalInterface();


			HashMap<String, String> item = ActivityLogin.item;

			Log.d("id", "result1111: " + item.get("PatientID").toString());

			StringBuffer strInputXMLBuffer = new StringBuffer();
								
			strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
			strInputXMLBuffer.append("<Type>VitalsignBloodPressure</Type>");
			strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
			strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
			strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
			strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
			strInputXMLBuffer.append("<PatientID>" + item.get("PatientID") + "</PatientID>");
			strInputXMLBuffer.append("<StartDate>" + startDate  + "</StartDate>");
			strInputXMLBuffer.append("<EndDate>" + endDate  + "</EndDate>");
			strInputXMLBuffer.append("</Request>");
								
			//xml리퀘스트
			Log.d("test", "request: " + strInputXMLBuffer.toString());
			String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
			Log.d("test", "result1234: " + result1);
			
			GetVitalsignBPDHandler handler1 = new GetVitalsignBPDHandler();
			
			try {
				Xml.parse(result1, handler1);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
//			Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.",
//					Toast.LENGTH_SHORT).show();
		}
	}

		
	public void updateDB(String date, String time, String bpdiastolic, String bpsystolic, String memo, String hospital, String _id)
	{	
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bpdiastolic", bpdiastolic);
		cv.put("bpsystolic", bpsystolic);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.update("BlOODPRESSUREBPD", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception {
		sqLiteDatabase.delete("BlOODPRESSUREBPD", "_id=?", new String[] { _id });
		//Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
	
	
	ListView lvBloodSugar;
	static ArrayAdapterBloodSugar arrayAdapterBloodSugar;
	static ArrayList<HashMap<String, Object>> arrayListBloodSugar = new ArrayList<HashMap<String, Object>>();
	String sqlBloodSugarList = "";
	Cursor cursorBloodSugarList;
	String[] itemValue = new String[10];
	
	public void showBloodSugarList(String selectedDate){//미니리스트(클릭시 수정화면)
		
		for(int i=0; i<itemValue.length;i++){
			itemValue[i] = "";
		}
		
		//lvBloodSugar = (ListView) findViewById(R.id.listview_layer1_1);		 
		arrayListBloodSugar.clear();
		sqlBloodSugarList = "select * from BlOODPRESSUREBPD where date='"+selectedDate+"' order by date, time asc";
		cursorBloodSugarList = sqLiteDatabase.rawQuery(sqlBloodSugarList, null);
		
		if (cursorBloodSugarList.getCount() != 0) {
			
			cursorBloodSugarList.moveToFirst();
			
			do {

				for (int i = 0; i < cursorBloodSugarList.getColumnCount(); i++) {
					
					if(cursorBloodSugarList.getString(i) == null){
						itemValue[i] = "";
					}else{
						itemValue[i] = cursorBloodSugarList.getString(i);	
					}					

//					Log.d("tttttFFtt", itemValue[i]);
				}

				HashMap<String, Object> item = new HashMap<String, Object>();

				item.put("_id", itemValue[0].toString().trim());
				item.put("date", itemValue[1].toString().trim());
				item.put("time", itemValue[2].toString().trim());
				item.put("bpdiastolic", itemValue[3].toString().trim());//이완기
				item.put("bpsystolic", itemValue[4].toString().trim());//수축기
				item.put("memo", itemValue[5].toString().trim());
				item.put("hospital", itemValue[6].toString().trim());

				arrayListBloodSugar.add(item);

			} while (cursorBloodSugarList.moveToNext());
			
			double bpdiastolicsum = 0, bpdiastolic = 0, bpsystolicsum = 0, bpsystolic = 0;
			
			for(int i = 0; i < arrayListBloodSugar.size();  i++)
			{
				HashMap<String, Object> item3 = arrayListBloodSugar.get(i);
				String id = item3.get("_id").toString();
				
				if(id.equals("-1"))
				{
					break;
				}
				else
				{
					bpdiastolic = Integer.parseInt(item3.get("bpdiastolic").toString());
					bpdiastolicsum += bpdiastolic;	
					
					bpsystolic = Integer.parseInt(item3.get("bpsystolic").toString());
					bpsystolicsum += bpsystolic;		
				}					
			}
			
			bpdiastolicsum = bpdiastolic / (double)(arrayListBloodSugar.size());
			bpsystolicsum = bpsystolic / (double)(arrayListBloodSugar.size());
			
			TextView tvDiastolicAverage = (TextView) findViewById(R.id.bpdiastolic_average);
			TextView tvSystolicAverage = (TextView) findViewById(R.id.bpsystolic_average);
			
			tvDiastolicAverage.setText(bpdiastolicsum + " mmHg");			
			tvSystolicAverage.setText(bpsystolicsum + " mmHg");			
			
			TableLayout dayTotalArea = (TableLayout) findViewById(R.id.tableLayout99911);
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
			TableLayout dayTotalArea = (TableLayout) findViewById(R.id.tableLayout99911);
			dayTotalArea.setVisibility(dayTotalArea.GONE);
		}
		
		cursorBloodSugarList.close();
		
/*		arrayAdapterBloodSugar = new ArrayAdapterBloodSugar(ActivityBloodPressure.this, R.layout.bloodsugarlist, arrayListBloodSugar);
		lvBloodSugar.setAdapter(arrayAdapterBloodSugar);
		
		lvBloodSugar.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
//				showInputDialog1(position);
//				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(ActivityBloodPressure.this, ActivityBloodPressure_change.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

				i.putExtra("SelectedDate2", SelectedDate2);
				i.putExtra("position", position+"");
			
				startActivity(i);		
				
			}
		});*/		
	}
		
	//리스트
	public class ArrayAdapterBloodSugar extends ArrayAdapter {
		ArrayList<HashMap<String, Object>> arraylist;
		public ArrayAdapterBloodSugar(Context context, int textViewResourceId, List objects) {
			super(context, textViewResourceId, objects);
			arraylist = (ArrayList) objects;
		}

		@Override
		public void notifyDataSetChanged() {
			super.notifyDataSetChanged();
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			View view= convertView;
//			View view = null;

			if (view == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.bloodsugarlist, null);
			}

//			TextView tvBSDate = (TextView) view.findViewById(R.id.bloodsugar_date);
			TextView tvBSTime = (TextView) view.findViewById(R.id.bloodsugar_time);
			TextView tvBSValue = (TextView) view.findViewById(R.id.bloodsugar_value);
//			TextView tvBSHosp = (TextView) view.findViewById(R.id.bloodsugar_hospital);
			TextView tvBSMemo = (TextView) view.findViewById(R.id.bloodsugar_memo);
//			Button btHospUser = (Button) view.findViewById(R.id.button_hosp_user_record);

			HashMap<String, Object> item = arraylist.get(position);

//			if (tvBSDate != null) {
//				String text = item.get("date").toString().substring(0, 4)+"년 "+item.get("date").toString().substring(5, 7)+"월 "+item.get("date").toString().substring(8, 10)+"일";
//				tvBSDate.setText(text);
//			}힘들어 죽겠다 2013 4 9

			if (tvBSTime != null) {
				String text = item.get("time").toString().substring(0, 2)+"시 "+item.get("time").toString().substring(3, 5)+"분";
				tvBSTime.setText(text);
			}

			if (tvBSValue != null) {
				String text = item.get("bpdiastolic").toString() + "-" + item.get("bpsystolic").toString();
				tvBSValue.setText("혈압(수축기/이완기) : "+text);
			}

		/*	if (btHospUser != null) {
				String text = item.get("hospital").toString();
				
				if(text.equals("Y")){
//					btHospUser.setBackgroundResource(R.drawable.hrecord);
				}else{
//					btHospUser.setBackgroundResource(R.drawable.urecord);
				}						
			}*/

			if (tvBSMemo != null) {
				String text = item.get("memo").toString();
				tvBSMemo.setText("메모: "+text);
			}
			
			return view;
		}

	}
	
	//토탈 리스트
	public class ArrayAdapterBloodSugarTotal extends BaseAdapter {
		ArrayList<HashMap<String, Object>> arraylist;
		public ArrayAdapterBloodSugarTotal(Context context, List objects) {
			arraylist = (ArrayList) objects;
		}

		@Override
		public void notifyDataSetChanged() {
			super.notifyDataSetChanged();
		}
		
		String tempDate = "";
		int start = 0;
		int stop = 0;

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
//			View view= convertView;
			View view = null;
			
			HashMap<String, Object> item = arraylist.get(position);
			String id = item.get("_id").toString();
			
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			
			
			if(!id.equals("-1")){
//				Log.d("dghghh", id);
//				tempDate = date;				
				if (view == null) {
					
					view = layoutInflater.inflate(R.layout.bloodsugarlisttotal, parent, false);
				}
				

				//TextView tvBSDate = (TextView) view.findViewById(R.id.total_bloodsugar_date);
				TextView tvBSTime = (TextView) view.findViewById(R.id.total_bloodsugar_time);
				TextView tvBSValue = (TextView) view.findViewById(R.id.total_bloodsugar_value);
//				TextView tvBSHosp = (TextView) view.findViewById(R.id.total_bloodsugar_hospital);
				TextView tvBSMemo = (TextView) view.findViewById(R.id.total_bloodsugar_memo);
//				Button btHospUser = (Button) view.findViewById(R.id.button_total_hosp_user_record);

//				HashMap<String, Object> item = arraylist.get(position);

//				if (tvBSDate != null) {
//					String text = item.get("date").toString().substring(0, 4)+"년 "+item.get("date").toString().substring(5, 7)+"월 "+item.get("date").toString().substring(8, 10)+"일";
//					tvBSDate.setText(text);
//				}
 
				if (tvBSTime != null) {
					String text = item.get("time").toString().substring(0, 2)+"시 "+item.get("time").toString().substring(3, 5)+"분";
					tvBSTime.setText(text);
				}

				if (tvBSValue != null) {
					String text = item.get("bpdiastolic").toString() + " - " + item.get("bpsystolic").toString();
					tvBSValue.setText(text+"mmHg");
				}

				/*if (btHospUser != null) {
					String text = item.get("hospital").toString();
					
					if(text.equals("Y")){
//						btHospUser.setBackgroundResource(R.drawable.hrecord);
					}else{
//						btHospUser.setBackgroundResource(R.drawable.urecord);
					}			
					
				}*/

				if (tvBSMemo != null) {
					String text = item.get("memo").toString();
					tvBSMemo.setText("메모: "+text);
				}

			}else{
				
				int sum = 0, count = 0, value = 0;
				
				for(int i = position+1; i<arraylist.size(); i++){
					HashMap<String, Object> item3 = arraylist.get(i);
					String id3 = item3.get("_id").toString();
					
					if(id3.equals("-1")){
						break;
					}else{
						value = Integer.parseInt(item3.get("bpdiastolic").toString());
						sum += value;
						count++;						
					}					
				}
				
				double mean = (double)sum/(double)count;
				
				if (view == null) 
				{
					if(sum != 0)
					{					
	//					LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						view = layoutInflater.inflate(R.layout.bloodsugarlisttotal_title, parent, false);
						
						HashMap<String, Object> item1 = arraylist.get(position+1);
						
						TextView tvBSDateTitle = (TextView) view.findViewById(R.id.total_bloodsugar_date_title);

						if (tvBSDateTitle != null) {
							String text = item1.get("date").toString().substring(0, 4)+"년 "+item1.get("date").toString().substring(5, 7)+"월 "+item1.get("date").toString().substring(8, 10)+"일 " + "혈압수축기-이완기";
							tvBSDateTitle.setText(text);
						}
						
						//TextView tvBSMeanValue = (TextView) view.findViewById(R.id.total_bloodsugar_mean_value);
						
						//if (tvBSMeanValue != null) {
						//		tvBSMeanValue.setText("혈압평균: "+ (double)((int)(mean*10))/(double)10);
						//	}
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
		public long getItemId(int position) {
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
		showBloodSugarList(SelectedDate2);
//		SelectedDate="";
//		SelectedDate2="";
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		sqLiteDatabase.close();
		dbHelpepr.close();
	}
	
}