//당화혈색소 입력

package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1_1_hb extends Activity {
	/** Called when the activity is first created. */

	static String SelectedDate2 = "";
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input_hb);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE); 
		
		dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();

		Intent intent = getIntent();

		SelectedDate2 = intent.getStringExtra("SelectedDate2");
		String date = SelectedDate2;
		date = date.substring(0, 4) + "년 "+ date.substring(5, 7) + "월 " + date.substring(8, 10) + "일";

		TextView tvTitle = (TextView) findViewById(R.id.textView_5_title_1_4_hb);
		tvTitle.setText(date);
		Button btInput = (Button) findViewById(R.id.button_5_input_save_hb);
		final TimePicker tpInput = (TimePicker) findViewById(R.id.timePicker_input_hb);

		final EditText etBloodSugar = (EditText) findViewById(R.id.textView_5_input_1_2_hb);
		final EditText etMemo = (EditText) findViewById(R.id.textView_5_input_1_5_hb);

		btInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etBloodSugar.getText().toString().equals("")) {
					AlertDialog alert = new AlertDialog.Builder(Activity1_1_hb.this)					
					.setTitle("알림")
					.setMessage("당화혈색소가 입력되지 않았습니다.")
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
				} else {

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
					strInputXMLBuffer.append("<Type>HBA1CAdd</Type>");
					strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
					strInputXMLBuffer.append("<KeyCD>" + item.get("KeyCD") + "</KeyCD>");
					strInputXMLBuffer.append("<ReqLoginID>" + item.get("LoginID") + "</ReqLoginID>");
					strInputXMLBuffer.append("<LoginID>" + item.get("LoginID") + "</LoginID>");
					strInputXMLBuffer.append("<HBA1C>");
					strInputXMLBuffer.append("<Date>" + Activity1_hb.SelectedDate2.replace('/', '-') + "</Date>");
					strInputXMLBuffer.append("<Time>" + hour + ":" + minute  + "</Time>");
					strInputXMLBuffer.append("<Value>" + etBloodSugar.getText().toString().trim()  + "</Value>");
					strInputXMLBuffer.append("</HBA1C>");
					strInputXMLBuffer.append("</Request>");
					
					HospitalInterface hospitalInterface = new HospitalInterface();
					
					//xml리퀘스트
					Log.d("test", "request: " + strInputXMLBuffer.toString());
					String result1 = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());	
					Log.d("test", "result1234: " + result1);

					insertDB(SelectedDate2, hour + ":" + minute, etBloodSugar
							.getText().toString().trim(), etMemo.getText()
							.toString().trim(), "N");
					
					finish();
				}
			}
		});
		
		Button button_back = (Button) findViewById(R.id.button_5_input_cancel_hb);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	public void insertDB(String date, String time, String hb,
			String memo, String hospital) {

		ContentValues cv = new ContentValues();
		cv.put("date", date);
		cv.put("time", time);
		cv.put("hb", hb);
		cv.put("memo", memo);
		cv.put("hospital", hospital);

		sqLiteDatabase.insert("HBA1C", "", cv);
	}

}