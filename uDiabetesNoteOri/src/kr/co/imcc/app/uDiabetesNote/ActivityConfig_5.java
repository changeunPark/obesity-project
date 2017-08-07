package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.util.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityConfig_5 extends Activity {
    /** Called when the activity is first created. */
	
//	static int ALARM_SETTING_FLAG = 0;	
//	TextView tvAlarmSetting;
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	static Button button_hosp;
	private BroadcastReceiver receiver;
	
	TextView tvLastCheckDay1;
	TextView tvLastCheckDay2;
	TextView tvLastCheckDay3;
	TextView tvLastCheckDay4;
	TextView tvLastCheckDay5;
	
	static TextView tvConnect1;
	static TextView tvConnect2;
	static TextView tvConnect3;
	static TextView tvConnect4;
	static TextView tvConnect5;
	
	TextView tvAlarm1;
    TextView tvAlarm2;
    TextView tvAlarm3;
    TextView tvAlarm4;
    TextView tvAlarm5; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.config_5);
        
        dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
        
        //������ �˻�����
        tvLastCheckDay1 = (TextView) findViewById(R.id.textView_config5_2_1);
        tvLastCheckDay2 = (TextView) findViewById(R.id.textView_config5_3_1);
        tvLastCheckDay3 = (TextView) findViewById(R.id.textView_config5_4_1);
        tvLastCheckDay4 = (TextView) findViewById(R.id.textView_config5_5_1);
        tvLastCheckDay5 = (TextView) findViewById(R.id.textView_config5_6_1);
        
        tvLastCheckDay1.setOnClickListener(listenerLastCheckDay);
        tvLastCheckDay2.setOnClickListener(listenerLastCheckDay);
        tvLastCheckDay3.setOnClickListener(listenerLastCheckDay);
        tvLastCheckDay4.setOnClickListener(listenerLastCheckDay);
        tvLastCheckDay5.setOnClickListener(listenerLastCheckDay);
        
        //�˻��ֱ�
//        TextView tvCheckCycle1 = (TextView) findViewById(R.id.textView_config5_2_3);
//        TextView tvCheckCycle2 = (TextView) findViewById(R.id.textView_config5_3_3);
//        TextView tvCheckCycle3 = (TextView) findViewById(R.id.textView_config5_4_3);
//        TextView tvCheckCycle4 = (TextView) findViewById(R.id.textView_config5_5_3);
//        TextView tvCheckCycle5 = (TextView) findViewById(R.id.textView_config5_6_3);
        
