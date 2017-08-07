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

public class tActivity1_1 extends Activity {
	/** Called when the activity is first created. */

	public static ArrayList<HashMap<String, String>> ArrayListVitalsignHeight = new ArrayList<HashMap<String, String>>();
	static Button button_hosp;
	EditText etHeight;
	EditText etWeight;
	RadioButton rbFemale;
	RadioButton rbMale;
	private BroadcastReceiver receiver;
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_1);
		
        actionBar = getActionBar();
		actionBar.setTitle("비만도 측정");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true); 

		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);

//		Button button1 = (Button) findViewById(R.id.button_layer2_1_back);

		etHeight = (EditText) findViewById(R.id.edittext_2_1_height);
		etWeight = (EditText) findViewById(R.id.edittext_2_1_weight);


		rbMale = (RadioButton) findViewById(R.id.radio_male);
		rbFemale = (RadioButton) findViewById(R.id.radio_female);
		/*button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
*/	/*	public boolean onCreateOptionMenu(Menu menu){
			super.onCreateOptionsMEsnu(menu);
			MenuInf
		}*/
		//������ư
	/*	button_hosp = (Button) findViewById(R.id.button_2_1_hosp);	
		button_hosp.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if (ActivityLogin.LOGIN_FLAG) {
						//createThreadAndDialog();
						hospitalConnectOn();
					} else {

						Intent i = new Intent(
								tActivity1_1.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);

					}
				}else{
					Toast.makeText(tActivity1_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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
					
				/*	AlertDialog alert = new AlertDialog.Builder(tActivity1_1.this)					
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

					/*AlertDialog alert = new AlertDialog.Builder(tActivity1_1.this)					
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
				else if(!rbMale.isChecked()&&!rbFemale.isChecked()){
					
					Toast.makeText(getApplicationContext(), "성별을 입력해주세요", 1000).show();
				}
				else
				{
					showResultDialog();
				}							
			}
		});
//		hospitalConnectOnLoad();
	}
	
	/*
	public void hospitalConnectOn(){		
		
		createThreadAndDialog();
		
	}*/
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	        finish();
	        
	    }
	    return true;
	}	
	
	/*
	
	public void hospitalConnectOnLoad(){
		if(ULNetworkReceiver.NETWORK_LIVE==true){
			
			if (ActivityLogin.LOGIN_FLAG) {
				button_hosp.setBackgroundResource(R.drawable.hospital_on2);
								
				hospitalConnectOn();
			} 
			
		}else{
			Toast.makeText(tActivity1_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	public static void test(){
		button_hosp.setClickable(true);
		
	}
	*/
	
	Dialog resultDialog;
	public void showResultDialog() {
		resultDialog = new Dialog(this);
		resultDialog.setTitle("�񸸵� �˻�");
		resultDialog.setContentView(R.layout.weightresult1);
		
		Button btExit = (Button) resultDialog.findViewById(R.id.button_2_1_result_result);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resultDialog.dismiss();
			}
		});
		
		//�񸸵� ���
		Double height = Double.parseDouble(etHeight.getText().toString());
		Double weight = Double.parseDouble(etWeight.getText().toString());
		Double idealWeight;
		Double overWeightPercent;
		String overWeight="";
		
		if(height > 160.0){
			idealWeight = (height-100.0)*0.9;
		}else if(height > 150.0 & height <= 160.0 ){
			idealWeight = (height-150.0)*0.5 + 50.0;
		}else { //150.0 ����
			idealWeight = height-100.0;
		}
		
		overWeightPercent = (weight / idealWeight) * 100;
		
		if(overWeightPercent <= 90.0){
			overWeight = "저체중";
		}else if(overWeightPercent>90.0 & overWeightPercent <= 110.0){
			overWeight = "정상";
		}else if(overWeightPercent>110.0 & overWeightPercent <= 120.0){
			overWeight = "과체중";
		}else if(overWeightPercent>120.0){
			overWeight = "비만";
		}
		//
		
		String strIdealWeight = longDouble2String(1, idealWeight);
		String strOverWeightPercent = longDouble2String(1, overWeightPercent);
		
		Intent intent = new Intent(tActivity1_1.this, kr.co.imcc.app.uDiabetesNote.tActivity1_1_1.class);
		intent.putExtra("idealWeight", strIdealWeight);
		intent.putExtra("overWeightPercent", strOverWeightPercent);
		intent.putExtra("overWeight", overWeight);
		
		startActivity(intent);	
		//tvIdealWeight.setText(idealWeight+"Kg");
		//tvOverWeight.setText(((int)(overWeightPercent*10.0))/10.0+"% ("+overWeight+")");
	}
	

	public String longDouble2String(int size, double value) 
	{

        NumberFormat nf = NumberFormat.getNumberInstance();

        nf.setMaximumFractionDigits(size);

        nf.setGroupingUsed(false);

        return nf.format(value);
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
						
						AlertDialog alert = new AlertDialog.Builder(tActivity1_1.this)					
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
														tActivity1_1.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);
											}
										}
										else
										{
											Toast.makeText(tActivity1_1.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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