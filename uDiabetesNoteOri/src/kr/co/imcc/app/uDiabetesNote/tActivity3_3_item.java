package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity3_3_item extends Activity {
	/** Called when the activity is first created. */
	String SelectedDate3_3;
	int position;
	String date = "";
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity3_3_item);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate3_3 = intent.getStringExtra("SelectedDate3_3");
		position = Integer.parseInt(intent.getStringExtra("position"));

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title123_1_4);

		HashMap<String, Object> item = tActivity3_3.arrayListBloodSugarTotal.get(position);
		
		final String _id = item.get("_id").toString();
		date = item.get("date").toString();
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";

		tvTitle.setText(date);
		
		String bloodsugar = item.get("bloodsugar").toString();
		String memo = item.get("memo").toString();
		String time = item.get("time").toString();

		
		int tHour = Integer.parseInt(time.substring(0, 2));
		int tMin = Integer.parseInt(time.substring(3, 5));
		
		Button btUpdate = (Button) findViewById(R.id.button__5_input123_1_result_update);
		Button btDelete = (Button) findViewById(R.id.button__5_input123_1_result_delete);
		//final TimePicker tpInput1 = (TimePicker) findViewById(R.id.timePicker_input123);
		
		final EditText etBloodSugar1 = (EditText) findViewById(R.id.textView_5_input123_1_2);
		final EditText etMemo1 = (EditText) findViewById(R.id.textView_5_input123_1_5);
		
		etBloodSugar1.setText(bloodsugar);
		etMemo1.setText(memo);
//		tpInput1.setCurrentHour(tHour);
//		tpInput1.setCurrentMinute(tMin);
		
		btUpdate.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(etBloodSugar1.getText().toString().equals("")){
					AlertDialog alert = new AlertDialog.Builder(tActivity3_3_item.this)					
					.setTitle("알림")
					.setMessage("혈당이 입력되지 않았습니다.")
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
				}else{
					
//					String hour="";
//					String minute="";
//					
//					if(tpInput1.getCurrentHour()<10) {
//						hour = "0"+tpInput1.getCurrentHour();
//					}else{
//						hour = ""+tpInput1.getCurrentHour();
//					}
//							
//					if(tpInput1.getCurrentMinute()<10) {
//						minute = "0"+tpInput1.getCurrentMinute();
//					}else{
//						minute = ""+tpInput1.getCurrentMinute();
//					}
					
					StringBuffer strInputXMLBuffer = new StringBuffer();
					
					HashMap<String, String> item = ActivityLogin.item;
					
					HashMap<String, Object> tempItem = tActivity3_3.arrayListBloodSugarTotal.get(position);
								
					String date = tempItem.get("date").toString();
					date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
					String time = tempItem.get("time").toString();
					
					strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
					strInputXMLBuffer.append("<Type>VitalsignBloodSugarModify</Type>");
					strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
					strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
					strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
					strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
					strInputXMLBuffer.append("<Vitalsign>");
					strInputXMLBuffer.append("<Date>" + date.replace('/', '-') + "</Date>");
					strInputXMLBuffer.append("<Time>" + time  + "</Time>");
					strInputXMLBuffer.append("<BloodSugar>" + etBloodSugar1.getText().toString().trim()  + "</BloodSugar>");
					strInputXMLBuffer.append("</Vitalsign>");
					strInputXMLBuffer.append("</Request>");
					
					HospitalInterface hospitalInterface = new HospitalInterface();
					
					//xml리퀘스트
					Log.d("test", "request: " + strInputXMLBuffer.toString());
					String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
					Log.d("test", "result1234: " + result1);
					
					updateDB(date, time, etBloodSugar1.getText().toString().trim(), etMemo1.getText().toString().trim(), "N", _id);
					
					sqLiteDatabase.close();		
					
					tempItem = tActivity3_3.arrayListBloodSugarTotal.get(position);
					tempItem.put("bloodsugar", etBloodSugar1.getText().toString().trim());
												
					tActivity3_3.arrayListBloodSugarTotal.set(position, tempItem);
					tActivity3_3.arrayAdapterBloodSugarTotal.notifyDataSetChanged();
					
					finish();
				}
			}
		});
		
		btDelete.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				StringBuffer strInputXMLBuffer = new StringBuffer();
				
				HashMap<String, String> item = ActivityLogin.item;
				
				HashMap<String, Object> tempItem = tActivity3_3.arrayListBloodSugarTotal.get(position);
				
				String date = tempItem.get("date").toString();
				date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
				String time = tempItem.get("time").toString();
				
				strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
				strInputXMLBuffer.append("<Type>VitalsignBloodSugarDelete</Type>");
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
			
				//tActivity1_4.listDialog.dismiss();
							
				tActivity3_3.arrayListBloodSugarTotal.remove(position);		
				tActivity3_3.arrayAdapterBloodSugarTotal.notifyDataSetChanged();
				
				finish();				
			}
		});
		
//		btInput.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				if(etBloodSugar.getText().toString().equals("")){
//					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
//							Toast.LENGTH_SHORT).show();
//				}else{
//					
//					String hour="";
//					String minute="";
//					
//					if(tpInput.getCurrentHour()<10) {
//						hour = "0"+tpInput.gsetCurrentHour();
//					}else{
//						hour = ""+tpInput.getCurrentHour();
//					}
//							
//					if(tpInput.getCurrentMinute()<10) {
//						minute = "0"+tpInput.getCurrentMinute();
//					}else{
//						minute = ""+tpInput.getCurrentMinute();
//					}
//					
//					insertDB(SelectedDate3_3, hour+":"+minute,etBloodSugar.getText().toString().trim(), etMemo.getText().toString().trim(), "N");
//					showBloodSugarList(SelectedDate3_3);
//					inputDialog1.dismiss();
//				}		
//				
//			}
//		});	
	}
	
	public void updateDB(String date, String time, String bloodsugar, String memo, String hospital, String _id){
		
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("bloodsugar", bloodsugar);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.update("BLOODSUGAR", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception {
		sqLiteDatabase.delete("BLOODSUGAR", "_id=?", new String[] { _id });
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
}