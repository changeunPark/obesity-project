//��ȭ������ �⺻

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

public class Activity1_hb extends Activity {
	/** Called when the activity is first created. */
	
	private BroadcastReceiver receiver;
	
	public String[] sWeek = {"��","��","ȭ","��","��","��","��"};
   
	static Button button_hosp;
	
   	Calendar calendar;
   	int dayCount;
   	static int[] iDate;	//�Ѱ��� ���� intent date
   	static int[] sltDate  = new int[3];  
   	// ���    
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
	
	public static cal_CalView_hb calendarView;
	
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

		// �� �־� �־�� �Ѵ�. �̷��� �ؾ� displayMetrics�� ������ �ȴ�.

		//getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
		//int dipWidth  = (int) (120  * displayMetrics.density);
		//int dipHeight = (int) (90 * displayMetrics.density);
		if(deviceHeight > 1200)
		{
			setContentView(R.layout.layer1_hbbig);
		}
		else
		{
			setContentView(R.layout.layer1_hb);
		}
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
		
		
		calendar = Calendar.getInstance();
		   
		// ������ �� ����
		if (sltDate[YEAR] == 0){
			sltDate[YEAR] = calendar.get(Calendar.YEAR);
			sltDate[MONTH] = calendar.get(Calendar.MONTH);
			sltDate[I_TODAY] = calendar.get(Calendar.DATE);
		}	
		setDate();
			   
		final TextView tvCalendar = (TextView) findViewById(R.id.tv1_hb);		
		tvCalendar.setText(sltDate[YEAR]+"��"+"  "+ (sltDate[MONTH]+1)+"��");
		
		Button btPrev = (Button) findViewById(R.id.button_layer1_1_hb);		
		Button btNext = (Button) findViewById(R.id.button_layer1_2_hb);
		
		btPrev.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
				
				calenminus();
        		setDate();
        		tvCalendar.setText(""+sltDate[YEAR]+"��"+"  "+(sltDate[MONTH]+1)+"��");//�ؽ�Ʈ ����
//        		calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
        		calendarView.invalidate();
        		
        		arrayListHb.clear();
        		//arrayAdapterHb.notifyDataSetChanged();
//        		dataCheck();
        		cal_CalView_hb.SELECT_FLAG = false;		
        		cal_CalView_hb.start = 0;
        		
        		showHbList(SelectedDate2);
			}
		});
		
		
		btNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				calSelectColor = getResources().getColor(R.color.cal_background);
				calendarView.setSelectColor(calSelectColor);	
        		
				calenplus();
				setDate();
				tvCalendar.setText(""+sltDate[YEAR]+"��"+"  "+(sltDate[MONTH]+1)+"��");//�ؽ�Ʈ ����
//				calSelectColor = getResources().getColor(R.color.cal_background);
//				calendarView.setSelectColor(calSelectColor);	
				calendarView.invalidate();
