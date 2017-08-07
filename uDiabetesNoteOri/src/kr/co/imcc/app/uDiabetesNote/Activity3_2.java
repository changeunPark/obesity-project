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

public class Activity3_2 extends Activity {
	/** Called when the activity is first created. */
	
	EditText etAge;
	RadioGroup rgSex;
	EditText etSBP;
	
	RadioButton rbMale;
	RadioButton rbFemale;
	
	RadioButton rbSmoke;
	RadioButton rbNosmoke;
	
	RadioButton rbDiabetes;
	RadioButton rbNodiabetes;
	
	RadioButton rbCVD;
	RadioButton rbNoCVD;
	
	RadioButton rbAF;
	RadioButton rbNoAF;
	
	RadioButton rbLVH;
	RadioButton rbNoLVH;
	
	static Button button_hosp;
	
	private BroadcastReceiver receiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer3_2);
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);

		
		etAge = (EditText) findViewById(R.id.edittext_3_2_age);
		rgSex = (RadioGroup) findViewById(R.id.radiogroup_3_2_1);
		etSBP = (EditText) findViewById(R.id.edittext_3_2_sbp);
		
		rbMale = (RadioButton) findViewById(R.id.radio_male_3_2);
		rbFemale = (RadioButton) findViewById(R.id.radio_female_3_2);
		
		rbSmoke = (RadioButton) findViewById(R.id.radio_smoke_y_3_2);
		rbNosmoke = (RadioButton) findViewById(R.id.radio_smoke_n_3_2);
		
		rbDiabetes = (RadioButton) findViewById(R.id.radio_diabetes_y_3_2);
		rbNodiabetes = (RadioButton) findViewById(R.id.radio_diabetes_n_3_2);
				
		rbCVD = (RadioButton) findViewById(R.id.radio_cvd_y_3_2);
		rbNoCVD = (RadioButton) findViewById(R.id.radio_cvd_n_3_2);
		
		rbAF = (RadioButton) findViewById(R.id.radio_af_y_3_2);
		rbNoAF = (RadioButton) findViewById(R.id.radio_af_n_3_2);
		
		rbLVH = (RadioButton) findViewById(R.id.radio_lvh_y_3_2);
		rbNoLVH = (RadioButton) findViewById(R.id.radio_lvh_n_3_2);

		Button button1 = (Button) findViewById(R.id.button_layer3_2_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});

		button_hosp = (Button) findViewById(R.id.button_3_2_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

					@Override
					public void onClick(View v) {
						
						if(ULNetworkReceiver.NETWORK_LIVE==true){
							if (ActivityLogin.LOGIN_FLAG) {
//								createThreadAndDialog();
								hospitalConnectOn();
							} else {

								Intent i = new Intent(
										Activity3_2.this,
										kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
								// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

								startActivityForResult(i, 1);

							}
						}else{
							Toast.makeText(Activity3_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
						}

					}
				});
		
		
		Button button_result = (Button) findViewById(R.id.button_3_2_result);

		button_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if ((!rbMale.isChecked()&&!rbFemale.isChecked())) {
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
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
				} else if(etAge.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
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
					
				} else if(etSBP.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
					.setTitle("�˸�")
					.setMessage("SBP(�ְ�����)�� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				} else if((!rbSmoke.isChecked()&&!rbNosmoke.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
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
					
				} else if((!rbDiabetes.isChecked()&&!rbNodiabetes.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
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
					
				} else if((!rbCVD.isChecked()&&!rbNoCVD.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
					.setTitle("�˸�")
					.setMessage("CVD(��������ȯ)���ΰ� ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				} else if((!rbAF.isChecked()&&!rbNoAF.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
					.setTitle("�˸�")
					.setMessage("AF(�ɹ漼��)���ΰ� ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				} else if((!rbLVH.isChecked()&&!rbNoLVH.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
					.setTitle("�˸�")
					.setMessage("LVH(�½ɽǺ��)���ΰ� ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				} else {
					showResultDialog();
				}

			}
		});
		
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
			Toast.makeText(Activity3_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	Dialog resultDialog;

	public void showResultDialog() {
		
		int point = 0;
		
		resultDialog = new Dialog(this);
		resultDialog.setTitle("������ ���赵");
		resultDialog.setContentView(R.layout.brainresult);
		
		// �˰���
		if(rbMale.isChecked()){ //����
				
			//����
			if(57 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 59){
				point += 1;
			}else if(60 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 62){
				point += 2;
			}else if(63 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 65){
				point += 3;
			}else if(66 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 68){
				point += 4;
			}else if(69 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 72){
				point += 5;
			}else if(73 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 75){
				point += 6;
			}else if(76 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 78){
				point += 7;
			}else if(79 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 81){
				point += 8;
			}else if(82 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 84){
				point += 9;
			}else if(85 <= Integer.parseInt(etAge.getText().toString())){
				point += 10;
			}
						
			//SBP
			if(106 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 115){
				point += 1;
			}else if(116 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 125){
				point += 2;
			}else if(126 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 135){
				point += 3;
			}else if(136 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 145){
				point += 4;
			}else if(146 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 155){
				point += 5;
			}else if(156 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 165){
				point += 6;
			}else if(166 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 175){
				point += 7;
			}else if(176 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 185){
				point += 8;
			}else if(186 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 195){
				point += 9;
			}else if(196 <= (int)Double.parseDouble(etSBP.getText().toString())){
				point += 10;
			}
			
			//��
			if(rbSmoke.isChecked()){
				point += 3;
			}
						
			//�索
			if(rbDiabetes.isChecked()){
				point += 2;
			}
			
			//CVD
			if(rbCVD.isChecked()){
				point += 4;
			}
			
			//AF
			if(rbAF.isChecked()){
				point += 4;
			}
			
			//LVH
			if(rbLVH.isChecked()){
				point += 5;
			}
				
			
			TextView tvResult = (TextView) resultDialog.findViewById(R.id.textview_3_2_heart_result_1);
			String risk="";
			
			//���			
			if(point==1){
				risk = "3%";
			}else if(point==2){
				risk = "3%";
			}else if(point==3){
				risk = "4%";
			}else if(point==4){
				risk = "4%";
			}else if(point==5){
				risk = "5%";
			}else if(point==6){
				risk = "5%";
			}else if(point==7){
				risk = "6%";
			}else if(point==8){
				risk = "7%";
			}else if(point==9){
				risk = "8%";
			}else if(point==10){
				risk = "10%";
			}else if(point==11){
				risk = "11%";
			}else if(point==12){
				risk = "13%";
			}else if(point==13){
				risk = "15%";
			}else if(point==14){
				risk = "17%";
			}else if(point==15){
				risk = "20%";
			}else if(point==16){
				risk = "22%";
			}else if(point==17){
				risk = "26%";
			}else if(point==18){
				risk = "29%";
			}else if(point==19){
				risk = "33%";
			}else if(point==20){
				risk = "37%";
			}else if(point==21){
				risk = "42%";
			}else if(point==22){
				risk = "47%";
			}else if(point==23){
				risk = "52%";
			}else if(point==24){
				risk = "57%";
			}else if(point==25){
				risk = "63%";
			}else if(point==26){
				risk = "68%";
			}else if(point==27){
				risk = "74%";
			}else if(point==28){
				risk = "79%";
			}else if(point==29){
				risk = "84%";
			}else if(30<=point){
				risk = "88% �̻�";
			}
			
			tvResult.setText("����� ������ ���赵�� " + risk + " �Դϴ�.");
			
		}else{ //����
			
			//����
			if(57 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 59){
				point += 1;
			}else if(60 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 62){
				point += 2;
			}else if(63 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 64){
				point += 3;
			}else if(65 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 67){
				point += 4;
			}else if(68 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 70){
				point += 5;
			}else if(71 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 73){
				point += 6;
			}else if(74 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 76){
				point += 7;
			}else if(77 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 78){
				point += 8;
			}else if(79 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 81){
				point += 9;
			}else if(82 <= Integer.parseInt(etAge.getText().toString())){
				point += 10;
			}
						
			//SBP
			if(95 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 106){
				point += 1;
			}else if(107 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 118){
				point += 2;
			}else if(119 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 130){
				point += 3;
			}else if(131 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 143){
				point += 4;
			}else if(144 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 155){
				point += 5;
			}else if(156 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 167){
				point += 6;
			}else if(168 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 180){
				point += 7;
			}else if(181 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 192){
				point += 8;
			}else if(193 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 204){
				point += 9;
			}else if(205 <= (int)Double.parseDouble(etSBP.getText().toString())){
				point += 10;
			}
			
			//��
			if(rbSmoke.isChecked()){
				point += 3;
			}
						
			//�索
			if(rbDiabetes.isChecked()){
				point += 3;
			}
			
			//CVD
			if(rbCVD.isChecked()){
				point += 2;
			}
			
			//AF
			if(rbAF.isChecked()){
				point += 6;
			}
			
			//LVH
			if(rbLVH.isChecked()){
				point += 5;
			}
				
			
			TextView tvResult = (TextView) resultDialog.findViewById(R.id.textview_3_2_heart_result_1);
			String risk="";
			
			//���			
			if(point==1){
				risk = "1%";
			}else if(point==2){
				risk = "1%";
			}else if(point==3){
				risk = "2%";
			}else if(point==4){
				risk = "2%";
			}else if(point==5){
				risk = "2%";
			}else if(point==6){
				risk = "3%";
			}else if(point==7){
				risk = "4%";
			}else if(point==8){
				risk = "4%";
			}else if(point==9){
				risk = "5%";
			}else if(point==10){
				risk = "6%";
			}else if(point==11){
				risk = "8%";
			}else if(point==12){
				risk = "9%";
			}else if(point==13){
				risk = "11%";
			}else if(point==14){
				risk = "13%";
			}else if(point==15){
				risk = "16%";
			}else if(point==16){
				risk = "19%";
			}else if(point==17){
				risk = "23%";
			}else if(point==18){
				risk = "27%";
			}else if(point==19){
				risk = "32%";
			}else if(point==20){
				risk = "37%";
			}else if(point==21){
				risk = "43%";
			}else if(point==22){
				risk = "50%";
			}else if(point==23){
				risk = "57%";
			}else if(point==24){
				risk = "64%";
			}else if(point==25){
				risk = "71%";
			}else if(point==26){
				risk = "78%";
			}else if(27<=point){
				risk = "84% �̻�";
			}
			
			tvResult.setText("����� ������ ���赵�� " + risk + " �Դϴ�.");
		}
		
		
		Button btExit = (Button) resultDialog.findViewById(R.id.button_3_2_result_result_1);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resultDialog.dismiss();
			}
		});

		resultDialog.show();
	}
	
	
	
	public void getPersonalInfo() {

		if (!ActivityLogin.item.get("PatientID").toString().equals("")) {

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

		} else {
			tMainActivity.LOGIN_FLAG = false;
			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
					Toast.LENGTH_SHORT).show();
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
				getBloodPressure();
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
						
						AlertDialog alert = new AlertDialog.Builder(Activity3_2.this)					
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
														Activity3_2.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
										}else{
											Toast.makeText(Activity3_2.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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

			if (ActivityConfig_1.ArrayListPersonalInfo.size() == 0) {
				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
			} else {
				// view ����
				HashMap<String, String> item2 = ActivityConfig_1.ArrayListPersonalInfo
						.get(0);

				etAge.setText(item2.get("Age").toString());

				if (item2.get("Gender").toString().equals("F")) {
					rgSex.check(R.id.radio_female_3_2);
				} else {
					rgSex.check(R.id.radio_male_3_2);
				}

				// view ����
			/*	for (int i = 0; i < tActivity1_4.ArrayListVitalsignBloodPressure
						.size(); i++) {

					item3 = tActivity1_4.ArrayListVitalsignBloodPressure
							.get(i);

					if (Double.parseDouble(item3.get("bpsystolic").toString()) != 0.0) {
						break;
					}

				}
				
				if (tActivity1_4.ArrayListVitalsignBloodPressure.size() == 0) {
					Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
							Toast.LENGTH_SHORT).show();
				} else {
					etSBP.setText(item3.get("bpsystolic").toString());
				}*/

			}
		
		}
	};
	
	
	HashMap<String, String> item3;

	public void getBloodPressure() 
	{
		if (!ActivityLogin.item.get("PatientID").toString().equals("")) 
		{
			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			String startDate = "1999-01-01";//������
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			Date date = new Date();
			String endDate = dateFormat.format(date);//������

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
								
			//xml������Ʈ
			Log.d("test", "request: " + strInputXMLBuffer.toString());
			String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
			Log.d("test", "result1234: " + result1);
			
			GetVitalsignBloodHandler handler1 = new GetVitalsignBloodHandler();
			try {
				Xml.parse(result1, handler1);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			tMainActivity.LOGIN_FLAG = false;
			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
					Toast.LENGTH_SHORT).show();
		}

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	
}