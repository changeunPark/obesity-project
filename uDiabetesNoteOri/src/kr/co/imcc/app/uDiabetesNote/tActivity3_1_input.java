package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity3_1_input extends Activity {
	/** Called when the activity is first created. */
	public static ArrayList<HashMap<String, String>> ArrayListVitalsignHeight = new ArrayList<HashMap<String, String>>();
	static Button button_hosp;
	
	private BroadcastReceiver receiver;
	
	String SelectedDate2;
	
	EditText etExTime;
	EditText etWeight;
	EditText etMemo;
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	EditText etTime;
	EditText etKind;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity3_1_input);
		
		/** 네트워크 상태 표시 */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		//연동버튼
	/*	button_hosp = (Button) findViewById(R.id.button_hosp);	
		button_hosp.setOnClickListener(new OnClickListener() 
		{ 
			@Override
			public void onClick(View v) 
			{	
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if (ActivityLogin.LOGIN_FLAG) {
						//createThreadAndDialog();
						hospitalConnectOn();
					} else {
						Intent i = new Intent(tActivity3_1_input.this, kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivityForResult(i, 1);
					}
				}else{
					Toast.makeText(tActivity3_1_input.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		*/
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		String date = SelectedDate2;
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title_1_4_51);
		tvTitle.setText(date);
		
		
		etTime = (EditText) findViewById(R.id.textView_5_input_1_2_51);	
		
		Calendar calendar = Calendar.getInstance();	
		
		int time = calendar.getTime().getHours();
		int minute = calendar.getTime().getMinutes();
		
		String timeString = "";
		String minString = "";
		
		if(time<10) {
			timeString = "0"+time;
		}else{
			timeString = ""+time;
		}
				
		if(minute<10) {
			minString = "0"+minute;
		}else{
			minString = ""+minute;
		}
		
		etTime.setText(timeString+"시 "+minString+"분");
		
		etTime.setOnClickListener(listenerTime);
		
		etKind = (EditText) findViewById(R.id.textView_5_input_1_4_51);		
		
		etKind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showKindDialog();
			}
		});
		
		
		Button btInput = (Button) findViewById(R.id.button_5_input_save_51);

		etExTime = (EditText) findViewById(R.id.textView_5_input_1_6_51);
		etWeight = (EditText) findViewById(R.id.textView_5_input_1_8_51);
		etMemo = (EditText) findViewById(R.id.textView_5_input_1_10_51);

		btInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etTime.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_input.this)					
					.setTitle("알림")
					.setMessage("시간이 입력되지 않았습니다.")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
//							Toast.LENGTH_SHORT).show();
				} else if (etKind.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_input.this)					
					.setTitle("알림")
					.setMessage("운동의 종류가 입력되지 않았습니다.")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
//							Toast.LENGTH_SHORT).show();
				} else if (etExTime.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_input.this)					
					.setTitle("알림")
					.setMessage("운동시간이 입력되지 않았습니다.")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
