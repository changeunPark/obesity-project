package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityConfig_3 extends Activity {
    /** Called when the activity is first created. */
	
	public static ArrayList<HashMap<String, String>> ArrayListBeginClinic = new ArrayList<HashMap<String, String>>();

	EditText etJindan;
	EditText etWeight;
	EditText etHeight;
	EditText etBmi;
	EditText etWaist;
	EditText etHba1c;
	EditText etBP1;
	EditText etBP2;
	
	static Button button_hosp;
	
	private BroadcastReceiver receiver;
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	String sqlBeginInfo= "";
	Cursor cursorBeginInfo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.config_3);
        
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        
        /** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
        
        dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
        
        etJindan = (EditText) findViewById(R.id.edittext_config_3_jindan);
        etHeight = (EditText) findViewById(R.id.edittext_config_3_height);
        etWeight = (EditText) findViewById(R.id.edittext_config_3_weight);
		etBmi = (EditText) findViewById(R.id.edittext_config_3_bmi);
	    etWaist = (EditText) findViewById(R.id.edittext_config_3_waist);
		etHba1c = (EditText) findViewById(R.id.edittext_config_3_hba1c);
		etBP1 = (EditText) findViewById(R.id.edittext_config_3_bp);
		etBP2 = (EditText) findViewById(R.id.edittext_config_3_bp_2);

        
        Button button1 = (Button) findViewById(R.id.button_config_3_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
		
		TextView tvKgm2 = (TextView) findViewById(R.id.textview_config_3_kgm2);
		tvKgm2.setText(Html.fromHtml("kg/m<sup>2</sup>"));
		
//		button_hosp = (Button) findViewById(R.id.button_config_3_hosp);
//
//		button_hosp.setOnClickListener(new OnClickListener() { // back
//
//					@Override
//					public void onClick(View v) {
//						
//						if(ULNetworkReceiver.NETWORK_LIVE==true){
//							if (ActivityLogin.LOGIN_FLAG) {
//								createThreadAndDialog();
//							} else {
//
//								Intent i = new Intent(
//										ActivityConfig_3.this,
//										kr.co.imcc.app.dmmanager.ActivityLogin.class);
//								// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//								startActivityForResult(i, 1);
//
//							}
//						}else{
//							Toast.makeText(ActivityConfig_3.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//						}
//
//					}
//				});
	
		Button btSave = (Button) findViewById(R.id.button_config_3_save);

		btSave.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(etJindan.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_3.this)					
					.setTitle("�˸�")
					.setMessage("������������ �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				}else{
					
					Toast.makeText(getApplicationContext(), "����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
					
					sqlBeginInfo = "select * from BEGININFO";
					cursorBeginInfo = sqLiteDatabase.rawQuery(sqlBeginInfo, null);
					
					if(cursorBeginInfo.getCount()>0){
						
						try {
							updateDB(etJindan.getText().toString(), etHeight.getText().toString(), etWeight.getText().toString(),
									etBmi.getText().toString(), etWaist.getText().toString(), etHba1c.getText().toString(), 
									etBP1.getText().toString(), etBP2.getText().toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						try {
							insertDB(etJindan.getText().toString(), etHeight.getText().toString(), etWeight.getText().toString(),
									etBmi.getText().toString(), etWaist.getText().toString(), etHba1c.getText().toString(), 
									etBP1.getText().toString(), etBP2.getText().toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}				
				
			}
		});
		
		
		Button btDelete = (Button) findViewById(R.id.button_config_3_delete);

		btDelete.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "�����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
				
				etJindan.setText("");
				etWeight.setText("");
				etHeight.setText("");
				etBmi.setText("");
				etWaist.setText("");
				etHba1c.setText("");
				etBP1.setText("");
				etBP2.setText("");				
					

				try {
					updateDB("", "", "", "", "", "", "", "");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		sqlBeginInfo = "select * from BEGININFO";
		cursorBeginInfo = sqLiteDatabase.rawQuery(sqlBeginInfo, null);
		
		if(cursorBeginInfo.getCount()>0){ 
			
			cursorBeginInfo.moveToFirst();		
			
			etJindan.setText(cursorBeginInfo.getString(0));
			etWeight.setText(cursorBeginInfo.getString(2));
			etHeight.setText(cursorBeginInfo.getString(1));
			etBmi.setText(cursorBeginInfo.getString(3));
			etWaist.setText(cursorBeginInfo.getString(4));
			etHba1c.setText(cursorBeginInfo.getString(5));
			etBP1.setText(cursorBeginInfo.getString(6));
			etBP2.setText(cursorBeginInfo.getString(7));		
		}
		
		
		etJindan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				new DatePickerDialog(ActivityConfig_3.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
				
			}
		});
		
		etBmi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etHeight.getText().toString().equals("")||etWeight.getText().toString().equals("")){
					
					etBmi.setText("");
					
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_3.this)					
					.setTitle("�˸�")
					.setMessage("Ű�� �����Ը� �Է��Ͻø� �ڵ���� �˴ϴ�.")
					.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.", Toast.LENGTH_SHORT).show();
				}
				else{
//					//�񸸵� ���
//					Double heightMeter = Double.parseDouble(etHeight.getText().toString())/100.0;
//					Double weight = Double.parseDouble(etWeight.getText().toString());
//					Double bmi;
//					String overWeight="";
//					
//					bmi = weight/(heightMeter*heightMeter);
//					
//					etBmi.setText(((int)(bmi*10.0))/10.0+"");
				}			
			}
		});
		
		
		etWeight.addTextChangedListener(textWatcher);
		etHeight.addTextChangedListener(textWatcher);
		
    }
    
    TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if(!etHeight.getText().toString().equals("")&&!etWeight.getText().toString().equals("")){
				//�񸸵� ���
				Double heightMeter = Double.parseDouble(etHeight.getText().toString())/100.0;
				Double weight = Double.parseDouble(etWeight.getText().toString());
				Double bmi;
				String overWeight="";
				
				bmi = weight/(heightMeter*heightMeter);
				
				etBmi.setText(((int)(bmi*10.0))/10.0+"");
			}else{
				etBmi.setText("");
			}
			
		}
	};
    
    
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override		
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

//			Toast.makeText(getApplicationContext(), "����.", Toast.LENGTH_SHORT).show();
			etJindan.setText(makeDate(year, monthOfYear, dayOfMonth));
			
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
    
    public void insertDB(String firstdate, String height, String weight, String bmi, String waist, String hba1c, String bpd, String bps) throws Exception{
		
    	ContentValues cv = new ContentValues();
		cv.put("firstdate", firstdate);
		cv.put("height", height);
		cv.put("weight", weight);
		cv.put("bmi", bmi);
		cv.put("waist", waist);
		cv.put("hba1c", hba1c);
		cv.put("bpd", bpd);
		cv.put("bps", bps);
		
		sqLiteDatabase.insert("BEGININFO", "", cv);
//		Toast.makeText(getApplicationContext(), sqLiteDatabase.insert("PERSONALINFO", "", cv)+"", Toast.LENGTH_SHORT).show();
	}
    
	public void updateDB(String firstdate, String height, String weight, String bmi, String waist, String hba1c, String bpd, String bps) throws Exception{
			
			ContentValues cv = new ContentValues();
			cv.put("firstdate", firstdate);
			cv.put("height", height);
			cv.put("weight", weight);
			cv.put("bmi", bmi);
			cv.put("waist", waist);
			cv.put("hba1c", hba1c);
			cv.put("bpd", bpd);
			cv.put("bps", bps);
			
			sqLiteDatabase.update("BEGININFO", cv, null, null);
	//		Toast.makeText(getApplicationContext(), sqLiteDatabase.update("PERSONALINFO", cv, "name=?", new String[] { name })+"update", Toast.LENGTH_SHORT).show();
		
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
    
    public void getBeginClinic() {

		if (!ActivityLogin.ArrayListLoginInfo.get(0).get("PatientID")
				.toString().equals("")) {

			HospitalInterface hospitalInterface = new HospitalInterface();


			HashMap<String, String> item = ActivityLogin.ArrayListLoginInfo
					.get(0);

			// Log.d("id", "result1111: " + item.get("PatientID").toString());

			String result1 = hospitalInterface.getBeginClinicWithThread("FirstConsultation", ActivityLogin.strLoginID,
					item.get("PatientID").toString(), "", "ED");

			Log.d("test", "begin: " + result1);
			GetBeginClinicHandler handler1 = new GetBeginClinicHandler();
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
    
    
    private ProgressDialog loagindDialog; // Loading Dialog

	void createThreadAndDialog() {
		/* ProgressDialog */
		loagindDialog = ProgressDialog
				.show(this, "", "Loading...", true, false);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// �ð��ɸ��� ó��
				getBeginClinic();
				handler.sendEmptyMessage(0);
			}
		});
		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // ���̾�α� ����

//			// view ����
			
			if (ArrayListBeginClinic.size() == 0) {
				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
			} else {
				
				HashMap<String, String> item = ArrayListBeginClinic.get(0);
				
				etJindan.setText(item.get("recorddate").toString());
				etWeight.setText(item.get("weight").toString());
				etHeight.setText(item.get("height").toString());
				etBmi.setText("");
				etWaist.setText("");
				etHba1c.setText("");
				
				String[] pressure = {"",""}; 
				
				if(!item.get("bloodpressure").toString().equals("")){
					
					pressure = item.get("bloodpressure").toString().split("-");
					
					etBP1.setText(pressure[0]);
					etBP2.setText(pressure[1]);
					
				}
				
				
			}
			
			
			
//			for (int i = 0; i < ArrayListBeginClinic.size(); i++) {
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
//				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
//						Toast.LENGTH_SHORT).show();
//			}else{
//				etHeight.setText(item1.get("height").toString());
//				etWeight.setText(item1.get("weight").toString());
//			}
			
		}
	};
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
    
}