//        tvCheckCycle1.setOnClickListener(listenerCheckCycle);
//        tvCheckCycle2.setOnClickListener(listenerCheckCycle);
//        tvCheckCycle3.setOnClickListener(listenerCheckCycle);
//        tvCheckCycle4.setOnClickListener(listenerCheckCycle);
//        tvCheckCycle5.setOnClickListener(listenerCheckCycle);
        
        //�˸�����
        tvAlarm1 = (TextView) findViewById(R.id.textView_config5_2_4);
        tvAlarm2 = (TextView) findViewById(R.id.textView_config5_3_4);
        tvAlarm3 = (TextView) findViewById(R.id.textView_config5_4_4);
        tvAlarm4 = (TextView) findViewById(R.id.textView_config5_5_4);
        tvAlarm5 = (TextView) findViewById(R.id.textView_config5_6_4);
        
        tvAlarm1.setOnClickListener(listenerAlarm);
        tvAlarm2.setOnClickListener(listenerAlarm);
        tvAlarm3.setOnClickListener(listenerAlarm);
        tvAlarm4.setOnClickListener(listenerAlarm);
        tvAlarm5.setOnClickListener(listenerAlarm);
        
        //�˻翹����
        tvConnect1 = (TextView) findViewById(R.id.textView_config5_2_5);
        tvConnect2 = (TextView) findViewById(R.id.textView_config5_3_5);
        tvConnect3 = (TextView) findViewById(R.id.textView_config5_4_5);
        tvConnect4 = (TextView) findViewById(R.id.textView_config5_5_5);
        tvConnect5 = (TextView) findViewById(R.id.textView_config5_6_5);
        
        tvConnect1.setOnClickListener(listenerCheckDay);
        tvConnect2.setOnClickListener(listenerCheckDay);
        tvConnect3.setOnClickListener(listenerCheckDay);
        tvConnect4.setOnClickListener(listenerCheckDay);
        tvConnect5.setOnClickListener(listenerCheckDay);
        
    	/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
        
        Button button1 = (Button) findViewById(R.id.button_config_5_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				onBackPressed();
				finish();
			}
		});
		
		
		button_hosp = (Button) findViewById(R.id.button_config5_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if (ActivityLogin.LOGIN_FLAG) {
//						createThreadAndDialog();
						hospitalConnectOn();
					} else {

						Intent i = new Intent(
								ActivityConfig_5.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);

					}
				}else{
					Toast.makeText(ActivityConfig_5.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		if (ActivityLogin.LOGIN_FLAG) {
			hospitalConnectOnLoad();
		}else{
			displayOnLoad();
		}
//				
    }
    
    
//    protected void onResume() {
//    	
//    	super.onResume();
//    	
//    	if (ActivityLogin.LOGIN_FLAG) {
//			hospitalConnectOnLoad();
//		}else{
//			displayOnLoad();
//		}    	
//    };
//    
//    protected void onRestart() {
//    	
//    	super.onRestart();
//    	
//    	if (ActivityLogin.LOGIN_FLAG) {
//			hospitalConnectOnLoad();
//		}else{
//			displayOnLoad();
//		}
//    };
    
    String sqlOnLoad;
    Cursor cursorOnLoad;
    public void displayOnLoad(){
    	sqlOnLoad = "select lastcheckdate, checkdate, checknm, alarm from DIABETESCHECKCYCLE";
    	cursorOnLoad = sqLiteDatabase.rawQuery(sqlOnLoad, null);
		

		if (cursorOnLoad.getCount() > 0) {
			
			cursorOnLoad.moveToFirst();
//			Toast.makeText(getApplicationContext(), cursorOnLoad.getString(4) + "vvv" + cursorOnLoad.getString(0), Toast.LENGTH_SHORT).show(); // + "vvv"+cursorOnLoad.getString(2) + "vvv"+cursorOnLoad.getString(3) + "vvv", Toast.LENGTH_SHORT).show();
			
			Log.d("ddfff",cursorOnLoad.getString(0) + " " + cursorOnLoad.getString(1) + " "+ cursorOnLoad.getString(2) + " "+ cursorOnLoad.getString(3) + " " );
			
			do {
				
				if(cursorOnLoad.getString(2).equals("��ȭ������")){
					tvLastCheckDay1.setText(cursorOnLoad.getString(0));
					tvConnect1.setText(cursorOnLoad.getString(1));
					tvAlarm1.setText(cursorOnLoad.getString(3)+"����");
				
				}else if(cursorOnLoad.getString(2).equals("�ܹ鴢�˻�")){
					tvLastCheckDay2.setText(cursorOnLoad.getString(0));
					tvConnect2.setText(cursorOnLoad.getString(1));
					tvAlarm2.setText(cursorOnLoad.getString(3)+"����");
					
				}else if(cursorOnLoad.getString(2).equals("���������˻�")){
					tvLastCheckDay3.setText(cursorOnLoad.getString(0));
					tvConnect3.setText(cursorOnLoad.getString(1));
					tvAlarm3.setText(cursorOnLoad.getString(3)+"����");
					
				}else if(cursorOnLoad.getString(2).equals("����ũ����Ƽ��")){
					tvLastCheckDay4.setText(cursorOnLoad.getString(0));
					tvConnect4.setText(cursorOnLoad.getString(1));
					tvAlarm4.setText(cursorOnLoad.getString(3)+"����");
					
				}else if(cursorOnLoad.getString(2).equals("�Ȱ��˻�")){
					tvLastCheckDay5.setText(cursorOnLoad.getString(0));
					tvConnect5.setText(cursorOnLoad.getString(1));
					tvAlarm5.setText(cursorOnLoad.getString(3)+"����");
					
				}


			} while (cursorOnLoad.moveToNext());
			
		}
			
		cursorOnLoad.close();
    }
    
    
    public void hospitalConnectOn(){		
		
		createThreadAndDialog();
		
	}
	
	public void hospitalConnectOnLoad(){
		if(ULNetworkReceiver.NETWORK_LIVE==true){
			
			if (ActivityLogin.LOGIN_FLAG) {
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
								
				hospitalConnectOn();
			} 
			
		}else{
			Toast.makeText(ActivityConfig_5.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
    
    TextView selectedView;
    
    OnClickListener listenerLastCheckDay = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			selectedView = (TextView) v;
//			Toast.makeText(ActivityConfig_5.this, "1" ,	Toast.LENGTH_SHORT).show();
			Calendar calendar = Calendar.getInstance();
			new DatePickerDialog(ActivityConfig_5.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
			
		}
	};
	
	String sqlDiabetesAlertList1;
	Cursor cursorDiabetesAlertList1;
	
	DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override		
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

//			Toast.makeText(getApplicationContext(), "����.", Toast.LENGTH_SHORT).show();
			
			if(makeDate(year, monthOfYear, dayOfMonth).length()==10){
				
				if(selectedView.getId()==R.id.textView_config5_2_1){//��ȭ
					
					sqlDiabetesAlertList1 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '��ȭ������'";
					cursorDiabetesAlertList1 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList1, null);
					
					if (cursorDiabetesAlertList1.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect1.getText().toString());
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm1.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList1.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList1.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", checkdate);
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm1.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView.getId()==R.id.textView_config5_3_1){//�ܹ�
					
					
					sqlDiabetesAlertList1 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�ܹ鴢�˻�'";
					cursorDiabetesAlertList1 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList1, null);
					
					if (cursorDiabetesAlertList1.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect2.getText().toString());
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm2.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList1.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList1.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect2.getText().toString());
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm2.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView.getId()==R.id.textView_config5_4_1){//��������
					
					
					sqlDiabetesAlertList1 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '���������˻�'";
					cursorDiabetesAlertList1 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList1, null);
					
					if (cursorDiabetesAlertList1.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect3.getText().toString());
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm3.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList1.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList1.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect3.getText().toString());
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm3.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView.getId()==R.id.textView_config5_5_1){//ũ����Ƽ��
					
					sqlDiabetesAlertList1 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '����ũ����Ƽ��'";
					cursorDiabetesAlertList1 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList1, null);
					
					if (cursorDiabetesAlertList1.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect4.getText().toString());
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm4.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList1.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList1.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect4.getText().toString());
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm4.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView.getId()==R.id.textView_config5_6_1){//�Ȱ��˻�
					
					sqlDiabetesAlertList1 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�Ȱ��˻�'";
					cursorDiabetesAlertList1 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList1, null);
					
					if (cursorDiabetesAlertList1.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect5.getText().toString());
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm5.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList1.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList1.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checkdate", tvConnect5.getText().toString());
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm5.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}
				
				cursorDiabetesAlertList1.close();
				
			}
			selectedView.setText(makeDate(year, monthOfYear, dayOfMonth));
			
		}
	}; 
	
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
	
	
//	TextView selectedView1;
//	
//	OnClickListener listenerCheckCycle = new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			selectedView1 = (TextView) v;
//			showCycleDialog();
////			Toast.makeText(ActivityConfig_5.this, "2" ,	Toast.LENGTH_SHORT).show();
//		}
//	};
//	
//	AlertDialog.Builder cycleDialog;
//	
//	int position;
//	int[] cyclevalue = {1,2,3,4,5,6,7,8,9,10,11,12};
//	
//	public void showCycleDialog() {
//		
//		cycleDialog = new AlertDialog.Builder(this);
//		cycleDialog.setTitle("�˻� �ֱ�");
//		
//		final CharSequence[] cyclelist = {"1����", "2����", "3����", "4����", "5����", "6����",
//				"7����", "8����", "9����","10����", "11����", "12����"}; 
//		
//		cycleDialog.setSingleChoiceItems(cyclelist, -1, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int pos) {
//						
//				position = pos;
//				selectedView1.setText(cyclevalue[position]+"����");
//				dialog.dismiss();				
//			}
//		});
//		
//		AlertDialog alert = cycleDialog.create();
//		alert.show();  
//
//	}
	
	TextView selectedView2;
	
	OnClickListener listenerAlarm = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			selectedView2 = (TextView) v;
			showAlarmDialog();
