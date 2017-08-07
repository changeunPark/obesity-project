package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityBloodPressure_input extends Activity {
	/** Called when the activity is first created. */

	static String SelectedDate2 = "";
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bloodpressureinput);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);	
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
	    
		
		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		String date = SelectedDate2;
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title_1_4);
		tvTitle.setText(date);
		Button btInput = (Button) findViewById(R.id.button_5_input_save);
		final TimePicker tpInput = (TimePicker) findViewById(R.id.timePicker_input);

		final EditText etBloodSugar = (EditText) findViewById(R.id.textView_5_input_1_2);
		final EditText etsystolic = (EditText) findViewById(R.id.editbpsystolic);
		final EditText etMemo = (EditText) findViewById(R.id.textView_5_input_1_5);
				
		btInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etBloodSugar.getText().toString().equals("") || etsystolic.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(ActivityBloodPressure_input.this)					
					.setTitle("알림")
					.setMessage("혈압이 입력되지 않았습니다.")
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
				}
				else if(etsystolic.getText().toString().equals(""))
				{
					AlertDialog alert = new AlertDialog.Builder(ActivityBloodPressure_input.this)					
					.setTitle("알림")
					.setMessage("혈압이 입력되지 않았습니다.")
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
				}
				else {
					
					//ActivityBloodPressure.termflag = 1;
					String hour = "";
					String minute = "";

					if (tpInput.getCurrentHour() < 10) {
						hour = "0" + tpInput.getCurrentHour();
					} else {
						hour = "" + tpInput.getCurrentHour();
					}

					if (tpInput.getCurrentMinute() < 10) {
						minute = "0" + tpInput.getCurrentMinute();
					} else {
						minute = "" + tpInput.getCurrentMinute();
					}
					
					StringBuffer strInputXMLBuffer = new StringBuffer();
					
					HashMap<String, String> item = ActivityLogin.item;
					
					strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
					strInputXMLBuffer.append("<Type>VitalsignBloodPressureAdd</Type>");
					strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
					strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
					strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
					strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
					strInputXMLBuffer.append("<Vitalsign>");
					strInputXMLBuffer.append("<Date>" + SelectedDate2.replace('/', '-') + "</Date>");
					strInputXMLBuffer.append("<Time>" + hour + ":" + minute  + "</Time>");
					strInputXMLBuffer.append("<BPDiastolic>" + etBloodSugar.getText().toString().trim()  + "</BPDiastolic>");
					strInputXMLBuffer.append("<BPSystolic>" + etsystolic.getText().toString().trim()  + "</BPSystolic>");
					strInputXMLBuffer.append("<Pulse></Pulse>");
					strInputXMLBuffer.append("<BodyTemperature></BodyTemperature>");
					strInputXMLBuffer.append("</Vitalsign>");
					strInputXMLBuffer.append("</Request>");
					
					HospitalInterface hospitalInterface = new HospitalInterface();
					
					//xml리퀘스트
					Log.d("test", "request: " + strInputXMLBuffer.toString());
					String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
					Log.d("test", "result1234: " + result1);

					insertDB(SelectedDate2, hour + ":" + minute, etBloodSugar.getText().toString().trim(), etsystolic.getText().toString().trim(),etMemo.getText()
							.toString().trim(), "N");
					
					finish();
				}
			}
		});
		
		Button button_back = (Button) findViewById(R.id.button_5_input_cancel);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});

	}

	public void insertDB(String date, String time, String bpdiastolic, String bpsystolic,
			String memo, String hospital) {

		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bpdiastolic", bpdiastolic);
		cv.put("bpsystolic", bpsystolic);
		cv.put("memo", memo);
		cv.put("hospital", hospital);

		sqLiteDatabase.insert("BlOODPRESSUREBPD", "", cv);
	}

}