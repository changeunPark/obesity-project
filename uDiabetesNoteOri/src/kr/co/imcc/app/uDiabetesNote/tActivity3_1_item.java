package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity3_1_item extends Activity {
	/** Called when the activity is first created. */
	String SelectedDate2;
	int position;
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	EditText etTime;
	EditText etKind;
	EditText etExTime;
	EditText etWeight;
	EditText etMemo;	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity3_1_item);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		position = Integer.parseInt(intent.getStringExtra("position"));

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title_1_4_52);

		HashMap<String, Object> item = tActivity3_1.arrayListExcerciseTotal.get(position);
		
		final String _id = item.get("_id").toString();
		String date = item.get("date").toString();
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";
		tvTitle.setText(date);
		
		String time = item.get("time").toString().substring(0, 2) +"시 " + item.get("time").toString().substring(3, 5)+"분";
		
		hours = item.get("time").toString().substring(0, 2);
		minutes = item.get("time").toString().substring(3, 5);
		
		String kind = item.get("kind").toString();
		String extime = item.get("extime").toString();
		String weight = item.get("weight").toString();
		String memo = item.get("memo").toString();
		
		etTime = (EditText) findViewById(R.id.textView_5_input_1_2_52);
		etWeight = (EditText) findViewById(R.id.textView_5_input_1_8_52);
		etMemo = (EditText) findViewById(R.id.textView_5_input_1_10_52);	
		
		etTime.setText(time);
		etTime.setOnClickListener(listenerTime);
		etWeight.setText(weight);
		etMemo.setText(memo);	
		
		etExTime = (EditText) findViewById(R.id.textView_5_input_1_6_52);	
		etExTime.setText(extime);
		
		etKind = (EditText) findViewById(R.id.textView_5_input_1_4_52);	
		etKind.setText(kind);
		
		etKind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showKindDialog();
			}
		});
		
		
//		int tHour = Integer.parseInt(time.substring(0, 2));
//		int tMin = Integer.parseInt(time.substring(3, 5));
//		
	/*	Button btUpdate = (Button) findViewById(R.id.button_5_input_update_52);
		Button btDelete = (Button) findViewById(R.id.button_5_input_delete_52);*/
//		final TimePicker tpInput1 = (TimePicker) findViewById(R.id.timePicker_input123);
//		
//		final EditText etBloodSugar1 = (EditText) findViewById(R.id.textView_5_input123_1_2);
//		final EditText etMemo1 = (EditText) findViewById(R.id.textView_5_input123_1_5);
//		
//		etBloodSugar1.setText(bloodsugar);
//		etMemo1.setText(memo);
//		tpInput1.setCurrentHour(tHour);
//		tpInput1.setCurrentMinute(tMin);
//		
		/*btUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				int met_pos=0;

				if (etTime.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_item.this)					
					.setTitle("알림")
					.setMessage("시간이 입력되지 않았습니다.")
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
				} else if (etKind.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_item.this)					
					.setTitle("알림")
					.setMessage("운동의 종류가 입력되지 않았습니다.")
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
				} else if (etExTime.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_item.this)					
					.setTitle("알림")
					.setMessage("운동시간이 입력되지 않았습니다.")
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
				} else if (etWeight.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(tActivity3_1_item.this)					
					.setTitle("알림")
					.setMessage("몸무게가 입력되지 않았습니다.")
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
					
//					Log.d("para1: ", etExTime.getText().toString());
//					Log.d("para: ", selectedMet + " "+ etExTime.getText().toString() + " "+etWeight.getText().toString());
					for(int i=0; i<metlist.length; i++)
					{		
						if(metlist[i].equals(tActivity3_1.arrayListExcerciseTotal.get(position)))
						{
							met_pos = i;	
							break;
						}						
					}

					String calorie = (int)(metvalue[met_pos]*(Double.parseDouble(etExTime.getText().toString())/60)*Double.parseDouble(etWeight.getText().toString())+0.5) + "";
//
					updateDB(SelectedDate2, hours + ":" + minutes, etKind.getText().toString().trim(),
							etExTime.getText().toString().trim(), etWeight.getText().toString().trim(),
							etMemo.getText().toString().trim(), calorie, _id);
					
					sqLiteDatabase.close();					
					
					HashMap<String, Object> tempItem = tActivity3_1.arrayListExcerciseTotal.get(position);
					
					tempItem = tActivity3_1.arrayListExcerciseTotal.get(position);
					tempItem.put("kind", etKind.getText().toString().trim());
					tempItem.put("extime", etExTime.getText().toString().trim());
					tempItem.put("weight", etWeight.getText().toString().trim());
					tempItem.put("calorie",calorie);
					
					tActivity3_1.arrayListExcerciseTotal.set(position, tempItem);
					tActivity3_1.arrayAdapterExcerciseTotal.notifyDataSetChanged();
					
					finish();
				}
			}
		});
					
//					updateDB(SelectedDate2, hour+":"+minute, etBloodSugar1.getText().toString().trim(), etMemo1.getText().toString().trim(), "N", _id);
////					showBloodSugarList(SelectedDate2);
//					
//					finish();
//				}
//			}
//		});
		
		btDelete.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				try {
					deleteDB(_id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tActivity3_1.arrayListExcerciseTotal.remove(position);
				tActivity3_1.arrayAdapterExcerciseTotal.notifyDataSetChanged();
				finish();
				
			}
		});*/
		