//			Toast.makeText(ActivityConfig_5.this, "3" ,	Toast.LENGTH_SHORT).show();
		}
	};
	
	
	AlertDialog.Builder alarmDialog;
	String sqlDiabetesAlertList3;
	Cursor cursorDiabetesAlertList3;
	
	int position1;
	int[] alarmvalue = {0,1,2,3};
	final CharSequence[] alarmlist = {"����","1����", "2����", "3����"};
	
	public void showAlarmDialog() {
		
		alarmDialog = new AlertDialog.Builder(this);
		alarmDialog.setTitle("�˸� ����");
		
		alarmDialog.setSingleChoiceItems(alarmlist, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int pos) {
				
				position1 = pos;
				selectedView2.setText(alarmlist[position1]);
				
				if(selectedView2.getId()==R.id.textView_config5_2_4){//��ȭ
					
					if(tvConnect1.getText().toString().length()!=10){
						
						position1 = 0;						
						selectedView2.setText(alarmlist[position1]); 
						Toast.makeText(ActivityConfig_5.this, "�˻翹������ ������ �ּ���!" ,	Toast.LENGTH_SHORT).show();
					}
					
					sqlDiabetesAlertList3 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '��ȭ������'";
					cursorDiabetesAlertList3 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList3, null);
					cursorDiabetesAlertList3.moveToFirst();
					
					if (cursorDiabetesAlertList3.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay1.getText().toString());
						cv.put("checkdate", tvConnect1.getText().toString());
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", cursorDiabetesAlertList3.getString(1));
						cv.put("alarm", alarmvalue[position1]+"");
						
						
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList3.getString(0) });
					}else{
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay1.getText().toString());
						cv.put("checkdate", tvConnect1.getText().toString());
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", "Y");
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
					
				}else if(selectedView2.getId()==R.id.textView_config5_3_4){//�ܹ�
					
					if(tvConnect2.getText().toString().length()!=10){
						
						position1 = 0;						
						selectedView2.setText(alarmlist[position1]); 
						Toast.makeText(ActivityConfig_5.this, "�˻翹������ ������ �ּ���!" ,	Toast.LENGTH_SHORT).show();
					}
					
					
					sqlDiabetesAlertList3 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�ܹ鴢�˻�'";
					cursorDiabetesAlertList3 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList3, null);
					cursorDiabetesAlertList3.moveToFirst();
					
					if (cursorDiabetesAlertList3.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay2.getText().toString());
						cv.put("checkdate", tvConnect2.getText().toString());
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", cursorDiabetesAlertList3.getString(1));
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList3.getString(0) });
					}else{
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay2.getText().toString());
						cv.put("checkdate", tvConnect2.getText().toString());
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "Y");
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView2.getId()==R.id.textView_config5_4_4){//��������
					
					if(tvConnect3.getText().toString().length()!=10){
						
						position1 = 0;						
						selectedView2.setText(alarmlist[position1]); 
						Toast.makeText(ActivityConfig_5.this, "�˻翹������ ������ �ּ���!" ,	Toast.LENGTH_SHORT).show();
					}
					
					sqlDiabetesAlertList3 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '���������˻�'";
					cursorDiabetesAlertList3 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList3, null);
					cursorDiabetesAlertList3.moveToFirst();
					
					if (cursorDiabetesAlertList3.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay3.getText().toString());
						cv.put("checkdate", tvConnect3.getText().toString());
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", cursorDiabetesAlertList3.getString(1));
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList3.getString(0) });
					}else{
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay3.getText().toString());
						cv.put("checkdate", tvConnect3.getText().toString());
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "Y");
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView2.getId()==R.id.textView_config5_5_4){//ũ����Ƽ��
					
					if(tvConnect4.getText().toString().length()!=10){
						
						position1 = 0;						
						selectedView2.setText(alarmlist[position1]); 
						Toast.makeText(ActivityConfig_5.this, "�˻翹������ ������ �ּ���!" ,	Toast.LENGTH_SHORT).show();
					}
					
					sqlDiabetesAlertList3 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '����ũ����Ƽ��'";
					cursorDiabetesAlertList3 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList3, null);
					cursorDiabetesAlertList3.moveToFirst();
					
					if (cursorDiabetesAlertList3.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay4.getText().toString());
						cv.put("checkdate", tvConnect4.getText().toString());
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", cursorDiabetesAlertList3.getString(1));
						cv.put("alarm", alarmvalue[position1]+"");
						
						
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList3.getString(0) });
					}else{
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay4.getText().toString());
						cv.put("checkdate", tvConnect4.getText().toString());
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", "Y");
						cv.put("alarm", alarmvalue[position1]+"");
						
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView2.getId()==R.id.textView_config5_6_4){//�Ȱ��˻�
					
					if(tvConnect5.getText().toString().length()!=10){
						
						position1 = 0;						
						selectedView2.setText(alarmlist[position1]); 
						Toast.makeText(ActivityConfig_5.this, "�˻翹������ ������ �ּ���!" ,	Toast.LENGTH_SHORT).show();
					}
					
					sqlDiabetesAlertList3 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�Ȱ��˻�'";
					cursorDiabetesAlertList3 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList3, null);
					cursorDiabetesAlertList3.moveToFirst();
					
					if (cursorDiabetesAlertList3.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay5.getText().toString());
						cv.put("checkdate", tvConnect5.getText().toString());
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", cursorDiabetesAlertList3.getString(1));
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList3.getString(0) });
					}else{
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay5.getText().toString());
						cv.put("checkdate", tvConnect5.getText().toString());
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "Y");
						cv.put("alarm", alarmvalue[position1]+"");
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}
				
				cursorDiabetesAlertList3.close();
				
				SetNoti noti =  new SetNoti(getApplicationContext());
				noti.finalize();
				
				dialog.dismiss();				
			}
		});
		
		AlertDialog alert = alarmDialog.create();
		alert.show();  

	}
	
	
    TextView selectedView3;
	    
    OnClickListener listenerCheckDay = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			selectedView3 = (TextView) v;
