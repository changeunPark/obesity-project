package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity2_2 extends Activity {
	/** Called when the activity is first created. */

	public static ArrayList<HashMap<String, String>> ArrayListVitalsignHeight = new ArrayList<HashMap<String, String>>();
	static Button button_hosp;
	EditText etHeight;
	EditText etWeight;
	RadioGroup rgSex;
	RadioButton  rbMale;
	RadioButton  rbFemale;
	
	private BroadcastReceiver receiver;
	 private ActionBar actionBar;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layer6_need_calo);
		

		 actionBar = getActionBar();
			actionBar.setTitle("건강 칼로리");
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);


		etHeight = (EditText) findViewById(R.id.edittext_2_1_height);
		etWeight = (EditText) findViewById(R.id.edittext_2_1_weight);
		rgSex = (RadioGroup) findViewById(R.id.radiogroup_config_1_1);
		rbMale = (RadioButton) findViewById(R.id.radio_male);
		rbFemale = (RadioButton) findViewById(R.id.radio_female);


		/*Button button1 = (Button) findViewById(R.id.button_layer2_1_back);


		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});*/
		/*
		button_hosp = (Button) findViewById(R.id.button_2_1_hosp);
		
		button_hosp.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if (ActivityLogin.LOGIN_FLAG) {
//						createThreadAndDialog();
						hospitalConnectOn();
					} else {

						Intent i = new Intent(
								tActivity2_2.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);

					}
				}else{
					Toast.makeText(tActivity2_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		*/
//		if(!ULNetworkReceiver.NETWORK_STATUS){
//			button_hosp.setClickable(false);
//			Toast.makeText(getApplicationContext(), "�׸��� �ʾҽ��ϴ�.", Toast.LENGTH_SHORT).show();
//		}else{
//			button_hosp.setClickable(true);
//		}
		
		ImageButton button_result = (ImageButton) findViewById(R.id.button_2_1_result);
		
		button_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etHeight.getText().toString().equals("")){
					
					Toast.makeText(getApplicationContext(), "키를 입력해주세요", 1000).show();

					/*
					AlertDialog alert = new AlertDialog.Builder(tActivity2_2.this)					
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
					.show();*/
					
					//Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.", Toast.LENGTH_SHORT).show();
				}else if(etWeight.getText().toString().equals("")){
					
					
					Toast.makeText(getApplicationContext(), "몸무게를 입력해주세요", 1000).show();

					/*AlertDialog alert = new AlertDialog.Builder(tActivity2_2.this)					
					.setTitle("�˸�")
					.setMessage("�����԰� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();*/
				}
				else if((!rbMale.isChecked()&&!rbFemale.isChecked()))
				{
					Toast.makeText(getApplicationContext(), "성별을 입력해주세요", 1000).show();
				
					
					/*AlertDialog alert = new AlertDialog.Builder(tActivity2_2.this)					
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
					.show();*/
				}
				else
				{
					showResultDialog();
				}							
			}
		});
//		hospitalConnectOnLoad();
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
	
	/*public void hospitalConnectOn(){		
		
		createThreadAndDialog();
		
	}*/
	
	public void hospitalConnectOnLoad(){
		if(ULNetworkReceiver.NETWORK_LIVE==true){
			
			if (ActivityLogin.LOGIN_FLAG) {
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
								
//				hospitalConnectOn();
			} 
			
		}else{
			Toast.makeText(tActivity2_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	public static void test(){
		button_hosp.setClickable(true);
		
	}
	
	
	Dialog resultDialog;
	public void showResultDialog() {
		resultDialog = new Dialog(this);
		resultDialog.setTitle("건강 칼로리");
		resultDialog.setContentView(R.layout.need_cal);
		/*
		Button btExit = (Button) resultDialog.findViewById(R.id.button_2_1_result_result);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resultDialog.dismiss();
			}
		});
		*/
		//�񸸵� ���
		Double height = Double.parseDouble(etHeight.getText().toString());
		Double weight = Double.parseDouble(etWeight.getText().toString());
		RadioButton rbSex = (RadioButton) findViewById(rgSex.getCheckedRadioButtonId());
		String strSex = rbSex.getText().toString();
		
		DecimalFormat format = new DecimalFormat(".#");

		Double Calolie;
		
		if (strSex == "����") 
		{
			Calolie = (weight * 30); 
		}
		else 
		{
			Calolie = (weight * 30) ;
		}
		
		
			
		
		TextView tvIdealWeight = (TextView) resultDialog.findViewById(R.id.textview_2_1_height_result_1);
		
		tvIdealWeight.setText(format.format(Calolie)+"kcal");

		
		resultDialog.show();
	}
	

	public void getPersonalInfo() 
	{
		if (!ActivityLogin.item.get("PatientID").toString().equals("")) 
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
			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
					Toast.LENGTH_SHORT).show();
		}
	}

	HashMap<String, String> item1;

	public void getHeightWeight() 
	{
		String startDate = "1999-01-01";//������
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date date = new Date();
		String endDate = dateFormat.format(date);//������
		
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
								
			//xml������Ʈ
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
//			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
//					Toast.LENGTH_SHORT).show();
		}
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

	void createThreadAndDialog() {
		/* ProgressDialog */
		loagindDialog = ProgressDialog
				.show(this, "", "Loading...", true, false);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// �ð��ɸ��� ó��
				getPersonalInfo();
				getHeightWeight();
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
						
						AlertDialog alert = new AlertDialog.Builder(tActivity2_2.this)					
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
									public void onClick(View v) 
									{
										if(ULNetworkReceiver.NETWORK_LIVE==true)
										{
											if (ActivityLogin.LOGIN_FLAG) 
											{
//												createThreadAndDialog();
												
//												hospitalConnectOn();
											}
											else 
											{
												Intent i = new Intent(
														tActivity2_2.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);
											}
										}
										else
										{
											Toast.makeText(tActivity2_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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

			// view ����
			for (int i = 0; i <  tActivity1_1.ArrayListVitalsignHeight.size(); i++) 
			{
				item1 = tActivity1_1.ArrayListVitalsignHeight
						.get(i);

				if (Double.parseDouble(item1.get("height").toString()) != 0.0 & Double.parseDouble(item1.get("weight").toString()) != 0) 
				{
					break;
				}
			}
			
			if(tActivity1_1.ArrayListVitalsignHeight.size()==0)
			{
				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.", Toast.LENGTH_SHORT).show();
			}
			else
			{				
				if(item1.get("height").toString().equals("") & item1.get("weight").toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(ActivityConfig_1.ArrayListPersonalInfo.size() != 0)
					{
						HashMap<String, String> item2 = ActivityConfig_1.ArrayListPersonalInfo.get(0);
						// view ����
						if (item2.get("Gender").toString().equals("F")) 
						{
			    			rgSex.check(R.id.radio_female);
			    		} 
						else 
			    		{
			    			rgSex.check(R.id.radio_male);
			    		}
					}
					
					etHeight.setText(item1.get("height").toString());
					etWeight.setText(item1.get("weight").toString());
				}	
			}		
		}
	};
	
	@Override
	protected void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}