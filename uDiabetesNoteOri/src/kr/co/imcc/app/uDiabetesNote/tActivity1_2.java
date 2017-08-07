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

public class tActivity1_2 extends Activity {
    /** Called when the activity is first created. */
	
//	public static ArrayList<HashMap<String, String>> ArrayListVitalsignHeight = new ArrayList<HashMap<String, String>>();

	EditText etHeight;
	EditText etWeight;
	RadioButton rbFemale;
	RadioButton rbMale;
	
	static Button button_hosp;
	private BroadcastReceiver receiver;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity1_2);
        
        actionBar = getActionBar();
     		actionBar.setTitle("체질량 지수 측정");
     		getActionBar().setDisplayHomeAsUpEnabled(true); 
        
        
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new ULNetworkReceiver(this);
		registerReceiver(receiver, filter);
		
		etHeight = (EditText) findViewById(R.id.edittext_2_2_height);
		etWeight = (EditText) findViewById(R.id.edittext_2_2_weight);

		rbMale = (RadioButton) findViewById(R.id.radio_male);
		rbFemale = (RadioButton) findViewById(R.id.radio_female);
		ImageButton button_result = (ImageButton) findViewById(R.id.button_2_2_result);
		
		button_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etHeight.getText().toString().equals("")){
					
					
					Toast.makeText(getApplicationContext(), "키를 입력해주세요", 1000).show();

				}else if(etWeight.getText().toString().equals("")){
					
					Toast.makeText(getApplicationContext(), "몸무게를 입력해주세요", 1000).show();

				}
				else if(!rbMale.isChecked()&&!rbFemale.isChecked()){
					
					Toast.makeText(getApplicationContext(), "성별을 입력해주세요", 1000).show();
				}
				else{
					showResultDialog();
				}			
				
			}
		});
		

    }
    
    
    public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
    
    
    Dialog resultDialog;
	public void showResultDialog() {
		resultDialog = new Dialog(this);
		resultDialog.setTitle("체질량 지수 측정");
		resultDialog.setContentView(R.layout.bmiresult);
		
		Double heightMeter = Double.parseDouble(etHeight.getText().toString())/100.0;
		Double weight = Double.parseDouble(etWeight.getText().toString());
		Double bmi;
		String overWeight="";
		
		bmi = weight/(heightMeter*heightMeter);
				
		if(bmi <= 22.0){
			overWeight = "저체중";
		}else if(bmi>22.0 & bmi <= 23.0){
			overWeight = "정상체중";
		}else if(bmi>23.0 & bmi <= 25.0){
			overWeight = "위험체중";
		}else if(bmi>25.0 & bmi <= 30.0){
			overWeight = "비만";
		}else if(bmi>30.0){
			overWeight = "고도비만";
		}
		TextView tvBMI = (TextView) resultDialog.findViewById(R.id.textview_2_2_height_result_1);
		TextView tvOverWeight = (TextView) resultDialog.findViewById(R.id.textview_2_2_weight_result_1);
		
		tvBMI.setText(((int)(bmi*10.0))/10.0+"");
		tvOverWeight.setText(overWeight);
		
		resultDialog.show();
	}
	
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
    
}