//				Toast.makeText(ActivityConfig_5.this, "1" ,	Toast.LENGTH_SHORT).show();
			Calendar calendar = Calendar.getInstance();
			new DatePickerDialog(ActivityConfig_5.this, dateSetListener1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
			
		}
	};
	
	String sqlDiabetesAlertList2;
	Cursor cursorDiabetesAlertList2;
	
	DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
		
		@Override		
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

//				Toast.makeText(getApplicationContext(), "����.", Toast.LENGTH_SHORT).show();
			
			if(makeDate(year, monthOfYear, dayOfMonth).length()==10){
				
				if(selectedView3.getId()==R.id.textView_config5_2_5){//��ȭ
					
					
					sqlDiabetesAlertList2 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '��ȭ������'";
					cursorDiabetesAlertList2 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList2, null);
					
					if (cursorDiabetesAlertList2.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay1.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm1.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList2.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList2.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay1.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "��ȭ������");
						cv.put("checkcycle", "3");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm1.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView3.getId()==R.id.textView_config5_3_5){//�ܹ�
					
					
					sqlDiabetesAlertList2 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�ܹ鴢�˻�'";
					cursorDiabetesAlertList2 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList2, null);
					
					if (cursorDiabetesAlertList2.getCount() > 0) { //tvConnect1	
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay2.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm2.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList2.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList2.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay2.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "�ܹ鴢�˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm2.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView3.getId()==R.id.textView_config5_4_5){//��������
									
					sqlDiabetesAlertList2 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '���������˻�'";
					cursorDiabetesAlertList2 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList2, null);
					
					if (cursorDiabetesAlertList2.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay3.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm3.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList2.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList2.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay3.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "���������˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm3.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView3.getId()==R.id.textView_config5_5_5){//ũ����Ƽ��
					
					
					sqlDiabetesAlertList2 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '����ũ����Ƽ��'";
					cursorDiabetesAlertList2 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList2, null);
					
					if (cursorDiabetesAlertList2.getCount() > 0) { //tvConnect1		
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay4.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm4.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList2.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList2.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay4.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "����ũ����Ƽ��");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm4.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}
					
				}else if(selectedView3.getId()==R.id.textView_config5_6_5){//�Ȱ��˻�
					
					
					sqlDiabetesAlertList2 = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�Ȱ��˻�'";
					cursorDiabetesAlertList2 = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList2, null);
					
					if (cursorDiabetesAlertList2.getCount() > 0) { //tvConnect1
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay5.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm5.getText().toString().replace("����", ""));
						
						cursorDiabetesAlertList2.moveToFirst();
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { cursorDiabetesAlertList2.getString(0) });
					}else{
						
						Calendar calendar = Calendar.getInstance();
						calendar.set(Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(0, 4)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(4, 6)), 
								Integer.parseInt(makeDate(year, monthOfYear, dayOfMonth).replace("/", "").toString().substring(6, 8)));
						calendar.add(calendar.MONTH, 3);
						String checkdate = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
						
						ContentValues cv = new ContentValues();
						cv.put("lastcheckdate", tvLastCheckDay5.getText().toString());
						cv.put("checkdate", makeDate(year, monthOfYear, dayOfMonth));
						cv.put("checknm", "�Ȱ��˻�");
						cv.put("checkcycle", "12");
						cv.put("hospital", "N");
						cv.put("alarm", tvAlarm5.getText().toString().replace("����", ""));
						
						ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
					}					
				}				
				cursorDiabetesAlertList2.close();				
			}
			
			
			selectedView3.setText(makeDate(year, monthOfYear, dayOfMonth));
			
		}
	}; 
    
    
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

	void createThreadAndDialog() {
		/* ProgressDialog */
		loagindDialog = ProgressDialog
				.show(this, "", "Loading...", true, false);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// �ð��ɸ��� ó��
				getDiabetesCheckCycle();
//				getHeightWeight(); 
				handler.sendEmptyMessage(0);
			}
		});
		thread.start();
	}
	
	
	
	HashMap<String, String> item1;

	public void getDiabetesCheckCycle() { 

		if (!ActivityLogin.item.get("PatientID").toString().equals("")) 
		{
			HospitalInterface hospitalInterface = new HospitalInterface();

			HashMap<String, String> item = ActivityLogin.item;

			// Log.d("id", "result1111: " + item.get("PatientID").toString());

			String result1 = hospitalInterface.getDiabetesAlertWithThread(
					"DiabetesCheckCycle", item.get("PatientID").toString());

//			Log.d("test1", "result12345: " + result1);
			GetDiabetesAlertHandler handler1 = new GetDiabetesAlertHandler();
			try {
				Xml.parse(result1, handler1);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			tMainActivity.LOGIN_FLAG = false;
//			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
//					Toast.LENGTH_SHORT).show();
		}
	}
	
	

	HashMap<String, String> item2;
	String sqlDiabetesAlertList = "";
	Cursor cursorDiabetesAlertList;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // ���̾�α� ����
			
//			Toast.makeText(getApplicationContext(), GetDiabetesAlertHandler.HB_COUNT + " ���� �����Ͱ� �߰��Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			
			if(ActivityLogin.LOGIN_FLAG == true){
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
				
				button_hosp.setOnClickListener(new OnClickListener() { // ������������

					@Override
					public void onClick(View v) {
						
						AlertDialog alert = new AlertDialog.Builder(ActivityConfig_5.this)					
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
//												createThreadAndDialog();
												
												hospitalConnectOn();
											} else {

												Intent i = new Intent(
														ActivityConfig_5.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
										}else{
											Toast.makeText(ActivityConfig_5.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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

			
			
			sqlDiabetesAlertList = "select * from DIABETESCHECKCYCLE";
			cursorDiabetesAlertList = sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
			
			
//			Toast.makeText(getApplicationContext(), cursorDiabetesAlertList.getCount() + "vvv", Toast.LENGTH_SHORT).show();
			

			if (cursorDiabetesAlertList.getCount() != 0) {
				
				cursorDiabetesAlertList.moveToFirst();
				
				do {
					
					if(cursorDiabetesAlertList.getString(3).equals("��ȭ������")){
						tvLastCheckDay1.setText(cursorDiabetesAlertList.getString(1));
						tvConnect1.setText(cursorDiabetesAlertList.getString(2));						
						tvAlarm1.setText(alarmlist[Integer.parseInt(cursorDiabetesAlertList.getString(6))]);
					
					}else if(cursorDiabetesAlertList.getString(3).equals("�ܹ鴢�˻�")){
						tvLastCheckDay2.setText(cursorDiabetesAlertList.getString(1));
						tvConnect2.setText(cursorDiabetesAlertList.getString(2));
						tvAlarm2.setText(alarmlist[Integer.parseInt(cursorDiabetesAlertList.getString(6))]);
						
					}else if(cursorDiabetesAlertList.getString(3).equals("���������˻�")){
						tvLastCheckDay3.setText(cursorDiabetesAlertList.getString(1));
						tvConnect3.setText(cursorDiabetesAlertList.getString(2));
						tvAlarm3.setText(alarmlist[Integer.parseInt(cursorDiabetesAlertList.getString(6))]);
						
					}else if(cursorDiabetesAlertList.getString(3).equals("����ũ����Ƽ��")){
						tvLastCheckDay4.setText(cursorDiabetesAlertList.getString(1));
						tvConnect4.setText(cursorDiabetesAlertList.getString(2));
						tvAlarm4.setText(alarmlist[Integer.parseInt(cursorDiabetesAlertList.getString(6))]);
						
					}else if(cursorDiabetesAlertList.getString(3).equals("�Ȱ��˻�")){
						tvLastCheckDay5.setText(cursorDiabetesAlertList.getString(1));
						tvConnect5.setText(cursorDiabetesAlertList.getString(2));
						tvAlarm5.setText(alarmlist[Integer.parseInt(cursorDiabetesAlertList.getString(6))]);
						
					}


				} while (cursorDiabetesAlertList.moveToNext());
				
			}else {
				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.", Toast.LENGTH_SHORT).show();
			}
				
			cursorDiabetesAlertList.close();

//			// view ����
//			HashMap<String, String> item1 = ActivityConfig_1.ArrayListPersonalInfo
//					.get(0);
//
//			// view ����
//			for (int i = 0; i < tActivity1_1.ArrayListVitalsignHeight.size(); i++) {
//
//				item2 = tActivity1_1.ArrayListVitalsignHeight
//						.get(i);
//
//				if (Double.parseDouble(item2.get("height").toString()) != 0.0
//						& Double.parseDouble(item2.get("weight").toString()) != 0) {
//					break;
//				}
//
//			}
//			
//			if ( tActivity1_1.ArrayListVitalsignHeight.size() == 0) {
//				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
//						Toast.LENGTH_SHORT).show();
//			}else{
//				
//				if (item1.get("Age").toString().equals("")
//						& item2.get("weight").toString().equals("")) {
//					Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
//							Toast.LENGTH_SHORT).show();
//				} else {
//					etAge.setText(item1.get("Age").toString());
//					etWeight.setText(item2.get("weight").toString());
//				}
//				
//			}


		}
	};
	
//	public void setText(TextView tv, String txt){
//		tv.setText(txt);
//	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		
		sqLiteDatabase.close();
		dbHelpepr.close();
		
		
	}

}