//							Toast.LENGTH_SHORT).show();
				} else if (etWeight.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_input.this)					
					.setTitle("알림")
					.setMessage("몸무게가 입력되지 않았습니다.")
					.setPositiveButton( "확인", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
//							Toast.LENGTH_SHORT).show();
				}else {

					String calorie = (int)(Double.parseDouble(selectedMet)*(Double.parseDouble(etExTime.getText().toString())/60)*Double.parseDouble(etWeight.getText().toString())+0.5) + "";
					
					//Toast.makeText(getApplicationContext(), hours+" "+minutes,
					//		Toast.LENGTH_SHORT).show();
//
					insertDB(SelectedDate2, etTime.getText().toString().substring(0, 2) + ":" + etTime.getText().toString().substring(4, 6), etKind.getText().toString().trim(),
							etExTime.getText().toString().trim(), etWeight.getText().toString().trim(),
							etMemo.getText().toString().trim(), calorie);
					
					sqLiteDatabase.close();					
					finish();
				}

			}
		});
		
		Button button_back = (Button) findViewById(R.id.button_5_input_cancel_51);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
		
		showKindDialog();
		hospitalConnectOnLoad();
	}
	
	public void hospitalConnectOnLoad(){
		if(ULNetworkReceiver.NETWORK_LIVE==true){
			
			if (ActivityLogin.LOGIN_FLAG) {
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);						
				hospitalConnectOn();
			} 
			
		}else{
			Toast.makeText(tActivity3_1_input.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	OnClickListener listenerTime = new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			
			Calendar calendar = Calendar.getInstance();			
			
			new TimePickerDialog(tActivity3_1_input.this, timeSetListener, calendar.getTime().getHours() , calendar.getTime().getMinutes() , true).show(); 
			
		}
	};
	
	public void hospitalConnectOn(){			
		createThreadAndDialog();	
	}
	
	private ProgressDialog loagindDialog; // Loading Dialog

	void createThreadAndDialog() {
		/* ProgressDialog */
		loagindDialog = ProgressDialog
				.show(this, "", "Loading...", true, false);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// 시간걸리는 처리
				getHeightWeight();
				handler.sendEmptyMessage(0);
			}
		});
		thread.start();
	}
	
	//refresh
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
	
	HashMap<String, String> item1;
	
	public void getHeightWeight() 
	{
		String startDate = "1999-01-01";//시작일
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date date = new Date();
		String endDate = dateFormat.format(date);//종료일
		
		if (!ActivityLogin.item.get("PatientID").toString().equals("")) 
		{
			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			HashMap<String, String> item = ActivityLogin.item;

			Log.d("id", "result1111: " + item.get("PatientID").toString());

			StringBuffer strInputXMLBuffer = new StringBuffer();
								
			strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
			strInputXMLBuffer.append("<Type>VitalsignHeight</Type>");
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

			// Log.d("test", "result1234: " + result1);
			GetVitalsignHeightHandler handler1 = new GetVitalsignHeightHandler();
			
			try 
			{
				Xml.parse(result1, handler1);
			} 
			catch (SAXException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else 
		{
			tMainActivity.LOGIN_FLAG = false;
//			Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.",
//					Toast.LENGTH_SHORT).show();
		}
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // 다이얼로그 삭제
			
			if(ActivityLogin.LOGIN_FLAG == true){
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
				
				button_hosp.setOnClickListener(new OnClickListener() { // 병원연동해제

					@Override
					public void onClick(View v) {
						
						AlertDialog alert = new AlertDialog.Builder(tActivity3_1_input.this)					
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
									public void onClick(View v) 
									{
										if(ULNetworkReceiver.NETWORK_LIVE==true)
										{
											if (ActivityLogin.LOGIN_FLAG) 
											{
//												createThreadAndDialog();
												
												hospitalConnectOn();
											}
											else 
											{
												Intent i = new Intent(
														tActivity3_1_input.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);
											}
										}
										else
										{
											Toast.makeText(tActivity3_1_input.this, "네트워크에 연결되어 있지 않습니다. 네트워크 설정을 확인해 주십시요." ,	Toast.LENGTH_SHORT).show();
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

			// view 갱신
			for (int i = 0; i < ArrayListVitalsignHeight.size(); i++) 
			{
				item1 = ArrayListVitalsignHeight
						.get(i);

				if (Double.parseDouble(item1.get("height").toString()) != 0.0 & Double.parseDouble(item1.get("weight").toString()) != 0) 
				{
					break;
				}
			}
			
			if(ArrayListVitalsignHeight.size()==0)
			{
				Toast.makeText(getApplicationContext(), "데이터가 없습니다.", Toast.LENGTH_SHORT).show();
			}
			else
			{				
				if(item1.get("height").toString().equals("") & item1.get("weight").toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "데이터가 없습니다.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					etWeight.setText(item1.get("weight").toString());
				}	
			}		
		}
	};
	
	
	static String hours;
	static String minutes;
	
	TimePickerDialog.OnTimeSetListener timeSetListener = new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			
			if(hourOfDay<10) {
				hours = "0"+hourOfDay;
			}else{
				hours = ""+hourOfDay;
			}
					
			if(minute<10) {
				minutes = "0"+minute;
			}else{
				minutes = ""+minute;
			}
			
			etTime.setText(hours+"시 "+minutes+"분");
		}
	}; 
	
	AlertDialog.Builder kindDialog;
	double[] metvalue = {1.5,4,6,7,6,9,6,5,7,3,6,3,5.1,6};
	String selectedMet = "";
	
	
	public void showKindDialog() {
		
		kindDialog = new AlertDialog.Builder(this);
		kindDialog.setTitle("운동의 종류");
		
		final CharSequence[] metlist = {"침상체조", "걷기", "조깅", "등산", "줄넘기(60~100회/분)", "줄넘기(100~1400회/분)",
				"계단오르기", "자전거 타기", "에어로빅 무용","낚시", "테니스", "볼링","골프", "수영"}; 
		
		kindDialog.setSingleChoiceItems(metlist, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int pos) {
						
				selectedMet = metvalue[pos] + "";
				etKind.setText(metlist[pos]);
				dialog.dismiss();				
			}
		});
		
		AlertDialog alert = kindDialog.create();
		alert.show();  

	}
		

	public void insertDB(String date, String time, String kind, String exTime, String weight, String memo, String calorie) {

		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("kind", kind);
		cv.put("extime", exTime);
		cv.put("weight", weight);
		cv.put("memo", memo);
		cv.put("calorie", calorie);

		sqLiteDatabase.insert("EXCERCISE", "", cv);
	}

}