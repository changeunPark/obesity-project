package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityConfig_1 extends Activity {
	/** Called when the activity is first created. */

	public static ArrayList<HashMap<String, String>> ArrayListPersonalInfo = new ArrayList<HashMap<String, String>>();
	
	private BroadcastReceiver receiver;

	EditText etName;
	EditText etAge;
	RadioGroup rgSex;
	RadioGroup rgSmoke;
	RadioGroup rgDiabetes;
	
	RadioButton rbMale;
	RadioButton rbFemale;
	
	RadioButton rbSmoke;
	RadioButton rbNoSmoke;
		
	RadioButton rbDiabetes;
	RadioButton rbNoDiabetes;
	
	static Button button_hosp;
		
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	String sqlPersonalInfo1= "";
	Cursor cursorPersonalInfo1;
	
	String sqlPersonalInfo= "";
	Cursor cursorPersonalInfo;
	
	String sqlDeletePersonalInfo= "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.config_1);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		etName = (EditText) findViewById(R.id.edittext_config_1_name);
		etAge = (EditText) findViewById(R.id.edittext_config_1_age);
		rgSex = (RadioGroup) findViewById(R.id.radiogroup_config_1_1);
		rgSmoke = (RadioGroup) findViewById(R.id.radiogroup_config_1_2);
		rgDiabetes = (RadioGroup) findViewById(R.id.radiogroup_config_1_3);
		Button btSave = (Button) findViewById(R.id.button_config_1_save);
		
		rbMale = (RadioButton) findViewById(R.id.radio_male);
		rbFemale = (RadioButton) findViewById(R.id.radio_female);
		
		rbSmoke = (RadioButton) findViewById(R.id.radio_smoke_y);
		rbNoSmoke = (RadioButton) findViewById(R.id.radio_smoke_n);
		
		rbDiabetes = (RadioButton) findViewById(R.id.radio_diabetes_y);
		rbNoDiabetes = (RadioButton) findViewById(R.id.radio_diabetes_n);
		
		Button button1 = (Button) findViewById(R.id.button_config_1_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});

		button_hosp = (Button) findViewById(R.id.button_config_1_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {	
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if(ActivityLogin.LOGIN_FLAG){
//						createThreadAndDialog(); 
						hospitalConnectOn();
					}else{
						
						Intent i = new Intent(ActivityConfig_1.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivityForResult(i, 1);				
					}					
				}else
				{
					Toast.makeText(ActivityConfig_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etName.getText().toString().equals("")){
//					Toast.makeText(getApplicationContext(), "�̸��� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
					.setTitle("�˸�")
					.setMessage("�̸��� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}else if(!rbMale.isChecked()&&!rbFemale.isChecked()){
//					Toast.makeText(getApplicationContext(), "������ �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
					.setTitle("�˸�")
					.setMessage("������ ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}else if(etAge.getText().toString().equals("")){
//					Toast.makeText(getApplicationContext(), "���̸� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
					.setTitle("�˸�")
					.setMessage("���̰� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}else if(!rbSmoke.isChecked()&&!rbNoSmoke.isChecked()){
//					Toast.makeText(getApplicationContext(), "���̸� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
					.setTitle("�˸�")
					.setMessage("�����ΰ� ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}else if(!rbDiabetes.isChecked()&&!rbNoDiabetes.isChecked()){
//					Toast.makeText(getApplicationContext(), "���̸� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
					.setTitle("�˸�")
					.setMessage("�索���ΰ� ���õ��� �ʾҽ��ϴ�.")
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
					
					sqlPersonalInfo1 = "select * from PERSONALINFO";
					cursorPersonalInfo1 = sqLiteDatabase.rawQuery(sqlPersonalInfo1, null);
					
					String sex="";
					
					if(rbMale.isChecked()){
						sex = "��";
					}else if(rbFemale.isChecked()){
						sex = "��";
					}
					
					String smoke="";
					
					if(rbSmoke.isChecked()){
						smoke = "Y";
					}else if(rbNoSmoke.isChecked()){
						smoke = "N";
					}
					
					String diabetes="";
					
					if(rbDiabetes.isChecked()){
						diabetes = "Y";
					}else if(rbNoDiabetes.isChecked()){
						diabetes = "N";
					}
					
					
					if(cursorPersonalInfo1.getCount()>0){ //update
						
						cursorPersonalInfo1.moveToFirst();						
						String _id = cursorPersonalInfo1.getString(0);
						
						try {
							updateDB(etName.getText().toString(), sex, etAge.getText().toString(), smoke, diabetes, "", "", _id);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{ //insert
						try {
							insertDB(etName.getText().toString(), sex, etAge.getText().toString(), smoke, diabetes, "", "");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}			
				
			}
		});
		
		Button btDelete = (Button) findViewById(R.id.button_config_1_delete);

		btDelete.setOnClickListener(new OnClickListener() { // delete

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "�����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
				
				etName.setText("");
				etAge.setText("");
				
				rgSex.clearCheck();
				rgSmoke.clearCheck();
				rgDiabetes.clearCheck();			
				
				sqlDeletePersonalInfo = "delete from PERSONALINFO";
				sqLiteDatabase.execSQL(sqlDeletePersonalInfo);
				
			}
		});
		
		
		sqlPersonalInfo = "select * from PERSONALINFO";
		cursorPersonalInfo = sqLiteDatabase.rawQuery(sqlPersonalInfo, null);
//		cursorPersonalInfo.moveToFirst();
		
		if(cursorPersonalInfo.getCount()>0){
			cursorPersonalInfo.moveToFirst();
//			Toast.makeText(getApplicationContext(), cursorPersonalInfo.getString(0)+"", Toast.LENGTH_SHORT).show();
			
			etName.setText(cursorPersonalInfo.getString(1));
			etAge.setText(cursorPersonalInfo.getString(3));
			
			if(cursorPersonalInfo.getString(2).equals("��")){
				rgSex.check(R.id.radio_male);
			}else if(cursorPersonalInfo.getString(2).equals("��")){
				rgSex.check(R.id.radio_female);
			}
			
			if(cursorPersonalInfo.getString(4).equals("Y")){
				rgSmoke.check(R.id.radio_smoke_y);
			}else if(cursorPersonalInfo.getString(4).equals("N")){
				rgSmoke.check(R.id.radio_smoke_n);
			}
			
			if(cursorPersonalInfo.getString(5).equals("Y")){
				rgDiabetes.check(R.id.radio_diabetes_y);
			}else if(cursorPersonalInfo.getString(5).equals("N")){
				rgDiabetes.check(R.id.radio_diabetes_n);
			}
			
		}
		
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
			} 
			
		}else{
			Toast.makeText(ActivityConfig_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	public void deleteDB() throws Exception {
		sqLiteDatabase.delete("BLOODSUGAR", null, null);
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
	
	
	public void insertDB(String name, String sex, String age, String smoke, String diabetes, String bloodsugar_target_max, String bloodsugar_target_min) throws Exception{
		
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("sex", sex);
		cv.put("age", age);
		cv.put("smoke", smoke);
		cv.put("diabetes", diabetes);
//		cv.put("bloodsugar_target_max", "");
//		cv.put("bloodsugar_target_min", "");
		
		sqLiteDatabase.insert("PERSONALINFO", "", cv);
//		Toast.makeText(getApplicationContext(), sqLiteDatabase.insert("PERSONALINFO", "", cv)+"", Toast.LENGTH_SHORT).show();
	}
	
	public void updateDB(String name, String sex, String age, String smoke, String diabetes, String bloodsugar_target_max, String bloodsugar_target_min, String _id) throws Exception{
		
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("sex", sex);
		cv.put("age", age);
		cv.put("smoke", smoke);
		cv.put("diabetes", diabetes);
//		cv.put("bloodsugar_target_max", "");
//		cv.put("bloodsugar_target_min", "");
		
		sqLiteDatabase.update("PERSONALINFO", cv, "_id=?", new String[] { _id });
//		Toast.makeText(getApplicationContext(), sqLiteDatabase.update("PERSONALINFO", cv, "name=?", new String[] { name })+"update", Toast.LENGTH_SHORT).show();
		
	}
	
	
	public void getPersonalInfo()
	{		
		if(!ActivityLogin.item.get("PatientID").toString().equals("")) 
		{
    		HospitalInterface hospitalInterface = new HospitalInterface();
    		
    		tMainActivity.LOGIN_FLAG = true;
    		
    		HashMap<String, String> item = ActivityLogin.item;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(System.currentTimeMillis()));
			String tempDate1 = new SimpleDateFormat("MMdd").format(cal.getTime());
			String tempDate2 = new SimpleDateFormat("ddMM").format(cal.getTime());
		    String date = Integer.toString(((Integer.parseInt(tempDate1)) * (Integer.parseInt(tempDate2))));

			Log.d("id", "result1111: " + item.get("PatientID").toString());

			StringBuffer strInputXMLBuffer = new StringBuffer();
								
			strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
			strInputXMLBuffer.append("<Type>PatientSearch</Type>");
			strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
			strInputXMLBuffer.append("<AccessCD>" + date  + "</AccessCD>");
			strInputXMLBuffer.append("<PatientID>" + item.get("PatientID") + "</PatientID>");
			strInputXMLBuffer.append("<SearchType>" + "All" + "</SearchType>");
			strInputXMLBuffer.append("</Request>");
								
			//xml������Ʈ
			Log.d("test", "request: " + strInputXMLBuffer.toString());
			String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());
			
    		GetPersonalInfoHandler handler1 = new GetPersonalInfoHandler();
    		
    		try {
    			Xml.parse(result1, handler1);
    		} catch (SAXException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}			
    	}
		else 
    	{
    		tMainActivity.LOGIN_FLAG = false;
    		Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.", Toast.LENGTH_SHORT).show();
    	}	
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
//    	Toast.makeText(getApplicationContext(), "�α��ο� ����", Toast.LENGTH_SHORT).show();
    	
    	
    	
    	if(resultCode==RESULT_OK){
    		if(requestCode==1){
    			
    			createThreadAndDialog();    			
    		}
    	}
    	
    }	
    
  
    private ProgressDialog loagindDialog; // Loading Dialog
    
    void createThreadAndDialog() {
        /* ProgressDialog */
         loagindDialog = ProgressDialog.show(this, "",
                "Loading...", true, false);
        
        Thread thread = new Thread(new Runnable() {
            @Override
			public void run() {
                // �ð��ɸ��� ó��
            	getPersonalInfo();
                handler.sendEmptyMessage(0);
            }
        });
        thread.start();
    }

    private Handler handler = new Handler() {
        @Override
		public void handleMessage(Message msg) {
            loagindDialog.dismiss(); // ���̾�α� ����
            
            if(ActivityLogin.LOGIN_FLAG == true){
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
				
				button_hosp.setOnClickListener(new OnClickListener() { // ������������

					@Override
					public void onClick(View v) {
						
						AlertDialog alert = new AlertDialog.Builder(ActivityConfig_1.this)					
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
											} 
											else 
											{

												Intent i = new Intent(
														ActivityConfig_1.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);
											}											
										}
										else
										{
											Toast.makeText(ActivityConfig_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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
            
            //view ����
            
    		
            if(ActivityConfig_1.ArrayListPersonalInfo.size()==0)
            {
            	Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
            }
            else
            {
            	HashMap<String, String> item1 = ActivityConfig_1.ArrayListPersonalInfo
        				.get(0);
            	
            	etName.setText(item1.get("PatientName").toString());
        		etAge.setText(item1.get("Age").toString());

        		if (item1.get("Gender").toString().equals("F")) {
        			rgSex.check(R.id.radio_female);
        		} else {
        			rgSex.check(R.id.radio_male);
        		}         	
            }
            
    		rgSmoke.clearCheck();
    		rgDiabetes.clearCheck();    		
        }
    };

    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

//
//    @Override
//    protected void onResume() {
//    	// TODO Auto-generated method stub
//    	super.onResume();
//    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//    }

	

}