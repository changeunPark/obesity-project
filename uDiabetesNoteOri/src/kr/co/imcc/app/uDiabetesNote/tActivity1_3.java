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

public class tActivity1_3 extends Activity {
	/** Called when the activity is first created. */

	public static ArrayList<HashMap<String, String>> ArrayListVitalsignWaist = new ArrayList<HashMap<String, String>>();

	EditText etWaist;
	EditText etHip;
	RadioGroup rgSex;
	
	static Button button_hosp;
	private BroadcastReceiver receiver;
    private ActionBar actionBar;

	RadioButton rbMale;
	RadioButton rbFemale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity1_3);
		
		actionBar = getActionBar();
 		actionBar.setTitle("복부 비만도 측정");
 		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);

		/*Button button1 = (Button) findViewById(R.id.button_layer2_3_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});*/

		etWaist = (EditText) findViewById(R.id.edittext_2_3_waist);
		etHip = (EditText) findViewById(R.id.edittext_2_3_hip);
		rgSex = (RadioGroup) findViewById(R.id.radiogroup_2_3_1);
		
		rbMale = (RadioButton) findViewById(R.id.radio_male_2_3);
		rbFemale = (RadioButton) findViewById(R.id.radio_female_2_3);
/*
		button_hosp = (Button) findViewById(R.id.button_2_3_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

					@Override
					public void onClick(View v) {//��������
						
						if(ULNetworkReceiver.NETWORK_LIVE==true){
							if (ActivityLogin.LOGIN_FLAG) {
//								createThreadAndDialog();
								hospitalConnectOn();
							} else {

								Intent i = new Intent(
										tActivity1_3.this,
										kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
								// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

								startActivityForResult(i, 1);

							}
						}else{
							Toast.makeText(tActivity1_3.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
						}

					}
				});
*/
		ImageButton button_result = (ImageButton) findViewById(R.id.button_2_3_result);

		button_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {//�������

				if (etWaist.getText().toString().equals("")) {
					
					
					Toast.makeText(getApplicationContext(), "허리둘레를 입력해주세요", 1000).show();

					/*
					AlertDialog alert = new AlertDialog.Builder(tActivity1_3.this)					
					.setTitle("�˸�")
					.setMessage("�㸮�ѷ��� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();*/
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
				}else if(etHip.getText().toString().equals("")){
					
					Toast.makeText(getApplicationContext(), "엉덩이 둘레를 입력해주세요", 1000).show();

				/*	AlertDialog alert = new AlertDialog.Builder(tActivity1_3.this)					
					.setTitle("�˸�")
					.setMessage("�����̵ѷ��� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();*/
				}else if(!rbMale.isChecked()&&!rbFemale.isChecked()){
					
					Toast.makeText(getApplicationContext(), "성별을 입력해주세요", 1000).show();

					/*
					AlertDialog alert = new AlertDialog.Builder(tActivity1_3.this)					
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
					.show();*/
				}else {
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
			Toast.makeText(tActivity1_3.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	

	Dialog resultDialog;

	public void showResultDialog() {
		resultDialog = new Dialog(this);
		resultDialog.setTitle("���� �񸸵� �˻�");
		resultDialog.setContentView(R.layout.overweightresult);

		Button btExit = (Button) resultDialog
				.findViewById(R.id.button_2_3_result_result);

		btExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				resultDialog.dismiss();
			}
		});

		// ���� �񸸵� ���
		Double waist = Double.parseDouble(etWaist.getText().toString());
		Double hip = Double.parseDouble(etHip.getText().toString());
		Double whr;
		String overWeight = "";

		whr = waist / hip;

		if (rgSex.getCheckedRadioButtonId() == R.id.radio_male_2_3) { // ����

			if (whr > 1.0) {
				overWeight = "복부비만";
			} else {
				overWeight = "정상";
			}

		} else {// ����

			if (whr > 0.85) {
				overWeight = "복부비만";
			} else {
				overWeight = "정상";
			}
		}
		
		String strwhr = longDouble2String(2, whr);

		Intent intent = new Intent(tActivity1_3.this, kr.co.imcc.app.uDiabetesNote.tActivity1_3_1.class);
		intent.putExtra("whr", strwhr);
		intent.putExtra("overWeight", overWeight);
		
		startActivity(intent);	
		//TextView tvWHR = (TextView) resultDialog.findViewById(R.id.textview_2_3_overweight_result);
		//tvWHR.setText("����� ���� �񸸵��� " + overWeight + " �Դϴ�.");
		//resultDialog.show();
	}
	
	public String longDouble2String(int size, double value) 
	{

        NumberFormat nf = NumberFormat.getNumberInstance();

        nf.setMaximumFractionDigits(size);

        nf.setGroupingUsed(false);

        return nf.format(value);
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

	public void getWaistHip() 
	{
		if (!ActivityLogin.item.get("PatientID").toString().equals("")) 
		{
			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			HashMap<String, String> item = ActivityLogin.item;
			
			String startDate = "1999-01-01";//������
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			Date date = new Date();
			String endDate = dateFormat.format(date);//������

			Log.d("id", "result1111: " + item.get("PatientID").toString());

			StringBuffer strInputXMLBuffer = new StringBuffer();
								
			strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
			strInputXMLBuffer.append("<Type>VitalsignWaist</Type>");
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
	
			GetVitalsignWaistHandler handler1 = new GetVitalsignWaistHandler();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		// Toast.makeText(getApplicationContext(), "�α��ο� ����",
		// Toast.LENGTH_SHORT).show();

		if (resultCode == RESULT_OK) 
		{
			if (requestCode == 1) 
			{
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
				getWaistHip();
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
						
						AlertDialog alert = new AlertDialog.Builder(tActivity1_3.this)					
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
												
//												hospitalConnectOn();
											} else {

												Intent i = new Intent(
														tActivity1_3.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
										}else{
											Toast.makeText(tActivity1_3.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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
			
			if((ActivityConfig_1.ArrayListPersonalInfo.size() == 0) && (ArrayListVitalsignWaist.size()== 0))
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
		    			rgSex.check(R.id.radio_female_2_3);
		    		} 
					else 
		    		{
		    			rgSex.check(R.id.radio_male_2_3);
		    		}
				}	
				
				if(ArrayListVitalsignWaist.size()!= 0)
				{
					// view ����
					for (int i = 0; i < ArrayListVitalsignWaist.size(); i++) 
					{
						item1 = ArrayListVitalsignWaist
								.get(i);
						if (Double.parseDouble(item1.get("waist").toString()) != 0.0 || Double.parseDouble(item1.get("hip").toString()) != 0.0) 
						{
							break;
						}
					}
					etWaist.setText(item1.get("waist").toString());
					etHip.setText(item1.get("hip").toString());		
				}											
			}
		}
	};
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}