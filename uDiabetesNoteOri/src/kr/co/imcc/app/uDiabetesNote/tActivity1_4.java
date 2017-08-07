package kr.co.imcc.app.uDiabetesNote;

import java.text.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity1_4 extends Activity {
	/** Called when the activity is first created. */

    private ActionBar actionBar;
    EditText edit_height, edit_weight, edit_waist, edit_ass, edit_age;
	RadioGroup rgSex, rgChoose;
    RadioButton rmale, rfemale, rYes, rNo;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_4);
		

        actionBar = getActionBar();
		actionBar.setTitle("비만위험도 지수 측정");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true); 

		rgChoose = (RadioGroup)findViewById(R.id.radiogroup_3_1_2);
		rYes = (RadioButton)findViewById(R.id.ryes);
		rNo = (RadioButton)findViewById(R.id.rno);
		
		rgSex = (RadioGroup)findViewById(R.id.radiogroup_3_1_1);
		rmale = (RadioButton)findViewById(R.id.radio_male);
		rfemale = (RadioButton)findViewById(R.id.radio_female);
		
		edit_age = (EditText)findViewById(R.id.age);
		
		edit_height = (EditText)findViewById(R.id.insert_height);
		edit_weight = (EditText)findViewById(R.id.insert_weight);
		edit_waist = (EditText)findViewById(R.id.insert_waist);
		edit_ass = (EditText)findViewById(R.id.insert_ass);
		
		ImageButton button_result = (ImageButton) findViewById(R.id.button_result);

		button_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(!rmale.isChecked()&&!rfemale.isChecked()){
					Toast.makeText(getApplicationContext(), "성별을 선택해 주세요.", 1000).show();
				}
				else if(edit_height.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "키를 입력해주세요", 1000).show();
				}
				else if(edit_weight.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "몸무게를 입력해주세요", 1000).show();
				}
				else if(edit_waist.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "허리둘레를 입력해주세요", 1000).show();
				}
				else if(edit_ass.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "엉덩이둘레를 입력해주세요", 1000).show();
				}
				else if(!rNo.isChecked()&&!rYes.isChecked()){
					Toast.makeText(getApplicationContext(), "가족 중 비만 여부를 선택해 주세요.", 1000).show();
				}
				
				else
				{
					showResult();
				}
				}

		});
	}
		

		public void showResult(){
			
//비만도 계산
			Double agenumber = Double.parseDouble(edit_age.getText().toString());
			Double height = Double.parseDouble(edit_height.getText().toString());
			Double weight = Double.parseDouble(edit_weight.getText().toString());
			Double waist = Double.parseDouble(edit_waist.getText().toString());
			Double ass = Double.parseDouble(edit_ass.getText().toString());
			Double abdomen;
			
			

//	비만 가족력
			String family="";		
//결과판별값
			String sex="";
//이상체중
			Double idealWeight;
//비만분류
			String overWeight="";
//비만도퍼센트	
			Double overWeightPercent;
//BMI계산
			Double heightMeter = Double.parseDouble(edit_height.getText().toString())/100.0;
			
			Double bmi;
			
			int overWeightPercentindex = 0;
			int resultindex=0;
			int abdomenindex=0;
			int bmiindex=0;
			int familyindex=0;
			
			String overWeightbmi="";
			
//			비만도 계산
			if(height > 160.0){
				idealWeight = (height-100.0)*0.9;
			}else if(height > 150.0 & height <= 160.0 ){
				idealWeight = (height-150.0)*0.5 + 50.0;
			}else {
				idealWeight = height-100.0;
			}
			
			overWeightPercent = (weight / idealWeight) * 100;

//			비만분류 계산식
/*			if(overWeightPercent <= 90.0){
				overWeight = "저체중";
			}else if(overWeightPercent>90.0 & overWeightPercent <= 110.0){
				overWeight = "정상";
			}else if(overWeightPercent>110.0 & overWeightPercent <= 120.0){
				overWeight = "과체중";
			}else if(overWeightPercent>120.0){
				overWeight = "비만";
			}*/
			
			
//			BMI계산
			bmi = weight/(heightMeter*heightMeter);
			bmi = ((int)(bmi*10.0))/10.0;
			
			/*	
			
			if(bmi>18.5 & bmi <= 22.0){
				overWeightbmi = "저체중";
			}else if(bmi>22.0 & bmi <= 23.0){
				overWeightbmi = "정상체중";
			}else if(bmi>23.0 & bmi <= 25.0){
				overWeightbmi = "위험체중";
			}else if(bmi>25.0 & bmi <= 30.0){
				overWeightbmi = "비만";
			}else if(bmi>30.0){
				overWeightbmi = "고도비만";
			}*/
			
//			복부비만도 계싼
			abdomen = waist / ass;
			
//			비만도 계산식
			if(overWeightPercent <= 90.0){
				overWeightPercentindex = 1;
			}else if(overWeightPercent>90.0 & overWeightPercent <= 110.0){
				overWeightPercentindex = 2;
			}else if(overWeightPercent>110.0 & overWeightPercent <= 120.0){
				overWeightPercentindex = 3;
			}else if(overWeightPercent>120.0){
				overWeightPercentindex = 4;
			}
			
//			bmi지수 계산식
			if(bmi>18.5 & bmi <= 22.0){
				bmiindex = 1;
			}else if(bmi>22.0 & bmi <= 23.0){
				bmiindex = 2;
			}else if(bmi>23.0 & bmi <= 25.0){
				bmiindex = 3;
			}else if(bmi>25.0){
				bmiindex = 4;
			}
			
//			복부비만도 계산식
			if (rmale.isChecked()) { // ����

				if (abdomen > 1.0) {
					abdomenindex = 2;
				} else {
					abdomenindex = 1;
				}

			} else {// ����

				if (abdomen > 0.85) {
					abdomenindex = 2;
				} else {
					abdomenindex = 1;
				}
			}
			
//			가족 중 비만경력 계산식
			
			if(rYes.isChecked())
			{
				familyindex=2;
				family = " 가지고 ";
			}
			else if(rNo.isChecked())
			{
				familyindex=1;
				family = " 가지고 있지 않고 ";
			}
			
			if(rmale.isChecked()){
				sex="남자";
				
			}
			else if(rfemale.isChecked()){
				sex="여자";
			}
			
			
			resultindex = overWeightPercentindex + bmiindex +abdomenindex+familyindex;
			String strresultindex;
			if(resultindex>10)
			{
				strresultindex="위험";
			}
			else if(resultindex>8)
			{
				strresultindex="높음";
			}
			else if(resultindex>5)
			{
				strresultindex="보통";
			}
			else
			{
				strresultindex="낮음";
			}
			
			
//			결과 엑티비티 값 전송
			String strIdealWeight = longDouble2String(1, idealWeight);
			String strOverWeightPercent = longDouble2String(1, overWeightPercent);
			String strabdomen = longDouble2String(2, abdomen);
			String strBmi = longDouble2String(1, bmi);
			String strheight = longDouble2String(1, height);
			String strweight = longDouble2String(1, weight);
			String strwaist = longDouble2String(1, waist);
			String strass = longDouble2String(1, ass);
			String strage = longDouble2String(1,agenumber);
			
			Intent result = new Intent(this, tActivity1_4_1.class);


			result.putExtra("overfamily", family);
			result.putExtra("oversex",sex);
			result.putExtra("overage", strage);
			
			
			result.putExtra("overheight", strheight);
			result.putExtra("overweight", strweight);
			result.putExtra("overwaist", strwaist);
			result.putExtra("overass", strass);
			result.putExtra("overWeightPercent", strOverWeightPercent);
			result.putExtra("bmi",strBmi);
			result.putExtra("abdomen", strabdomen);
			
			result.putExtra("result", strresultindex);
			
			startActivity(result);
			finish();
		}
		
		public String longDouble2String(int size, double value) 
		{

	        NumberFormat nf = NumberFormat.getNumberInstance();

	        nf.setMaximumFractionDigits(size);

	        nf.setGroupingUsed(false);

	        return nf.format(value);
	    }
		
		
		public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	        finish();
	        
	    }
	    return true;
	}	
}
