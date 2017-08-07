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

public class Activity3_CVD extends Activity {
	/** Called when the activity is first created. */

	public static ArrayList<HashMap<String, String>> ArrayListVitalsignCholesterol = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> ArrayListVitalsignBloodPressure = new ArrayList<HashMap<String, String>>();
	public static HashMap<String, String> radio = new HashMap<String, String>(); 

	EditText etName;
	EditText etAge;
	RadioGroup rgSex;

	EditText etTotalChol;
	EditText etHDL;
	EditText etSBP;
	
	RadioButton rbMale;
	RadioButton rbFemale;
	
	RadioButton rbSmoke;
	RadioButton rbNosmoke;
	
	RadioButton rbDiabetes;
	RadioButton rbNodiabetes;
	
	static Button button_hosp;
	
	private BroadcastReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer3_cvd);
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);

		etName = (EditText) findViewById(R.id.edittext_3_1_name);
		etAge = (EditText) findViewById(R.id.edittext_3_1_age);
		rgSex = (RadioGroup) findViewById(R.id.radiogroup_3_1_1);

		etTotalChol = (EditText) findViewById(R.id.edittext_3_1_totalchol);
		etHDL = (EditText) findViewById(R.id.edittext_3_1_hdl);
		etSBP = (EditText) findViewById(R.id.edittext_3_1_sbp);
		
		rbMale = (RadioButton) findViewById(R.id.radio_male_3_1);
		rbFemale = (RadioButton) findViewById(R.id.radio_female_3_1);
		
		rbSmoke = (RadioButton) findViewById(R.id.radio_smoke_y_3_1);
		rbNosmoke = (RadioButton) findViewById(R.id.radio_smoke_n_3_1);
		
		rbDiabetes = (RadioButton) findViewById(R.id.radio_diabetes_y_3_1);
		rbNodiabetes = (RadioButton) findViewById(R.id.radio_diabetes_n_3_1);
		

		Button button1 = (Button) findViewById(R.id.button_layer3_1_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});

		button_hosp = (Button) findViewById(R.id.button_3_1_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(ULNetworkReceiver.NETWORK_LIVE==true){
					if (ActivityLogin.LOGIN_FLAG) {
//						createThreadAndDialog();
						hospitalConnectOn();
					} else {

						Intent i = new Intent(
								Activity3_CVD.this,
								kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
						// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivityForResult(i, 1);

					}
				}else{
					Toast.makeText(Activity3_CVD.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		
		Button button_result = (Button) findViewById(R.id.button_3_1_result);

		button_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(etName.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
				}			
				else if ((!rbMale.isChecked()&&!rbFemale.isChecked())) {
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
				}else if(etAge.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
					
				}else if(etHDL.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
					.setTitle("�˸�")
					.setMessage("HDL�� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				}else if(etTotalChol.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
					.setTitle("�˸�")
					.setMessage("���ݷ����׷��� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					
				}else if(etSBP.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
					
				}else if((!rbSmoke.isChecked()&&!rbNosmoke.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
					
				}else if((!rbDiabetes.isChecked()&&!rbNodiabetes.isChecked())){
					
					AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
				}
				else 
				{
					if(rbSmoke.isChecked())
					{
						radio.put("smoke", "Y");
					}
					else if(rbNosmoke.isChecked())
					{
						radio.put("smoke", "N");
					}
					
					if(rbDiabetes.isChecked())
					{
						radio.put("Diabetes", "Y");
					}
					else if(rbNodiabetes.isChecked())
					{
						radio.put("Diabetes", "N");
					}
					
					if(rbMale.isChecked())
					{
						radio.put("Gender", "M");
					}
					else if(rbFemale.isChecked())
					{
						radio.put("Gender", "F");
					}
					
					radio.put("Name", etName.getText().toString());
					radio.put("Age", etAge.getText().toString());
					radio.put("TotalChol", etTotalChol.getText().toString());
					radio.put("HDL", etHDL.getText().toString());
					radio.put("SBP", etSBP.getText().toString());
					
					
					
					
					Intent intent = new Intent(Activity3_CVD.this,
							kr.co.imcc.app.uDiabetesNote.Activity3_CVD_Result.class);
					startActivity(intent);
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
			Toast.makeText(Activity3_CVD.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
		}

	}
	
	Dialog resultDialog;

	public void showResultDialog() {
		
		int point = 0;
		
		resultDialog = new Dialog(this);
		resultDialog.setTitle("�������� ��ȯ ���赵");
		resultDialog.setContentView(R.layout.heartresult);
		
		// �˰���
		if(rbMale.isChecked()){ //����
				
			//����
			if(35 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 39){
				point += 2;
			}else if(40 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 44){
				point += 5;
			}else if(45 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 49){
				point += 6;
			}else if(50 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 54){
				point += 8;
			}else if(55 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 59){
				point += 10;
			}else if(60 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 64){
				point += 11;
			}else if(65 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 69){
				point += 12;
			}else if(70 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 74){
				point += 14;
			}else if(75 <= Integer.parseInt(etAge.getText().toString())){
				point += 15;
			}
			
			//HDL
			if(60 <= (int)Double.parseDouble(etHDL.getText().toString())){
				point -= 2;
			}else if(50 <= (int)Double.parseDouble(etHDL.getText().toString()) && (int)Double.parseDouble(etHDL.getText().toString()) <= 59){
				point -= 1;
			}else if(35 <= (int)Double.parseDouble(etHDL.getText().toString()) && (int)Double.parseDouble(etHDL.getText().toString()) <= 44){
				point += 1;
			}else if((int)Double.parseDouble(etHDL.getText().toString()) <= 34){
				point += 2;
			}
			
			//���ݷ����׷�
			if(160 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 199){
				point += 1;
			}else if(200 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 239){
				point += 2;
			}else if(240 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 279){
				point += 3;
			}else if(280 <= (int)Double.parseDouble(etTotalChol.getText().toString())){
				point += 4;
			}
			
			//SBP
			if((int)Double.parseDouble(etSBP.getText().toString()) <= 119){
				point -= 2;
			}else if(130 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 139){
				point += 1;
			}else if(140 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 159){
				point += 2;
			}else if(160 <= (int)Double.parseDouble(etSBP.getText().toString())){
				point += 3;
			}
			
			//��
			if(rbSmoke.isChecked()){
				point += 4;
			}
						
			//�索
			if(rbDiabetes.isChecked()){
				point += 3;
			}
				
			
			TextView tvResult = (TextView) resultDialog.findViewById(R.id.textview_3_1_heart_result);
			String risk="";
			
			//���
			if(point<=-3){
				risk = "1% ����";
			}else if(point==-2){
				risk = "1.10%";
			}else if(point==-1){
				risk = "1.40%";
			}else if(point==0){
				risk = "1.60%";
			}else if(point==1){
				risk = "1.90%";
			}else if(point==2){
				risk = "2.30%";
			}else if(point==3){
				risk = "2.80%";
			}else if(point==4){
				risk = "3.30%";
			}else if(point==5){
				risk = "3.90%";
			}else if(point==6){
				risk = "4.70%";
			}else if(point==7){
				risk = "5.60%";
			}else if(point==8){
				risk = "6.70%";
			}else if(point==9){
				risk = "7.90%";
			}else if(point==10){
				risk = "9.40%";
			}else if(point==11){
				risk = "11.20%";
			}else if(point==12){
				risk = "13.20%";
			}else if(point==13){
				risk = "15.60%";
			}else if(point==14){
				risk = "18.40%";
			}else if(point==15){
				risk = "21.60%";
			}else if(point==16){
				risk = "25.30%";
			}else if(point==17){
				risk = "29.40%";
			}else if(18<=point){
				risk = "30% �̻�";
			}
			
			tvResult.setText("����� �������� ��ȯ ���赵�� " + risk + " �Դϴ�.");
			
		}else{ //����
			
			//����
			if(35 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 39){
				point += 2;
			}else if(40 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 44){
				point += 4;
			}else if(45 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 49){
				point += 5;
			}else if(50 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 54){
				point += 7;
			}else if(55 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 59){
				point += 8;
			}else if(60 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 64){
				point += 9;
			}else if(65 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 69){
				point += 10;
			}else if(70 <= Integer.parseInt(etAge.getText().toString()) && Integer.parseInt(etAge.getText().toString()) <= 74){
				point += 11;
			}else if(75 <= Integer.parseInt(etAge.getText().toString())){
				point += 12;
			}
			
			//HDL
			if(60 <= (int)Double.parseDouble(etHDL.getText().toString())){
				point -= 2;
			}else if(50 <= (int)Double.parseDouble(etHDL.getText().toString()) && (int)Double.parseDouble(etHDL.getText().toString()) <= 59){
				point -= 1;
			}else if(35 <= (int)Double.parseDouble(etHDL.getText().toString()) && (int)Double.parseDouble(etHDL.getText().toString()) <= 44){
				point += 1;
			}else if((int)Double.parseDouble(etHDL.getText().toString()) <= 34){
				point += 2;
			}
			
			//���ݷ����׷�
			if(160 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 199){
				point += 1;
			}else if(200 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 239){
				point += 3;
			}else if(240 <= (int)Double.parseDouble(etTotalChol.getText().toString()) && (int)Double.parseDouble(etTotalChol.getText().toString()) <= 279){
				point += 4;
			}else if(280 <= (int)Double.parseDouble(etTotalChol.getText().toString())){
				point += 5;
			}
			
			//SBP
			if((int)Double.parseDouble(etSBP.getText().toString()) <= 119){
				point -= 2;
			}else if(130 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 139){
				point += 1;
			}else if(140 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 149){
				point += 2;
			}else if(150 <= (int)Double.parseDouble(etSBP.getText().toString()) && (int)Double.parseDouble(etSBP.getText().toString()) <= 159){
				point += 4;
			}else if(160 <= (int)Double.parseDouble(etSBP.getText().toString())){
				point += 5;
			}
			
			//��
			if(rbSmoke.isChecked()){
				point += 3;
			}
						
			//�索
			if(rbDiabetes.isChecked()){
				point += 4;
			}
				
			
			TextView tvResult = (TextView) resultDialog.findViewById(R.id.textview_3_1_heart_result);
			String risk="";
			
			//���
			if(point<=-2){
				risk = "1% ����";
			}else if(point==-1){
				risk = "1.00%";
			}else if(point==0){
				risk = "1.20%";
			}else if(point==1){
				risk = "1.50%";
			}else if(point==2){
				risk = "1.70%";
			}else if(point==3){
				risk = "2.00%";
			}else if(point==4){
				risk = "2.40%";
			}else if(point==5){
				risk = "2.80%";
			}else if(point==6){
				risk = "3.30%";
			}else if(point==7){
				risk = "3.90%";
			}else if(point==8){
				risk = "4.50%";
			}else if(point==9){
				risk = "5.30%";
			}else if(point==10){
				risk = "6.30%";
			}else if(point==11){
				risk = "7.30%";
			}else if(point==12){
				risk = "8.60%";
			}else if(point==13){
				risk = "10.00%";
			}else if(point==14){
				risk = "11.70%";
			}else if(point==15){
				risk = "13.70%";
			}else if(point==16){
				risk = "15.90%";
			}else if(point==17){
				risk = "18.50%";
			}else if(point==18){
				risk = "21.50%";
			}else if(point==19){
				risk = "24.80%";
			}else if(point==20){
				risk = "28.50%";
			}else if(21<=point){
				risk = "30% �̻�";
			}
			
			tvResult.setText("����� �������� ��ȯ ���赵�� " + risk + " �Դϴ�.");
		}
		
		
		Button btExit = (Button) resultDialog.findViewById(R.id.button_3_1_result_result);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				resultDialog.dismiss();
			}
		});

		resultDialog.show();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Toast.makeText(getApplicationContext(), "�α��ο� ����",
		// Toast.LENGTH_SHORT).show();

		if (resultCode == RESULT_OK) {
			if (requestCode == 1) 
			{
				createThreadAndDialog();
			}
		}
	}

	private ProgressDialog loagindDialog; // Loading Dialog

	void createThreadAndDialog() {
		/* ProgressDialog */
		loagindDialog = ProgressDialog.show(this, "", "Loading...", true, false);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// �ð��ɸ��� ó��
				getPersonalInfo();
				getCholesterol();
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
						
						AlertDialog alert = new AlertDialog.Builder(Activity3_CVD.this)					
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
														Activity3_CVD.this,
														kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
												// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

												startActivityForResult(i, 1);

											}
											
										}else{
											Toast.makeText(Activity3_CVD.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
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
				HashMap<String, String> item2 = ActivityConfig_1.ArrayListPersonalInfo.get(0);

				etName.setText(item2.get("PatientName").toString());
				etAge.setText(item2.get("Age").toString());

				if (item2.get("Gender").toString().equals("F")) {
					rgSex.check(R.id.radio_female_3_1);
				} else {
					rgSex.check(R.id.radio_male_3_1);
				}

				// view ����
				for (int i = 0; i < Activity3_CVD.ArrayListVitalsignCholesterol.size(); i++) 
				{

					item1 = Activity3_CVD.ArrayListVitalsignCholesterol
							.get(i);
					
					if(!item1.get("totalchol").toString().equals("") && !item1.get("hdl").toString().equals(""))
					{
						
						if (Double.parseDouble(item1.get("totalchol").toString()) != 0.0
								& Double.parseDouble(item1.get("hdl").toString()) != 0.0) 
						{
							break;
						}					
					}
				}

				if (Activity3_CVD.ArrayListVitalsignCholesterol.size() == 0) {
					Toast.makeText(getApplicationContext(), "�ݷ����׷� �����Ͱ� �����ϴ�.",
							Toast.LENGTH_SHORT).show();
				} else {
					etTotalChol.setText(item1.get("totalchol").toString());
					etHDL.setText(item1.get("hdl").toString());
				
				}

				// view ����
				for (int i = 0; i < Activity3_CVD.ArrayListVitalsignBloodPressure
						.size(); i++) {

					item3 = Activity3_CVD.ArrayListVitalsignBloodPressure
							.get(i);

					if (Double.parseDouble(item3.get("bpsystolic").toString()) != 0.0) {
						break;
					}

				}
				
				if (Activity3_CVD.ArrayListVitalsignBloodPressure.size() == 0) {
					Toast.makeText(getApplicationContext(), "���� �����Ͱ� �����ϴ�.",
							Toast.LENGTH_SHORT).show();
				} else {
					etSBP.setText(item3.get("bpsystolic").toString());
				}				
			}
		}
	};
	

	public void getPersonalInfo() {

		if (!ActivityLogin.item.get("PatientID")
				.toString().equals("")) {

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

	public void getCholesterol() 
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
			strInputXMLBuffer.append("<Type>VitalsignCholesterol</Type>");
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
						
			GetVitalsignCholesterolHandler handler1 = new GetVitalsignCholesterolHandler();
			
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
			Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.",
					Toast.LENGTH_SHORT).show();
		}
	}

	HashMap<String, String> item3;

	public void getBloodPressure() {

		if (!ActivityLogin.item.get("PatientID")
				.toString().equals("")) {

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