////		btInput.setOnClickListener(new OnClickListener() {
////			
////			@Override
////			public void onClick(View v) {
////				
////				if(etBloodSugar.getText().toString().equals("")){
////					Toast.makeText(getApplicationContext(), "혈당이 입력되지 않았습니다.",
////							Toast.LENGTH_SHORT).show();
////				}else{
////					
////					String hour="";
////					String minute="";
////					
////					if(tpInput.getCurrentHour()<10) {
////						hour = "0"+tpInput.getCurrentHour();
////					}else{
////						hour = ""+tpInput.getCurrentHour();
////					}
////							
////					if(tpInput.getCurrentMinute()<10) {
////						minute = "0"+tpInput.getCurrentMinute();
////					}else{
////						minute = ""+tpInput.getCurrentMinute();
////					}
////					
////					insertDB(SelectedDate2, hour+":"+minute,etBloodSugar.getText().toString().trim(), etMemo.getText().toString().trim(), "N");
////					showBloodSugarList(SelectedDate2);
////					inputDialog1.dismiss();
////				}		
////				
////			}
////		});
//		
	}
	
	
	OnClickListener listenerTime = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Calendar calendar = Calendar.getInstance();			
			
			new TimePickerDialog(tActivity3_1_item.this, timeSetListener, calendar.getTime().getHours() , calendar.getTime().getMinutes() , true).show(); 
			
		}
	};
	
	
	String hours;
	String minutes;
	
	TimePickerDialog.OnTimeSetListener timeSetListener = new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			
			
			if(hourOfDay<10) {
				hours = "0"+hourOfDay;
			}else{
				hours = ""+hourOfDay;
			}
					
			if(minute<10) {
				minutes = "0"+minute;
			}else{
				minutes = ""+minute;
			}
			
			etTime.setText(hours+"시 "+minutes+"분");
		}
	}; 
	
	AlertDialog.Builder kindDialog;
	double[] metvalue = {1.5,4,6,7,6,9,6,5,7,3,6,3,5.1,6};
	String selectedMet = "";
	final CharSequence[] metlist = {"침상체조", "걷기", "조깅", "등산", "줄넘기(60~100회/분)", "줄넘기(100~1400회/분)",
			"계단오르기", "자전거 타기", "에어로빅 무용","낚시", "테니스", "볼링","골프", "수영"}; 
	
	
	public void showKindDialog() {
		
		kindDialog = new AlertDialog.Builder(this);
//		kindDialog.setTitle("운동의 종류");
		
		kindDialog.setSingleChoiceItems(metlist, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int pos) {
						
				selectedMet = metvalue[pos] + "";
				etKind.setText(metlist[pos]);
				dialog.dismiss();				
			}
		});
		
		AlertDialog alert = kindDialog.create();
		alert.show();  

	}
	
	
	public void updateDB(String date, String time, String kind, String extime,  
			String weight, String memo, String calorie, String _id){
		
		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("kind", kind);
		cv.put("extime", extime);
		cv.put("weight", weight);
		cv.put("memo", memo);
		cv.put("calorie", calorie);
		
		sqLiteDatabase.update("EXCERCISE", cv, "_id=?", new String[] { _id });
	}
	
	public void deleteDB(String _id) throws Exception {
		sqLiteDatabase.delete("EXCERCISE", "_id=?", new String[] { _id });
//		Toast.makeText(MainMenuActivity.this, ptntNo, Toast.LENGTH_SHORT).show();

	}
}