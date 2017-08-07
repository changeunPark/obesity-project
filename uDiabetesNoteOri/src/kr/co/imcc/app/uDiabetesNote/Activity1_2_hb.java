package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1_2_hb extends Activity {
	/** Called when the activity is first created. */
	String SelectedDate2;
	int position;
	String date = "";
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input1_hb);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		position = Integer.parseInt(intent.getStringExtra("position"));

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title123_1_4_hb);

		HashMap<String, Object> item = Activity1_hb.arrayListHbTotal.get(position);
		
		final String _id = item.get("_id").toString();
		String date = item.get("date").toString();
		date = date.substring(0, 4) + "�� "+ date.substring(5, 7) + "�� " + date.substring(8, 10) + "��";
		
		tvTitle.setText(date);
		
		String Hb = item.get("hb").toString();
		String memo = item.get("memo").toString();
		String time = item.get("time").toString();
		
		int tHour = Integer.parseInt(time.substring(0, 2));
		int tMin = Integer.parseInt(time.substring(3, 5));
		
		Button btUpdate = (Button) findViewById(R.id.button__5_input123_1_result_update_hb);
		Button btDelete = (Button) findViewById(R.id.button__5_input123_1_result_delete_hb);
		//final TimePicker tpInput1 = (TimePicker) findViewById(R.id.timePicker_input123_hb);
		
		final EditText etHb1 = (EditText) findViewById(R.id.textView_5_input123_1_2_hb);
		final EditText etMemo1 = (EditText) findViewById(R.id.textView_5_input123_1_5_hb);
		
		etHb1.setText(Hb);
		etMemo1.setText(memo);
/*		tpInput1.setCurrentHour(tHour);
		tpInput1.setCurrentMinute(tMin);*/
		
		btUpdate.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				if(etHb1.getText().toString().equals("")){
					AlertDialog alert = new AlertDialog.Builder(Activity1_2_hb.this)					
					.setTitle("�˸�")
					.setMessage("��ȭ�����Ұ� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
//					Toast.makeText(getApplicationContext(), "������ �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
				}else{
					
					StringBuffer strInputXMLBuffer = new StringBuffer();
					
					HashMap<String, String> item = ActivityLogin.item;
					
					HashMap<String, Object> tempItem = Activity1_hb.arrayListHbTotal.get(position);
								
					String date = tempItem.get("date").toString();
					date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
					String time = tempItem.get("time").toString();
					
					strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
					strInputXMLBuffer.append("<Type>HBA1CModify</Type>");
					strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
					strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
					strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
					strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
					strInputXMLBuffer.append("<HBA1C>");
					strInputXMLBuffer.append("<Date>" + date.replace('/', '-') + "</Date>");
					strInputXMLBuffer.append("<Time>" + time  + "</Time>");
					strInputXMLBuffer.append("<Value>" + etHb1.getText().toString().trim()  + "</Value>");
					strInputXMLBuffer.append("</HBA1C>");
					strInputXMLBuffer.append("</Request>");
					
					HospitalInterface hospitalInterface = new HospitalInterface();
					
					//xml������Ʈ
					Log.d("test", "request: " + strInputXMLBuffer.toString());
					String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
					Log.d("test", "result1234: " + result1);
					
					updateDB(date, time, etHb1.getText().toString().trim(), etMemo1.getText().toString().trim(), "N", _id);
//					showHbList(SelectedDate2);
					
					tempItem = Activity1_hb.arrayListHbTotal.get(position);
					tempItem.put("hb", etHb1.getText().toString().trim());
												
					Activity1_hb.arrayListHbTotal.set(position, tempItem);
					Activity1_hb.arrayAdapterHbTotal.notifyDataSetChanged();
					
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
				
				HashMap<String, Object> tempItem = Activity1_hb.arrayListHbTotal.get(position);
							
				String date = tempItem.get("date").toString();
				date = date.substring(0, 4) + "/"+ date.substring(5, 7) + "/" + date.substring(8, 10);
				String time = tempItem.get("time").toString();
				
				strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
				strInputXMLBuffer.append("<Type>HBA1CDelete</Type>");
				strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
				strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
				strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
				strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
				strInputXMLBuffer.append("<HBA1Cs>");
				strInputXMLBuffer.append("<HBA1C>");
				strInputXMLBuffer.append("<Date>" + date.replace('/', '-') + "</Date>");
				strInputXMLBuffer.append("<Time>" + time  + "</Time>");
				strInputXMLBuffer.append("<Value>" + etHb1.getText().toString().trim()  + "</Value>");
				strInputXMLBuffer.append("</HBA1C>");
				strInputXMLBuffer.append("</HBA1Cs>");
				strInputXMLBuffer.append("</Request>");
				
				HospitalInterface hospitalInterface = new HospitalInterface();
				
				//xml������Ʈ
				Log.d("test", "request: " + strInputXMLBuffer.toString());
				String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
				Log.d("test", "result1234: " + result1);
				
				try {
					deleteDB(_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Activity1_hb.arrayListHbTotal.remove(position);
				Activity1_hb.arrayAdapterHbTotal.notifyDataSetChanged();
				
				finish();			
			}
		});
		
//		btInput.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				if(etHb.getText().toString().equals("")){
//					Toast.makeText(getApplicationContext(), "������ �Էµ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
//				}else{
//					
//					String hour="";
//					String minute="";
//					
//					if(tpInput.getCurrentHour()<10) {
//						hour = "0"+tpInput.getCurrentHour();
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
//					insertDB(SelectedDate2, hour+":"+minute,etHb.getText().toString().trim(), etMemo.getText().toString().trim(), "N");
//					showHbList(SelectedDate2);
//					inputDialog1.dismiss();
//				}		
//				
//			}
//		});
		
	}
	
	public void updateDB(String date, String time, String hb, String memo, String hospital, String _id){		
		
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("hb", hb);
		cv.put("memo", memo);
		cv.put("hospital", hospital);
		
		sqLiteDatabase.update("HBA1C", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception {
		sqLiteDatabase.delete("HBA1C", "_id=?", new String[] { _id });
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
}