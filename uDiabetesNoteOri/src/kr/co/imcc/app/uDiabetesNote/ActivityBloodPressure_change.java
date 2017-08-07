package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityBloodPressure_change extends Activity {
	/** Called when the activity is first created. */
	static String SelectedDate2 = "";
	int position;
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bloodpressurechange);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		position = Integer.parseInt(intent.getStringExtra("position"));

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title123_1_4);

		HashMap<String, Object> item = ActivityBloodPressure.arrayListBloodSugarTotal.get(position);
		
		final String _id = item.get("_id").toString();
		String date = item.get("date").toString();
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";
		tvTitle.setText(date);
		
		String bpdiastolic = item.get("bpdiastolic").toString();
		String bpsystolic = item.get("bpsystolic").toString();
		String memo = item.get("memo").toString();
		String time = item.get("time").toString();
		
		int tHour = Integer.parseInt(time.substring(0, 2));
		int tMin = Integer.parseInt(time.substring(3, 5));
		
		Button btUpdate = (Button) findViewById(R.id.button__5_input123_1_result_update);
		Button btDelete = (Button) findViewById(R.id.button__5_input123_1_result_delete);
		//final TimePicker tpInput1 = (TimePicker) findViewById(R.id.timePicker_input123);
		
		final EditText editbpdiastolic = (EditText) findViewById(R.id.textView_5_input123_1_2);
		final EditText editbpsystolic = (EditText) findViewById(R.id.editbpsystolic);
		final EditText etMemo1 = (EditText) findViewById(R.id.textView_5_input123_1_5);
		
		editbpdiastolic.setText(bpdiastolic);
		editbpsystolic.setText(bpsystolic);
		etMemo1.setText(memo);
/*		tpInput1.setCurrentHour(tHour);
		tpInput1.setCurrentMinute(tMin);*/
		
		
		btUpdate.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(editbpdiastolic.getText().toString().equals("") || editbpsystolic.getText().toString().equals(""))
				{
					AlertDialog alert = new AlertDialog.Builder(ActivityBloodPressure_change.this)					
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
				else
				{					
					String hour="";
					String minute="";
					
/*					if(tpInput1.getCurrentHour()<10) {
						hour = "0"+tpInput1.getCurrentHour();
					}else{
						hour = ""+tpInput1.getCurrentHour();
					}
							
					if(tpInput1.getCurrentMinute()<10) {
						minute = "0"+tpInput1.getCurrentMinute();
					}else{
						minute = ""+tpInput1.getCurrentMinute();
					}*/
					
					StringBuffer strInputXMLBuffer = new StringBuffer();
					
					HashMap<String, String> item = ActivityLogin.item;
					
					HashMap<String, Object> tempItem = ActivityBloodPressure.arrayListBloodSugarTotal.get(position);
								
					String date = tempItem.get("date").toString();
					date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
					String time = tempItem.get("time").toString();
					
					strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
					strInputXMLBuffer.append("<Type>VitalsignBloodPressureModify</Type>");
					strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
					strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
					strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
					strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
					strInputXMLBuffer.append("<Vitalsign>");
					strInputXMLBuffer.append("<Date>" + date.replace('/', '-') + "</Date>");
					strInputXMLBuffer.append("<Time>" + time  + "</Time>");
					strInputXMLBuffer.append("<BPDiastolic>" + editbpdiastolic.getText().toString().trim()  + "</BPDiastolic>");
					strInputXMLBuffer.append("<BPSystolic>" + editbpsystolic.getText().toString().trim()  + "</BPSystolic>");
					strInputXMLBuffer.append("<Pulse></Pulse>");
					strInputXMLBuffer.append("<BodyTemperature></BodyTemperature>");
					strInputXMLBuffer.append("</Vitalsign>");
					strInputXMLBuffer.append("</Request>");
					
					HospitalInterface hospitalInterface = new HospitalInterface();
					
					//xml리퀘스트
					Log.d("test", "request: " + strInputXMLBuffer.toString());
					String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
					Log.d("test", "result1234: " + result1);
					
					updateDB(SelectedDate2, time, editbpdiastolic.getText().toString().trim(), 
							editbpsystolic.getText().toString().trim(), etMemo1.getText().toString().trim(), "N", _id);
					
					tempItem.put("bpdiastolic", editbpdiastolic.getText().toString().trim());
					tempItem.put("bpsystolic", editbpsystolic.getText().toString().trim());
												
					ActivityBloodPressure.arrayListBloodSugarTotal.set(position, tempItem);
					ActivityBloodPressure.arrayAdapterBloodSugarTotal.notifyDataSetChanged();
					
					finish();
				}
			}
		});
		
		btDelete.setOnClickListener(new OnClickListener() 
		{ // back		
			@Override
			public void onClick(View v) 
			{
				StringBuffer strInputXMLBuffer = new StringBuffer();
				
				HashMap<String, String> item = ActivityLogin.item;
				
				HashMap<String, Object> tempItem = ActivityBloodPressure.arrayListBloodSugarTotal.get(position);
				
				String date = tempItem.get("date").toString();
				date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
				String time = tempItem.get("time").toString();
				
				strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
				strInputXMLBuffer.append("<Type>VitalsignBloodPressureDelete</Type>");
				strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
				strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
				strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
				strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
				strInputXMLBuffer.append("<Vitalsigns>");
				strInputXMLBuffer.append("<Vitalsign>");
				strInputXMLBuffer.append("<Date>" + date.replace('/', '-') + "</Date>");
				strInputXMLBuffer.append("<Time>" + time  + "</Time>");		
				strInputXMLBuffer.append("</Vitalsign>");
				strInputXMLBuffer.append("</Vitalsigns>");
				strInputXMLBuffer.append("</Request>");
				
				HospitalInterface hospitalInterface = new HospitalInterface();
				
				//xml리퀘스트
				Log.d("test", "request: " + strInputXMLBuffer.toString());
				String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
				Log.d("test", "result1234: " + result1);
				
				try {
					deleteDB(_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ActivityBloodPressure.arrayListBloodSugarTotal.remove(position);
				ActivityBloodPressure.arrayAdapterBloodSugarTotal.notifyDataSetChanged();
				finish();				
			}
		});
	}	

		
	public void updateDB(String date, String time, String bpdiastolic, String bpsystolic, String memo, String hospital, String _id)
	{	
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bpdiastolic", bpdiastolic);
		cv.put("bpsystolic", bpsystolic);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.update("BlOODPRESSUREBPD", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception 
	{
		sqLiteDatabase.delete("BlOODPRESSUREBPD", "_id=?", new String[] { _id });
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();
	}
}