//				dataCheck();
				arrayListHb.clear();
        		//arrayAdapterHb.notifyDataSetChanged();
        		
        		cal_CalView_hb.SELECT_FLAG = false;
        		cal_CalView_hb.start = 0;
        		
        		showHbList(SelectedDate2);
			}
		});
		
		
		LinearLayout linearCalendar = (LinearLayout) findViewById(R.id.linear_layer1_2222_hb);
		
		calendarView = new cal_CalView_hb(this);
		
		// ���� set
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
		

		Button button_back = (Button) findViewById(R.id.button_layer1_back_hb);

		button_back.setOnClickListener(new OnClickListener() { //�ڷι�ư

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});
		
		
		Button button_input = (Button) findViewById(R.id.button_layer1_3_hb);

		button_input.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				if(!ActivityLogin.LOGIN_FLAG)
				{
					Toast.makeText(Activity1_hb.this, "PHR������ �Է� �� �� �ֽ��ϴ�." ,	Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(SelectedDate.equals("") || cal_CalView_hb.SELECT_FLAG==false)
					{
						Toast.makeText(getApplicationContext(), "��¥�� ������ �ּ���.",
								Toast.LENGTH_SHORT).show();
					}
					else
					{
	//					showInputDialog();	
						
						Intent i = new Intent(Activity1_hb.this, Activity1_1_hb.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	
						i.putExtra("SelectedDate2", SelectedDate2);
						// i.putExtra("MESSAGE", message);
					
						startActivity(i);
					}				
			}
			}
		});
		
		
		button_hosp = (Button) findViewById(R.id.button_layer1_hosp_hb);

		button_hosp.setOnClickListener(new OnClickListener() { // ��������

			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					
					if (ActivityLogin.LOGIN_FLAG) {
//						createThreadAndDialog();
						hospitalConnectOn();
					} else {

						Intent i = new Intent(
								Activity1_hb.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);

					}
					
					calendarView.invalidate();
					
				}else{
					Toast.makeText(Activity1_hb.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		
		Button button_graph = (Button) findViewById(R.id.button_layer1_4_hb);

		button_graph.setOnClickListener(new OnClickListener() { // graph
			@Override
			public void onClick(View v) 
			{				
				showGraphDialog();
							
				if(startflag)
				{		
					hScrollView.post(new Runnable()
					{
			            public void run() 
			            {
			            	hScrollView.scrollTo(graphCusorPosition-50, 0);	//��ũ�Ѻ� Ŀ�� �̵�	
			            }
					});
				}
			}
		});
		
		
		Button button_list = (Button) findViewById(R.id.button_layer1_5_hb);

		button_list.setOnClickListener(new OnClickListener() { // list

			@Override
			public void onClick(View v) {
				
				showListDialog();
			}
		});	
		showHbList("");
		hospitalConnectOnLoad();	
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
			Toast.makeText(Activity1_hb.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	public void onBackPressed() {
		super.onBackPressed();
		SelectedDate="";
		SelectedDate2="";
		cal_CalView_hb.start = 0;
		sltDate[YEAR] = 0;	sltDate[YEAR] = 0;	//��Ƽ��Ƽ ����۽� ����ƽ ��¥�� �ʱ�ȭ�� ����
	};
	
	Dialog graphDialog;
	String sqlHbListGraph = "";
	Cursor cursorHbListGraph;
	String[] itemValueGraph = new String[10];
	
	String sqlHbMaxMin = "";
	Cursor cursorHbMaxMin;
	
	static GraphViewHb graphView;
	Button btnDateStart;
	Button btnDateEnd;
	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	HorizontalScrollView hScrollView;
	
	public void showGraphDialog() 
	{
		graphDialog = new Dialog(this);
		graphDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		//graphDialog.setTitle("��ȭ������ �׷���(3����)");
		graphDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		if(deviceHeight > 1200)
		{
			graphDialog.setContentView(R.layout.graphdialog_hbbig);
		}
		else
		{
			graphDialog.setContentView(R.layout.graphdialog_hb);
		}
		
		Button btClose = (Button) graphDialog.findViewById(R.id.button_3_2_graphdialog_result);
		Button btnDateSearch = (Button) graphDialog.findViewById(R.id.btn_search);
		//tvDateStart = (TextView) graphDialog.findViewById(R.id.textview_graph_2);
		btnDateStart = (Button) graphDialog.findViewById(R.id.button1);
		btnDateEnd = (Button) graphDialog.findViewById(R.id.button2);
		TextView title = (TextView) graphDialog.findViewById(R.id.textview_graph_1); 
		title.setText("��ȭ������ �׷���");
		
		hScrollView = (HorizontalScrollView) graphDialog.findViewById(R.id.horizontalScrollView1_graph);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(graphDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		graphDialog.getWindow().setAttributes(lp); 
					
		btClose.setOnClickListener
		(
				new OnClickListener() 
				{	
					@Override
					public void onClick(View v) 
					{		
						graphDialog.dismiss();
					}
				}
		);
			
			
		if(termflag == 0)
		{
			startCalendar.add(calendar.MONTH, -3);//���糯¥-3����			
			//sltDate[MONTH] = calendar.get(Calendar.MONTH);
		}	
		
		endCalendar.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
		endCalendar.add(calendar.MONTH, 3);
			
		String startDate = makeDate(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
		String endDate = makeDate(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
		
		termflag = 1;//��¥�� ����	
		
		//������ ǥ��
		btnDateStart.setText(startDate);
		btnDateEnd.setText(endDate);
				
		drawGraph(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)
				,endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
				
				
		//���۳�¥���� ���̾�α�
		btnDateStart.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {			
				new DatePickerDialog(Activity1_hb.this, startDateSetListener, startCalendar.get(Calendar.YEAR), 
						startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)).show();		
			}
		});	
				
		//���ᳯ¥���� ���̾�α�
		btnDateEnd.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {						
				new DatePickerDialog(Activity1_hb.this, endDateSetListener, endCalendar.get(Calendar.YEAR), 
						endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH)).show();		
			}
		});			
								
		//�˻���ư
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
					new AlertDialog.Builder(Activity1_hb.this)					
					.setTitle("�˸�")
					.setMessage("�˻������� �߸� �����Ǿ����ϴ�")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
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
					new AlertDialog.Builder(Activity1_hb.this)					
					.setTitle("�˸�")
					.setMessage("�˻������� �ִ� 3�������� ���� �� �� �ֽ��ϴ�")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
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
					hScrollView.scrollTo(graphCusorPosition - 50, 0);//ù������ ��ġ�� Ŀ���̵�		
					Toast.makeText(Activity1_hb.this, "�˻� �Ǿ����ϴ�", Toast.LENGTH_SHORT).show();
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
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(y, m, d);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(y2, m2, d2);
			
		
		String strEndDate = makeDate(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
		
		ArrayList<String> xlabel = new ArrayList<String>();
		
		while(true){//�Ⱓ ��¥�� ������ֱ�
			
			String date = makeDate(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH));
			xlabel.add(date);
			
			cal2.add(cal2.DAY_OF_MONTH, 1);
			
			if(date.equals(strEndDate)) break;
			
			Log.d("date: ", date);
			
		}
		
		ArrayList<String> xvalue = new ArrayList<String>();
		ArrayList<String> yvalue = new ArrayList<String>();
		
		sqlHbListGraph = "select date, time, hb  from HBA1C where date between '"+ strStartDate +"' and '" + strEndDate + "' order by date asc,time asc";
		cursorHbListGraph = sqLiteDatabase.rawQuery(sqlHbListGraph, null);
		
		if (cursorHbListGraph.getCount() != 0) {
			
			cursorHbListGraph.moveToFirst();
			
			do {

				for (int i = 0; i < cursorHbListGraph.getColumnCount(); i++) {
					
					if(cursorHbListGraph.getString(i) == null){
						itemValueGraph[i] = "";
					}else{
						itemValueGraph[i] = cursorHbListGraph.getString(i);	
					}					

//					Log.d("tttttFFtt", itemValue[i]);
				}
				
				xvalue.add(itemValueGraph[0].toString().trim() + itemValueGraph[1].toString().trim());
				yvalue.add(itemValueGraph[2].toString().trim());	
				
//				getDaysInMonth()

			} while (cursorHbListGraph.moveToNext());		
		}
		
		cursorHbListGraph.close();
		
		float[] values = new float[yvalue.size()];
		
		for(int i=0; i<yvalue.size();i++){
			values[i] = Float.parseFloat(yvalue.get(i).toString());
		}

		LinearLayout linearGraph = (LinearLayout) graphDialog.findViewById(R.id.linear_graphdialog_2222);	
		
		if(graphView != null)
		{
			linearGraph.removeAllViews();
		}
		
		float max = 6.0f;//����ġ ��
		float min = 4.0f;
		
		TextView textStandard = (TextView)graphDialog.findViewById(R.id.textStandard);
		textStandard.setText("*���� ��ȭ�����ҹ��� : 4~6%");		
		
		graphView = new GraphViewHb(this, values, xvalue, xlabel, xlabel, 500.0f , 10000.0f, max, min);
		if(values.length != 0)
		{
			graphCusorPosition = Math.round(graphView.SearchPosition(1));//ó�� �� ��������
		}
		else
		{
			graphCusorPosition = 0;
		}
		
		linearGraph.addView(graphView);
		
		//���κи�
		LineViewHb lineView = new LineViewHb(this, values, xvalue, xlabel, xlabel, 500.0f , 10000.0f, max, min);
		LinearLayout linearLine = (LinearLayout) graphDialog.findViewById(R.id.linear_line);
		linearLine.addView(lineView);		
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
		listDialog.setTitle("��ȭ������ ����Ʈ");
		
		if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.listdialog_hbbig);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog_hb);
		}
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showHbListTotal("total");
	
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_1_hb);
		
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
		listDialog.setTitle("���� ����Ʈ");
		
		if(deviceHeight > 1200)
		{
			listDialog.setContentView(R.layout.listdialog_hbbig);
		}
		else
		{
			listDialog.setContentView(R.layout.listdialog_hb);
		}
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(listDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		listDialog.getWindow().setAttributes(lp); 
		
		showHbListTotal(SelectedDate2);
	
		Button btClose = (Button) listDialog.findViewById(R.id.button_3_2_listdialog_result_1_hb);
		
		btClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				listDialog.dismiss();
			}
		});
		
		listDialog.show();
	}
	
		
	ListView lvHbTotal;
	static ArrayAdapterHbTotal arrayAdapterHbTotal;
	static ArrayList<HashMap<String, Object>> arrayListHbTotal = new ArrayList<HashMap<String, Object>>();
	String sqlHbListTotal = "";
	Cursor cursorHbListTotal;
	String[] itemValueTotal = new String[10];
	
		
	public void showHbListTotal(String selectedDate)
	{
		String tempDate="";
		
		for(int i=0; i<itemValueTotal.length;i++)
		{
			itemValueTotal[i] = "";
		}
		
		lvHbTotal = (ListView) listDialog.findViewById(R.id.listview_listdialog_1_hb);		 
		arrayListHbTotal.clear();
		
		if(selectedDate == "total")
		{
			sqlHbListTotal = "select * from HBA1C order by date desc, time asc";
		}
		else
		{
			sqlHbListTotal = "select * from HBA1C where date='"+selectedDate+"' order by date, time asc";
		}
		cursorHbListTotal = sqLiteDatabase.rawQuery(sqlHbListTotal, null);
		
		if (cursorHbListTotal.getCount() != 0) {
			
			cursorHbListTotal.moveToFirst();
			
			do {

				for (int i = 0; i < cursorHbListTotal.getColumnCount(); i++) 
				{					
					if(cursorHbListTotal.getString(i) == null)
					{
						itemValueTotal[i] = "";
					}
					else
					{
						itemValueTotal[i] = cursorHbListTotal.getString(i);	
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
					item1.put("hb", "");
					item1.put("memo", "");
					item1.put("hospital", "");
					
					arrayListHbTotal.add(item1);
					
				}

				item.put("_id", itemValueTotal[0].toString().trim());
				item.put("date", itemValueTotal[1].toString().trim());
				item.put("time", itemValueTotal[2].toString().trim());
				item.put("hb", itemValueTotal[3].toString().trim());
				item.put("memo", itemValueTotal[4].toString().trim());
				item.put("hospital", itemValueTotal[5].toString().trim());

				arrayListHbTotal.add(item);

			} while (cursorHbListTotal.moveToNext());
		}
		
		cursorHbListTotal.close();
		
		arrayAdapterHbTotal = new ArrayAdapterHbTotal(Activity1_hb.this, R.layout.hblisttotal, arrayListHbTotal);
		
		lvHbTotal.setAdapter(arrayAdapterHbTotal);	
		
		lvHbTotal.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
			{			
//				showInputDialog1(position);
//				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
				HashMap<String, Object> item = arrayListHbTotal.get(position);
				
				String hospital = item.get("hospital").toString();
				
				if(!hospital.equals(""))
				{		
					if(!ActivityLogin.LOGIN_FLAG)
					{
						Toast.makeText(Activity1_hb.this, "PHR������ ����, ���� �� �� �ֽ��ϴ�." ,	Toast.LENGTH_SHORT).show();
					}
					else if(hospital.equals("Y"))
					{
						Toast.makeText(Activity1_hb.this, "���������ʹ� ����, ���� �� �� �����ϴ�." ,	Toast.LENGTH_SHORT).show();
					}
					else
					{
						Intent i = new Intent(Activity1_hb.this, Activity1_2_hb.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						
						i.putExtra("SelectedDate2", SelectedDate2);
						i.putExtra("position", position+"");
					
						startActivity(i);	
					}
				}
			}
		}); 				
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Toast.makeText(getApplicationContext(), "�α��ο� ����",
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
			
			loagindDialog = ProgressDialog.show(Activity1_hb.this, "", "Loading...", true, true);
			GetVitalsignHbHandler.HB_COUNT = 0;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getHb();
			return null;
		}
	
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			loagindDialog.dismiss(); // ���̾�α� ����
			
			if(ActivityLogin.LOGIN_FLAG == true){
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
				
				button_hosp.setOnClickListener(new OnClickListener() { // ������������

					@Override
					public void onClick(View v) {
						
						AlertDialog alert = new AlertDialog.Builder(Activity1_hb.this)					
						.setTitle("�������� ����")
						.setMessage("���� ������ ���� �Ͻðڽ��ϱ�?")
						.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								ActivityLogin.LOGIN_FLAG = false;
								button_hosp.setBackgroundResource(R.drawable.hosp_selector);
								
								button_hosp.setOnClickListener(new OnClickListener() { // ��������

									@Override
									public void onClick(View v) {
										
										if(ULNetworkReceiver.NETWORK_LIVE==true){
											
											if (ActivityLogin.LOGIN_FLAG) {
												createThreadAndDialog();
												
												hospitalConnectOn();
											} else {

												Intent i = new Intent(
														Activity1_hb.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
											calendarView.invalidate();
											
										}else{
											Toast.makeText(Activity1_hb.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
										}

									}
								});
								dialog.dismiss();
							}
						})
						.setNegativeButton("���", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						})
						.show();
									
					}
				});
				
			}
			Toast.makeText(getApplicationContext(),"PHR�����Ͱ� �����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			//Toast.makeText(getApplicationContext(), GetVitalsignHbHandler.HB_COUNT + " ���� �����Ͱ� �߰��Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			calendarView.invalidate();
		}		
	}
			
	Handler handler1 = new Handler();
	
	void createThreadAndDialog() {		
		new AddTask().execute(); 
//		/* ProgressDialog */	
//		loagindDialog = ProgressDialog
//				.show(tActivity1_4.this, "", "Loading...", true, true);
//	
//		
//		GetVitalsignHbHandler.HB_COUNT = 0;
//
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// �ð��ɸ��� ó��
//				getHb();
//				handler.sendEmptyMessage(0);
//			}
//		});
//		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // ���̾�α� ����
			Toast.makeText(getApplicationContext(), GetVitalsignHbHandler.HB_COUNT + " ���� �����Ͱ� �߰��Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
		}
	};
	
	HashMap<String, String> item1;

	public void getHb() {

		if (!ActivityLogin.item.get("PatientID").toString().equals("")) {
						
			String startDate = "1999-01-01";			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			Date date = new Date();
			String endDate = dateFormat.format(date);
			
/*			String sqlHbListLast = "";
			Cursor cursorHbListLast;
			
			sqlHbListLast = "select date(date(replace(date, '/', '-')), '+1 days') from HBA1C where hospital = 'Y' order by date desc";
			cursorHbListLast = sqLiteDatabase.rawQuery(sqlHbListLast, null);
			
			if(cursorHbListLast.getCount()>0){ 
				
				cursorHbListLast.moveToFirst();	
				
				startDate = cursorHbListLast.getString(0);
				//max = Float.parseFloat(cursorHbMaxMin.getString(0));
				//min = Float.parseFloat(cursorHbMaxMin.getString(1));	
				
				//Toast.makeText(getApplicationContext(), cursorHbListLast.getString(0), Toast.LENGTH_SHORT).show();			
			}

			cursorHbListLast.close();*/
			
			
			String sqlHbTableDelete = "delete from HBA1C";
			sqLiteDatabase.execSQL(sqlHbTableDelete);
			
			Log.d("date", "result1112: " + startDate + " "+ endDate);

			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			HashMap<String, String> item = ActivityLogin.item;

			StringBuffer strInputXMLBuffer = new StringBuffer();
			
			strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
			strInputXMLBuffer.append("<Type>HBA1C</Type>");
			strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
			strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
			strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
			strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
			strInputXMLBuffer.append("<PatientID>" + item.get("PatientID") + "</PatientID>");
			strInputXMLBuffer.append("<StartDate>" + startDate  + "</StartDate>");
			strInputXMLBuffer.append("<EndDate>" + endDate  + "</EndDate>");
			strInputXMLBuffer.append("</Request>");
			
			//xml������Ʈ
			Log.d("test", "request: " + strInputXMLBuffer.toString()); //������Ʈ  �α�
			String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());
			
//			String result1 = hospitalInterface.getVitalSignWithThread(
//					"VitalsignHb", ActivityLogin.strLoginID,
//					"5109079", "20050101", "20111207");

			Log.d("test", "response: " + result1);//�������� �α�
			
			GetVitalsignHbHandler handler1 = new GetVitalsignHbHandler();
			try {
				Xml.parse(result1, handler1);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		} else {
			tMainActivity.LOGIN_FLAG = false;
//			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
//			Toast.LENGTH_SHORT).show();
		}
	}

	
	//������
	Dialog inputDialog;
	public void showInputDialog() {
		inputDialog = new Dialog(this, R.style.myDialog);
		inputDialog.setTitle(SelectedDate);
		inputDialog.setContentView(R.layout.tactivity3_3_input);
		
		Button btInput = (Button) inputDialog.findViewById(R.id.button_5_input_save);
		final TimePicker tpInput = (TimePicker) inputDialog.findViewById(R.id.timePicker_input);
		
		final EditText etHb = (EditText) inputDialog.findViewById(R.id.textView_5_input_1_2);
		final EditText etMemo = (EditText) inputDialog.findViewById(R.id.textView_5_input_1_5);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(); 
		lp.copyFrom(inputDialog.getWindow().getAttributes()); 
		lp.width = WindowManager.LayoutParams.FILL_PARENT;
		lp.height = WindowManager.LayoutParams.FILL_PARENT; 		
		inputDialog.getWindow().setAttributes(lp); 

		
		btInput.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etHb.getText().toString().equals("")){
					AlertDialog alert = new AlertDialog.Builder(Activity1_hb.this)					
					.setTitle("�˸�")
					.setMessage("������ �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();

//					Toast.makeText(getApplicationContext(), "������ �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
				}else{
					
					String hour="";
					String minute="";
					
					if(tpInput.getCurrentHour()<10) {
						hour = "0"+tpInput.getCurrentHour();
					}else{
						hour = ""+tpInput.getCurrentHour();
					}
							
					if(tpInput.getCurrentMinute()<10) {
						minute = "0"+tpInput.getCurrentMinute();
					}else{
						minute = ""+tpInput.getCurrentMinute();
					}
					
					insertDB(SelectedDate2, hour+":"+minute,etHb.getText().toString().trim(), etMemo.getText().toString().trim(), "N");
					showHbList(SelectedDate2);
					inputDialog.dismiss();
				}		
				
			}
		});		
		inputDialog.show();
	}
	
	
	public void insertDB(String date, String time, String hb, String memo, String hospital){
		
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bloodsugar", hb);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.insert("HBA1C", "", cv);
	}
	
	public void updateDB(String date, String time, String hb, String memo, String hospital, String _id){
		
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bloodsugar", hb);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.update("HBA1C", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception {
		sqLiteDatabase.delete("HBA1C", "_id=?", new String[] { _id });
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
	
	
	ListView lvHb;
	static ArrayAdapterHb arrayAdapterHb;
	static ArrayList<HashMap<String, Object>> arrayListHb = new ArrayList<HashMap<String, Object>>();
	String sqlHbList = "";
	Cursor cursorHbList;
	String[] itemValue = new String[10];
	
	
	
	public void showHbList(String selectedDate)
	{	
		for(int i=0; i<itemValue.length;i++)
		{
			itemValue[i] = "";
		}
		
		//lvHb = (ListView) findViewById(R.id.listview_layer1_1_hb);		 
		arrayListHb.clear();
		sqlHbList = "select * from HBA1C where date='"+selectedDate+"' order by date, time asc";
		cursorHbList = sqLiteDatabase.rawQuery(sqlHbList, null);
		
		if (cursorHbList.getCount() != 0) 
		{		
			cursorHbList.moveToFirst();
			
			do {

				for (int i = 0; i < cursorHbList.getColumnCount(); i++) {
					
					if(cursorHbList.getString(i) == null){
						itemValue[i] = "";
					}else{
						itemValue[i] = cursorHbList.getString(i);	
					}					

//					Log.d("tttttFFtt", itemValue[i]);
				}

				HashMap<String, Object> item = new HashMap<String, Object>();

				item.put("_id", itemValue[0].toString().trim());
				item.put("date", itemValue[1].toString().trim());
				item.put("time", itemValue[2].toString().trim());
				item.put("hb", itemValue[3].toString().trim());
				item.put("memo", itemValue[4].toString().trim());
				item.put("hospital", itemValue[5].toString().trim());

				arrayListHb.add(item);

			} while (cursorHbList.moveToNext());			
			
			double HBsum = 0, HB = 0;
			
			for(int i = 0; i < arrayListHb.size();  i++)
			{
				HashMap<String, Object> item3 = arrayListHb.get(i);
				String id = item3.get("_id").toString();
				
				if(id.equals("-1"))
				{
					break;
				}
				else
				{
					HB = Integer.parseInt(item3.get("hb").toString());
					HBsum += HB;				
				}					
			}
						
			HBsum = HBsum /(double)(arrayListHb.size());
			
			TextView tvAverage = (TextView) findViewById(R.id.bloodsugar_average);
			
			tvAverage.setText(HBsum+ "%");			
			
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
			
		cursorHbList.close();
			
/*		arrayAdapterHb = new ArrayAdapterHb(Activity1_hb.this, R.layout.hblist, arrayListHb);
		lvHb.setAdapter(arrayAdapterHb);
		
		lvHb.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
//				showInputDialog1(position);
//				Toast.makeText(getApplicationContext(), "okkk", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(Activity1_hb.this, Activity1_2_hb.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

				i.putExtra("SelectedDate2", SelectedDate2);
				i.putExtra("position", position+"");
			
				startActivity(i);			
			}
		});*/
		
	}
	
	
	//������
	/*Dialog inputDialog1;
	public void showInputDialog1(final int position) {
		
		HashMap<String, Object> item = arrayListHb.get(position);
		
		final String _id = item.get("_id").toString();
		String date = item.get("date").toString();
		date = date.substring(0, 4) + "�� "+ date.substring(5, 7) + "�� " + date.substring(8, 10) + "��";
		
		String bloodsugar = item.get("bloodsugar").toString();
		String memo = item.get("memo").toString();
		String time = item.get("time").toString();
		
		int tHour = Integer.parseInt(time.substring(0, 2));
		int tMin = Integer.parseInt(time.substring(3, 5));
		
		inputDialog1 = new Dialog(this, R.style.myDialog);
		inputDialog1.setTitle(date);
		inputDialog1.setContentView(R.layout.input1);
		
		Button btUpdate = (Button) inputDialog1.findViewById(R.id.button__5_input123_1_result_update);
		Button btDelete = (Button) inputDialog1.findViewById(R.id.button__5_input123_1_result_delete);
		final TimePicker tpInput1 = (TimePicker) inputDialog1.findViewById(R.id.timePicker_input123);
		
		final EditText etHb1 = (EditText) inputDialog1.findViewById(R.id.textView_5_input123_1_2);
		final EditText etMemo1 = (EditText) inputDialog1.findViewById(R.id.textView_5_input123_1_5);
		
		etHb1.setText(bloodsugar);
		etMemo1.setText(memo);
		tpInput1.setCurrentHour(tHour);
		tpInput1.setCurrentMinute(tMin);
		
		btUpdate.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(etHb1.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "������ �Էµ��� �ʾҽ��ϴ�.",
							Toast.LENGTH_SHORT).show();
				}else{
					
					String hour="";
					String minute="";
					
					if(tpInput1.getCurrentHour()<10) {
						hour = "0"+tpInput1.getCurrentHour();
					}else{
						hour = ""+tpInput1.getCurrentHour();
					}
							
					if(tpInput1.getCurrentMinute()<10) {
						minute = "0"+tpInput1.getCurrentMinute();
					}else{
						minute = ""+tpInput1.getCurrentMinute();
					}
					
					updateDB(SelectedDate2, hour+":"+minute, etHb1.getText().toString().trim(), etMemo1.getText().toString().trim(), "N", _id);
					showHbList(SelectedDate2);
					
					inputDialog1.dismiss();
				}
			}
		});
		
		btDelete.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				try {
					deleteDB(_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				arrayListHb.remove(position);
				arrayAdapterHb.notifyDataSetChanged();
				inputDialog1.dismiss();
				
			}
		});
				
		inputDialog1.show();
	}*/
	
	public class ArrayAdapterHb extends ArrayAdapter {
		ArrayList<HashMap<String, Object>> arraylist;
		public ArrayAdapterHb(Context context, int textViewResourceId, List objects) {
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
				view = layoutInflater.inflate(R.layout.hblist, null);
			}

//			TextView tvBSDate = (TextView) view.findViewById(R.id.bloodsugar_date);
			TextView tvBSTime = (TextView) view.findViewById(R.id.bloodsugar_time_hb);
			TextView tvBSValue = (TextView) view.findViewById(R.id.bloodsugar_value_hb);
//			TextView tvBSHosp = (TextView) view.findViewById(R.id.bloodsugar_hospital);
			TextView tvBSMemo = (TextView) view.findViewById(R.id.bloodsugar_memo_hb);
//			Button btHospUser = (Button) view.findViewById(R.id.button_hosp_user_record_hb);

			HashMap<String, Object> item = arraylist.get(position);

//			if (tvBSDate != null) {
//				String text = item.get("date").toString().substring(0, 4)+"�� "+item.get("date").toString().substring(5, 7)+"�� "+item.get("date").toString().substring(8, 10)+"��";
//				tvBSDate.setText(text);
//			}

			if (tvBSTime != null) {
				String text = item.get("time").toString().substring(0, 2)+"�� "+item.get("time").toString().substring(3, 5)+"��";
				tvBSTime.setText(text);
			}

			if (tvBSValue != null) {
				String text = item.get("hb").toString();
				tvBSValue.setText("��ȭ������: "+text);
			}

/*			if (btHospUser != null) {
				String text = item.get("hospital").toString();
				
				if(text.equals("Y")){
//					btHospUser.setBackgroundResource(R.drawable.hrecord);
				}else{
//					btHospUser.setBackgroundResource(R.drawable.urecord);
				}			
				
			}*/

			if (tvBSMemo != null) {
				String text = item.get("memo").toString();
				tvBSMemo.setText("�޸�: "+text);
			}		
			return view;
		}
	}
	
	
	public class ArrayAdapterHbTotal extends ArrayAdapter {
		ArrayList<HashMap<String, Object>> arraylist;
		public ArrayAdapterHbTotal(Context context, int textViewResourceId, List objects) {
			super(context, textViewResourceId, objects);
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
					
					view = layoutInflater.inflate(R.layout.hblisttotal, parent, false);
				}
				

				TextView tvBSDate = (TextView) view.findViewById(R.id.total_bloodsugar_date_hb_total);
				TextView tvBSTime = (TextView) view.findViewById(R.id.total_bloodsugar_time_hb_total);
				TextView tvBSValue = (TextView) view.findViewById(R.id.total_bloodsugar_value_hb_total);
//				TextView tvBSHosp = (TextView) view.findViewById(R.id.total_bloodsugar_hospital);
				TextView tvBSMemo = (TextView) view.findViewById(R.id.total_bloodsugar_memo_hb_total);
				Button btHospUser = (Button) view.findViewById(R.id.button_total_hosp_user_record_hb_total);

//				HashMap<String, Object> item = arraylist.get(position);

//				if (tvBSDate != null) {
//					String text = item.get("date").toString().substring(0, 4)+"�� "+item.get("date").toString().substring(5, 7)+"�� "+item.get("date").toString().substring(8, 10)+"��";
//					tvBSDate.setText(text);
//				}

				if (tvBSTime != null) {
					String text = item.get("time").toString().substring(0, 2)+"�� "+item.get("time").toString().substring(3, 5)+"��";
					tvBSTime.setText(text);
				}

				if (tvBSValue != null) {
					String text = item.get("hb").toString();
					tvBSValue.setText("��ȭ������: "+text + "%");
				}

				if (btHospUser != null) {
					String text = item.get("hospital").toString();
					
					if(text.equals("Y")){
//						btHospUser.setBackgroundResource(R.drawable.hrecord);
					}else{
//						btHospUser.setBackgroundResource(R.drawable.urecord);
					}			
					
				}

				if (tvBSMemo != null) {
					String text = item.get("memo").toString();
					tvBSMemo.setText("�޸�: "+text);
				}

			}else{
				
				int count = 0;
				double sum = 0.0;
				double value = 0.0;
				
				for(int i = position+1; i<arraylist.size(); i++){
					HashMap<String, Object> item3 = arraylist.get(i);
					String id3 = item3.get("_id").toString();
					
					if(id3.equals("-1")){
						break;
					}else{
						Log.d("dddfff",item3.get("hb").toString());
						value = Double.parseDouble(item3.get("hb").toString());
						sum += value;
						count++;						
					}					
				}
				
				double mean = (double)sum/(double)count;
				
				if (view == null) {
//					LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					if(sum != 0) 	
					{							
					view = layoutInflater.inflate(R.layout.hblisttotal_title, parent, false);
					
					HashMap<String, Object> item1 = arraylist.get(position+1);
					
					TextView tvBSDateTitle = (TextView) view.findViewById(R.id.total_bloodsugar_date_title_hb_total);
					
					if (tvBSDateTitle != null) {
						String text = item1.get("date").toString().substring(0, 4)+"�� "+item1.get("date").toString().substring(5, 7)+"�� "+item1.get("date").toString().substring(8, 10)+"��";
						tvBSDateTitle.setText(text);
					}
					
					TextView tvBSMeanValue = (TextView) view.findViewById(R.id.total_bloodsugar_mean_value_hb_total);
					
					if (tvBSMeanValue != null) {
						tvBSMeanValue.setText("���: "+ (double)((int)(mean*10))/(double)10);
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
		iDate = new int[3]; // 0:���۵���, 1:ǥ�õǾ�� �� �ϼ� , 2 ���� ����, 3,�� ��Ż ����
	   
		dayCount=0;
		for( int i =1; i <= calendar.getActualMaximum(Calendar.DATE); i++)
			dayCount++;
		
		
		iDate[I_CDAY] = dayCount;
	   
		iDate[I_SDAY] = calendar.get( Calendar.DAY_OF_WEEK ) -1; // ���۵Ǵ� ������ �����´�.
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
		layout.setOrientation(a);//���Ĺ��� ����
		layout.setGravity(Gravity.CENTER);
	}
	
	private void setTextView( TextView view, String text, int color, int gravity, LinearLayout.LayoutParams params) {
		view.setText(""+text);//�ؽ�Ʈ ����
		view.setTextColor(color);//�ؽ�Ʈ ���� ����
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
		// �� �� ȭ �� �� �� ��
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
		showHbList(SelectedDate2);
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