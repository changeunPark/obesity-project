package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity5_temp extends Activity {
	/** Called when the activity is first created. */

	EditText etAge;
	EditText etWeight;
	EditText etMet;
	
	static Button button_hosp;
	
	private BroadcastReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer5);
		
//		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout5_1_1_1_1);
//		layout.setFocusable(true);
//		layout.setFocusableInTouchMode(true);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		/** ��Ʈ��ũ ���� ǥ�� */
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);


		etAge = (EditText) findViewById(R.id.edittext_5_1_agevalue);
		etWeight = (EditText) findViewById(R.id.edittext_5_3_agevalue);

		final TextView tvGoal = (TextView) findViewById(R.id.textview_5_1_goal);
		final TextView tvEnergy = (TextView) findViewById(R.id.textview_5_1_goal_1);

		final EditText etTime = (EditText) findViewById(R.id.edittext_5_2_agevalue);
		etMet = (EditText) findViewById(R.id.edittext_5_4_agevalue);
		
//		etAge.setInputType(0);
//		etWeight.setInputType(0);
//		et1.setInputType(0);
//		et2.setInputType(0);
//
//		etAge.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				etAge = (EditText) findViewById(R.id.edittext_5_1_agevalue);
//				etAge.setInputType(1);
//			}
//		});
//		
//		etWeight.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				etWeight = (EditText) findViewById(R.id.edittext_5_3_agevalue);
//				etWeight.setInputType(1);
//			}
//		});
//		
//		et1.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				EditText et1 = (EditText) findViewById(R.id.edittext_5_2_agevalue);
//				et1.setInputType(1);
//			}
//		});
//		
//		et2.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				EditText et2 = (EditText) findViewById(R.id.edittext_5_4_agevalue);
//				et2.setInputType(1);
//			}
//		});

		// Button button1 = (Button) findViewById(R.id.button_layer5_1);
		// Button button2 = (Button) findViewById(R.id.button_layer5_2);
		//
		// button1.setOnClickListener(new OnClickListener() { // � ��ǥ �ƹڼ�
		//
		// @Override
		// public void onClick(View v) {
		//
		// Intent intent = new Intent(tActivity3_1.this,
		// kr.co.imcc.app.dmmanager.Activity5_1.class);
		// startActivity(intent);
		//
		// }
		// });
		//
		// button2.setOnClickListener(new OnClickListener() { // ��� ����
		//
		// @Override
		// public void onClick(View v) {
		//
		// Intent intent = new Intent(tActivity3_1.this,
		// kr.co.imcc.app.dmmanager.Activity5_2.class);
		// startActivity(intent);
		//
		// }
		// });

		Button button_back = (Button) findViewById(R.id.button_layer5_back);

		button_back.setOnClickListener(new OnClickListener() { // back

					@Override
					public void onClick(View v) {

						onBackPressed();

					}
				});

		button_hosp = (Button) findViewById(R.id.button_5_1_hosp);

		button_hosp.setOnClickListener(new OnClickListener() { // back

					@Override
					public void onClick(View v) {
						
						if(ULNetworkReceiver.NETWORK_LIVE==true){
							if (ActivityLogin.LOGIN_FLAG) {
								createThreadAndDialog();
							} else {

								Intent i = new Intent(
										Activity5_temp.this,
										kr.co.imcc.app.uDiabetesNote.ActivityLogin.class);
								// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

								startActivityForResult(i, 1);

							}
						}else{
							Toast.makeText(Activity5_temp.this, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
						}

					}
				});

		Button button_result1 = (Button) findViewById(R.id.button_layer5_result1);

		button_result1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etAge.getText().toString().equals("")) {
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(Activity5_temp.this)					
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
				} else {
					String minPulse = (int) ((220 - Integer.parseInt(etAge
							.getText().toString())) * 0.6 + 0.5) + "";
					String maxPulse = (int) ((220 - Integer.parseInt(etAge
							.getText().toString())) * 0.85 + 0.5) + "";

					tvGoal.setText("� ��ǥ �ƹڼ�: " + minPulse + " ~ " + maxPulse);
				}

			}
		});
		
		
		Button button_met = (Button) findViewById(R.id.button_layer5_met_1);
		
		button_met.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showMetDialog();
			}
		});
		
		
		Button button_result2 = (Button) findViewById(R.id.button_layer5_result1_1);
		
		button_result2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (etTime.getText().toString().equals("")) {
//					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(Activity5_temp.this)					
					.setTitle("�˸�")
					.setMessage("��ð��� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
				} else if(etWeight.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity5_temp.this)					
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
					.show();
					
				} else if(etMet.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(Activity5_temp.this)					
					.setTitle("�˸�")
					.setMessage("��������Һ���հ��� �Էµ��� �ʾҽ��ϴ�.")
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
					
					tvEnergy.setText("�ҿ� ������: " + (int)(Double.parseDouble(etMet.getText().toString())*(Double.parseDouble(etTime.getText().toString())/60)*Double.parseDouble(etWeight.getText().toString())+0.5) + " kcal");

				}
			}
		});
		
		
		Button button_ref1 = (Button) findViewById(R.id.button_layer5_reference1);
		
		button_ref1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showRef1Dialog();
			}
		});
		
		
		
		Button button_ref2 = (Button) findViewById(R.id.button_layer5_reference1_1);
		
		
		button_ref2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showRef2Dialog();
			}
		});

	}
	
	
	Dialog ref1Dialog;
	public void showRef1Dialog() {
		ref1Dialog = new Dialog(this);
		ref1Dialog.setTitle("���ɿ� ���� ��ǥ �ƹڼ�");
		ref1Dialog.setContentView(R.layout.exeref1);
		
		Button btExit = (Button) ref1Dialog.findViewById(R.id.button_5_1_result_result);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ref1Dialog.dismiss();
			}
		});
		
	
		
		ref1Dialog.show();
	}
	
	
	Dialog ref2Dialog;
	public void showRef2Dialog() {
		ref2Dialog = new Dialog(this);
		ref2Dialog.setTitle("���� 100kcal �Һ� �ð�");
		ref2Dialog.setContentView(R.layout.exeref2);
		
		Button btExit = (Button) ref2Dialog.findViewById(R.id.button__5_ref2_1_result_result);
		
		btExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ref2Dialog.dismiss();
			}
		});
		
		
		ref2Dialog.show();
	}
	
	AlertDialog.Builder metDialog;
	
	int position;
	double[] metvalue = {1.5,4,6,7,6,9,6,5,7,3,6,3,5.1,6};
	
	public void showMetDialog() {
		
		metDialog = new AlertDialog.Builder(this);
		metDialog.setTitle("��� ����");
		
		final CharSequence[] metlist = {"ħ��ü��", "�ȱ�", "����", "���", "�ٳѱ�(60~100ȸ/��)", "�ٳѱ�(100~1400ȸ/��)",
				"��ܿ�����", "������ Ÿ��", "����κ� ����","����", "�״Ͻ�", "����","����", "����"}; 
		
		metDialog.setSingleChoiceItems(metlist, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int pos) {
						
				position = pos;
				etMet.setText(metvalue[position]+"");
				dialog.dismiss();				
			}
		});
		
		AlertDialog alert = metDialog.create();
		alert.show();  

	}
	

	public void getPersonalInfo() {

		if (!ActivityLogin.ArrayListLoginInfo.get(0).get("PatientID")
				.toString().equals("")) {

			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			HashMap<String, String> item = ActivityLogin.ArrayListLoginInfo
					.get(0);

			// Log.d("id", "result1111: " + item.get("PatientID").toString());

			String result1 = hospitalInterface.getPersonalInfoWithThread(
					"BasicPersonalInformation", "10", ActivityLogin.etLoginID
							.getText().toString(), item.get("PatientID")
							.toString());
			// Log.d("test", "result1234: " + result1);
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

	HashMap<String, String> item1;

	public void getHeightWeight() {

		if (!ActivityLogin.ArrayListLoginInfo.get(0).get("PatientID")
				.toString().equals("")) {

			HospitalInterface hospitalInterface = new HospitalInterface();

			tMainActivity.LOGIN_FLAG = true;

			HashMap<String, String> item = ActivityLogin.ArrayListLoginInfo
					.get(0);

			// Log.d("id", "result1111: " + item.get("PatientID").toString());

			String result1 = hospitalInterface.getVitalSignWithThread(
					"VitalsignHeight", ActivityLogin.strLoginID,
					item.get("PatientID").toString(), "20050101", "20111207");

			// Log.d("test", "result1234: " + result1);
			GetVitalsignHeightHandler handler1 = new GetVitalsignHeightHandler();
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
				getHeightWeight();
				handler.sendEmptyMessage(0);
			}
		});
		thread.start();
	}

	HashMap<String, String> item2;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loagindDialog.dismiss(); // ���̾�α� ����

			// view ����
			HashMap<String, String> item1 = ActivityConfig_1.ArrayListPersonalInfo
					.get(0);

			// view ����
			for (int i = 0; i < tActivity1_1.ArrayListVitalsignHeight.size(); i++) {

				item2 = tActivity1_1.ArrayListVitalsignHeight
						.get(i);

				if (Double.parseDouble(item2.get("height").toString()) != 0.0
						& Double.parseDouble(item2.get("weight").toString()) != 0) {
					break;
				}

			}
			
			if ( tActivity1_1.ArrayListVitalsignHeight.size() == 0) {
				Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
						Toast.LENGTH_SHORT).show();
			}else{
				
				if (item1.get("Age").toString().equals("")
						& item2.get("weight").toString().equals("")) {
					Toast.makeText(getApplicationContext(), "�����Ͱ� �����ϴ�.",
							Toast.LENGTH_SHORT).show();
				} else {
					etAge.setText(item1.get("Age").toString());
					etWeight.setText(item2.get("weight").toString());
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